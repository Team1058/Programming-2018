package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.Status;

public class SampleAutoCommand extends AutoCommand {

    Status status;

    public SampleAutoCommand() {
        super();
        SampleAutoSubCommand sampleSubCommand1 = new SampleAutoSubCommand(this);

    }

}
