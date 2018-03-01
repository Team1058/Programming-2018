package org.pvcpirates.frc2018.commands;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class DriveMPTest extends Command {
    @Override
    public void init() {
        Waypoint[] waypoints = {new Waypoint(0,1,0)};
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,5,1,9.2,.022/2,2);
        commands.add(new DriveMP(waypoints, config,10));
    }

    @Override
    public void finished() {
        super.finished();
    }
}
