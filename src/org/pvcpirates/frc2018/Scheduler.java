package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.State;
import org.pvcpirates.frc2018.state.TeleopState;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
    public static final State auto = new AutoState();
    public static final State teleOp = new TeleopState();
    @Override
    public void robotInit() {
        auto.init();
        teleOp.init();
    }

    @Override
    public void autonomousPeriodic() {
        robot.state.exec();
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