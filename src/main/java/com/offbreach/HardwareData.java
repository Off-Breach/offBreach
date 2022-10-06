package com.offbreach;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HardwareData {

    private Looca looca = new Looca();
    private Sistema sistema = looca.getSistema();
    private String hostname = "";
    private Memoria memoria = looca.getMemoria();

    public void cadastrarSistema() {

        Temperatura temperatura = looca.getTemperatura();
        Processador processador = looca.getProcessador();

        System.out.println(temperatura);
        System.out.println(processador);
        System.out.println(sistema);
    }

    public Memoria getMemoryData() {
        return looca.getMemoria();
    }

    public String getTemperatura() {
        return looca.getTemperatura().toString();
    }

    public Processador getProcessador() {
        return looca.getProcessador();
    }

    public Sistema getSistema() {
        return looca.getSistema();
    }
    
    public String getHostname(){
        return this.hostname;
    }

    public void setHostname() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.hostname = address.getHostName();
        } catch (UnknownHostException e) {
            System.out.println("[Erro] - Não foi possível obter o hostname");
        }
    }
}
