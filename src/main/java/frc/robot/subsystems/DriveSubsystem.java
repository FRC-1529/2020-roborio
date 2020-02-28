/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.lang.Math;

public class DriveSubsystem extends SubsystemBase {
  // Lets define our resources!
  public int direction = 1; // 1 = Intake, -1 = Shooter
  public double power_step = 0.05; //How fast the motors should get up to power
  public double target_power_left = 0; //The current power target for the left motors
  public double target_power_right = 0; //The current power target for the right motors
  public final TalonFX motor_intake_left = new TalonFX(Constants.kMotor_DriveRearLeft);
  public final TalonFX motor_shooter_left = new TalonFX(Constants.kMotor_DriveFrontLeft);
  public final TalonFX motor_shooter_right = new TalonFX(Constants.kMotor_DriveFrontRight);
  public final TalonFX motor_intake_right = new TalonFX(Constants.kMotor_DriveRearRight);
  //Set two motors to be the masters
  public final TalonFX motors_left = motor_intake_left;
  public final TalonFX motors_right = motor_intake_right;

  //Shifter variables
  public boolean gear_shift = false; //Is shifted into 2nd gear
  public final Solenoid shifter = new Solenoid(1, 0);

  //Motor control fuctions
  public void stepMotor(TalonFX motor, double target) {
     //Check if the motors match the target, then adjust them.
     double power = motor.getMotorOutputPercent();
     double diff = target - power;
     //Check to see if there is something to do.
     if (diff != 0) {
       //If the difference is less than the step, just set the boi.
       if (Math.abs(diff) <= power_step) motor.set(ControlMode.PercentOutput, target);
       else {
         //Calcualate new power to target.
         double new_power = power+Math.copySign(power_step, diff*-1);
         motor.set(ControlMode.PercentOutput, new_power);
       }
     }
  };

  //Tank drive command for Parker.
  public void driveTank(double left_percent, double right_percent) {
    target_power_left = left_percent*direction*-1;
    target_power_right = right_percent*direction;
  };

  //Autonomous Driving Commands
  public void switchDirection() {
    //Change the direction of controlled inputs;
    direction = direction*-1;
  };

  public void shift() {
    //Shift gears
    shifter.set(gear_shift);
    gear_shift = !gear_shift;
    if (gear_shift) {
      System.out.println("Shifted into 2nd gear!");
    } else {
      System.out.println("Shifted down to 1st gear!");
    }
  }
  
  public DriveSubsystem() {
    //Assing our slave motors to follow the commands of the masters
    motor_shooter_left.follow(motors_left);
    motor_shooter_right.follow(motors_right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Tell our motors to adjust their power output to step towards the targets.
    stepMotor(motors_left, target_power_left);
    stepMotor(motors_right, target_power_left);
  }
}
