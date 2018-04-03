package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmPolarSetpoint extends Command {
    private double ext;
    private double angle;
    private boolean inMin;
    private double wrist;
    private boolean levelWrist = false;
    public MoveArmPolarSetpoint(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
        levelWrist = true;
        init();
        
    }

    public MoveArmPolarSetpoint(){
    	init();
    }
    
    public MoveArmPolarSetpoint(double ext, double angle,double wrist){
    	this.wrist = wrist;
    	levelWrist = false;
    	init();
    }
    
    @Override
    public void init() {
        this.setStatus(Status.INIT);
        inMin = (angle <=-38||angle>=215);
    }
    public void set(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);
        inMin = (angle <=-33||angle>=205);
        this.wrist = -100000;
        levelWrist = true;
    }
    public void set(double ext, double angle,double wrist) {
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);
        inMin = (angle <=-33||angle>=205);
        this.wrist = wrist;
        levelWrist = false;
    }
    
    @Override
    public void exec() {
    	boolean inRange =(Arm.getPivotAngle() <=-33||Arm.getPivotAngle()>=205);
    	Arm.levelWrist();
        if (Arm.getPivotAngle() <= angle + 4 && Arm.getPivotAngle() >= angle - 4) {
            //this.setStatus(Status.STOP);
            if (Math.abs(Arm.getArmExtension() - ext) < 5){
            	this.setStatus(Status.STOP);
            	this.finished();
            }else
            	Arm.extendArm(ext);
        }else if(inRange && Arm.getArmExtension() < ext-2 && inMin && Math.abs(Arm.getPivotAngle()-angle)<90 ){
        	Arm.pivotArm(angle);
        	Arm.extendArm(ext);
        	System.out.println("waaaa");
        }else{
        	
	        if (Arm.getArmExtension() < 4||(inRange&&inMin&& Math.abs(Arm.getPivotAngle()-angle)<90)){
	        	System.out.println("RUNRUNRUN"+angle);
	    		Arm.pivotArm(angle);
	        }else{
        		Arm.pivotArm(Arm.getPivotAngle());
        		Arm.extendArm(0);
	        }
	        
	        
        }
    }

    @Override
    public void finished() {
    	System.out.println("EXTENDO");
        
    	Arm.pivotArm(angle);
        if (levelWrist){
        	Arm.levelWrist();
        	System.out.println("NOOOO");
        }else{
        	System.out.println("Wrist "+wrist);
        	Arm.wristRotate(wrist);
        }
        levelWrist = true;
    }


}