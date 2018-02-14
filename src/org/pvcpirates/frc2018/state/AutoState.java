package org.pvcpirates.frc2018.state;


import java.util.List;

import org.pvcpirates.frc2018.Scheduler;
import org.pvcpirates.frc2018.autonomous.Command;
import org.pvcpirates.frc2018.autonomous.CommandFactory;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class AutoState extends State {
    private List<Command> commands;
    public static CommandFactory commandFactory = new CommandFactory();
    public TalonSRX l = Hardware.getInstance().leftDrive1;
    public TalonSRX r = Hardware.getInstance().rightDrive1;
    //TODO commandGroup
    
    @Override
    public void init() {
    	Scheduler.chooser.getSelected().init();
    }

    //TODO:Parallel/Sequential stuff
    @Override
    public void exec() {
		Scheduler.chooser.getSelected().exec();
    }

    @Override
    public void stop() {
    	Scheduler.chooser.getSelected().finished();
    }
}
