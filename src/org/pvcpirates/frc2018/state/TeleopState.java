package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.SafeMoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.commands.ZeroExtension;
import org.pvcpirates.frc2018.commands.ZeroWrist;
import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware h = Hardware.getInstance();
    private Command zeroArm = new ZeroArm(); 

    boolean noWrist = false;
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        
        
        Arm.configurePID();
        
        Robot.getInstance().hardware.rightDrive1.configClosedloopRamp(0, 10);
        Robot.getInstance().hardware.leftDrive1.configClosedloopRamp(0, 10);
        
        
        Robot.getInstance().hardware.rightDrive1.setNeutralMode(NeutralMode.Coast);
        Robot.getInstance().hardware.rightDrive2.setNeutralMode(NeutralMode.Coast);
        Robot.getInstance().hardware.leftDrive1.setNeutralMode(NeutralMode.Coast);
        Robot.getInstance().hardware.leftDrive2.setNeutralMode(NeutralMode.Coast);

    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        
        
        //System.out.println("Ext: "+Robot.getInstance().hardware.armExtendMotor.getSensorCollection().getQuadraturePosition());
        //System.out.println("Inches: "+Arm.getArmExtension());
    	//System.out.println("Navx "+h.navx.getAngle());
    	//Arm.pivotArm(0);
        
    	if (driverGamepad.getButton(GamepadEnum.BACK_BUTTON))
    		noWrist = !noWrist;
        if(noWrist){
        	Arm.wristRotate(-90);
        	/*}else if(gamepad.getButton(GamepadEnum.Y_BUTTON)){
        	 * Arm.wristRotate(-20);
        	 * }*/
        }else{
        	Arm.levelWrist();
        }
        System.out.println("Extension"+Arm.getArmExtension());
        
        //System.out.println("Piv: "+Robot.getInstance().hardware.armPivotMotor.getSensorCollection().getAnalogIn());
        //System.out.println("Angle: "+Arm.getPivotAngle());
        //System.out.println("Analog: "+h.armPivotMotor.getSensorCollection().getAnalogIn());
        //System.out.println("Wrist Enc: "+h.wristPivotMotor.getSensorCollection().getQuadraturePosition());
        //System.out.println("L: "+h.leftDrive1.getSensorCollection().getQuadraturePosition());
        //System.out.println("R: "+h.rightDrive1.getSensorCollection().getQuadraturePosition());
    }

    @Override
    public void stop() {
    }

}
