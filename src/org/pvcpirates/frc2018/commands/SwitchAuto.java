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
	}

	private void configure() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		

		//CENTER START
		if(location == StartingLocation.CENTER){
			if(gameData.charAt(0) == 'L'){
				commands.add(new DriveFor(30));
				commands.add(new TurnToAngle(45));
				commands.add(new DriveFor(83));
				commands.add(new TurnToAngle(-45));
				commands.add(new DriveFor(35));
			}else if(gameData.charAt(0) == 'R'){
				commands.add(new DriveFor(30));
				commands.add(new TurnToAngle(-45));
				commands.add(new DriveFor(83));
				commands.add(new TurnToAngle(45));
				commands.add(new DriveFor(37));
			}
		}

		//RIGHT START
		if(location == StartingLocation.RIGHT){
			if(gameData.charAt(0) == 'R'){
				commands.add(new DriveFor(172));
				commands.add(new TurnToAngle(90));
			}else if(gameData.charAt(0) == 'L'){
				return;
			}
		}

		if(location == StartingLocation.LEFT){
			if(gameData.charAt(0) == 'L'){
				commands.add(new DriveFor(172));
				commands.add(new TurnToAngle(90));
			}else if(gameData.charAt(0) == 'R'){
				return;
			}
		}
		
		//commands.add(new PivotArm(60));
		commands.add(new WristRotate(90));
		commands.add(new SpitCube());
	}
}