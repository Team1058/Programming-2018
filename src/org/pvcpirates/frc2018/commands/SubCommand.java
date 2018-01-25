package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.*;

public class SubCommand{
	Command parentCommand;
	Status status;
	
	public SubCommand(Command parent) {
		parentCommand = parent;		
	}
	
	public void changeStatus(Status stat) {
	    parentCommand.statusChanged(status);
	}
	
	public void init() {
		changeStatus(Status.INIT);
	}
	
	public void exec() {
		changeStatus(Status.EXEC);
	}
	
	public void finished() {
		changeStatus(Status.STOP);
	}
	
}
