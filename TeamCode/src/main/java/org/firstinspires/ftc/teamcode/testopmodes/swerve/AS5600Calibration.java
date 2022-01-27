package org.firstinspires.ftc.teamcode.testopmodes.swerve;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.helperfunctions.AS5600;
import org.firstinspires.ftc.teamcode.helperfunctions.MathFunctions;
import org.firstinspires.ftc.teamcode.swerve.SwerveConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@TeleOp(name = "Analog Encoder Calibration", group = "TestOpModes")
public class AS5600Calibration extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        AS5600 as5600 = new AS5600(hardwareMap, "analogtest", 2.523);
        DcMotorEx rot = hardwareMap.get(DcMotorEx.class, "rot");
        rot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TelemetryPacket packet = new TelemetryPacket();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        File file = new File("sdcard/FIRST/swervedata.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitForStart();
        while(opModeIsActive()) {
            sleep(10);
            double quad = rot.getCurrentPosition() / SwerveConstants.ticksPerRot * Math.PI * 2;
            quad = MathFunctions.angleWrap(quad);
            double analog = as5600.getAngle();
            packet.put("Quad Encoder Angle", quad);
            packet.put("Analog Angle (rad)", analog);
            dashboard.sendTelemetryPacket(packet);
            String quadS = String.valueOf(quad);
            String analogS = String.valueOf(analog);
            System.out.print(quadS + "," + analogS);
            file.setWritable(true);
            try {
                writer.write(quadS + "," + analogS);
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
