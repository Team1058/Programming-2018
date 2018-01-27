package org.pvcpirates.frc2018.autonomous;

import java.awt.event.ActionEvent;
import org.pvcpirates.frc2018.*;

public class SampleAutoCommand extends AutoCommand {

	Status status;
	
	public SampleAutoCommand() {
		super();
		SampleAutoSubCommand sampleSubCommand1 = new SampleAutoSubCommand(this);
		
	}

}
