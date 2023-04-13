package org.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.javafx.model.Tarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private ObservableList<Tarea> tareas;
    @FXML
    private Button botonSalir;
    @FXML
    private Button addTarea;
    @FXML
    private Button goHome;

    @FXML
    private Button modoOscuro;
    @FXML
    private TableView<Tarea> tabla;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDesc;
    @FXML
    private TableColumn colEst;

    @FXML
    private TextField textID;
    @FXML
    private TextField textNombre;

    @FXML
    private TextField textDescripcion;

    @FXML
    private TextField textEstado;
    @FXML
    private Label nTareas;



    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }


    public void goHome(ActionEvent actionEvent) throws IOException {
        App.setRoot("home");
    }


    public void modoOscuro(ActionEvent actionEvent){
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Agregar controlador de eventos onMousePressed al nodo raíz
        navbar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Agregar controlador de eventos onMouseDragged al nodo raíz
        navbar.setOnMouseDragged(event -> {
            Stage stage = (Stage) navbar.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        tareas = FXCollections.observableArrayList();
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colDesc.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.colEst.setCellValueFactory(new PropertyValueFactory("estado"));
        reloadNTarea();
    }
    @FXML
    public void newTarea(){
        Tarea newTarea = new Tarea(Integer.parseInt(textID.getText()),textNombre.getText() , textDescripcion.getText(), textEstado.getText());
        this.tareas.add(newTarea);
        this.tabla.setItems(tareas);
        reloadNTarea();
    }

    @FXML
    private Pane navbar; // Referencia al nodo raíz de la escena

    private double xOffset = 0;
    private double yOffset = 0;

    // Método de inicialización que se llama después de cargar la vista
    @FXML
    public void reloadNTarea(){
        nTareas.setText(Integer.toString(tareas.size()));
    }

}
