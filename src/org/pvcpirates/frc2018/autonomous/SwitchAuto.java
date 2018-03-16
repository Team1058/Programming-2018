package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.commands.SpitCube;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.commands.WristRotate;

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
				commands.add(new TurnToAngle(-45));
				commands.add(new DriveFor(66));
				commands.add(new TurnToAngle(40));
				commands.add(new PivotArm(0,true));
				commands.add(new WristRotate(0,true));
				commands.add(new DriveFor(40));
			}else if(gameData.charAt(0) == 'R'){
				commands.add(new DriveFor(30));
				commands.add(new TurnToAngle(45));
				commands.add(new DriveFor(66));
				commands.add(new TurnToAngle(-45));
				commands.add(new PivotArm(0,true));
				commands.add(new WristRotate(0,true));
				commands.add(new DriveFor(42));
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
		
		commands.add(new SpitCube());
	}
}
