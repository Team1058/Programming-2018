package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.SafeMoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware h = Hardware.getInstance();
    private Command zeroArm = new ZeroArm(); 

    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);

        zeroArm.init();
        while (zeroArm.getStatus() != Status.STOP)
           zeroArm.exec();
        
        
        Arm.configurePID();
        
        Robot.getInstance().hardware.rightDrive1.configClosedloopRamp(0, 10);
        Robot.getInstance().hardware.leftDrive1.configClosedloopRamp(0, 10);
        Robot.getInstance().hardware.rightDrive1.setNeutralMode(NeutralMode.Coast);
        Robot.getInstance().hardware.leftDrive1.setNeutralMode(NeutralMode.Coast);

    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        System.out.println(Robot.getInstance().hardware.armExtendMotor.getSensorCollection().getQuadraturePosition());
    }

    @Override
    public void stop() {
    }

}
