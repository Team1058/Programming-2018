package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.State;
import org.pvcpirates.frc2018.state.TeleopState;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
    public static final State auto = new AutoState();
    public static final State teleOp = new TeleopState();
    SendableChooser<Command> sendableChooser = new SendableChooser<>();
    @Override
    public void robotInit() {
        auto.init();
        teleOp.init();

    }

    @Override
    public void autonomousInit() {
        robot.setState(auto);
    }

    @Override
    public void autonomousPeriodic() {
        robot.state.exec();
    }

    @Override
    public void teleopInit() {
        robot.setState(teleOp);
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
        SmartDashboard.putNumber("Ultra",robot.hardware.leftUltrasonic.getRangeInches());
    }

}