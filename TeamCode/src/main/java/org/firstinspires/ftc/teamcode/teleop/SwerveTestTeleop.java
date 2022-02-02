package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.swerve.SwerveDrive;

@TeleOp(name = "Drive Only", group = "Teleop")
public class SwerveTestTeleop extends LinearOpMode {
    public void runOpMode(){
        SwerveDrive swerveDrive = new SwerveDrive(hardwareMap);
        TelemetryPacket packet = new TelemetryPacket();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        waitForStart();
        while(opModeIsActive()) {
            double rotation = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double forward = -gamepad1.left_stick_y;
            swerveDrive.setMotorPowers(rotation, strafe, forward);
            packet.put("RF Current", swerveDrive.swerveModules[0].getAngle());
            packet.put("Atan2 Target", swerveDrive.swerveKinematics.getWheelAngles()[0]);
            packet.put("Final Target", swerveDrive.swerveModules[0].getTargetAngle());
            dashboard.sendTelemetryPacket(packet);
        }
    }
}