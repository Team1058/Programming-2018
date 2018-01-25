package org.pvcpirates.frc2018.commands;

import java.awt.event.ActionEvent;
import org.pvcpirates.frc2018.*;

public class SampleCommand extends Command {

	Status status;
	
	public SampleCommand() {
		super();
		SampleSubCommand sampleSubCommand1 = new SampleSubCommand(this);
		
	}

}
