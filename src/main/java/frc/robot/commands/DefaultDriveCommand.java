/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;


/*
 * The  default driving commands.  It constantly runs, gets input from the drivers xbox controller, and sets the motors
 */
public class DefaultDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_subsystem;
  private final DoubleSupplier m_leftaxis;
  private final DoubleSupplier m_rightaxis;


  public DefaultDriveCommand(DriveSubsystem subsystem, DoubleSupplier left_axis, DoubleSupplier right_axis) {
    m_subsystem = subsystem;
    m_leftaxis = left_axis;
    m_rightaxis = right_axis;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(m_leftaxis.getAsDouble(), m_rightaxis.getAsDouble());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}