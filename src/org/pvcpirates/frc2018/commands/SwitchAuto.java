package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.autonomous.StartingLocation;

import edu.wpi.first.wpilibj.DriverStation;

public class SwitchAuto extends Command {
	
	StartingLocation location;
	
	public SwitchAuto(StartingLocation location){
		this.location = location;
	}
	
	@Override
	public void init(){
		configure();
		super.init();
		System.out.println("Bleh");
	}

	private void configure() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(location == StartingLocation.CENTER){
			if(gameData.charAt(0) == 'L'){
				commands.add(new DriveFor(57.6));
				commands.add(new TurnToAngle(-45));
				commands.add(new DriveFor(110.88));
				commands.add(new TurnToAngle(45));
			}else if(gameData.charAt(0) == 'R'){
				commands.add(new DriveFor(57.6));
				commands.add(new TurnToAngle(45));
				commands.add(new DriveFor(110.88));
				commands.add(new TurnToAngle(-45));
			}
		}
		
		commands.add(new PivotArm(60));
		commands.add(new SpitCube());
	}
}
