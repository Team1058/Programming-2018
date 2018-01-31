package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.TeleopState;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();

    @Override
    public void robotInit() {
    }

    @Override
    public void autonomousInit() {
        robot.setState(new AutoState());
        robot.state.init();
    }

    @Override
    public void autonomousPeriodic() {
        robot.state.exec();
    }

    @Override
    public void teleopInit() {
        robot.setState(new TeleopState());
        robot.state.init();
    }

    @Override
    public void teleopPeriodic() {
        robot.state.exec();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {

    }

}