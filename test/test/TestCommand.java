package test;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.commands.Command;

public class TestCommand extends Command{
	
	private int target;
	private String message;
	
	TestCommand(int target, String message){
		this.target = target;
		this.message = message;
	}
	
	@Override
	public void init(){
		System.out.println(message+" Initialized");
		setStatus(Status.EXEC);
	}
	
	@Override
	public void exec(){
		if(target == 0){
			setStatus(Status.STOP);
		}else{
			System.out.println(message);
			target--;
		}
	}
	
	@Override
	public void finished(){
		System.out.println(message+" Finished");
	}
	
}
