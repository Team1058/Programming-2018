package org.pvcpirates.frc2018;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int LEFT_DRIVE_1 = 2;
        public static final int RIGHT_DRIVE_1 = 0;
        public static final int LEFT_DRIVE_2 = 3;
        public static final int RIGHT_DRIVE_2 = 1;
        public static final int RIGHT_CUBE_GRABBER = 4;
        public static final int LEFT_CUBE_GRABBER = 5;
        public static final int ARM_PIVOT_TALON = -1;
        public static final int ARM_EXTEND_TALON = -1;
    }

    public static final class SensorIDs {
        public static final int[] LEFT_DRIVE_ENCODER_PORTS = {0, 1};
        public static final int[] RIGHT_DRIVE_ENCODER_PORTS = {2, 3};
        public static final int CUBE_LIMIT_SWITCH = 4;

    }
    
    public static final class PneumaticIds {
    		public static final int GRABBER_1 = 1;
    		public static final int GRABBER_2 = 2;
    }

    public static final class Constants {
        public static final int DRIVEBASE_TIMEOUT = 0;
        public static final double DRIVEBASE_kP = 0;
        public static final double DRIVEBASE_kI = 0;
        public static final double DRIVEBASE_kD = 0;
        public static final double DRIVEBASE_kF = 0;
        public static final double DRIVE_DISTANCE_PER_TICK = 0; //Feet
        public static final double ARM_MASS = 0;
        public static final double ARM_OFFSET_DEGREES = 0;
        public static final double ARM_DISTANCE = 0;
    }


}
