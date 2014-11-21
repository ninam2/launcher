package de.ninam.projects.launcher.console;

import de.ninam.projects.launcher.LauncherService;
import de.ninam.projects.launcher.targets.Targets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link de.ninam.projects.launcher.console.LauncherControl}
 */
@RunWith(MockitoJUnitRunner.class)
public class LauncherControlTest {

    @Mock
    private LauncherService launcherService;

    @InjectMocks
    private LauncherControl launcherControl;

    @Test
    public void testInit() {

        // call init
        launcherControl.init();

        // make sure that ledOn was called on service
        verify(launcherService, times(1)).ledOn();
    }

    @Test
    public void testShutDown() {

        // call shutDown
        launcherControl.shutDown();

        // make sure that ledOff was called on service
        verify(launcherService, times(1)).ledOff();
    }

    @Test
    public void testExecuteCommandZero() {

        // call ExecuteCommand('0')
        launcherControl.executeCommand('0');

        // make sure that zero() was called on service
        verify(launcherService, times(1)).zero();
    }


    @Test
    public void testExecuteCommandUp() {

        // call ExecuteCommand('w')
        launcherControl.executeCommand('w');

        // make sure that up() was called on service
        verify(launcherService, times(1)).up();
    }

    @Test
    public void testExecuteCommandDown() {

        // call ExecuteCommand('s')
        launcherControl.executeCommand('s');

        // make sure that down() was called on service
        verify(launcherService, times(1)).down();
    }

    @Test
    public void testExecuteCommandLeft() {

        // call ExecuteCommand('a')
        launcherControl.executeCommand('a');

        // make sure that left() was called on service
        verify(launcherService, times(1)).left();
    }

    @Test
    public void testExecuteCommandRight() {

        // call tExecuteCommand('d')
        launcherControl.executeCommand('d');

        // make sure that right() was called on service
        verify(launcherService, times(1)).right();
    }

    @Test
    public void testExecuteCommandLaunch() {

        // call tExecuteCommand(' ')
        launcherControl.executeCommand(' ');

        // make sure that launch was called on service
        verify(launcherService, times(1)).launch();
    }

    @Test
    public void testExecuteCommandTargetOne() {

        // call tExecuteCommand('1')
        launcherControl.executeCommand('1');

        // make sure that Targets.one was called on service
        verify(launcherService, times(1)).autoShoot(Targets.one);
    }

    @Test
    public void testExecuteCommandTargetTwo() {

        // call ExecuteCommand('2')
        launcherControl.executeCommand('2');

        // make sure that Targets.two was called on service
        verify(launcherService, times(1)).autoShoot(Targets.two);
    }

    @Test
    public void testExecuteCommandTargetThree() {

        // call  ExecuteCommand('3')
        launcherControl.executeCommand('3');

        // make sure that Targets.three was called on service
        verify(launcherService, times(1)).autoShoot(Targets.three);
    }

    @Test
    public void testExecuteCommandTargetFour() {

        // call ExecuteCommand('4')
        launcherControl.executeCommand('4');

        // make sure that Targets.four was called on service
        verify(launcherService, times(1)).autoShoot(Targets.four);
    }

    @Test
    public void testExecuteCommandTargetsAll() {

        // call txecuteCommand('t')
        launcherControl.executeCommand('t');

        // make sure that all targets were called on service
        verify(launcherService, times(1)).autoShoot(Targets.one);
        verify(launcherService, times(1)).autoShoot(Targets.two);
        verify(launcherService, times(1)).autoShoot(Targets.three);
        verify(launcherService, times(1)).autoShoot(Targets.four);
    }
}
