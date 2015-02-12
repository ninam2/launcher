package de.ninam.projects.launcher.console;

import de.ninam.projects.launcher.LauncherService;

import java.io.IOException;

/**
 * Program entry point. Instruments {@link LauncherControl} for console-usage.
 */
public class Launcher {

    public static void main(String[] args) throws IOException {

        // init launcher-control first (may produce exception)
        final LauncherControl launcherControl = new LauncherControl();
        launcherControl.launcherService = new LauncherService();

        // print usage
        printUsage();

        // init launcher
        launcherControl.init();

        // read commands and execute until 'x' is pressed
        char c;
        while ((c = (char) System.in.read()) != 'x') {
            launcherControl.executeCommand(c);
        }

        // say goodbye
        System.out.println("exiting...");

        // shut-down launcher
        launcherControl.shutDown();
    }

    private static void printUsage() {

        System.out.println("Welcome to launcher control!");
        System.out.println("Press...");
        System.out.println("   1 Button to hit target one");
        System.out.println("   2 Button to hit target two");
        System.out.println("   3 Button to hit target three");
        System.out.println("   4 Button to hit target four");
        System.out.println("   t Button to hit all targets");
        System.out.println("   w Button to move Launcher up");
        System.out.println("   s Button to move Launcher down");
        System.out.println("   a Button to move Launcher left");
        System.out.println("   d Button to move Launcher right");
        System.out.println("   0 Button to move to zero-position");
        System.out.println("   space Button to launch");
        System.out.println("   x Button to exit program");
    }
}
