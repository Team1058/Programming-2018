package org.pvcpirates.frc2018.commands;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.commands.Command;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveArmPolarSetpoint extends Command {
    private double ext;
    private double angle;
    private boolean inMin;
    private double wrist;
    private boolean levelWrist = false;
    private double minAngle = -30;
    private double maxAngle = 210;
    private boolean lastScale=false;
    public MoveArmPolarSetpoint(double ext, double angle) {
        this.ext = ext;
        this.angle = angle;
        levelWrist = true;
        init();
        lastScale = false;
        
    }
    public MoveArmPolarSetpoint(double ext, double angle,boolean lastScale) {
        this.ext = ext;
        this.angle = angle;
        levelWrist = true;
        this.lastScale = lastScale;
        init();
        
    }
    public MoveArmPolarSetpoint(){
    	lastScale = false;
    	init();
    }
    
    public MoveArmPolarSetpoint(double ext, double angle,double wrist){
    	this.wrist = wrist;
    	lastScale = false;
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
        lastScale = false;
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
    public void set(double ext, double angle,double wrist,boolean lastScale) {
    	if (lastScale)
    		Arm.wristRotate(0);
        this.ext = ext;
        this.angle = angle;
        this.setStatus(Status.EXEC);
        this.lastScale = lastScale;
        inMin = (angle <=minAngle||angle>=maxAngle);
        this.wrist = wrist;
        levelWrist = false;
    }
    
    @Override
    public void exec() {
    	

    	boolean inRange =(Arm.getPivotAngle() <=minAngle||Arm.getPivotAngle()>=maxAngle);
    	
    	
    	if (!lastScale){
    		Arm.levelWrist();
			if (Arm.getPivotAngle() <= angle + 10 && Arm.getPivotAngle() >= angle - 10) {
				if (Math.abs(Arm.getArmExtension() - ext) < 5) {
					this.setStatus(Status.STOP);
					this.finished();
				} else {
					Arm.extendArm(ext);
					Arm.pivotArm(Arm.getPivotAngleClosedLoopTarget());
				}
			} else if (inRange && Arm.getArmExtension() < ext - 2 && inMin
					&& Math.abs(Arm.getPivotAngle() - angle) < 90) {
				Arm.pivotArm(angle);
				Arm.extendArm(ext);
				
			} else {
				if ((Arm.getArmExtension() < 4) || (inRange && inMin && Math.abs(Arm.getPivotAngle() - angle) < 90)) {
					
					if (inMin && !inRange)
						Arm.pivotArm(angle < 90 ? minAngle : maxAngle);
					else
						Arm.pivotArm(angle);
				} else {

					if (Arm.getPivotAngleClosedLoopTarget() < minAngle
							|| Arm.getPivotAngleClosedLoopTarget() > maxAngle)
						Arm.stopPivot();
					else
						Arm.pivotArm(Arm.getPivotAngleClosedLoopTarget());
					Arm.extendArm(0);
				}

			}
    	}else{
    		
    		Arm.pivotArm(90);
    		if (Arm.getPivotAngle() >85 && Arm.getPivotAngle() < 95)
    			lastScale = false;
    		
    	}
    }

    @Override
    public void finished() {
        
    	Arm.pivotArm(angle);
        if (levelWrist){
        	Arm.levelWrist();
        }else{
        	Arm.wristRotate(wrist);
        }
        levelWrist = true;
    }


}
