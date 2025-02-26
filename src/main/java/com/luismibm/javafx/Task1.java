package com.luismibm.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Crear los componentes
        TextField txtNum1 = new TextField();
        txtNum1.setPromptText("Número 1");
        
        TextField txtNum2 = new TextField();
        txtNum2.setPromptText("Número 2");
        
        Button btnSumar = new Button("Sumar");
        TextField txtResultado = new TextField();
        txtResultado.setEditable(false); // No editable
        txtResultado.setPromptText("Resultado");
        
        Label lblError = new Label(); // Para mostrar mensajes de error
        
        // Acción del botón
        btnSumar.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(txtNum1.getText());
                double num2 = Double.parseDouble(txtNum2.getText());
                double suma = num1 + num2;
                txtResultado.setText(String.valueOf(suma));
                lblError.setText(""); // Limpiar error
            } catch (NumberFormatException ex) {
                lblError.setText("Error: Ingrese números válidos.");
                txtResultado.setText("");
            }
        });
        
        // Layout
        VBox root = new VBox(10, txtNum1, txtNum2, btnSumar, txtResultado, lblError);
        root.setPadding(new Insets(15));
        
        // Escena y Stage
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Suma en JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
