package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;

public class ZeroPivot extends Command {

    @Override
    public void init() {
        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        Hardware.getInstance().armPivotMotor.set(ControlMode.Position, 0);
        setStatus(Status.STOP);
    }

    @Override
    public void finished() {

    }

}
