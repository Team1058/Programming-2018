package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pvcpirates.frc2018.commands.Command;

public class CommandTest {
	
	private ByteArrayOutputStream baos;
	private PrintStream ps;
	private PrintStream old;
	
	
	@Before
	public void setUpPrintStream(){
		//Every System.out.println writes to a separate byte array instead of console
		old = System.out;
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
	}
	
	@After
	public void cleanUp(){
		//Prints the same byte array to the console
		System.out.flush();
		System.setOut(old);
	}
	
	@Test
	public void testSequential() { 
		String expected = ""
				+ "foo Initialized\r\n"
				+ "foo\r\n"
				+ "foo\r\n"
				+ "foo Finished\r\n"
				+ "bar Initialized\r\n"
				+ "bar\r\n"
				+ "bar\r\n"
				+ "bar\r\n"
				+ "bar Finished\r\n";
		TestDaddyCommand seqCommand = new TestDaddyCommand(false);
		seqCommand.init();
		autoPeriodic(seqCommand);
		seqCommand.finished();
		//see if byte array is equal to expected value;
		assertEquals(expected, baos.toString());
	}
	
	
	@Test
	public void testParallel() {
		String expected = ""
				+"foo Initialized\r\n"
				+"bar Initialized\r\n"
				+"foo\r\n"
				+"bar\r\n"
				+"foo\r\n"
				+"bar\r\n"
				+"foo Finished\r\n"
				+"bar\r\n"
				+"bar Finished\r\n";
		TestDaddyCommand parCommand = new TestDaddyCommand(true);
		parCommand.init();
		autoPeriodic(parCommand);
		parCommand.finished();
		assertEquals(expected, baos.toString());
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
