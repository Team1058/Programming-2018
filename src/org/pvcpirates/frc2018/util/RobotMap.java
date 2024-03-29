package org.pvcpirates.frc2018.util;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int LEFT_DRIVE_1 = 6;
        public static final int RIGHT_DRIVE_1 = 10;
        public static final int LEFT_DRIVE_2 = 3;
        public static final int RIGHT_DRIVE_2 = 9;
        public static final int RIGHT_CUBE_GRABBER = 0;
        public static final int LEFT_CUBE_GRABBER = 1;
        //TODO: TALON IDSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
        public static final int ARM_PIVOT_TALON = 1;
        public static final int ARM_EXTEND_TALON = 2;
        public static final int ARM_EXTEND_TALON_FOLLOWER = 11;
        public static final int WRIST_PIVOT_MOTOR = 0;
    }

    public static final class SensorIDs {
        public static final int[] LEFT_DRIVE_ENCODER_PORTS = {0, 1};
        public static final int[] RIGHT_DRIVE_ENCODER_PORTS = {2, 3};
        public static final int CUBE_LIMIT_SWITCH = 4;

        public static final int PIVOT_POT = 0;

    }

    public static final class PneumaticIds {
        public static final int GRABBER_1 = 1;
        public static final int GRABBER_2 = 0;
        public static final int CLIMBER = 3;
    }

    public static final class Constants {
        //FIXME LITERALLY all this bs
        public static final double DRIVE_DISTANCE_PER_TICK = 0; //Feet
        public static final double ARM_MASS = 0;
        public static final double ARM_DISTANCE = 38;
        public static final double PIVOT_POINT_HEIGHT = 0;
        public static final double MAX_ARM_HEIGHT = 71;
        public static final double PIVOT_HEIGHT = 38;
        public static final double PIVOT_TO_MAX_PERIM = 31;
        public static final int ROBOT_TIMEOUT = 10;
        public static final double SPROCKET_DIAMETER = 1.751;
        public static final double GROUND_TO_PIVOT = 38;
        public static final double MAX_VELOCITY = 9.2;
    }

    public static final class Ranges {
        public static final int PIVOT_ENCODER_MAX = 940;
        public static final int PIVOT_ENCODER_MIN = 80;
        //TODO GET THESE VALUES PLEAAAAAAAAAAAAAAAAAAAAAAAAAAAASE
        public static final int WRIST_ENCODER_MAX = 2073;
        public static final int WRIST_ENCODER_MIN = 0;
        public static final int THE_MIDDLE = 0;
        public static final int ARM_EXTEND_ENCODER_MAX = 24500;
        public static final int ARM_EXTEND_ENCODER_MIN = -1000;

    }

}