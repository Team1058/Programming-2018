package org.pvcpirates.frc2018.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.PriorityQueue;

import org.pvcpirates.frc2018.Status;

public class Command {

	PriorityQueue<SubCommand> subCommands;

	public Command() {
		subCommands = new PriorityQueue<SubCommand>();
	}
	
	public void addSubCommand(SubCommand toAdd) {
		subCommands.add(toAdd);
	}
	
	public void executeCommand() {
		runNextSubCommand();
	}
	
	private void runNextSubCommand() {
		// Exec next command in stack
		if(!subCommands.isEmpty()) {
		  SubCommand nextCommand = subCommands.remove();
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
