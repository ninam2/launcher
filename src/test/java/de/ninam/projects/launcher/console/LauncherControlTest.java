package de.ninam.projects.launcher.console;

import de.ninam.projects.launcher.LauncherService;
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

        // TODO
    }

    @Test
    public void testExecuteCommandZero() {

        // TODO
    }

    @Test
    public void testExecuteCommandUp() {

        // TODO
    }

    // TODO
}