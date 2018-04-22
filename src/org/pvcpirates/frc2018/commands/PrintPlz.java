package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.Status;

public class PrintPlz extends Command {

	String msg;
	public PrintPlz(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	@Override
	public void exec() {
		System.out.println(msg);
		this.setStatus(Status.STOP);
		this.finished();
	}
	

	
}
