package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class ZeroArm extends Command {

    public ZeroArm() {
        super();
        this.parallel = true;
        //commands.add(new PivotArm(90));
        commands.add(new ZeroExtension());
        commands.add(new ZeroWrist());
        
        
    }
}
