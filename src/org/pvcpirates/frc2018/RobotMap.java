package org.pvcpirates.frc2018;

public class RobotMap {
    public static final class CANTalonIds {
        public static final int LEFT_DRIVE_1 = 7;
        public static final int RIGHT_DRIVE_1 = 3;
        public static final int LEFT_DRIVE_2 = 8;
        public static final int RIGHT_DRIVE_2 = 2;
    }

    public static final class SensorIDs {
        public static final int[] LEFT_DRIVE_ENCODER_PORTS = {0, 1};
        public static final int[] RIGHT_DRIVE_ENCODER_PORTS = {2, 3};

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

    public static final class GamepadPorts {
        public static final int xButton = 3;
        public static final int aButton = 1;
        public static final int bButton = 2;
        public static final int yButton = 4;
        public static final int leftBumper = 5;
        public static final int rightBumper = 6;
        public static final int backButton = 7;
        public static final int startButton = 8;
        public static final int leftStickPress = 9;
        public static final int rightStickPress = 10;

        public static final int leftStick_xAxis = 0;
        public static final int leftStick_yAxis = 1;
        public static final int leftTrigger_Axis = 2;
        public static final int rightTrigger_Axis = 3;
        public static final int rightStick_xAxis = 4;
        public static final int rightStick_yAxis = 5;
    }
}
