package org.pvcpirates.frc2018.teleop;

import org.pvcpirates.frc2018.robot.Status;
import org.pvcpirates.frc2018.commands.MoveArmPolarSetpoint;
import org.pvcpirates.frc2018.commands.ZeroArm;
import org.pvcpirates.frc2018.gamepads.BaseGamepad;
import org.pvcpirates.frc2018.gamepads.VJoyEnum;
import org.pvcpirates.frc2018.robot.subsystems.Arm;

public class MoveToExtraSetpoints extends TeleopCommand {

	MoveArmPolarSetpoint moveSetpoint = null;
	ZeroArm zeroArm = new ZeroArm();
	private boolean lastScale = false;

	public MoveToExtraSetpoints(BaseGamepad gp) {
		super(gp);
		moveSetpoint = new MoveArmPolarSetpoint();
	}

	@Override
	public void exec() {
		if (gamepad.getButton(VJoyEnum.SPACE)) {
			// post scale scoring

			moveSetpoint.set(0, 90, 0, lastScale);
			
		} else if (gamepad.getButton(VJoyEnum.H)) {
			// intake front
			lastScale = false;
			moveSetpoint.set(20, -60);
		} else if (gamepad.getButton(VJoyEnum.V)) {
			// intake back
			lastScale = false;
			moveSetpoint.set(20, 240);
		} else if (gamepad.getButton(VJoyEnum.LMENU)) {
			// switch front
			lastScale = false;
			moveSetpoint.set(0, -10);
		} else if (gamepad.getButton(VJoyEnum.LCONTROL)) {
			// starting front
			lastScale = false;
			moveSetpoint.set(0, 83);
		} else if (gamepad.getButton(VJoyEnum.LEFTSHIFTKEY)) {
			// scale ready front
			moveSetpoint.set(22, 83);
			lastScale = true;
		} else if (gamepad.getButton(VJoyEnum.TAB)) {
			// scale high front
			moveSetpoint.set(31, 83, 20);
			lastScale = true;
		} else if (gamepad.getButton(VJoyEnum.O)) {
			// scale high back
			moveSetpoint.set(31, 97, -20);
			lastScale = true;
		} else if (gamepad.getButton(VJoyEnum.G)) {
			// scale ready back
			lastScale = true;
			moveSetpoint.set(22, 97);
		} else if (gamepad.getButton(VJoyEnum.OEMCOMMA)) {
			// starting back
			lastScale = false;
			moveSetpoint.set(0, 83);
		} else if (gamepad.getButton(VJoyEnum.OEMPERIOD)) {
			// switch back
			lastScale = false;
			moveSetpoint.set(0, 190);
		} else if (gamepad.getButton(VJoyEnum.VOLUMEDOWN)) {
			// climb set
			lastScale = false;
			moveSetpoint.set(34, 75, -90);
		} else if (gamepad.getButton(VJoyEnum.VOLUMEUP)) {
			// climb up
			lastScale = false;
			moveSetpoint.set(8, 75, -90);
		} else if (gamepad.getButton(VJoyEnum.ESCAPE)) {
			if (zeroArm.getStatus() == Status.STOP)
				zeroArm = new ZeroArm();
			while(zeroArm.getStatus() != Status.STOP){
				zeroArm.exec();
			}
			moveSetpoint.set(0, 90, -90);
			lastScale = false;
		} else if (moveSetpoint.getStatus() == Status.EXEC) {
			moveSetpoint.exec();
		}

		if (gamepad.getButton(VJoyEnum.VOLUMEMUTE)) {
			// climb ready
			// moveSetpoint.set(34, 95, -90);
			Arm.wristRotate(0);
		}

	}

}