package com.luismibm.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Task5 extends Application {
    public static class Persona {
        private String nombre;
        private int edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }
    }

    private ObservableList<Persona> personas = FXCollections.observableArrayList();
    private TableView<Persona> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        Button btnAgregar = new Button("Agregar Persona");

        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));

        TableColumn<Persona, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        tableView.getColumns().addAll(colNombre, colEdad);
        tableView.setItems(personas);

        btnAgregar.setOnAction(e -> mostrarVentanaAgregar());

        VBox root = new VBox(10, btnAgregar, tableView);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Gestión de Personas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaAgregar() {
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Agregar Persona");

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad");

        Button btnGuardar = new Button("Guardar");
        Label lblError = new Label();

        btnGuardar.setOnAction(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                int edad = Integer.parseInt(txtEdad.getText().trim());
                if (nombre.isEmpty()) {
                    lblError.setText("Error: El nombre no puede estar vacío.");
                    return;
                }
                personas.add(new Persona(nombre, edad));
                ventana.close();
            } catch (NumberFormatException ex) {
                lblError.setText("Error: Ingrese una edad válida.");
            }
        });

        VBox layout = new VBox(10, txtNombre, txtEdad, btnGuardar, lblError);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 300, 200);
        ventana.setScene(scene);
        ventana.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
