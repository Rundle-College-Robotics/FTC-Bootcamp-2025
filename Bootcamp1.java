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
        armMotor = hardwareMap.dcMotor.get("armMotor");
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        setUpTeleOp();

        while (opModeIsActive()) {

            moveArm();

            // Update telemetry
            telemetry.addData("Arm Power", armMotor.getPower());
            telemetry.addData("Arm Position", armMotor.getCurrentPosition());
            telemetry.update();
        }
    }


    public void moveArm(){

        if (gamepad2.right_bumper) {
            isExtendMove = false;
        }
        if (gamepad2.left_bumper) {
            isExtendMove = true;
        }
        // Arm control
        /*
       
       
        */
        if (gamepad2.right_stick_y != 0) {
            armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            shouldLockArm = true;
            if (gamepad2.right_stick_y < 0) {
                // Move down
                if (isExtendMove) {
                    armMotor.setPower(gamepad2.right_stick_y);
                } else {
                    armMotor.setPower(gamepad2.right_stick_y / 5);
                }
            } else {
                // Move up
                if (armMotor.getCurrentPosition() < 1900) {
                    armMotor.setPower(gamepad2.right_stick_y / 2);
                } else {
                    if (isExtendMove) {
                        armMotor.setPower(gamepad2.right_stick_y / 1.7);
                    } else {
                        // Safety, do not go too far
                        armMotor.setPower(0.0);
                    }
                }
            }
        } else {
            if (shouldLockArm) {
                shouldLockArm = false;
                holdArmPositition = armMotor.getCurrentPosition();
            }
            armMotor.setTargetPosition(holdArmPositition);
            armMotor.setPower(0.6);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}