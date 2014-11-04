package de.ninam.projects.launcher;

import java.io.IOException;

public class LauncherControl {

    public static void main(String[] args) throws IOException {

        final LauncherService launcherService = new LauncherService();

        System.out.println("Welcome to launcher control!");
        System.out.println("Press...");
        System.out.println("   w Button to move Launcher up");
        System.out.println("   s Button to move Launcher down");
        System.out.println("   a Button to move Launcher left");
        System.out.println("   d Button to move Launcher right");
        System.out.println("   space Button to launch");
        System.out.println("   x Button to exit program");

        // go to 0-position
        launcherService.zero();

        // enable led
        launcherService.ledOn();

        char c;
        while ((c = (char) System.in.read()) != 'x') {

            switch (c) {
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
            }
        }

        System.out.println("exiting...");

        // go to 0-position
        launcherService.zero();
        // disable led
        launcherService.ledOff();
    }
}
