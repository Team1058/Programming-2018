package org.pvcpirates.frc2018;

import org.pvcpirates.frc2018.autonomous.AutoType;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.TeleopState;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
    public static SendableChooser<Command> autoChooser = new SendableChooser<>();
    private Command temp;

    @Override
    public void robotInit() {
        autoChooser.addDefault("Drive ForwardCenter", new DriveFor(126));
        autoChooser.addObject("Drive ReverseCenter", new DriveFor(-126));
        autoChooser.addObject("Test boy", AutoState.autoCommandFactory.generate(StartingLocation.CENTER, temp ,AutoType.SWITCH));
        autoChooser.addObject("Turny boy", new TurnToAngle(90));
        SmartDashboard.putData("Auto Chooser",autoChooser);
    }

    @Override
    public void autonomousInit() {
    	Robot.getInstance().hardware.navx.reset();
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