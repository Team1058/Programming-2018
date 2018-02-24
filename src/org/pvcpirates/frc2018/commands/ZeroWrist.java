package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class ZeroWrist extends Command {

    public ZeroWrist() {
        setStatus(Status.INIT);
    }

    @Override
    public void init() {
        setStatus(Status.EXEC);
        Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, -.4);
    }

    @Override
    public void exec() {
        if (Hardware.getInstance().wristPivotMotor.getSensorCollection().isRevLimitSwitchClosed()) {
            System.out.println("ZERO");
            Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, 0);
            setStatus(Status.STOP);
            finished();
            return;
        } else {
            System.out.println(Arm.getWristAngle());
            Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, -.4);
        }
    }

    @Override
    public void finished() {
        Hardware.getInstance().wristPivotMotor.set(ControlMode.PercentOutput, 0);
    }


}
