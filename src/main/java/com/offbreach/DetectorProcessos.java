/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.offbreach;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author rafae
 */
public class DetectorProcessos {
    Looca looca = new Looca();
    
    public List<Processo> getTodosProcessos() {
        return looca.getGrupoDeProcessos().getProcessos();
    }
    
    public Processo getProcessoMaiorUsoRam(Integer index) {
        List<Processo> todosProcessos = getTodosProcessos();
        todosProcessos.sort(Comparator.comparing(Processo::getUsoMemoria));
        return todosProcessos.get(index);
    }
    
    public Processo getProcessoMaiorUsoCpu(Integer index) {
        List<Processo> todosProcessos = getTodosProcessos();
        todosProcessos.sort(Comparator.comparing(Processo::getUsoCpu));
        return todosProcessos.get(index);
    }
}
