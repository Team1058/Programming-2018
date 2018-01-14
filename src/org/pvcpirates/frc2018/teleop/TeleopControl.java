package org.pvcpirates.frc2018.teleop;

import java.util.ArrayList;

public class TeleopControl {
    private static TeleopControl ourInstance = new TeleopControl();

    public static TeleopControl getInstance() {
        return ourInstance;
    }

    private ArrayList<TeleopComponent> components;

    private TeleopControl() {
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