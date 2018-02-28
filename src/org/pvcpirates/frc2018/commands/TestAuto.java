package org.pvcpirates.frc2018.commands;

public class TestAuto extends Command {
	public TestAuto(){
		parallel = false;
		commands.add(new DriveAndPivot(126,60,30));
		commands.add(new SpitCube());
	}
}
