package com.luismibm.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task2 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create components
        TextField txtNum1 = new TextField();
        txtNum1.setPromptText("Number 1");

        TextField txtNum2 = new TextField();
        txtNum2.setPromptText("Number 2");

        ToggleGroup operationGroup = new ToggleGroup();
        RadioButton rbSum = new RadioButton("Sum");
        rbSum.setToggleGroup(operationGroup);
        rbSum.setSelected(true);
        RadioButton rbSubtract = new RadioButton("Subtract");
        rbSubtract.setToggleGroup(operationGroup);
        RadioButton rbMultiply = new RadioButton("Multiply");
        rbMultiply.setToggleGroup(operationGroup);
        RadioButton rbDivide = new RadioButton("Divide");
        rbDivide.setToggleGroup(operationGroup);

        Button btnCalculate = new Button("Calculate");
        TextField txtResult = new TextField();
        txtResult.setEditable(false);
        txtResult.setPromptText("Result");

        Label lblError = new Label(); // For displaying error messages

        // Button action
        btnCalculate.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(txtNum1.getText());
                double num2 = Double.parseDouble(txtNum2.getText());
                double result = 0;

                if (rbSum.isSelected()) {
                    result = num1 + num2;
                } else if (rbSubtract.isSelected()) {
                    result = num1 - num2;
                } else if (rbMultiply.isSelected()) {
                    result = num1 * num2;
                } else if (rbDivide.isSelected()) {
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        lblError.setText("Error: Division by zero.");
                        txtResult.setText("");
                        return;
                    }
                }

                txtResult.setText(String.valueOf(result));
                lblError.setText(""); // Clear error
            } catch (NumberFormatException ex) {
                lblError.setText("Error: Enter valid numbers.");
                txtResult.setText("");
            }
        });

        // Layout
        VBox root = new VBox(10, txtNum1, txtNum2, rbSum, rbSubtract, rbMultiply, rbDivide, btnCalculate, txtResult, lblError);
        root.setPadding(new Insets(15));

        // Scene and Stage
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Calculator in JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}