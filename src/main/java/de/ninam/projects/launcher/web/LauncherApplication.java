package de.ninam.projects.launcher.web;

import de.ninam.projects.launcher.console.Launcher;
import de.ninam.projects.launcher.console.LauncherControl;
import de.ninam.projects.launcher.targets.Target;
import de.ninam.projects.launcher.targets.Targets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "de.ninam.projects")
@EnableAutoConfiguration
public class LauncherApplication {

    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }

    @Bean
    //tells Spring that the method will return an object that should be registered in the spring application context
    public Launcher launcher() {
        return new Launcher();
    }

    @Bean
    public LauncherControl launchercontrol() {
        return new LauncherControl();
    }

    @Bean
    public Target target() {
        return new Target(0, 0);
    }

    @Bean
    public Targets targets() {
        return new Targets();
    }


}
