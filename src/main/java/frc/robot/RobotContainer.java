/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveShiftCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;

import java.util.function.DoubleSupplier;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Lets define our inputs
  private final XboxController m_driveController = new XboxController(Constants.kController_Driver);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final DriveCommand m_driveCommand;
  private final DriveShiftCommand m_driveShiftCommand = new DriveShiftCommand(m_driveSubsystem);

  private final AnalogInput m_distance = new AnalogInput(5);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    DoubleSupplier ds_leftaxis = () -> m_driveController.getRawAxis(Constants.kControllerMap_DriveLeft);
    DoubleSupplier ds_rightaxis = () -> m_driveController.getRawAxis(Constants.kControllerMap_DriveRight);
    m_driveCommand = new DriveCommand(m_driveSubsystem, ds_leftaxis, ds_rightaxis);
    // Configure the button bindings
    double volt = m_distance.getVoltage();
    System.out.println(volt);
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton directionButton = new JoystickButton(m_driveController, Constants.kControllerMap_DriveDirectionChange);
    directionButton.whenPressed(m_driveShiftCommand);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
    
  // }
}
