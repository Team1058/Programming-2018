package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CubeGrabber extends TeleopCommand{

    public final TalonSRX rightMotor = new TalonSRX(1);
    public final TalonSRX leftMotor = new TalonSRX(2);
    
    public final DoubleSolenoid solenoid = new DoubleSolenoid(1,2);

	public CubeGrabber(BaseGamepad gp) {
		super(gp);
	}
	
	private void openGrabber(){
	    solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	private void closeGrabber(){
	    solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	

	@Override
	public void executeCommand() {
		if(gamepad.getButton(GamepadEnum.A_BUTTON)){
			rightMotor.set(ControlMode.PercentOutput,-1);
			leftMotor.set(ControlMode.PercentOutput, .75);
			openGrabber();
		}else if(gamepad.getButton(GamepadEnum.X_BUTTON)){
			rightMotor.set(ControlMode.PercentOutput,1);
			leftMotor.set(ControlMode.PercentOutput, -1);
			//openGrabber();
		}else if(gamepad.getButton(GamepadEnum.B_BUTTON)){
			rightMotor.set(ControlMode.PercentOutput,-.1);
			leftMotor.set(ControlMode.PercentOutput, .1);
		}else{
			rightMotor.set(ControlMode.PercentOutput,0);
			leftMotor.set(ControlMode.PercentOutput,0);	
			closeGrabber();
		}
	}
}
