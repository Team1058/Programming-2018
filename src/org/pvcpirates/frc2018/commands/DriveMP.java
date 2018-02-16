package org.pvcpirates.frc2018.commands;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Notifier;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import org.pvcpirates.frc2018.robot.Hardware;
import org.pvcpirates.frc2018.robot.Robot;

public class DriveMP extends Command {
    private static final int MIN_POINTS = 15;
    public MotionProfileStatus status = new MotionProfileStatus();
    public MPState mpState = MPState.STOP;
    Notifier notifer = new Notifier(new PeriodicRunnable());
    private Trajectory trajectoryL;
    private Trajectory trajectoryR;
    private Hardware hardware = Robot.getInstance().hardware;

    DriveMP(Waypoint[] points, Trajectory.Config config, double wheelbaseWidth) {
        double timeFrame = config.dt;
        hardware.leftDrive1.changeMotionControlFramePeriod((int) timeFrame * 1000);
        notifer.startPeriodic(timeFrame);
        //for tankdrive but other types can be implemented
        TankModifier tankModifier = new TankModifier(Pathfinder.generate(points, config));
        tankModifier.modify(wheelbaseWidth);
        trajectoryL = tankModifier.getLeftTrajectory();
        trajectoryR = tankModifier.getRightTrajectory();

    }

    @Override
    public void exec() {

        hardware.leftDrive1.getMotionProfileStatus(status);
        if (!hardware.leftDrive1.getControlMode().equals(ControlMode.MotionProfile))
            mpState = mpState.STOP;
        switch (mpState) {
            case INIT:
                mpState = mpState.START;
                sendPoints();
                break;
            case START:
                if (status.btmBufferCnt >= MIN_POINTS)
                    mpState = mpState.RUN;
                break;
            case RUN:
                if (status.activePointValid && status.isLast)
                    mpState = mpState.STOP;
        }
    }

    private void sendPoints() {
        hardware.leftDrive1.clearMotionProfileTrajectories();
        hardware.leftDrive1.clearMotionProfileTrajectories();
        TrajectoryPoint pointL = new TrajectoryPoint();
        TrajectoryPoint pointR = new TrajectoryPoint();
        for (int i = 0; i < trajectoryR.length(); i++) {
            Trajectory.Segment segL = trajectoryL.get(i);
            Trajectory.Segment segR = trajectoryR.get(i);


            hardware.leftDrive1.pushMotionProfileTrajectory(genPoint(segR, i));
            hardware.leftDrive1.pushMotionProfileTrajectory(genPoint(segL, i));
        }
    }

    private TrajectoryPoint genPoint(Trajectory.Segment seg, int i) {
        TrajectoryPoint point = new TrajectoryPoint();

        point.headingDeg = seg.heading;
        point.position = seg.position;
        point.profileSlotSelect0 = 0;
        point.timeDur = TrajectoryPoint.TrajectoryDuration.valueOf(String.valueOf(Math.round(seg.dt * .1) * 10));
        point.velocity = seg.velocity;

        point.zeroPos = i == 0;
        point.isLastPoint = i == trajectoryR.length() - 1;
        return point;
    }

    //change points
    private enum MPState {
        STOP, INIT, START, RUN;
    }

    class PeriodicRunnable implements java.lang.Runnable {
        public void run() {
            hardware.leftDrive1.processMotionProfileBuffer();
            hardware.leftDrive1.processMotionProfileBuffer();
        }

    }
}
