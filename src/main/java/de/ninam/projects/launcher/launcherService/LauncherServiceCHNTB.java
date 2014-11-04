package de.ninam.projects.launcher.launcherService;

import ch.ntb.usb.Device;
import ch.ntb.usb.USB;
import ch.ntb.usb.USBException;

public class LauncherServiceCHNTB extends LauncherService {

    private Device dev = null;

    public LauncherServiceCHNTB() {
        dev = USB.getDevice((short) 0x2123, (short) 0x1010); // Vendor ID, Product ID
        try {
            dev.open(1, 0, -1); // Open the device (Configuration(default), Interface(Control), AlternativeConfig(None))
        } catch (USBException ex) {
            System.out.println("Please check the driver for device VID:0x2123, PID:0x1010");
            ex.printStackTrace();
        }
    }

    @Override
    public void execute(byte[] cmd, int time) {
        try {
            if (dev.isOpen()) {
                dev.controlMsg(0x21, 0x09, 0, 0, cmd, cmd.length, 2000, false);
                long waitUntil = System.currentTimeMillis() + time;
                while (waitUntil > System.currentTimeMillis()) {
                    //Burn Time and let the Launcher execute.
                }
                dev.controlMsg(0x21, 0x09, 0, 0, CMD_STOP, CMD_STOP.length, 2000, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
