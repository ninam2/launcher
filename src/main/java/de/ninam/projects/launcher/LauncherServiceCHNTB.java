package de.ninam.projects.launcher;

import ch.ntb.usb.Device;
import ch.ntb.usb.USB;
import ch.ntb.usb.USBException;

public class LauncherServiceCHNTB implements LauncherService {

    private final byte[][] COMMANDS = {{0x02, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x03, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x02, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x02, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x02, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, {0x02, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}};
    private Device dev = null;

    LauncherServiceCHNTB() {
        usbSetup();
    }

    @Override
    public void up() {
        execute(Command.UP, 200);
    }

    @Override
    public void down() {
        execute(Command.DOWN, 200);
    }

    @Override
    public void left() {
        execute(Command.LEFT, 200);
    }

    @Override
    public void right() {
        execute(Command.RIGHT, 200);
    }

    @Override
    public void zero() {
        execute(LauncherServiceCHNTB.Command.DOWN, 3000);
        execute(LauncherServiceCHNTB.Command.LEFT, 6000);
        execute(LauncherServiceCHNTB.Command.RIGHT, 3000);
        execute(LauncherServiceCHNTB.Command.UP, 200);
    }

    @Override
    public void launch() {
        execute(Command.FIRE, 10000);
    }

    /**
     * Open the USB Thunder Launcher
     */
    private void usbSetup() {
        dev = USB.getDevice((short) 0x2123, (short) 0x1010); // Vendor ID, Product ID
        try {
            dev.open(1, 0, -1); // Open the device (Configuration(default), Interface(Control), AlternativeConfig(None))
        } catch (USBException ex) {
            System.out.println("Please check the driver for device VID:0x2123, PID:0x1010");
            ex.printStackTrace();
        }
    }

    /**
     * @param c        Use the public "Command" enum to control the turret.
     * @param Duration The number of milliseconds to execute the command.
     *                 The FIRE command takes about 3300 milliseconds.
     */
    public void execute(Command c, long Duration) {
        try {
            if (dev.isOpen()) {
                switch (c) {
                    case STOP: // Send a control message
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[0], COMMANDS[0].length, 2000, false);
                        break;
                    case LEDON:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[1], COMMANDS[1].length, 2000, false);
                        break;
                    case LEDOFF:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[2], COMMANDS[2].length, 2000, false);
                        break;
                    case UP:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[3], COMMANDS[3].length, 2000, false);
                        break;
                    case DOWN:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[4], COMMANDS[4].length, 2000, false);
                        break;
                    case LEFT:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[5], COMMANDS[5].length, 2000, false);
                        break;
                    case RIGHT:
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[6], COMMANDS[6].length, 2000, false);
                        break;
                    case FIRE://The fire command needs about 3300 milis to execute
                        dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[7], COMMANDS[7].length, 2000, false);
                        break;
                }
                long wait = System.currentTimeMillis() + Duration;
                while (wait > System.currentTimeMillis()) {
                    //Burn Time and let the Launcher execute.
                }
                dev.controlMsg(0x21, 0x09, 0, 0, COMMANDS[0], COMMANDS[0].length, 2000, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Command {STOP, LEDON, LEDOFF, UP, DOWN, LEFT, RIGHT, FIRE}
}
