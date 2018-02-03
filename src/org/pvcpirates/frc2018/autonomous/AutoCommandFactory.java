package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.autonomous.command.AutoCommand;
import org.pvcpirates.frc2018.autonomous.subcommands.DriveFor;
import org.pvcpirates.frc2018.autonomous.subcommands.TurnToAngle;

import edu.wpi.first.wpilibj.DriverStation;


public class AutoCommandFactory {

	private String gameData;
	
    public AutoCommandFactory() {
        //Config?
    }
    public AutoCommand generate(StartingLocation location, AutoCommand command, AutoType type){
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

    		}
    		//Drive behind the switch on right side
    		if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,180));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,180));

    		}
    	}else if(location == StartingLocation.RIGHT){
    		//Go to right side
    		if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));

    		}
    		//Drive behind the switch to left side
    		if(gameData.charAt(0) == 'L'){
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command,-180));
    		}
    	}else if(location == StartingLocation.CENTER){
    		if(gameData.charAt(0) == 'L'){
    			command.addSubCommand(new TurnToAngle(command, -45));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command, 45));
    		}else if(gameData.charAt(0) == 'R'){
    			command.addSubCommand(new TurnToAngle(command, 45));
    			command.addSubCommand(new DriveFor(command,0));
    			command.addSubCommand(new TurnToAngle(command, -45));
    		}
    	}
    	return command;
    }
}
