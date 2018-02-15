package test;

import org.pvcpirates.frc2018.autonomous.Command;

public class TestDaddyCommand extends Command{
	TestDaddyCommand(boolean parallel){
		super();
		commands.add(new TestCommand(2,"foo"));
		commands.add(new TestCommand(3,"bar"));
		this.parallel = parallel;
	}
	
}
