/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;

/*
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //System Device IDs

    //Drive Motors
    public static final int kMotor_DriveRearLeft = 0; //FalconFX - CAN
    public static final int kMotor_DriveFrontLeft = 1; //FalconFX - CAN
    public static final int kMotor_DriveFrontRight = 2; //FalconFX - CAN
    public static final int kMotor_DriveRearRight = 3; //FalconFX - CAN

    //Shooter Encoders
    public static final int kEncoder_ConveryorBottom = 0;
    public static final int kEncoder_converyorTop = 1;

    //Shooter Motors
    public static final int kMotor_ShooterTop = 0; //VictorSPX - CAN
    public static final int kMotor_ShooterBottom = 1; //VictorSPX - CAN
    public static final int kMotor_Conveyor = 2; //VictorSPX - CAN
    public static final int kMotor_Intake = 3; //VictorSPX - CAN

    //Shooter Sensors
    public static final int kSensor_ConveryorBottom = 0; //Bottom Distance Sensor
    public static final int kSensor_ConveryorTop = 1;

    //Color Wheel Manipulator
    public static final int kMotor_ColorWheel = 0; //VictorSPX - PWM

    //Color Wheel Encoder
    public static final int kEncoder_ColorWheel = 0;

    //Xbox Controller IDs
    public static final int kController_Driver = 0;
    public static final int kController_Operator = 1;

    //Xbox Controller Mappings
    public static final int kControllerMap_DriveY = XboxController.Axis.kLeftY.value;
    public static final int kControllerMap_DriveX = XboxController.Axis.kLeftX.value;
    public static final int kControllerMap_DriveLeft = XboxController.Axis.kLeftY.value;
    public static final int kControllerMap_DriveRight = XboxController.Axis.kRightY.value;
    public static final int kControllerMap_DriveDirectionChange = XboxController.Button.kA.value;
    public static final int kControllerMap_DriveShifter = XboxController.Button.kB.value;

    public static final int kControllerMap_OpFire = 0;
    public static final int kControllerMap_OpIntake = 0;
    public static final int kControllerMap_OpShootShifter = 0;
    public static final int kControllerMap_OpAutoShoot = 0;
    public static final int kControllerMap_OpStartColor = 0;
    public static final int kControllerMap_OpAutoColor = 0;
    public static final int kControllerMap_OpColorAxis = 0;

    //Network Tables
    //Targeting Table
    public static final String kNetworkTable_Targeting = "Targeting";
    public static final String kTableEntry_OnTarget = "On Target Detection";
    public static final String kTableEntry_TargetVisibility = "Target Visibility";
    public static final String kTableEntry_TargetDeltaX = "Target Delta X";
    public static final String kTableEntry_TargetDeltaY = "Target Delta Y";

    //Color Sensor Table
    public static final String kNetworkTable_ColorSensor = "Color Sensor";
    public static final String kTableEntry_DetectedColor = "Detected Color";
    public static final String kTableEntry_RawSensorRed = "Raw Sensor Data - Red";
    public static final String kTableEntry_RawSensorGreen = "Raw Sensor Data - Green";
    public static final String kTableEntry_RawSensorBlue = "Raw Sensor Data - Blue";

    //Mapping.  Unit: cm
    public static final int kMapping_FieldLength = 1598;
    public static final int kMapping_FieldHeight = 821;
    public static final int kMapping_Resolution = 50;
    public static final int kMapping_IntitationLine = 305;
    public static final int kMapping_PowerPortWall_Center = 240;
    public static final int kMapping_PowerPortWall_Width = 122;
    public static final int kMapping_PowerPortBottom_Width = 86;
    public static final int kMapping_PowerPortBottom_Height = 25;
    public static final int kMapping_PowerPortBottom_Center_FromGround = 59;
    public static final int kMapping_PowerPortOuter_Width = 87;
    public static final int kMapping_PowerPortOuter_Height = 75;
    public static final int kMapping_PowerPortOuter_Center_FromGround = 249;
    public static final int kMapping_PowerPortInnter_Diameter = 33;
    public static final int kMapping_Robot_Length = 25;
    public static final int kMapping_Robot_Height = 25;
}
