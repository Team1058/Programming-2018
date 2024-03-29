package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.DriveForGyro;
import org.pvcpirates.frc2018.commands.DriveForMM;
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
		double Kp=.000033;
		double Kg = .3;
		double Kg2 = 2;
		double maxSpeed = .33;
		//CENTER START
		if(location == StartingLocation.CENTER){
			if(gameData.charAt(0) == 'L'){
				commands.add(new DriveForGyro(14,Kp,maxSpeed));
				commands.add(new TurnToAngle(-38));
				commands.add(new DriveForGyro(77,Kp,maxSpeed));
				
				commands.add(new TurnToAngle(0));
				commands.add(new PivotArm(0,true));
				commands.add(new WristRotate(0,true));
				commands.add(new DriveForGyro(22,Kp,maxSpeed));
				
				
			}else if(gameData.charAt(0) == 'R'){
				commands.add(new DriveForGyro(14,Kp,maxSpeed));
				commands.add(new TurnToAngle(50));
				commands.add(new DriveForGyro(70,Kp,maxSpeed));
				
				commands.add(new TurnToAngle(0));
				commands.add(new PivotArm(0,true));
				commands.add(new WristRotate(0,true));
				commands.add(new DriveForGyro(25,Kp,maxSpeed));
				
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
