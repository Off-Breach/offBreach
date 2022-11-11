package com.offbreach;

/**
 *
 * @author rafae
 */
public class DetectorUso {

    HardwareData hwData = new HardwareData();

    public Boolean isServerBeingHacked(Double index) {
        return index > 200;
    }
    
    public Boolean shouldSendTelegramWarning(Double index) {
        return index > 90;
    }

    public Double calculateUse(Double currentIndex) {
        Double usoRamPercentage = ((double) hwData.getMemoryData().getEmUso() / hwData.getTotalMemoria()) * 100;
        Double usoCpuPercentage = Double.min(hwData.getProcessador().getUso() * 1.5, 100);
        Double usoDiscoPercentage = hwData.getTempoAtividadeDisco();

        currentIndex += calculateRisk(usoRamPercentage);
        currentIndex += calculateRisk(usoCpuPercentage);
        currentIndex += calculateRisk(usoDiscoPercentage);

        return Math.max(currentIndex, 0);
    }

    private Double calculateRisk(Double use) {
        if (use > 95) {
            return 15.0;
        } else if (use > 90) {
            return 10.0;
        } else if (use > 80) {
            return 5.0;
        } else if (use > 70) {
            return 1.0;
        } else if (use > 60) {
            return -5.0;
        } else {
            return -10.0;
        }
    }

}
