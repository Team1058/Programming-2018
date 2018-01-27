package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.robot.controllers.BaseController;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

public class AutoCommandFactory {

    public AutoCommandFactory() {
        //Config?
    }

    public <T> AutoCommand generate(BaseController controller, T t) {
        if (controller instanceof Drivetrain) {
            //return whatever also ik you dont need it for drivebase

        }
        return null; //new AutoCommand(true,()->{});
    }
}
