package de.ninam.projects.launcher.web;

import de.ninam.projects.launcher.console.LauncherControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/nina")
public class LauncherWebController {

    @Autowired
    private LauncherControl launcherControl;

    @RequestMapping(value = "/launcher", method = RequestMethod.POST)
    public void executeCommand(@RequestBody Input command) {
        Character ctl = command.getCtl();
        launcherControl.executeCommand(ctl);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public String handleException(HttpMessageNotReadableException e) {
        return e.getMessage();
    }

}

