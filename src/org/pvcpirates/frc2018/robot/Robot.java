package org.pvcpirates.frc2018.robot;

import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;
import org.pvcpirates.frc2018.state.State;

public class Robot {

    private static Robot ourInstance;
    public final Hardware hardware = Hardware.getInstance();
    public State state;

    private Robot() {
    }

    public static Robot getInstance() {
        if (ourInstance == null) {
            ourInstance = new Robot();
        }
        return ourInstance;
    }

    public void setState(State state) {
        this.state = state;
    }
}
