package org.pvcpirates.frc2018;

import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.SwitchAuto;
import org.pvcpirates.frc2018.commands.TurnAnglePosition;
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
    	/*
    	 * 
    	 */
        autoChooser.addDefault("Drive Forward", new DriveFor(220));
        autoChooser.addObject("Nothing", new DriveFor(0));
        autoChooser.addObject("Switch Auto Center", new SwitchAuto(StartingLocation.CENTER));
        autoChooser.addObject("TURN TEST!!!!!!!!!!", new TurnToAngle(45));
        
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