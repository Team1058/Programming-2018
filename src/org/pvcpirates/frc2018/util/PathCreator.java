package org.pvcpirates.frc2018.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathCreator {
	public void configTalons(){
		
	}
	public static Trajectory[] genPath(Waypoint[] points){
		double conv = 1/.3048;
		Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 20, 11*conv, 20*conv, 6*conv);
        double timeFrame = config.dt;
        double wheelBaseWidth = 22;
        //for tankdrive but other types can be implemented
        TankModifier tankModifier = new TankModifier(Pathfinder.generate(points, config));
        tankModifier.modify(wheelBaseWidth);
        Trajectory[] trajs = {tankModifier.getLeftTrajectory(),tankModifier.getLeftTrajectory()};
        return trajs;
	}
}
