package org.pvcpirates.frc2018;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.pvcpirates.frc2018.autonomous.AutoType;
import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.autonomous.command.DriveAuto;
import org.pvcpirates.frc2018.autonomous.command.SwitchAuto;
import org.pvcpirates.frc2018.robot.Hardware;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveFor;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveUltra;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.state.AutoState;
import org.pvcpirates.frc2018.state.State;
import org.pvcpirates.frc2018.state.TeleopState;

public class Scheduler extends IterativeRobot {

    public static final Robot robot = Robot.getInstance();
<<<<<<< HEAD
	public static SendableChooser<AutoCommand> chooser = new SendableChooser<>();


    @Override
    public void robotInit() {
    	chooser.addDefault("Drive Forward", AutoState.commandFactory.generate(StartingLocation.CENTER, new DriveAuto(), AutoType.DRIVE));
    	chooser.addObject("Switch",AutoState.commandFactory.generate(StartingLocation.CENTER, new SwitchAuto(),AutoType.SWITCH ));
        SmartDashboard.putData("Auto Chooser",chooser);
=======
    public static final State auto = new AutoState();
    public static final State teleOp = new TeleopState();
    SendableChooser<AutoCommand> chooser = new SendableChooser<>();
    @Override
    public void robotInit() {
    	
    	AutoCommandFactory commandFactory = new AutoCommandFactory();

        AutoCommand ultraAutoCommand = new AutoCommand();
        DriveUltra driveUltra = new DriveUltra(ultraAutoCommand,20);
        ultraAutoCommand.addSubCommand(driveUltra);

        AutoCommand driveForCommand = new AutoCommand();
        DriveFor driveFor = new DriveFor(driveForCommand,100);
        driveForCommand.addSubCommand(driveFor);
        //chooser.addObject("Switch",commandFactory.generate(StartingLocation.CENTER, new SwitchAuto(),AutoType.SWITCH ));
        chooser.addDefault("DriveUltra",ultraAutoCommand);
        chooser.addObject("DriveFor",driveForCommand);
        SmartDashboard.putData("Auto",chooser);

>>>>>>> Move auto chooser to robot init
    }

    @Override
    public void autonomousInit() {
<<<<<<< HEAD
        robot.setState(new AutoState());
        robot.state.init();
=======
        robot.setState(auto);
        auto.init();
>>>>>>> Move auto chooser to robot init
    }

    @Override
    public void autonomousPeriodic() {
        robot.state.exec();
    }

    @Override
    public void teleopInit() {
<<<<<<< HEAD
        robot.setState(new TeleopState());
        robot.state.init();
=======
        robot.setState(teleOp);
        teleOp.init();
>>>>>>> Move auto chooser to robot init
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