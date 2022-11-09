package com.offbreach;

import com.github.britooo.looca.api.util.Conversor;

/**
 *
 * @author rafae
 */
public class DetectorUso {

    HardwareData hwData = new HardwareData();

    public Boolean isServerBeingHacked(Double index) {
        return index > 90;
    }

    public Double calculateUse(Double currentIndex) {
        Double usoRamPercentage = ((double) hwData.getMemoryData().getEmUso() / hwData.getTotalMemoria()) * 100;
        Double cpuUsoPorcentagemNaoAdulterado = hwData.getProcessador().getUso();
        Double usoCpuPercentage = Double.min(cpuUsoPorcentagemNaoAdulterado * 1.5, 100);
        System.out.println(usoRamPercentage);
        System.out.println("Uso CPU:");
        System.out.println(usoCpuPercentage);
        System.out.println(cpuUsoPorcentagemNaoAdulterado);
        if (usoRamPercentage > 95) {
            currentIndex += 15;
        } else if (usoRamPercentage > 90) {
            currentIndex += 10;
        } else if (usoRamPercentage > 80) {
            currentIndex += 5;
        } else if (usoRamPercentage > 70) {
            currentIndex += 1;
        } else if (usoRamPercentage > 60) {
            currentIndex -= 10;
        } else {
            currentIndex -= 50;
        }

        if (usoCpuPercentage > 95) {
            currentIndex += 15;
        } else if (usoCpuPercentage > 90) {
            currentIndex += 10;
        } else if (usoCpuPercentage > 80) {
            currentIndex += 5;
        } else if (usoCpuPercentage > 70) {
            currentIndex += 1;
        } else if (usoCpuPercentage > 60) {
            currentIndex -= 10;
        } else {
            currentIndex -= 50;
        }

        return Math.max(currentIndex, 0);
    }

}
