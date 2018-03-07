package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.subsystems.Drivetrain;

public class TurnToAngle extends Command {

    double goal;
    double current;
    boolean init;
    int sign = 1;
    public TurnToAngle(double goal) {
        this.goal = goal;
    }

    @Override
    public void init() {
        current = Hardware.getInstance().navx.getAngle()+1;
       // if (goal)
        setStatus(Status.EXEC);
        Hardware.getInstance().leftDrive1.configPeakOutputForward(.65, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputForward(.65, 10);
        Hardware.getInstance().leftDrive1.configPeakOutputReverse(-.65, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputReverse(-.65, 10);
        

    }

    @Override
    public void exec() {
    	sign = (int) (Math.abs(goal-current)/(goal-current));
    	current = Hardware.getInstance().navx.getAngle()+1;
    	if (!init){
    		init = true;
    		goal+= current;
    	}
        double output = 0;
        System.out.println("goal"+goal);
        System.out.println("NAVX"+current);
        System.out.println("Diff: "+(goal-current));
        
        if (Math.abs(goal - current) < 10) {
            this.setStatus(Status.STOP);
            this.finished();
        } else {
        	
            output = 3.5*(Math.abs(goal - current) / goal);
            System.out.println("out"+Hardware.getInstance().leftDrive1.getMotorOutputPercent());
            System.out.println("SANITY"+output);
            Drivetrain.setDrive(ControlMode.PercentOutput,sign*-output, sign*-output);
        }
    }

    @Override
    public void finished() {
        Drivetrain.stopAll();

        Hardware.getInstance().leftDrive1.configPeakOutputForward(1, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputForward(1, 10);
        Hardware.getInstance().leftDrive1.configPeakOutputReverse(-1, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputReverse(-1, 10);
    }

}
