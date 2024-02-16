// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.SetHoodAngle;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  private final DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();


    m_IntakeSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_IntakeSubsystem.sensorControl(m_driverController.getRawButton(1), m_driverController.getRawButton(2));
    }, m_IntakeSubsystem));

    m_DriveSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_DriveSubsystem.driveRobot(m_driverController.getLeftY(), m_driverController.getLeftX());
    }, m_DriveSubsystem));
  }

  private void configureBindings() {
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    new JoystickButton(m_driverController, 3).onTrue(new SetHoodAngle(.35, m_ShooterSubsystem));

    new JoystickButton(m_driverController, 4).onTrue(new SetHoodAngle(.55, m_ShooterSubsystem));

    new JoystickButton(m_driverController, 5).whileTrue(new RunCommand(() ->{
      m_ShooterSubsystem.setShooter(.5);
    })).onFalse(new RunCommand(()->{
      m_ShooterSubsystem.setShooter(0);
    }));
    
    new JoystickButton(m_driverController, 6).whileTrue(new RunCommand(() ->{
      m_IntakeSubsystem.setQueue2(.6);
    }));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
