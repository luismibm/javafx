package com.luismibm.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task4 extends Application {
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

    @Override
    public void start(Stage primaryStage) {
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        
        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad");
        
        Button btnAgregar = new Button("Agregar Persona");
        Button btnModificar = new Button("Modificar");
        Button btnEliminar = new Button("Eliminar");
        
        TableView<Persona> tableView = new TableView<>();
        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        
        TableColumn<Persona, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());
        
        tableView.getColumns().addAll(colNombre, colEdad);
        
        ObservableList<Persona> personas = FXCollections.observableArrayList();
        tableView.setItems(personas);
        
        Label lblError = new Label();
        
        btnAgregar.setOnAction(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                int edad = Integer.parseInt(txtEdad.getText().trim());
                if (nombre.isEmpty()) {
                    lblError.setText("Error: El nombre no puede estar vacío.");
                    return;
                }
                personas.add(new Persona(nombre, edad));
                txtNombre.clear();
                txtEdad.clear();
                lblError.setText("");
            } catch (NumberFormatException ex) {
                lblError.setText("Error: Ingrese una edad válida.");
            }
        });

        btnModificar.setOnAction(e -> {
            Persona personaSeleccionada = tableView.getSelectionModel().getSelectedItem();
            if (personaSeleccionada != null) {
                try {
                    String nuevoNombre = txtNombre.getText().trim();
                    int nuevaEdad = Integer.parseInt(txtEdad.getText().trim());
                    if (nuevoNombre.isEmpty()) {
                        lblError.setText("Error: El nombre no puede estar vacío.");
                        return;
                    }
                    personaSeleccionada.setNombre(nuevoNombre);
                    personaSeleccionada.setEdad(nuevaEdad);
                    tableView.refresh();
                    txtNombre.clear();
                    txtEdad.clear();
                    lblError.setText("");
                } catch (NumberFormatException ex) {
                    lblError.setText("Error: Ingrese una edad válida.");
                }
            } else {
                lblError.setText("Error: Seleccione una persona para modificar.");
            }
        });

        btnEliminar.setOnAction(e -> {
            Persona personaSeleccionada = tableView.getSelectionModel().getSelectedItem();
            if (personaSeleccionada != null) {
                personas.remove(personaSeleccionada);
                txtNombre.clear();
                txtEdad.clear();
                lblError.setText("");
            } else {
                lblError.setText("Error: Seleccione una persona para eliminar.");
            }
        });

        tableView.setOnMouseClicked(e -> {
            Persona personaSeleccionada = tableView.getSelectionModel().getSelectedItem();
            if (personaSeleccionada != null) {
                txtNombre.setText(personaSeleccionada.getNombre());
                txtEdad.setText(String.valueOf(personaSeleccionada.getEdad()));
            }
        });
        
        VBox root = new VBox(10, txtNombre, txtEdad, btnAgregar, btnModificar, btnEliminar, tableView, lblError);
        root.setPadding(new Insets(15));
        
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Gestión de Personas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
