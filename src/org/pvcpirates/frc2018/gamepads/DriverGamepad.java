package org.pvcpirates.frc2018.gamepads;

<<<<<<< HEAD
import org.pvcpirates.frc2018.gamepads.Button.ButtonAction;
import org.pvcpirates.frc2018.robot.Robot;
import org.pvcpirates.frc2018.teleop.CubeGrabber;
=======
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c
import org.pvcpirates.frc2018.teleop.DriveCommand;
import org.pvcpirates.frc2018.teleop.DriveVelocity;

public class DriverGamepad extends BaseGamepad {

<<<<<<< HEAD
	public DriverGamepad(int port) {
		super(port);
		// TODO: Do the concrete abstract methods get called by the super class constructor??
		//mapControlsToCommands();
	}
	
	void mapCommandsToController() {
		//teleopCommands.add(new DriveCommand(this));
		teleopCommands.add(new CubeGrabber(this));
	}
=======
    public DriverGamepad(int port) {
        super(port);
        // TODO: Do the concrete abstract methods get called by the super class constructor??
        //mapControlsToCommands();
    }

    void mapCommandsToController() {
        teleopCommands.add(new DriveCommand(this));
        teleopCommands.add(new DriveVelocity(this));
    }
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c

}
