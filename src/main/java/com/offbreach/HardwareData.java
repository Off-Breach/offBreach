package com.offbreach;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.util.Util;

public class HardwareData {

    private final Looca looca = new Looca();
    private String hostname = "";
    private final DiscosGroup discos = looca.getGrupoDeDiscos();
    private final Memoria memoria = looca.getMemoria();
    private final GrupoDispositivoUsb dispositivosUsb = new GrupoDispositivoUsb();

    public Memoria getMemoryData() {
        return looca.getMemoria();
    }

    public String getMemoriaEmUso() {
        return Conversor.formatarBytes(getMemoryData().getEmUso());
    }

    public Long getTotalMemoria() {
        return memoria.getTotal();
    }

    public List<Disco> getDiscoData() {
        return discos.getDiscos();
    }

    public Long getTotalDisco() {
        return discos.getTamanhoTotal();
    }

    public Double getTempoAtividadeDisco() {
        HWDiskStore disk = new SystemInfo().getHardware().getDiskStores().get(0);
        Long firstGetTransferTime = disk.getTransferTime();
        Long firstGetTimeStamp = disk.getTimeStamp();
        Util.sleep(5000);
        disk.updateAttributes();
        Long secondGetTransferTime = disk.getTransferTime();
        Long secondGetTimeStamp = disk.getTimeStamp();
        return (double) (secondGetTransferTime - firstGetTransferTime) / (secondGetTimeStamp - firstGetTimeStamp) * 100;
    }

    public Long getDisponivelDisco() {
        return discos.getVolumes().get(0).getDisponivel();
    }
    
    public Double getUsoDisco() {
        return (double) getTotalDisco() - getDisponivelDisco();
    }

    public String getDiscoNome(Integer index) {
        Disco disco = getDiscoData().get(index);
        return disco.getNome();
    }

    public Integer getQuantidadeDeDiscos() {
        return getDiscoData().size();
    }

    public Double getTemperatura() {
        return looca.getTemperatura().getTemperatura();
    }

    public Processador getProcessador() {
        return looca.getProcessador();
    }

    public Sistema getSistema() {
        return looca.getSistema();
    }
    
    public List<USBStorageDevice> getDispositivosUsb() {
        return dispositivosUsb.getDispositivosUsbConectados();
    }
    
    
    public List<Processo> getTodosProcessos() {
        return looca.getGrupoDeProcessos().getProcessos();
    }
    
    public Processo getProcessoMaiorUsoRam(Integer index) {
        List<Processo> todosProcessos = getTodosProcessos();
        todosProcessos.sort(Comparator.comparing(Processo::getUsoMemoria).reversed());
        return todosProcessos.get(index);
    }
    
    public Processo getProcessoMaiorUsoCpu(Integer index) {
        List<Processo> todosProcessos = getTodosProcessos();
        todosProcessos.sort(Comparator.comparing(Processo::getUsoCpu).reversed());
        return todosProcessos.get(index);
    }
    
    public Integer getPIDProcessoMaiorUsoRam(Integer index) {
        return getProcessoMaiorUsoRam(index).getPid();
    }
    
    public Integer getPIDProcessoMaiorUsoCpu(Integer index) {
        return getProcessoMaiorUsoCpu(index).getPid();
    }
    
    public List<Processo> getProcessGroupByName(String nome) {
        List<Processo> todosProcessos = getTodosProcessos();
        List<Processo> processosComNomeIgual = new ArrayList<>();
        for (Processo processo : todosProcessos) {
            if (processo.getNome().toLowerCase().contains(nome)) {
                processosComNomeIgual.add(processo);
            }
        }
        return processosComNomeIgual;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.hostname = address.getHostName();
        } catch (UnknownHostException e) {
            System.out.println("[Erro] - Não foi possível obter o hostname");
            this.hostname = "hostname";
        }
    }
}
