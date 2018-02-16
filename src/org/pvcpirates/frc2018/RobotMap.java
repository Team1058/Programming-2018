package org.pvcpirates.frc2018;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int LEFT_DRIVE_1 = 2;
        public static final int RIGHT_DRIVE_1 = 0;
        public static final int LEFT_DRIVE_2 = 3;
        public static final int RIGHT_DRIVE_2 = 1;
        public static final int RIGHT_CUBE_GRABBER = 8;
        public static final int LEFT_CUBE_GRABBER = 9;
        //TODO: TALON IDSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
        public static final int ARM_PIVOT_TALON = 7;
        public static final int ARM_EXTEND_TALON = 5;
        public static final int ARM_EXTEND_TALON_FOLLOWER =4;
        public static final int WRIST_PIVOT_MOTOR = 6;
    }

    public static final class SensorIDs {
        public static final int[] LEFT_DRIVE_ENCODER_PORTS = {0, 1};
        public static final int[] RIGHT_DRIVE_ENCODER_PORTS = {2, 3};
        public static final int CUBE_LIMIT_SWITCH = 4;

        public static final int PIVOT_POT = 0;

    }
    
    public static final class PneumaticIds {
    		public static final int GRABBER_1 = 1;
    		public static final int GRABBER_2 = 2;
    }

    public static final class Constants {
        //FIXME LITERALLY all this bs
        public static final double DRIVE_DISTANCE_PER_TICK = 0; //Feet
        public static final double ARM_MASS = 0;
        public static final double ARM_DISTANCE = 48;
        public static final double PIVOT_POINT_HEIGHT = 0;
        public static final double MAX_ARM_HEIGHT = 71;
        public static final double PIVOT_HEIGHT = 38;
        public static final double PIVOT_TO_MAX_PERIM = 32.5;
        public static final int ROBOT_TIMEOUT = 10;

    }

    public static final class Ranges {
        public static final int POTENTIOMETER_MAX = -1;
        public static final int POTENTIOMETER_MIN = 1000000;
        //TODO GET THESE VALUES PLEAAAAAAAAAAAAAAAAAAAAAAAAAAAASE
        public static final int WRIST_MAX = -1;
        public static final int WRIST_MIN = 1000000000;
        public static final int THE_MIDDLE = -1;
        public static final int ARM_EXTEND_MAX = 10;
        public static final int ARM_EXTEND_MIN =1;

    }

}