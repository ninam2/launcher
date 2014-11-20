package de.ninam.projects.launcher;
import java.io.IOException;

public class LauncherControl {

    public static void main(String[] args) throws IOException {

        final LauncherService launcherService = new LauncherService();

        System.out.println("Welcome to launcher control!");
        System.out.println("Press...");
        System.out.println("   1 Button to move Launcher to the  top right corner");
        System.out.println("   2 Button to move Launcher to the  bottom right corner");
        System.out.println("   3 Button to move Launcher to the  bottom left corner");
        System.out.println("   4 Button to move Launcher to the  top left corner");
        System.out.println("   1 Button to move Launcher to the  bottom right corner");
        System.out.println("   t Button to move Launcher to all corners");
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

                case '0':
                    launcherService.zero();
                    break;

                case '1':
                    launcherService.rightup();
                    launcherService.launch();
                    break;

                case '2':
                    launcherService.rightdown();
                    launcherService.launch();
                    break;


                case '3':
                    launcherService.leftdown();
                    launcherService.launch();
                    break;

                case '4':
                    launcherService.leftup();
                    launcherService.launch();
                    break;

                case 't':
                    launcherService.algorithmus();




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
