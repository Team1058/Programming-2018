package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.autonomous.StartingLocation;
import org.pvcpirates.frc2018.teleop.MoveToSetpoint;

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
				commands.add(new TurnToAngle(20));
				commands.add(new DriveFor(104));
				commands.add(new TurnToAngle(-20));
				commands.add(new DriveFor(1));
				commands.add(new SafeMoveArmPolarSetpoint(0,-10));
				commands.add(new SpitCube());
			}else if(gameData.charAt(0) == 'R'){
				commands.add(new TurnToAngle(-20));
				commands.add(new DriveFor(104));
				commands.add(new TurnToAngle(20));
				commands.add(new DriveFor(1));
				commands.add(new SafeMoveArmPolarSetpoint(0,-10));
				commands.add(new SpitCube());
			}
		}

		//RIGHT START
		if(location == StartingLocation.RIGHT){
			if(gameData.charAt(0) == 'R' && gameData.charAt(1)=='R'){
				commands.add(new DriveFor(160));
				commands.add(new TurnToAngle(-90));
				commands.add(new DriveFor(41.125));
				commands.add(new SafeMoveArmPolarSetpoint(0,-10));
				commands.add(new SpitCube());
				commands.add(new TurnToAngle(90));
				commands.add(new SafeMoveArmPolarSetpoint(22,-60));
				commands.add(new DriveFor(32.5));
				commands.add(new TurnToAngle(-90));
				commands.add(new CubeGrab());
				commands.add(new DriveFor(-82.65));
				commands.add(new SafeMoveArmPolarSetpoint(31 , 97, -60));
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
