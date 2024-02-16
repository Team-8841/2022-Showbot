package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase{

    private final TalonSRX m_shooter = new TalonSRX(ShooterConstants.kShooterMainMotor);
    private final TalonSRX m_follower = new TalonSRX(ShooterConstants.kShooterFollowerMotor);

    private Servo m_hoodServo1 = new Servo(ShooterConstants.kHoodServo1Port);
    private Servo m_hoodServo2 = new Servo(ShooterConstants.kHoodServo2Port);
    
    public ShooterSubsystem(){
        m_shooter.setNeutralMode(NeutralMode.Coast);
        m_follower.setNeutralMode(NeutralMode.Coast);

        m_shooter.setInverted(false);
        m_follower.setInverted(true);

        m_follower.follow(m_shooter);

        updateStatus();
    }

    public void setHoodAngle(double angle){
        m_hoodServo1.set(angle);
        m_hoodServo2.set(angle);
    }

    public void setShooter(double speed){
        m_shooter.set(ControlMode.PercentOutput, speed);
    }

    public double getHoodAngle1(){
        return m_hoodServo1.getPosition();
    }

    public double getHoodAngle2() {
        return m_hoodServo2.getPosition();
    }

    public void updateStatus(){
        SmartDashboard.putNumber("[Shooter]: Hood Server 1 Angle", getHoodAngle1());
        SmartDashboard.putNumber("[Shooter]: Hood Server 2 Angle", getHoodAngle2());
    }
}