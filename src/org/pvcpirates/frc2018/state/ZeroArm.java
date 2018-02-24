package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.ZeroExtension;
import org.pvcpirates.frc2018.commands.ZeroWrist;
import org.pvcpirates.frc2018.robot.Hardware;

public class ZeroArm extends Command {
	
	public ZeroArm(){
		super();
		this.parallel = false;
		commands.add(new ZeroExtension());
		commands.add(new ZeroWrist());
		
	}

}
