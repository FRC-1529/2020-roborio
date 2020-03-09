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
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.lang.Math;

public class DriveSubsystem extends SubsystemBase {
  //Motors
  private final TalonFX intake_left_motor = new TalonFX(0);
  private final TalonFX flywheel_left_motor = new TalonFX(1);
  private final TalonFX flywheel_right_motor = new TalonFX(2);
  private final TalonFX intake_right_motor = new TalonFX(3);
  private final TalonFX left_motors = intake_left_motor; //Master motor on left
  private final TalonFX right_motors = intake_right_motor; //Master motor on right

  //Gear shifter
  private final Solenoid shifter_solenoid = new Solenoid(1, 0);

  //Operational Variables
  private int direction = 1; // 1 = forward, -1 = backwards;


  //Subsystem Constructor
  public DriveSubsystem() {
    //Assign our flywheel motors to follow the intake motors
    flywheel_left_motor.follow(left_motors);
    flywheel_right_motor.follow(right_motors);
  }

  //Driving Commands
  public void drive(double left_power_target, double right_power_target) {
    left_motors.set(ControlMode.PercentOutput, left_power_target*direction);
    right_motors.set(ControlMode.PercentOutput, right_power_target*direction);
  }

  public void switchDirection() {
    direction *= -1; //Multiply direction by -1, changing the direction.
  }

  //Shifting Commands
  public void shiftDown() {
    shifter_solenoid.set(false);
  }

  public void shiftUp() {
    shifter_solenoid.set(true);
  }
}
