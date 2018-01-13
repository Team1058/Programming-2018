package org.pvcpirates.frc2018.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonControl {
    private static AutonControl ourInstance = new AutonControl();

    public static AutonControl getInstance() {
        return ourInstance;
    }

    private SendableChooser <Command> chooser = new SendableChooser<>();
    private AutonControl() {
        //chooser.addDefault("Default Blah", new DefaultBlah());
        //chooser.addObject("Blah", new BestAuto());
    }
    public Command getChoice() {
        return chooser.getSelected();
    }
}