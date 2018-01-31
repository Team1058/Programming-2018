package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.IterativeRobot;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.pvcpirates.frc2018.autonomous.AutoType;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.autonomous.command.DriveAuto;
import org.pvcpirates.frc2018.autonomous.command.SwitchAuto;
import org.pvcpirates.frc2018.robot.Hardware;
=======
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
>>>>>>> Auto stuf
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.State;
import org.pvcpirates.frc2018.state.TeleopState;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
	public static SendableChooser<AutoCommand> chooser = new SendableChooser<>();


    @Override
    public void robotInit() {
    	chooser.addDefault("Drive Forward", AutoState.commandFactory.generate(StartingLocation.CENTER, new DriveAuto(), AutoType.DRIVE));
    	chooser.addObject("Switch",AutoState.commandFactory.generate(StartingLocation.CENTER, new SwitchAuto(),AutoType.SWITCH ));
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