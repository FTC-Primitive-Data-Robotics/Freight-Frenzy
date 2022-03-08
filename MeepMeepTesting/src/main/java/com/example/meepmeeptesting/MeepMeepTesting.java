package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import kotlin.math.MathKt;

public class MeepMeepTesting {

    public static double startx = 15.0;
    public static double starty = 70.0;
    public static double startAng = Math.toRadians(90);

    public static double scoreHubPosx = 7;
    public static double scoreHubPosy = 52;

    public static double scoreHubPosAngB = 40;
    public static double scoreHubPosAngR = -40;

    public static double repositionX = 15.0;
    public static double reposistionY = 71.5;

    public static double distanceForwards = 30;
    public static double strafeDistance = 24;
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);


        Pose2d startPosB = new Pose2d(startx, starty, startAng);
        Pose2d startPosR = new Pose2d(startx, -starty, -startAng);

        Vector2d scoreHubPosB = new Vector2d(scoreHubPosx, scoreHubPosy);
        Vector2d scoreHubPosR = new Vector2d(scoreHubPosx, -scoreHubPosy);

        Pose2d repositionB = new Pose2d(repositionX,reposistionY,Math.toRadians(0));

        RoadRunnerBotEntity myBotBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(180), Math.toRadians(180), 9.85)
                .setDimensions(11.838583, 14.4882 )
                //.setStartPose(startPos)
                .followTrajectorySequence(drive ->

                        drive.trajectorySequenceBuilder(startPosB)
                                .waitSeconds(2)
                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(scoreHubPosB, Math.toRadians(scoreHubPosAngB)))                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                            //scoringMech.release();
                        })
                                .waitSeconds(1)
                                //.lineToLinearHeading(repositionB)
                                .lineTo(new Vector2d(scoreHubPosx, reposistionY -20))
                                .splineToSplineHeading(new Pose2d(repositionX+5, reposistionY-2, Math.toRadians(0)), Math.toRadians(0))
                                .lineTo(new Vector2d(repositionX + distanceForwards, reposistionY-2))
                                .waitSeconds(1)
                                .splineTo(new Vector2d(repositionX+5, reposistionY-2), Math.toRadians(180))
                                .splineToSplineHeading(new Pose2d(scoreHubPosx, scoreHubPosy, Math.toRadians(40)), Math.toRadians(270))
                                .waitSeconds(1)
                                .lineTo(new Vector2d(scoreHubPosx, reposistionY -20))
                                .splineToSplineHeading(new Pose2d(repositionX+5, reposistionY-2, Math.toRadians(0)), Math.toRadians(0))
                                .lineTo(new Vector2d(repositionX + distanceForwards, reposistionY-2))
                                .waitSeconds(1)
                                .lineTo(new Vector2d(repositionX+5, reposistionY-2)) //Math.toRadians(180))
                                .splineToSplineHeading(new Pose2d(scoreHubPosx, scoreHubPosy, Math.toRadians(40)), Math.toRadians(270))
                                .waitSeconds(1)
                                .lineTo(new Vector2d(scoreHubPosx, reposistionY -20))
                                .splineToSplineHeading(new Pose2d(repositionX+5, reposistionY-2, Math.toRadians(0)), Math.toRadians(0))
                                .lineTo(new Vector2d(repositionX + distanceForwards, reposistionY-2))
                                .strafeRight(strafeDistance)
                                .build()

                );

        RoadRunnerBotEntity myBotRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 30, Math.toRadians(180), Math.toRadians(180), 11)
                // .setStartPose(startPos)
                .setDimensions(11.839, 14.141)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPosR)
                        .waitSeconds(2)
                        .setReversed(true)
                        .lineToLinearHeading(new Pose2d(scoreHubPosR, Math.toRadians(scoreHubPosAngR)))                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                            //scoringMech.release();
                        })
                        .waitSeconds(1)
                        //.lineToLinearHeading(repositionB)
                        .lineTo(new Vector2d(scoreHubPosx, -reposistionY +18))
                        .splineToSplineHeading(new Pose2d(repositionX+5, -reposistionY, Math.toRadians(0)), Math.toRadians(0))
                        .lineTo(new Vector2d(repositionX + distanceForwards, -reposistionY))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(repositionX+5, -reposistionY))//, Math.toRadians(180))
                        .splineToSplineHeading(new Pose2d(scoreHubPosx, -scoreHubPosy, Math.toRadians(-40)), Math.toRadians(90))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(scoreHubPosx, -reposistionY +18))
                        .splineToSplineHeading(new Pose2d(repositionX+5, -reposistionY, Math.toRadians(0)), Math.toRadians(0))
                        .lineTo(new Vector2d(repositionX + distanceForwards, -reposistionY))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(repositionX+5, -reposistionY))
                        .splineToSplineHeading(new Pose2d(scoreHubPosx, -scoreHubPosy, Math.toRadians(-40)), Math.toRadians(90))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(scoreHubPosx, -reposistionY +18))
                        .splineToSplineHeading(new Pose2d(repositionX+5, -reposistionY, Math.toRadians(0)), Math.toRadians(0))
                        .lineTo(new Vector2d(repositionX + distanceForwards, -reposistionY))
                        .strafeLeft(strafeDistance)
                        .build()

                );





        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBotBlue)
                .addEntity(myBotRed)
                .start();
    }
}