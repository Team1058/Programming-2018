package org.pvcpirates.frc2018.autonomous.command;

import java.util.LinkedList;

import org.pvcpirates.frc2018.Status;

public class Command {
	
	public LinkedList<Command> commands;
	private Command current;
	Status status = Status.INIT;
	boolean parallel = false;
	
	public void init(){
		current = commands.getFirst();
		this.setStatus(Status.EXEC);
	}
	
	public void exec(){
		boolean allDone = true;
		//LONG COMMANDS WILL NOT WORK WITH PARALLEL
		if(parallel){
			for(Command cmd: commands){
				if(cmd.getStatus() == Status.INIT){
					cmd.init();
				}
				if(cmd.getStatus() == Status.EXEC){
					allDone = false;
					cmd.exec();
				}
			}
			if(allDone){
				this.finished();
			}
		}else{
			while(!commands.isEmpty()){
				if(current.getStatus() == Status.STOP){
					commands.removeFirst();
					if(commands.isEmpty()){
						this.finished();
					}else{
						current = commands.getFirst();
					}
				}
				if(current.getStatus() == Status.INIT){
					current.init();
				}
				if(current.getStatus() == Status.EXEC){
					current.exec();
				}
			}
		}
	}
	
	public void finished(){
		this.setStatus(Status.STOP);
	}
	
	private void setStatus(Status status){
		this.status = status;
	}
	
	public Status getStatus(){
		return this.status;
	}

}