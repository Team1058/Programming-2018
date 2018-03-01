package org.pvcpirates.frc2018.state;


import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.AutoCommandFactory;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class AutoState extends State {
    public static AutoCommandFactory autoCommandFactory = new AutoCommandFactory();
    private Command command;
    private Command zeroArm;

    @Override
    public void init() {
    	zeroArm = new ZeroArm();
    	zeroArm.init();
    	//while(zeroArm.getStatus() != Status.STOP){
    		zeroArm.exec();
    	//}
        Scheduler.autoChooser.getSelected().init();

    }

    @Override
    public void exec() {
        Scheduler.autoChooser.getSelected().exec();
    	//Hardware.getInstance().leftDrive1.set(ControlMode.PercentOutput, .6);
    	//Hardware.getInstance().rightDrive1.set(ControlMode.PercentOutput, .6);
    	
    }

    @Override
    public void stop() {
        Scheduler.autoChooser.getSelected().finished();
    }
}
