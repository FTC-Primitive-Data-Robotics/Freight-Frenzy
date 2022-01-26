package org.firstinspires.ftc.teamcode.helperfunctions;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AS5600 {
    HardwareMap hardwareMap;
    AnalogInput angleOut; // analog output
    private double angle; // stores current angle
    private final double correction = 0.0; // voltage that corresponds to 0 rad
    private final double maxVoltage = 5.0;
    private final double minVoltage = 0.0;

    public AS5600(HardwareMap hardwareMap, String deviceName) {
        this.hardwareMap = hardwareMap;
        angleOut = hardwareMap.get(AnalogInput.class, deviceName);
    }

    // returns angle in pi to -pi format
    public double getAngle() {
        update();
        return MathFunctions.angleWrap(angle);
    }

    public void update() {
        angle = angleOut.getVoltage() - correction;
        angle -= minVoltage;
        angle /= (maxVoltage - minVoltage);
        angle *= (Math.PI * 2);
    }

    public double applyLinearity(double angle) {
        //TODO: linearity equation
        return angle;
    }

    public double getVoltage() {
        return angleOut.getVoltage();
    }
}
