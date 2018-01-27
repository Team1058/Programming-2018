package org.pvcpirates.frc2018.autonomous;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.PriorityQueue;

import org.pvcpirates.frc2018.Status;

public class AutoCommand {

	PriorityQueue<AutoSubCommand> subCommands;

	public AutoCommand() {
		subCommands = new PriorityQueue<AutoSubCommand>();
	}
	
	public void addSubCommand(AutoSubCommand toAdd) {
		subCommands.add(toAdd);
	}
	
	public void executeCommand() {
		runNextSubCommand();
	}
	
	private void runNextSubCommand() {
		// Exec next command in stack
		if(!subCommands.isEmpty()) {
		  AutoSubCommand nextCommand = subCommands.remove();
		  nextCommand.exec();
		}else {
			//All subcommands done.
		}
	}
	
	public void statusChanged(Status status) {
		if(status == Status.STOP) {
			runNextSubCommand();
		}else if(status == Status.ERROR) {
			// Default behaviour of statusChanged will kill rest of commands if one errors out
			subCommands.clear();
		}
	}
	
}
