package de.ninam.projects.launcher;

import javax.usb.*;
import java.io.UnsupportedEncodingException;

/**
 * Service that interacts with the rocket launcher.
 */
public class LauncherService {

    private final static String LAUNCHER_PRODUCT_STRING = "USB Missile Launcher";
    /**
     * First init packet to send to the missile launcher.
     */
    private static final byte[] INIT_A = new byte[]{85, 83, 66, 67, 0, 0, 4,
            0};
    /**
     * Second init packet to send to the missile launcher.
     */
    private static final byte[] INIT_B = new byte[]{85, 83, 66, 67, 0, 64, 2,
            0};
    /**
     * Command to rotate the launcher up.
     */
    private static final int CMD_UP = 0x01;
    /**
     * Command to rotate the launcher down.
     */
    private static final int CMD_DOWN = 0x02;
    /**
     * Command to rotate the launcher to the left.
     */
    private static final int CMD_LEFT = 0x04;
    /**
     * Command to rotate the launcher to the right.
     */
    private static final int CMD_RIGHT = 0x08;
    /**
     * Command to fire a missile.
     */
    private static final int CMD_FIRE = 0x10;
    private UsbDevice rocketLauncher;

    public LauncherService() throws UsbException, UnsupportedEncodingException {

        rocketLauncher = findLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
        if (rocketLauncher == null) {
            throw new RuntimeException("launcher not found");
        }
        // Claim the interface
        UsbConfiguration configuration = (UsbConfiguration) rocketLauncher.getUsbConfigurations().get(0);
        UsbInterface iface = (UsbInterface) configuration.getUsbInterfaces().get(0);
        iface.claim(new UsbInterfacePolicy() {
            @Override
            public boolean forceClaim(UsbInterface usbInterface) {
                return true;
            }
        });
    }

    /**
     * Sends a message to the missile launcher.
     *
     * @param device  The USB device handle.
     * @param message The message to send.
     * @throws UsbException When sending the message failed.
     */
    public static void sendMessage(UsbDevice device, byte[] message)
            throws UsbException {
        UsbControlIrp irp = device.createUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                        UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE), (byte) 0x09,
                (short) 2, (short) 1);
        irp.setData(message);
        device.syncSubmit(irp);
    }

    /**
     * Sends a command to the missile launcher.
     *
     * @param device  The USB device handle.
     * @param command The command to send.
     * @throws UsbException When USB communication failed.
     */
    public static void sendCommand(UsbDevice device, int command)
            throws UsbException {
        byte[] message = new byte[64];
        message[1] = (byte) ((command & CMD_LEFT) > 0 ? 1 : 0);
        message[2] = (byte) ((command & CMD_RIGHT) > 0 ? 1 : 0);
        message[3] = (byte) ((command & CMD_UP) > 0 ? 1 : 0);
        message[4] = (byte) ((command & CMD_DOWN) > 0 ? 1 : 0);
        message[5] = (byte) ((command & CMD_FIRE) > 0 ? 1 : 0);
        message[6] = 8;
        message[7] = 8;
        sendMessage(device, INIT_A);
        sendMessage(device, INIT_B);
        sendMessage(device, message);
    }

    public void up() throws UsbException {

        sendCommand(rocketLauncher, CMD_UP);
    }

    public void down() {
        System.out.println("moving down");
    }

    public void left() {
        System.out.println("moving left");
    }

    public void right() {
        System.out.println("moving right");
    }

    public void launch() {
        System.out.println("launching rocket!");
    }


    /**
     * Finds a Rocket Launcher (if connected)
     *
     * @param hub root hub to search rocket launcher on
     * @return rocket launcher device or null if not found
     * @throws UnsupportedEncodingException
     * @throws UsbException
     */
    private UsbDevice findLauncher(UsbHub hub) throws UnsupportedEncodingException, UsbException {

        // iterate over devices connected to hub
        for (Object device : hub.getAttachedUsbDevices()) {

            // we can not handle devices that are no usb devices (should not happen)
            if (!(device instanceof UsbDevice)) {
                System.out.println("cannot handle: " + device.getClass());
                continue;
            }

            // cast to usb device
            UsbDevice usbDevice = (UsbDevice) device;

            // if device is a hub, search launcher recursively
            if (usbDevice.isUsbHub()) {
                UsbDevice launcher = findLauncher((UsbHub) usbDevice);
                if (launcher != null) {
                    return launcher;
                }
            } else {
                if (LAUNCHER_PRODUCT_STRING.equals(usbDevice.getProductString())) {
                    return usbDevice;
                }
            }
        }

        return null;
    }
}
