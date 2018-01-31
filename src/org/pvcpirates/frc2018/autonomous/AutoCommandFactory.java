package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.AutoType;
import org.pvcpirates.frc2018.StartingLocation;

import edu.wpi.first.wpilibj.DriverStation;


public class AutoCommandFactory {

	private String gameData;
	
    public AutoCommandFactory() {
        //Config?
    }
    public <T> AutoCommand generate(StartingLocation location, AutoCommand command, AutoType type){
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(type == AutoType.SWITCH){
    		command = configureSwitch(location, command);
    	}
    	
    	return command;
    }
    
    public AutoCommand configureSwitch(StartingLocation location, AutoCommand command){
    	if(location == StartingLocation.LEFT){
    		//Go to left side
    		if(gameData.charAt(0) == 'L'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,180));
    			command.addSubCommand(new DriveUltra(command));
    		}
    		//Drive behind the switch on right side
    		if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,180));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,180));
    			command.addSubCommand(new DriveUltra(command,0));
    		}
    	}else if(location == StartingLocation.RIGHT){
    		//Go to right side
    		if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));
    			command.addSubCommand(new DriveUltra(command));
    		}
    		//Drive behind the switch to left side
    		if(gameData.charAt(0) == 'L'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));
    			command.addSubCommand(new DriveUltra(command,0));
    		}
    	}else if(location == StartingLocation.CENTER){
    		if(gameData.charAt(0) == 'L'){
    			command.addSubCommand(new TurnToAngle(command, -45));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command, 45));
    			command.addSubCommand(new DriveUltra(command, 0));
    		}else if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new TurnToAngle(command, 45));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command, -45));
    			command.addSubCommand(new DriveUltra(command, 0));
    		}
    	}
    	return command;
    }
}
