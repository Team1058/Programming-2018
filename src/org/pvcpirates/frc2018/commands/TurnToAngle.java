package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class TurnToAngle extends Command {

    double goal;
    double current;
    boolean init;

    public TurnToAngle(double goal) {
        this.goal = goal;
    }

    @Override
    public void init() {
        current = Hardware.getInstance().navx.getAngle();
       // if (goal)
        setStatus(Status.EXEC);
        Hardware.getInstance().leftDrive1.configNominalOutputForward(.75, 10);
        Hardware.getInstance().rightDrive1.configNominalOutputForward(.75, 10);
        Hardware.getInstance().leftDrive1.configNominalOutputReverse(-.75, 10);
        Hardware.getInstance().rightDrive1.configNominalOutputReverse(-.75, 10);
    }

    @Override
    public void exec() {
    	current = Hardware.getInstance().navx.getAngle();
    	if (!init){
    		init = true;
    		goal+= current;
    	}
        double output = 0;
        
        System.out.println(current);
        System.out.println("Diff: "+(goal-current));

        if (Math.abs(goal - current) < 5) {
            this.setStatus(Status.STOP);
            this.finished();
        } else {
            output = 0.005*((goal - current) / goal);
            System.out.println("out"+Hardware.getInstance().leftDrive1.getMotorOutputPercent());
            Drivetrain.setDrive(ControlMode.PercentOutput, output, output);
        }
    }

    @Override
    public void finished() {
        Drivetrain.stopAll();
        Hardware.getInstance().leftDrive1.configNominalOutputForward(1, 10);
        Hardware.getInstance().rightDrive1.configNominalOutputForward(1, 10);
        Hardware.getInstance().leftDrive1.configNominalOutputReverse(1, 10);
        Hardware.getInstance().rightDrive1.configNominalOutputReverse(1, 10);
    }

}
