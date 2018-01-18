package org.pvcpirates.frc2018.gamepads;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick {
    //TODO: Button Scheduler so that way we dont have to rely on wpilib gross commands
    public Gamepad(int port) {
        super(port);
    }

}
