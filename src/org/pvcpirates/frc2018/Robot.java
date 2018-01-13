
package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.pvcpirates.frc2018.auton.AutonControl;
import org.pvcpirates.frc2018.io.ControllerInput;
import org.pvcpirates.frc2018.io.RobotOutput;
import org.pvcpirates.frc2018.io.SensorInput;
import org.pvcpirates.frc2018.teleop.TeleopControl;

public class Robot extends IterativeRobot{

    private RobotOutput robotOut;
    private ControllerInput controllerInput;
    private SensorInput sensorInput;
    private TeleopControl teleopControl;

    @Override
    public void robotInit() {
        this.robotOut = RobotOutput.getInstance();
        this.controllerInput = ControllerInput.getInstance();
        this.sensorInput = SensorInput.getInstance();
        this.teleopControl = TeleopControl.getInstance();
    }
    @Override
    public void autonomousInit() {
        AutonControl.getInstance().getChoice().start();
        this.sensorInput.reset();
    }
    @Override
    public void autonomousPeriodic() {
        this.sensorInput.update();
    }
    @Override
    public void teleopInit() {

    }
    @Override
    public void teleopPeriodic() {
        this.sensorInput.update();
        this.teleopControl.runCycle();
    }
    @Override
    public void disabledInit() {
        this.robotOut.stopAll();
        this.teleopControl.disable();
    }
    @Override
    public void disabledPeriodic() {
        this.sensorInput.update();
    }

}