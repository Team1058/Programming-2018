package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ZeroWrist extends Command {

    public ZeroWrist() {
        setStatus(Status.INIT);
    }

    @Override
    public void init() {
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
    	Arm.pivotArm(90);
    	//Drive wrist backwards until limit switch is hit
    	System.out.println("H*CK: "+(!Hardware.getInstance().wristPivotMotor.getSensorCollection().isRevLimitSwitchClosed()));
        if (!Hardware.getInstance().wristPivotMotor.getSensorCollection().isRevLimitSwitchClosed()) {
            Hardware.getInstance().wristPivotMotor.getSensorCollection().setQuadraturePosition(0,10);
            setStatus(Status.STOP);
            finished();
            return;
        } else {
            Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, -.4);
        }
    }

    @Override
    public void finished() {
        Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, 0);
    }


}
