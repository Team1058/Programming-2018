package org.pvcpirates.frc2018.robot;

import org.pvcpirates.frc2018.robot.controllers.Drivetrain;
import org.pvcpirates.frc2018.state.State;

public class Robot {

    public final Hardware hardware = Hardware.getInstance();
    public final Drivetrain drivetrain = new Drivetrain();
    public State state;

    private static Robot ourInstance;
    public static Robot getInstance() {
    	if(ourInstance == null){
    		ourInstance = new Robot();
    	}
    	return ourInstance;
    }

    private Robot() {
    }

    public void setState(State state) {
        this.state = state;
    }
}
