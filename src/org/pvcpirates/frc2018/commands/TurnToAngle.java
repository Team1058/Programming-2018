package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;

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
    	if (goal == 0){
    		goal = .01;
    	}
    	
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
    	if (this.status != Status.STOP){
	    	sign = (int) (Math.abs(goal-current)/(goal-current));
	    	System.out.println("Sign "+sign);
	    	current = Hardware.getInstance().navx.getAngle()+1;
	    	
	        double output = 0;
	        System.out.println("goal"+goal);
	        System.out.println("NAVX"+current);
	        System.out.println("Diff: "+(goal-current));
	        
	        if (Math.abs(goal - current) < 2.5/*&&Hardware.getInstance().getInstance().leftDrive1.getSensorCollection().getQuadratureVelocity()>1000*/) {
	            this.setStatus(Status.STOP);
	            this.finished();
	        } else {
	            output = Math.abs(.2*(goal - current));
	            System.out.println("out"+Hardware.getInstance().leftDrive1.getMotorOutputPercent());
	            System.out.println("SANITY"+output);
	            if (sign == -1){
	            	//Drivetrain.setDrive(ControlMode.PercentOutput,output, output);
	            	Drivetrain.setDrive(ControlMode.Velocity,10000*output, 10000*output);
	            }else{
	            	Drivetrain.setDrive(ControlMode.Velocity,-10000*output, -10000*output);
	            	//Drivetrain.setDrive(ControlMode.PercentOutput,-output, -output);
	            }
	        }
	        
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
