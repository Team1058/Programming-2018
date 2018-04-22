package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.robot.Hardware;

public class ZeroExtension extends Command {

    public ZeroExtension() {
        setStatus(Status.INIT);
    }

    @Override
    public void init() {

        setStatus(Status.EXEC);
    }

    @Override
    public void exec() {
        if (!Hardware.getInstance().armExtendMotor.getSensorCollection().isRevLimitSwitchClosed()) {
        	//Zero extension after the limit switch is hit
            Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, 0);
            Hardware.getInstance().armExtendMotor.getSensorCollection().setQuadraturePosition(0,10);
            setStatus(Status.STOP);
            finished();
            return;
        } else {
        	//Move arm down if not zeroed
            Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, -.25);
        }
    }


    @Override
    public void finished() {
    	//Stop running after arm is zeroed
        Hardware.getInstance().armExtendMotor.set(ControlMode.PercentOutput, 0);
    }
}
