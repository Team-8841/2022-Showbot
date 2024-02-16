package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase{

    private static TalonSRX m_intakeMotor = new TalonSRX(IntakeConstants.kIntakeMotor);
    private static VictorSPX m_queueMotor1 = new VictorSPX(IntakeConstants.kIntakeQueue1);
    private static TalonSRX m_queueMotor2 = new TalonSRX(IntakeConstants.kIntakeQueue2);

    private final DigitalInput m_queueSensor1 = new DigitalInput(IntakeConstants.kQueue1SensorPort);
    private final DigitalInput m_queueSensor2 = new DigitalInput(IntakeConstants.kQueue2SensorPort);


    public IntakeSubsystem() {
        m_intakeMotor.setNeutralMode(NeutralMode.Brake);
        m_queueMotor1.setNeutralMode(NeutralMode.Brake);
        m_queueMotor2.setNeutralMode(NeutralMode.Brake);

        m_intakeMotor.setInverted(false);
        m_queueMotor1.setInverted(true);
        m_queueMotor2.setInverted(false);
    }

    @Override
    public void periodic() {
        updateStatus();
    }

    public void setIntake(double speed) {
        m_intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setQueue1(double speed) {
        m_queueMotor1.set(ControlMode.PercentOutput, speed);
    }

    public void setQueue2(double speed) {
        m_queueMotor2.set(ControlMode.PercentOutput, speed);
    }

    public boolean getQueue1Sensor() {
        return !m_queueSensor1.get();
    }

    public boolean getQueue2Sensor() {
        return !m_queueSensor2.get();
    }

    public void updateStatus() {
        SmartDashboard.putBoolean("[CH] Queue 1 Sensor", getQueue1Sensor());
        SmartDashboard.putBoolean("[CH] Queue 2 Sensor", getQueue2Sensor());
    }


    public void sensorControl(boolean intakeIn, boolean intakeOut) {
        double intakeSpeed = .5;
        double queue1Speed = .4;
        double queue2Speed = .3;

        if (intakeOut) {
            setIntake(-intakeSpeed);
            setQueue1(-queue1Speed);
            setQueue2(-queue2Speed);
        } else if (intakeIn) {
            // No cargo
            if (!getQueue1Sensor() & !getQueue2Sensor()) {
                setIntake(intakeSpeed);
                setQueue1(queue1Speed);
                setQueue2(queue2Speed);
            }
            // cargo in queue 1
            else if (getQueue1Sensor() & !getQueue2Sensor()) {
                setIntake(intakeSpeed);
                setQueue1(queue1Speed);
                setQueue2(queue2Speed);
            }
            // cargo in queue 2
            else if (!getQueue1Sensor() & getQueue2Sensor()) {
                setIntake(intakeSpeed);
                setQueue1(queue1Speed);
                setQueue2(0);
            }
            // cargo in queue 1 and 2
            else if (getQueue1Sensor() & getQueue2Sensor()) {
                setIntake(-intakeSpeed);
                setQueue1(0);
                setQueue2(0);
                // setIntakeSolenoid(false);
            }
        } else {
            setIntake(0);
            setQueue1(0);
            setQueue2(0);
        }
    }
}
