package de.ninam.projects.launcher;

import ch.ntb.usb.Device;
import ch.ntb.usb.USB;
import ch.ntb.usb.USBException;

public class LauncherService {

    private final byte[] CMD_STOP = new byte[]{0x02, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_LED_ON = new byte[]{0x03, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_LED_OFF = new byte[]{0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_UP = new byte[]{0x02, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_DOWN = new byte[]{0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_LEFT = new byte[]{0x02, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_RIGHT = new byte[]{0x02, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte[] CMD_FIRE = new byte[]{0x02, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private Device rocketLauncher = null;

    public LauncherService() {
        rocketLauncher = USB.getDevice((short) 0x2123, (short) 0x1010); // Vendor ID, Product ID
        try {
            rocketLauncher.open(1, 0, -1); // Open the device (Configuration(default), Interface(Control), AlternativeConfig(None))
        } catch (USBException ex) {
            throw new RuntimeException("launcher not available");
        }
    }

    public void stop() {
        execute(CMD_STOP, 100);
    }

    public void ledOn() {
        execute(CMD_LED_ON, 100);
    }

    public void ledOff() {
        execute(CMD_LED_OFF, 100);
    }

    public void up() {
        execute(CMD_UP, 100);
    }

    public void down() {
        execute(CMD_DOWN, 100);
    }

    public void left() {
        execute(CMD_LEFT, 100);
    }

    public void right() {
        execute(CMD_RIGHT, 100);
    }

    public void launch() {
        execute(CMD_FIRE, 3300);
    }

    /**
     * zero position.
     */
    public void zero() {
        execute(CMD_DOWN, 3000);
        execute(CMD_LEFT, 6000);
        execute(CMD_RIGHT, 3000);
        execute(CMD_UP, 200);
    }

    /**
     * executes a command
     *
     * @param cmd  command
     * @param time that the command shall be executed
     */
    private void execute(byte[] cmd, int time) {
        try {
            if (rocketLauncher.isOpen()) {
                rocketLauncher.controlMsg(0x21, 0x09, 0, 0, cmd, cmd.length, 2000, false);
                long waitUntil = System.currentTimeMillis() + time;
                while (waitUntil > System.currentTimeMillis()) {
                    //Burn Time and let the Launcher execute.
                }
                rocketLauncher.controlMsg(0x21, 0x09, 0, 0, CMD_STOP, CMD_STOP.length, 2000, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
