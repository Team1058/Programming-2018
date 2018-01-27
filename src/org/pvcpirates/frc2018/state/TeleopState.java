package org.pvcpirates.frc2018.state;

import org.pvcpirates.frc2018.gamepads.DriverGamepad;
import org.pvcpirates.frc2018.gamepads.OperatorGamepad;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.Compressor;

public class TeleopState extends State{
=======
public class TeleopState extends State {
>>>>>>> 7db7131138724ea89896ca315052e335daa7067c
    private DriverGamepad driverGamepad;
    private OperatorGamepad operatorGamepad;

    @Override
    public void init() {
        driverGamepad = new DriverGamepad(0);
        operatorGamepad = new OperatorGamepad(1);
    }

    @Override
    public void exec() {
        driverGamepad.executeCommands();
        operatorGamepad.executeCommands();
    }

    @Override
    public void stop() {
    }

}
