package com.offbreach;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

/**
 *
 * @author rafae
 */
@Slf4j
public class GrupoDispositivoUsb {
    private final USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();

    public Integer getTotalDispositvosUsbConectados() {
        return driveDetector.getRemovableDevices().size();
    }

    public List<USBStorageDevice> getDispositivosUsbConectados() {
        return driveDetector.getRemovableDevices();
    }
    
    public USBStorageDevice getDispositivoUsbPorNome(String nome) {
        List<USBStorageDevice> devices = getDispositivosUsbConectados();
        for (USBStorageDevice device : devices) {
            if (device.getDeviceName().equalsIgnoreCase(nome)) {
                return device;
            }
        }
        return null;
    }
    
    public void desmontarTodosDispositivosUsb() {
        List<USBStorageDevice> devices = getDispositivosUsbConectados();
        for (USBStorageDevice device : devices) {
            try {
                driveDetector.unmountStorageDevice(device);
                log.info("Dispositivo usb removido com sucesso. Info: " + device.toString());
            } catch (IOException e) {
                log.error("Erro ao desmontar dispositivo. Info: " + device.toString() );
            }
        }
    }
    
    public void desmontarDispositivoUsb(USBStorageDevice dispositivoUsb) throws IOException {
        try {
            driveDetector.unmountStorageDevice(dispositivoUsb);
            log.info("Dispositivo USB removido com sucesso.");
        } catch (IOException e) {
            log.error("Erro ao desmontar dispositivo USB. Erro: " + e.getMessage());
        }
    }
}
