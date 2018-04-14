package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;

public class Delay extends Command {
	int mills;
	public Delay(int mills){
		this.mills =mills;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
				try {
					Thread.sleep(mills);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		this.setStatus(Status.EXEC);
	}

	@Override
	public void exec() {
		this.setStatus(Status.STOP);
	}
	

}
