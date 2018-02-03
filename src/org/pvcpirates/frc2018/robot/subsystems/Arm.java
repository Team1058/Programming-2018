package org.pvcpirates.frc2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import org.pvcpirates.frc2018.RobotMap;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

public class Arm extends  BaseController {
    Hardware hardware = Robot.getInstance().hardware;
    public Arm() {
        hardware.armExtendMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0, RobotMap.Constants.DRIVEBASE_TIMEOUT);
        hardware.armPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0, RobotMap.Constants.DRIVEBASE_TIMEOUT);
    }
    public void changeHeight(int heightInches){
        int targetPos = 0;
    }
}
