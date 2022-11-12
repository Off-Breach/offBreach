package com.offbreach;

/**
 *
 * @author rafae
 */
public class DetectorUso {

    HardwareData hwData = new HardwareData();
    DatabaseConnection dbConnection = new DatabaseConnection();

    public Boolean isServerBeingHacked(Integer index) {
        return index > 200;
    }
    
    public Boolean shouldSendTelegramWarning(Integer index) {
        return index > 90;
    }

    public Integer calculateUse() {
        Integer currentIndex = dbConnection.getServerDangerStatus();
        Double usoRamPercentage = ((double) hwData.getMemoryData().getEmUso() / hwData.getTotalMemoria()) * 100;
        Double usoCpuPercentage = Double.min(hwData.getProcessador().getUso() * 1.5, 100);
        Double usoDiscoPercentage = hwData.getTempoAtividadeDisco();

        currentIndex += calculateRisk(usoRamPercentage);
        currentIndex += calculateRisk(usoCpuPercentage);
        currentIndex += calculateRisk(usoDiscoPercentage);

        return Math.max(currentIndex, 0);
    }

    private Integer calculateRisk(Double use) {
        if (use > 95) {
            return 15;
        } else if (use > 90) {
            return 10;
        } else if (use > 80) {
            return 5;
        } else if (use > 70) {
            return 1;
        } else if (use > 60) {
            return -5;
        } else {
            return -10;
        }
    }
    
    public void teste() {
        Integer novoStatusPerigo = calculateUse();
        if (isServerBeingHacked(novoStatusPerigo)) {
          
            
        }
    }
    
    public void terminarProcesso() {
        
    }

}
