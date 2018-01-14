package org.pvcpirates.frc2018.util;

public class PIDF {

    private double p;
    private double i;
    private double d;
    private double f;
    private double value;
    private double range;

    private double previousError;
    private double totalError;

    private double maxOutput;
    private double minOutput;

    private boolean firstCycle;


    public PIDF(double p, double i, double d, double f, double value, double range) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.value = value;
        this.range = range;
        firstCycle = false;
        maxOutput = 1;
        minOutput = -1;
    }
    public PIDF(double p, double i, double d, double f, double range) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.range = range;
        firstCycle = false;
        maxOutput = 1;
        minOutput = -1;
    }

    public double calculate(double error){
        double pVal;
        double iVal;
        double dVal;
        double fVal;
        double result;

        if(firstCycle){
            previousError = error;
            firstCycle = false;
        }

        //P
        pVal = p * error;

        //I
        if(Math.abs(pVal) < 1){
            totalError += error;
        }else{
            totalError = 0;
        }
        iVal = i * totalError;

        //D
        dVal = d * (error - previousError);

        //F
        fVal = f * value;

        this.previousError = error;

        result = pVal + iVal + dVal + fVal;
        if(result > 1){
            result = 1;
        }else if (result < -1){
            result = -1;
        }

        return result;
    }

    public void setValue(double value){
        this.value = value;
    }

}
