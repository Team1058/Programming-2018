
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
    double Kg;
    int sign = 1;
    
    public TurnToAngle(double goal) {
        this.goal = goal;
        this.Kg = .2;
    }
    public TurnToAngle(double goal,double Kg) {
        this.goal = goal;
        this.Kg = Kg;
    }

    @Override
    public void init() {
    	if (goal == 0){
    		goal = .01;
    	}
    	
        current = Hardware.getInstance().navx.getAngle()+1;
       // if (goal)
        setStatus(Status.EXEC);
        Hardware.getInstance().leftDrive1.configPeakOutputForward(.5, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputForward(.5, 10);
        Hardware.getInstance().leftDrive1.configPeakOutputReverse(-.5, 10);
        Hardware.getInstance().rightDrive1.configPeakOutputReverse(-.5, 10);
        
    }

    @Override
    public void exec() {
    	if (this.status != Status.STOP){
	    	sign = (int) (Math.abs(goal-current)/(goal-current));
	    	current = Hardware.getInstance().navx.getAngle()+1;
	    	
	        double output = 0;
	        System.out.println("goal"+goal);
	        System.out.println("NAVX"+current);
	        System.out.println("Diff: "+(goal-current));
	        System.out.println("VELOCITY"+Hardware.getInstance().leftDrive1.getSensorCollection().getQuadratureVelocity());
	        if (Math.abs(goal - current) < 1.3&&Hardware.getInstance().leftDrive1.getMotorOutputPercent()<.3/*&&Hardware.getInstance().getInstance().leftDrive1.getSensorCollection().getQuadratureVelocity()>1000*/) {
	            this.setStatus(Status.STOP);
	            this.finished();
	        } else {
	            output = Math.abs(Kg*(goal - current));
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
