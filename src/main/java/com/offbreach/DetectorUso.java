package com.offbreach;

import com.github.britooo.looca.api.group.processos.Processo;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author rafae
 */
@Slf4j
public class DetectorUso {

    HardwareData hwData = new HardwareData();
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void executar(Double usoCpu, Double usoRam, Double usoDisco) {
        Integer currentValue = dbConnection.getServerDangerStatus();
        System.out.println(currentValue);
        if (currentValue == 300) {
            dbConnection.resetServerDangerStatus();
            dbConnection.updateServerOnStatus(false);
            encerrarServidor();
        } else {
            Integer newValue = calculateUse(currentValue, usoCpu, usoRam);
            System.out.println(newValue);
            if (isServerBeingHacked(newValue)) {
                log.info("Nós detectamos um ataque ao seu servidor. Iniciando encerramento de processos!");
                terminarProcessos();
            } else {
                dbConnection.saveServerDangerStatus(newValue);
            }
        }
    }

    private Boolean isServerBeingHacked(Integer index) {
        return index >= 200;
    }

    private Integer calculateUse(Integer currentIndex, Double usoCpu, Double usoRam) {
        Double usoRamPercentage = (usoRam / hwData.getTotalMemoria()) * 100;
        Double usoCpuPercentage = Double.min(usoCpu, 100);

        currentIndex += calculateRisk(usoCpuPercentage);

        return Math.min(215, Math.max(currentIndex, 0));
    }

    private Integer calculateRisk(Double use) {
        if (use > 95) {
            return 25;
        }
        if (use > 90) {
            return 15;
        }
        if (use > 80) {
            return 10;
        }
        if (use > 70) {
            return 5;
        }
        if (use > 50) {
            return -10;
        }
        return -20;

    }

    public void terminarProcessos() {
        try {
            List<Processo> processosMaiorusoCPU = hwData.getProcessosMaiorUsoCpu();
            System.out.println(processosMaiorusoCPU);
            String sistemaOperacional = hwData.getSistema().getSistemaOperacional();
            for (Processo processo : processosMaiorusoCPU) {
                if (!processo.getNome().contains("jvm")
                        || !processo.getNome().contains("java")
                        || !processo.getNome().contains("jre")) {
                    terminarCadaProcessoIndidualmente(processo, sistemaOperacional);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void terminarCadaProcessoIndidualmente(Processo processo, String sistemaOperacional) throws IOException {
        try {
            String killCommand;
            Runtime r = Runtime.getRuntime();
            if (sistemaOperacional.toLowerCase().contains("windows")) {
                killCommand = "taskkill /F /PID " + processo.getPid();
            } else {
                killCommand = "kill -9 " + processo.getPid();
            }
            System.out.println("Terminando o processo " + processo.getNome());
            r.exec(killCommand);
        } catch (IOException e) {
            log.error(String.format("Erro ao terminar o processo: %s", processo.toString()));
            System.out.println(e);
        }
    }

    private void encerrarServidor() {
        try {
            Runtime r = Runtime.getRuntime();
            String sistemaOperacional = hwData.getSistema().getSistemaOperacional();
            String shutDownCommand;
            if (sistemaOperacional.toLowerCase().contains("windows")) {
                shutDownCommand = "shutdown /s";
                log.info("Executando o comando: " + shutDownCommand);
                r.exec(shutDownCommand);
            } else {
                shutDownCommand = "shutdown";
                log.info("Executando o comando: " + shutDownCommand);
                r.exec(shutDownCommand);
            }
        } catch (IOException iOException) {
            log.error("Erro ao desligar a máquina. Erro: " + iOException.toString());
        }
    }

}
