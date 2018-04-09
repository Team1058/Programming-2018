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
    private double minAngle = -20;
    private double maxAngle = 200;
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
        inMin = (angle <=minAngle||angle>=maxAngle);
    }
    public void set(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);

        inMin = (angle <=minAngle||angle>=maxAngle);
        this.wrist = -100000;
        levelWrist = true;
    }
    public void set(double ext, double angle,double wrist) {
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);

        inMin = (angle <=minAngle||angle>=maxAngle);
        this.wrist = wrist;
        levelWrist = false;
    }
    
    @Override
    public void exec() {
    	Arm.levelWrist();

    	boolean inRange =(Arm.getPivotAngle() <=minAngle||Arm.getPivotAngle()>=maxAngle);
    	Arm.levelWrist();
    	System.out.println("INOMINO: "+inMin + inRange);
        if (Arm.getPivotAngle() <= angle + 4 && Arm.getPivotAngle() >= angle - 4) {
            //this.setStatus(Status.STOP);
        	System.out.println("AAWWFWF");
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
        	
	        if ((Arm.getArmExtension() < 4)||(inRange&&inMin&& Math.abs(Arm.getPivotAngle()-angle)<90&&Arm.getArmExtension() > 600)){
	        	System.out.println("RUNRUNRUN"+angle);
	        	if (inMin && !inRange)
	        		Arm.pivotArm(angle < 90? minAngle:maxAngle);
	        	else
	        		Arm.pivotArm(angle);
	        }else{
	        	System.out.println("kill me pls");
        		//Arm.extendArm(Arm.getArmExtensionClosedLoopTarget());
	        	if (Arm.getPivotAngleClosedLoopTarget()<minAngle||Arm.getPivotAngleClosedLoopTarget()>maxAngle)
	        		Arm.stopPivot();
	        	else
	        		//Arm.stopPivot();
	        		Arm.pivotArm(Arm.getPivotAngleClosedLoopTarget());
	        	
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
