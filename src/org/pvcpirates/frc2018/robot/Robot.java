package org.pvcpirates.frc2018.robot;

import org.pvcpirates.frc2018.State;

public class Robot {

    public final Hardware hardware = Hardware.getInstance();
    public State state;

    private static Robot ourInstance = new Robot();
    public static Robot getInstance() {
        return ourInstance;
    }

    private Robot() {
    }
}
