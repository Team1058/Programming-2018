package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.CameraServer;

public class TeleopState extends State {
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;
    private Hardware hardware;

    boolean noWrist = false;
    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
        hardware = Hardware.getInstance();
        //Reset ramp set in auto
        hardware.rightDrive1.configClosedloopRamp(0, 10);
        hardware.leftDrive1.configClosedloopRamp(0, 10);
        
        hardware.leftDrive1.configPeakOutputForward(1, 10);
        hardware.rightDrive1.configPeakOutputForward(1, 10);
        hardware.leftDrive1.configPeakOutputReverse(-1, 10);
        hardware.rightDrive1.configPeakOutputReverse(-1, 10);
        
        //Change motors to coast mode
        hardware.rightDrive1.setNeutralMode(NeutralMode.Coast);
        hardware.rightDrive2.setNeutralMode(NeutralMode.Coast);
        hardware.leftDrive1.setNeutralMode(NeutralMode.Coast);
        hardware.leftDrive2.setNeutralMode(NeutralMode.Coast);
        
        
        hardware.leftDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        hardware.rightDrive1.getSensorCollection().setQuadraturePosition(0, 10);
        
    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
        //System.out.println("Angle of arm "+Arm.getPivotAngle());
 
        /*System.out.println("Arm "+Arm.getPivotAngle());
        if (Arm.getPivotAngle() < 85)
            System.out.println("Less "+Arm.getPivotAngle());
        else if (Arm.getPivotAngle() > 95)
            System.out.println("Greater "+(Arm.getPivotAngle() - 180));
        System.out.println("Wrist "+Arm.getWristAngle());*/
        
        System.out.println("L: "+hardware.leftDrive1.getSensorCollection().getQuadraturePosition());
        System.out.println("R: "+hardware.rightDrive1.getSensorCollection().getQuadraturePosition());
        
    	
    }

    @Override
    public void stop() {
    }

}
