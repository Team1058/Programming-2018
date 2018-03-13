package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class SafeMoveArmPolarSetpoint extends Command {
    private double ext;
    private double angle;
    private boolean inMin;
    boolean zeroed;
    private double wrist = -100000;
    public SafeMoveArmPolarSetpoint(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
        init();
    }

    public SafeMoveArmPolarSetpoint(){
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
        zeroed = false;
    }
    public void set(double ext, double angle,double wrist) {
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);
        inMin = (angle <=-33||angle>=205);
        this.wrist = wrist;
        zeroed = false;
    }
    
    @Override
    public void exec() {
    	boolean inRange =(Arm.getPivotAngle() <=-33||Arm.getPivotAngle()>=205);
        if (Arm.getPivotAngle() <= angle + 3 && Arm.getPivotAngle() >= angle - 3) {
            this.setStatus(Status.STOP);
            this.finished();
        }else if(inRange && Arm.getArmExtension() < ext-2 && inMin && Math.abs(Arm.getPivotAngle()-angle)<90){
        	Arm.pivotArm(angle);
        	Arm.extendArm(ext);
        	System.out.println("waaaa");
        }else{
        	
	        if (Arm.getArmExtension() < 4||(inRange&&inMin&& Math.abs(Arm.getPivotAngle()-angle)<90)){
	        	System.out.println("RUNRUNRUN"+angle);
	    		Arm.pivotArm(angle);
	        }else{
        		Arm.pivotArm(Arm.getPivotAngle());
        		System.out.println("gaaaa");
                Arm.extendArm(0);
	        }

	        
        }
    }

    @Override
    public void finished() {
    	System.out.println("EXTENDO");
        Arm.extendArm(ext);
        
        if (wrist !=-100000){
        	Arm.wristRotate(wrist);
        	System.out.println("wrist");
        }
    }


}
