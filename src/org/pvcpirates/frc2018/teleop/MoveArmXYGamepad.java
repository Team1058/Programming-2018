package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.MoveArmXY;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.GamepadEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmXYGamepad extends TeleopCommand {
    public static final double INCREMENT = 1;
    public MoveArmXY moveArmXY;

    public MoveArmXYGamepad(BaseGamepad gp) {
        super(gp);
    }

    @Override
    public void exec() {
        double x = Arm.getArmX()+gamepad.getAxis(GamepadEnum.LEFT_STICK_X) * INCREMENT;
        double y = Arm.getArmY()+gamepad.getAxis(GamepadEnum.RIGHT_STICK_Y) * INCREMENT;
        if (moveArmXY == null)
            moveArmXY = new MoveArmXY(x, y);
        else if (moveArmXY.getStatus() == Status.STOP)
            moveArmXY.finished();
        else {
            moveArmXY.setXY(x, y);
            moveArmXY.exec();
        }
    }
}
