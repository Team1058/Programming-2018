package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.ExtendArm;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.commands.SpitCube;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.commands.WristRotate;
import org.pvcpirates.frc2018.commands.SpitCube.SPEEDS;

import edu.wpi.first.wpilibj.DriverStation;

public class ScaleAuto extends Command {
	StartingLocation location;
	public ScaleAuto(StartingLocation location) {
		this.location = location;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(){
		configure();
		super.init();
	}

	private void configure() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		Command c = new Command();
    	c.commands.add(new ExtendArm(31));
    	c.commands.add(new PivotArm(83));
    	c.commands.add(new WristRotate(20));
    	
		

		//RIGHT START
		if(location == StartingLocation.RIGHT){
			if(gameData.charAt(1) == 'R'){
				commands.add(new DriveFor(269));
				commands.add(new TurnToAngle(-45));
				commands.add(c);
				
			}else if(gameData.charAt(1) == 'L'){
				commands.add(new DriveFor(228));
				commands.add(new TurnToAngle(-90));
				
			}
		}

		if(location == StartingLocation.LEFT){
			if(gameData.charAt(1) == 'L'){
				commands.add(new DriveFor(269));
				commands.add(new TurnToAngle(45));
				commands.add(c);
				
			}else if(gameData.charAt(1) == 'R'){
				
				
			}
		}
		commands.add(new SpitCube(SPEEDS.HALF,false));
		
	}
}