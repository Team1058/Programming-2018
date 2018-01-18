package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.controllers.BaseController;
import org.pvcpirates.frc2018.robot.controllers.Drivetrain;

public class CommandFactory {
    public CommandFactory() {
        //Config?
    }
    public <T> Command generate(BaseController controller,T t){
        if (controller instanceof Drivetrain){
            //return whatever also ik you dont need it for drivebase
        }
        return new Command(true,()->{});
    }
}
