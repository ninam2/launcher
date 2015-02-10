package de.ninam.projects.launcher.web;


public class CommandResponse {

    private Character command;

    public CommandResponse(Character command) {
        this.command = command;
    }

    public Character getCommand() {
        return command;
    }

    public void setCommand(Character command) {
        this.command = command;
    }

}
