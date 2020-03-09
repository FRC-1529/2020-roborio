/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;

public class ShootingSubsystem extends SubsystemBase {
  //Motors
  private final VictorSPX flywheel_bottom_motor = new VictorSPX(0);
  private final VictorSPX flywheel_top_motor = new VictorSPX(1);
  private final VictorSPX conveyor_motor = new VictorSPX(2);
  private final VictorSPX intake_motor = new VictorSPX(3);

  //Sensors
  private final DigitalInput intake_ball_sensor = new DigitalInput(0);
  private final DigitalInput flywheel_ball_sensor = new DigitalInput(1);


  //Operating Variables
  private double intake_power = .5;
  private double conveyor_power = .5;

  private int flywheel_power = 0;
  private double[] flywheel_power_steps = { 0, .7, .75, .8, .85, .9, .95, 1 }; //The possible power states of the flywheels

  //Subsystem Constructor
  public ShootingSubsystem() {}

  //Intake Commands
  public void poweroffIntake() {
    intake_motor.set(ControlMode.PercentOutput, 0);
  }
  public void poweronIntake() {
    intake_motor.set(ControlMode.PercentOutput, intake_power);
  }
  public void toggleIntake() {
    double current_power = intake_motor.getMotorOutputPercent();
    if (current_power > 0) {
      //If its currently moving
      poweroffIntake();
    } else {
      poweronIntake();
    }
  }

  //Conveyor Commands
  public void poweroffConveyor() {
    conveyor_motor.set(ControlMode.PercentOutput, 0);
  }
  public void poweronConveyor() {
    conveyor_motor.set(ControlMode.PercentOutput, conveyor_power);
  }
  public void toggleConveyor() {
    double current_power = conveyor_motor.getMotorOutputPercent();
    if (current_power > 0) {
      //If its currently moving
      poweroffConveyor();
    } else {
      poweronConveyor();
    }
  }

  //Flywheel Commands
  public void adjustFlywheels(boolean direction) {
    if (direction) {
      //If we want to adjust the power up;
      if (flywheel_power < flywheel_power_steps.length) {
        //If there is a power step above the one we are one
        flywheel_power += 1; //Increment it by one;
      }
    } else {
      //If we want to adjust the power down;
      if (flywheel_power > 0) {
        //If there is a power step below the one we are one;
        flywheel_power -= 1; //Decrement it by one;
      }
    }

    //Now set the new power for the motors
    flywheel_bottom_motor.set(ControlMode.PercentOutput, flywheel_power_steps[flywheel_power]);
    flywheel_top_motor.set(ControlMode.PercentOutput, flywheel_power_steps[flywheel_power]);
  }

  //Compound Commands
  public void intakePowercell() {
    //First turn on the intake
    poweronIntake();
    while (!intake_ball_sensor.get()) {
      //While we cant see the ball
      //Do nothing
    }
    poweronConveyor();
    while (intake_ball_sensor.get() || !flywheel_ball_sensor.get()) {
      //While we can see the ball OR until we hit the top
      //Do nothing
    }
    poweroffConveyor();
    poweroffIntake();
  }

  @Override
  public void periodic() {}
}
