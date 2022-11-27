package com.offbreach;

import com.github.britooo.looca.api.group.processos.Processo;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author rafae
 */
public class DetectorUso {

    HardwareData hwData = new HardwareData();
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void executar() {
        Integer currentValue = dbConnection.getServerDangerStatus();
        System.out.println(currentValue);
        Integer newValue = calculateUse(currentValue);
        System.out.println(newValue);
        if (isServerBeingHacked(currentValue)) {
            terminarProcessos();
            System.out.println("Terminou um processo");
        }
        dbConnection.saveServerDangerStatus(newValue);
    }

    private Boolean isServerBeingHacked(Integer index) {
        return index >= 200;
    }

    private Integer calculateUse(Integer currentIndex) {
        Double usoRamPercentage = ((double) hwData.getMemoryData().getEmUso() / hwData.getTotalMemoria()) * 100;
        Double usoCpuPercentage = Double.min(hwData.getProcessador().getUso() * 1.5, 100);
        Double usoDiscoPercentage = hwData.getTempoAtividadeDisco();

        currentIndex += calculateRisk(usoRamPercentage);
        currentIndex += calculateRisk(usoCpuPercentage);
        currentIndex += calculateRisk(usoDiscoPercentage);

        return Math.min(200, Math.max(currentIndex, 0));
    }

    private Integer calculateRisk(Double use) {
        if (use > 95) {
            return 15;
        }
        if (use > 90) {
            return 10;
        }
        if (use > 80) {
            return 5;
        }
        if (use > 70) {
            return 1;
        }
        if (use > 60) {
            return -5;
        }
        return -15;

    }

    public void terminarProcessos() {
        try {
            Processo processoMaiorusoCPU = hwData.getProcessoMaiorUsoCpu(0);
            if (processoMaiorusoCPU.getNome().contains("java")
                    || processoMaiorusoCPU.getNome().contains("jvm")
                    || processoMaiorusoCPU.getNome().contains("jre")) {
                processoMaiorusoCPU = hwData.getProcessoMaiorUsoRam(1);
            }
            List<Processo> processosComMesmoNome = hwData.getProcessGroupByName(processoMaiorusoCPU.getNome());
            String sistemaOperacional = hwData.getSistema().getSistemaOperacional();
            for (Processo processo : processosComMesmoNome) {
                terminarCadaProcessoIndidualmente(processo, sistemaOperacional);
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
            System.out.println(e);
        }
    }

}
