package org.pvcpirates.frc2018.autonomous.command;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.autonomous.subcommands.SampleAutoSubCommand;

public class SampleAutoCommand extends AutoCommand {

    Status status;

    public SampleAutoCommand() {
        super();
        SampleAutoSubCommand sampleSubCommand1 = new SampleAutoSubCommand(this);

    }

}
