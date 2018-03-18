package org.pvcpirates.frc2018;

import org.pvcpirates.frc2018.autonomous.ScaleAuto;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.autonomous.SwitchAuto;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.DriveForGyro;
import org.pvcpirates.frc2018.commands.DriveForMM;
import org.pvcpirates.frc2018.commands.ExtendArm;
import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.commands.SpitCube;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.commands.WristRotate;
import org.pvcpirates.frc2018.commands.SpitCube.SPEEDS;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.TeleopState;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
    public static SendableChooser<Command> autoChooser = new SendableChooser<>();
    Command c;
    @Override
    public void robotInit() {
    	 
        autoChooser.addObject("Drive Forward", new DriveForGyro(259));
        autoChooser.addDefault("Nothing", new DriveFor(0));
        autoChooser.addObject("ScaleRight", new ScaleAuto(StartingLocation.RIGHT));
        autoChooser.addObject("ScaleLeft", new ScaleAuto(StartingLocation.LEFT));
        autoChooser.addObject("Switch Auto Center", new SwitchAuto(StartingLocation.CENTER));
        //autoChooser.addObject("TURN RIGHT", new TurnToAngle(90));
       
        SmartDashboard.putData("Auto Chooser",autoChooser);
        
        Arm.configurePID();
    }

    @Override
    public void autonomousInit() {
    	/*
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	
    	c.parallel = false;
    	c.commands.add(new DriveForGyro(259));
    	 if(gameData.charAt(1) == 'L'){
	    	
	    	
			Command d = new Command();
	    	d.commands.add(new ExtendArm(31));
	    	d.commands.add(new PivotArm(83));
	    	d.commands.add(new WristRotate(20));
	    	c.commands.add(new TurnToAngle(20));
	    	c.commands.add(d);
	    	c.commands.add(new SpitCube(SPEEDS.FULL,false));
    	 }
    	*/
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