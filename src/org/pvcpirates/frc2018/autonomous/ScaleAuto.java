package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.SpitCube;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.commands.WristRotate;

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
				return;
			}else if(gameData.charAt(0) == 'L'){
				return;
			}
		}

		if(location == StartingLocation.LEFT){
			if(gameData.charAt(1) == 'L'){
				commands.add(new DriveFor(250));
				commands.add(new TurnToAngle(-35));
				commands.add(new MoveArmPolarSetpoint(31, 83));
				commands.add(new DriveFor(60));
				
			}else if(gameData.charAt(1) == 'R'){
				commands.add(new DriveFor(250));
				commands.add(new TurnToAngle(-90));
				commands.add(new DriveFor(190));
				commands.add(new TurnToAngle(90));
				commands.add(new MoveArmPolarSetpoint(31, 83));
				commands.add(new DriveFor(70));
				return;
			}
		}
		commands.add(new WristRotate(90));
		commands.add(new SpitCube());
		
	}
}
