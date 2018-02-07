package org.pvcpirates.frc2018.autonomous;

import org.pvcpirates.frc2018.autonomous.commands.DriveFor;
import org.pvcpirates.frc2018.autonomous.commands.DriveUltra;
import org.pvcpirates.frc2018.autonomous.commands.TurnToAngle;

import edu.wpi.first.wpilibj.DriverStation;


public class CommandFactory {

	private String gameData;
	
    public CommandFactory() {
        //Config?
    }
    public Command generate(StartingLocation location, Command command, AutoType type){
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(type == AutoType.SWITCH){
    		
    	}
    	switch(type){
    	case SWITCH: command = configureSwitch(location, command);
    	case DRIVE: command = configureDrive(command);
		default: command = configureDrive(command);
    	}
    	
    	return command;
    }
    
    private Command configureDrive(Command command) {
		command.commands.add(new DriveFor(10));
		return command;
	}
	public Command configureSwitch(StartingLocation location, Command command){
    	if(location == StartingLocation.LEFT){
    		//Go to left side
    		if(gameData.charAt(0) == 'L'){
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(180));
    			command.commands.add(new DriveUltra(0));
    		}
    		//Drive behind the switch on right side
    		if(gameData.charAt(0) == 'R'){
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(180));
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(180));
    			command.commands.add(new DriveUltra(0));
    		}
    	}else if(location == StartingLocation.RIGHT){
    		//Go to right side
    		if(gameData.charAt(0) == 'R'){
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(-180));
    			command.commands.add(new DriveUltra(0));
    		}
    		//Drive behind the switch to left side
    		if(gameData.charAt(0) == 'L'){
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(-180));
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(-180));
    			command.commands.add(new DriveUltra(0));
    		}
    	}else if(location == StartingLocation.CENTER){
    		if(gameData.charAt(0) == 'L'){
    			command.commands.add(new TurnToAngle(-45));
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(45));
    			command.commands.add(new DriveUltra(0));
    		}else if(gameData.charAt(0) == 'R'){
    			command.commands.add(new TurnToAngle(45));
    			command.commands.add(new DriveFor(0));
    			command.commands.add(new TurnToAngle(-45));
    			command.commands.add(new DriveUltra(0));
    		}
    	}
    	return command;
    }
}
