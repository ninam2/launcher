package de.ninam.projects.launcher.launcherService;

public abstract class LauncherService {

    byte[] CMD_STOP = new byte[]{0x02, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_LED_ON = new byte[]{0x03, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_LED_OFF = new byte[]{0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_UP = new byte[]{0x02, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_DOWN = new byte[]{0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_LEFT = new byte[]{0x02, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_RIGHT = new byte[]{0x02, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    byte[] CMD_FIRE = new byte[]{0x02, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public void stop() {
        execute(CMD_STOP, 200);
    }

    public void ledOn() {
        execute(CMD_LED_ON, 200);
    }

    public void ledOff() {
        execute(CMD_LED_OFF, 200);
    }

    public void up() {
        execute(CMD_UP, 200);
    }

    public void down() {
        execute(CMD_DOWN, 200);
    }

    public void left() {
        execute(CMD_LEFT, 200);
    }

    public void right() {
        execute(CMD_RIGHT, 200);
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

    public void launch() {
        execute(CMD_FIRE, 3300);
    }

    /**
     * executes a command
     *
     * @param cmd  command
     * @param time that the command shall be executed
     */
    public abstract void execute(byte[] cmd, int time);
}
