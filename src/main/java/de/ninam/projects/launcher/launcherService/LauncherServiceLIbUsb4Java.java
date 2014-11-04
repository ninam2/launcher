package de.ninam.projects.launcher.launcherService;

import javax.usb.UsbConfiguration;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import java.io.UnsupportedEncodingException;

/**
 * Service that interacts with the rocket launcher.
 */
public class LauncherServiceLIbUsb4Java extends LauncherService {

    /**
     * First init packet to send to the missile launcher.
     */
    private static final byte[] INIT_A = new byte[]{85, 83, 66, 67, 0, 0, 4, 0};
    /**
     * Second init packet to send to the missile launcher.
     */
    private static final byte[] INIT_B = new byte[]{85, 83, 66, 67, 0, 64, 2, 0};


    private UsbDevice rocketLauncher;

    public LauncherServiceLIbUsb4Java() throws UsbException, UnsupportedEncodingException {

        rocketLauncher = findLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
        if (rocketLauncher == null) {
            throw new RuntimeException("launcher not found");
        }
        // Claim the interface
        UsbConfiguration configuration = rocketLauncher.getActiveUsbConfiguration();
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

    @Override
    public void execute(byte[] cmd, int time) {

        try {
            sendMessage(rocketLauncher, INIT_A);
            sendMessage(rocketLauncher, INIT_B);
            sendMessage(rocketLauncher, cmd);
            long waitUntil = System.currentTimeMillis() + time;
            while (waitUntil > System.currentTimeMillis()) {
                //Burn Time and let the Launcher execute.
            }
            sendMessage(rocketLauncher, CMD_STOP);
        } catch (UsbException e) {
            throw new RuntimeException(e);
        }
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
                final UsbDeviceDescriptor usbDeviceDescriptor = usbDevice.getUsbDeviceDescriptor();
                if (usbDeviceDescriptor.idVendor() == 0x2123 && usbDeviceDescriptor.idProduct() == 0x1010) {
                    return usbDevice;
                }
                System.out.println("ff");
            }
        }

        return null;
    }
}
