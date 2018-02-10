package org.pvcpirates.frc2018;

import org.pvcpirates.frc2018.autonomous.AutoType;
import org.pvcpirates.frc2018.autonomous.Command;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.autonomous.commands.DriveFor;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.TeleopState;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
	public static SendableChooser<Command> chooser = new SendableChooser<>();


    @Override
    public void robotInit() {
    	chooser.addDefault("Drive Forward", AutoState.commandFactory.generate(StartingLocation.CENTER, new DriveFor(60), AutoType.DRIVE));
        SmartDashboard.putData("Auto Chooser",chooser);

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