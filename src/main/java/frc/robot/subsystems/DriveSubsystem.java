package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase{

    private final WPI_VictorSPX leftDrive = new WPI_VictorSPX(DriveConstants.kLeftMotor);
    private final WPI_TalonSRX rightDrive = new WPI_TalonSRX(DriveConstants.kRightMotor);

    private final DifferentialDrive m_drive = new DifferentialDrive(leftDrive, rightDrive);


    public DriveSubsystem() {
        leftDrive.setNeutralMode(NeutralMode.Coast);
        rightDrive.setNeutralMode(NeutralMode.Coast);

        leftDrive.setInverted(true);
        rightDrive.setInverted(false);

        m_drive.setMaxOutput(DriveConstants.maxSpeed);
    }


    public void driveRobot(double forward, double right) {
        m_drive.arcadeDrive(forward, right);
    }


    
}
