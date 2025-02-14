package org.firstinspires.ftc.RoboticsBootcamp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Bootcamp1 extends LinearOpMode {
    private DcMotor armMotor = null;
   
    @Override
    public void runOpMode() {
        
        // ------------ SET UP SECTION ------------
        armMotor = hardwareMap.dcMotor.get("armMotor");
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // ------------ MOVE ROBOT SECTION ------------

            if (gamepad1.right_bumper) {
                armMotor.setPower(1.0);
            }

            if (gamepad1.left_bumper) {
                armMotor.setPower(-1.0);
            }

            // ------------ TELEMETRY SECTION ------------
            telemetry.addData("Arm Power", armMotor.getPower());
            telemetry.addData("Arm Position", armMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}