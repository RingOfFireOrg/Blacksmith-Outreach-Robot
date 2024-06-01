package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;


public class Motors {
    
    VictorSP leftFrontDrive;
    VictorSP leftBackDrive;
    VictorSP rightFrontDrive;
    VictorSP rightBackDrive;

    VictorSPX intake;
    TalonSRX transferUp;
    VictorSPX transferSide;
    
    TalonFX shooter;
    XboxController Operator;
    XboxController Drive;
    AddressableLED m_led;
    AddressableLEDBuffer m_ledBuffer;
    

    

    public Motors() {
        leftFrontDrive = new VictorSP(0);
        leftBackDrive = new VictorSP(1);
        rightFrontDrive = new VictorSP(2);
        rightBackDrive = new VictorSP(3);
        leftFrontDrive.addFollower(leftBackDrive);
        rightFrontDrive.addFollower(rightBackDrive);
        Operator = new XboxController(1);
        Drive = new XboxController(0);

        intake = new VictorSPX(1);
        transferUp = new TalonSRX(2);
        transferSide =  new VictorSPX(3);
        shooter = new TalonFX(4);

        
        m_led = new AddressableLED(7);
        m_ledBuffer = new AddressableLEDBuffer(95);
        //m_led.setLength(m_ledBuffer.getLength());
        m_led.setLength(m_ledBuffer.getLength());
        for(var i = 0; i < m_ledBuffer.getLength(); i++){
            m_ledBuffer.setRGB(i,255,0, 0);
        }
        
//        m_led.setData(m_ledBuffer);
 //       m_led.start();

        /*for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 255, 0, 0);
         }
         m_led.setData(m_ledBuffer);
         */
         

    }

    public void Teleop(){

        m_led.setData(m_ledBuffer);
        m_led.start();

        leftFrontDrive.set(Drive.getRightY()*
        0.3);
        rightFrontDrive.set(Drive.getLeftY()*-0.3);
        shooter.set(Operator.getRawAxis(3)*0.3);
        if (Operator.getAButton()) {
            intake.set(ControlMode.PercentOutput, 0.9);
        } else if (Operator.getBButton()) {
            intake.set(ControlMode.PercentOutput, -0.8);
        } else {
            intake.set(ControlMode.PercentOutput, 0);
        }
        /*if (Operator.povUp()) {
            transferSide.set(ControlMode.PercentOutput, 0.5);
            transferUp.set(ControlMode.PercentOutput, 0.5);
        }else if (Operator.getBButton()) {
            transferSide.set(ControlMode.PercentOutput, -0.5);
            transferUp.set(ControlMode.PercentOutput, -0.5);
        }else {


        }*/
  
        transferSide.set(ControlMode.PercentOutput, Operator.getLeftY()*0.5);
        transferUp.set(ControlMode.PercentOutput, Operator.getLeftY());
        }}

