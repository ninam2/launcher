package de.ninam.projects.launcher.console;

import de.ninam.projects.launcher.LauncherService;
import de.ninam.projects.launcher.targets.Targets;

/**
 * Glue-code between {@link Launcher} and {@link de.ninam.projects.launcher.LauncherService}.
 * Translates user input into commands.
 */
public class LauncherControl {

    private LauncherService launcherService;

    public LauncherControl() {
    }

    public LauncherControl(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    /**
     * Initializes the rocket launcher.
     * Will shut-on the led.
     */
    public void init() {

        // enable led
        launcherService.ledOn();
    }

    /**
     * Shuts the rocket launcher down.
     * Will shut-off the led.
     */
    public void shutDown() {

        // disable led
        launcherService.ledOff();
    }

    /**
     * Executes a command with the help of {@link de.ninam.projects.launcher.LauncherService}
     * <p/>
     * <ul>
     * <li>0 - moves to a zero-position</li>
     * <li>1 - moves to </li>
     * </ul>
     *
     * @param command will be executed
     */
    public void executeCommand(char command) {

        switch (command) {
            case '0':
                launcherService.zero();
                break;
            case 'w':
                launcherService.up();
                break;
            case 's':
                launcherService.down();
                break;
            case 'a':
                launcherService.left();
                break;
            case 'd':
                launcherService.right();
                break;
            case ' ':
                launcherService.launch();
                break;
            case '1':
                launcherService.autoShoot(Targets.one);
                break;
            case '2':
                launcherService.autoShoot(Targets.two);
                break;
            case '3':
                launcherService.autoShoot(Targets.three);
                break;
            case '4':
                launcherService.autoShoot(Targets.four);
                break;
            case 't':
                launcherService.autoShoot(Targets.one);
                launcherService.autoShoot(Targets.two);
                launcherService.autoShoot(Targets.three);
                launcherService.autoShoot(Targets.four);
                break;
        }
    }
}
