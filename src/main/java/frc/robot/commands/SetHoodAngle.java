package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class SetHoodAngle extends Command{
    
    private ShooterSubsystem m_shooter;

    private double m_angle;

    public SetHoodAngle(double angle, ShooterSubsystem shooter) {
        m_shooter = shooter;
        m_angle = angle;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_shooter.setHoodAngle(m_angle);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
