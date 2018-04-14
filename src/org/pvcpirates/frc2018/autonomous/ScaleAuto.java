package org.pvcpirates.frc2018.autonomous;

import java.util.LinkedList;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.commands.Delay;
import org.pvcpirates.frc2018.commands.DriveFor;
import org.pvcpirates.frc2018.commands.DriveForGyro;
import org.pvcpirates.frc2018.commands.DriveForMM;
import org.pvcpirates.frc2018.commands.ExtendArm;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.PivotArm;
import org.pvcpirates.frc2018.commands.SpitCube;
import org.pvcpirates.frc2018.commands.TurnToAngle;
import org.pvcpirates.frc2018.commands.WristRotate;
import org.pvcpirates.frc2018.commands.ZeroArm;
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
		commands = new LinkedList<Command>();
		configure();
		super.init();
	}

	private void configure() {
		
		System.out.println("COOOOOOOOOOOOOOONNNNNNNNNNNNFFFFFFFFFFFf");
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("GameData: "+gameData);
		double Kp = .0000037;
		double Kg = .125;
		Command c = new Command();
    	c.commands.add(new ExtendArm(31));
    	c.commands.add(new PivotArm(83));
    	c.commands.add(new WristRotate(20));
		

		//RIGHT START
		if(location == StartingLocation.RIGHT){
			if(gameData.charAt(1) == 'R'){
				commands.add(new DriveForGyro(259,Kp));
				commands.add(new Delay(1000));
				commands.add(new TurnToAngle(-90)); 
				commands.add(c);
				commands.add(new SpitCube(SPEEDS.FULL,false));
				
			}else if(gameData.charAt(1) == 'L'){
//				commands.add(new DriveForGyro(228));
//				commands.add(new TurnToAngle(-90));
//				//FIND MEEEEE
//				commands.add(new DriveForGyro(190));
//				commands.add(new TurnToAngle(0));
//				commands.add(new DriveForGyro(41));
				commands.add(new DriveForGyro(160));
			}
		}

		if(location == StartingLocation.LEFT){
			if(gameData.charAt(1) == 'L'){
				commands.add(new DriveForGyro(259,Kp));
				commands.add(new Delay(1000));
				commands.add(new TurnToAngle(90));
				commands.add(c);
				commands.add(new SpitCube(SPEEDS.FULL,false));
				
			}else if(gameData.charAt(1) == 'R'){
				
				commands.add(new DriveForGyro(182,Kp));
				commands.add(new Delay(500));
				commands.add(new TurnToAngle(90,2,false));
//				//FIND MEEEEE
				commands.add(new DriveForGyro(180,Kp));
				commands.add(new Delay(500));
				commands.add(new TurnToAngle(0,2,false));
				commands.add(c);
				commands.add(new DriveForGyro(45));
				commands.add(new SpitCube(SPEEDS.ZERO,true));
//				commands.add(new DriveForGyro(-20));
//				commands.add(new ZeroArm());
//				commands.add(new DriveForGyro(160));
			}
		}
		System.out.println("Size:"+commands.size());
		
	}
	@Override
	public void finished() {
		// TODO Auto-generated method stub
		//commands = null;
	}
}
