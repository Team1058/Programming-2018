package org.pvcpirates.frc2018.state;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Arm;
import org.pvcpirates.frc2018.robot.subsystems.Grabber;
import org.pvcpirates.frc2018.teleop.CubeGrabber;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
    	while(zeroArm.getStatus() != Status.STOP)
    		zeroArm.exec();
    	Arm.configurePID();
    }

    @Override
    public void exec() {
    	
   
    		driverGamepad.executeCommands();
            operatorGamepad.executeCommands();
            //Arm.levelWrist();
            //System.out.println(Arm.getWristAngle());
    		//h.wristPivotMotor.set(ControlMode.Position, 1035.5);
    		//System.out.println("WRIST "+h.wristPivotMotor.getSensorCollection().getQuadraturePosition());
    }

    @Override
    public void stop() {
    }

}
