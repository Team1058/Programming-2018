package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pvcpirates.frc2018.autonomous.Command;

public class CommandTest {

//	@Test
//	public void testSequential() {
//		TestDaddyCommand seqCommand = new TestDaddyCommand(false);
//		seqCommand.init();
//		seqCommand.exec();
//	}
	
	@Test
	public void testParallel() {
		TestDaddyCommand parCommand = new TestDaddyCommand(true);
		parCommand.init();
		autoPeriodic(parCommand);
		parCommand.finished();
	}
	
	
	private void autoPeriodic(Command command){
		while(command.getStatus() == org.pvcpirates.frc2018.Status.EXEC){
			command.exec();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
