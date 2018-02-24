package org.pvcpirates.frc2018.commands;

public class ZeroArm extends Command {

    public ZeroArm() {
        super();
        this.parallel = false;
        commands.add(new ZeroExtension());
        commands.add(new ZeroWrist());

    }

}
