/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DriveShiftUpCommand;
import frc.robot.commands.DriveShiftDownCommand;
import frc.robot.commands.DriveSwitchDirectionsCommand;

import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.commands.FlywheelIncrementCommand;
import frc.robot.commands.FlywheelDecrementCommand;
import frc.robot.commands.IntakeOnCommand;
import frc.robot.commands.IntakeOffCommand;
import frc.robot.commands.ConveyorOnCommand;
import frc.robot.commands.ConveyorOffCommand;

import frc.robot.Constants;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Lets define our inputs
  private final XboxController drive_controller = new XboxController(0);
  private final XboxController ops_controller = new XboxController(1);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem drive_subsystem = new DriveSubsystem();
  private DefaultDriveCommand default_drive_command;
  private DriveShiftUpCommand drive_shift_up_command = new DriveShiftUpCommand(drive_subsystem);
  private DriveShiftDownCommand drive_shift_down_command = new DriveShiftDownCommand(drive_subsystem);
  private DriveSwitchDirectionsCommand drive_switch_directions_command = new DriveSwitchDirectionsCommand(drive_subsystem);

  private final ShootingSubsystem shooting_subsystem = new ShootingSubsystem();
  private FlywheelIncrementCommand flywheel_increment_command = new FlywheelIncrementCommand(shooting_subsystem);
  private FlywheelDecrementCommand flywheel_decrement_command = new FlywheelDecrementCommand(shooting_subsystem);
  private IntakeOnCommand intake_on_command = new IntakeOnCommand(shooting_subsystem);
  private IntakeOffCommand intake_off_command = new IntakeOffCommand(shooting_subsystem);
  private ConveyorOnCommand conveyor_on_command = new ConveyorOnCommand(shooting_subsystem);
  private ConveyorOffCommand conveyor_off_command = new ConveyorOffCommand(shooting_subsystem);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Lets get this bad boy driving
    DoubleSupplier ds_leftaxis = () -> drive_controller.getRawAxis(XboxController.Axis.kLeftY.value);
    DoubleSupplier ds_rightaxis = () -> drive_controller.getRawAxis(XboxController.Axis.kRightY.value);
    default_drive_command = new DefaultDriveCommand(drive_subsystem, ds_leftaxis, ds_rightaxis);
    drive_subsystem.setDefaultCommand(default_drive_command);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton btn_direction_change = new JoystickButton(drive_controller, XboxController.Button.kStickLeft.value);
    btn_direction_change.whenPressed(drive_switch_directions_command);
    POVButton btn_shift_up = new POVButton(drive_controller, 0);
    btn_shift_up.whenPressed(drive_shift_up_command);
    POVButton btn_shift_down = new POVButton(drive_controller, 180);
    btn_shift_down.whenPressed(drive_shift_down_command);

    POVButton btn_flywheel_increment = new POVButton(ops_controller, 0);
    btn_flywheel_increment.whenPressed(flywheel_increment_command);
    POVButton btn_flywheel_decrement = new POVButton(ops_controller, 180);
    btn_flywheel_decrement.whenPressed(flywheel_decrement_command);
    JoystickButton btn_intake = new JoystickButton(ops_controller, XboxController.Button.kBumperLeft.value);
    btn_intake.whenPressed(intake_on_command).whenReleased(intake_off_command);
    JoystickButton btn_conveyor = new JoystickButton(ops_controller, XboxController.Button.kBumperRight.value);
    btn_conveyor.whenPressed(conveyor_on_command).whenReleased(conveyor_off_command);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
    
  // }
}
