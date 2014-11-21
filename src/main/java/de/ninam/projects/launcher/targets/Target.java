package de.ninam.projects.launcher.targets;

public class Target {

    /**
     * steps that launcher has to move right to hit the target
     */
    private int stepsRight = 0;

    /**
     * steps that the launcher has to move up to hit the target
     */
    private int stepsUp = 0;

    /**
     * @param stepsRight steps that launcher has to move right to hit the target
     * @param stepsUp    steps that the launcher has to move up to hit the target
     */
    public Target(int stepsRight, int stepsUp) {
        this.stepsRight = stepsRight;
        this.stepsUp = stepsUp;
    }

    public int getStepsRight() {
        return stepsRight;
    }

    public int getStepsUp() {
        return stepsUp;
    }
}
