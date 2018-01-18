package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.util.LogitechF310Gamepad;

import java.util.ArrayList;

public class TeleopControl {
    private static TeleopControl ourInstance = new TeleopControl();
    protected LogitechF310Gamepad driver;
    protected LogitechF310Gamepad operator;
    public static TeleopControl getInstance() {
        return ourInstance;
    }

    private ArrayList<TeleopComponent> components;

    private TeleopControl() {
        driver = new LogitechF310Gamepad(0);
        operator = new LogitechF310Gamepad(1);
        components = new ArrayList<>();
        components.add(TeleopDrive.getInstance());
        //compnents.add(TeleopSubsystem.getInstance());
    }
    public void runCycle(){
        for (TeleopComponent t: this.components
                ) {
            t.calculate();
        }
    }
    public void disable(){
        for (TeleopComponent t: this.components
                ) {
            t.disable();
        }
    }
}