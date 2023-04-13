package org.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.javafx.model.Tarea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

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
    private Label nTareas;
    @FXML
    private Button addTareaButton;
    @FXML
    private Button backToHomeButton;
    @FXML Button exitButton;
    @FXML
    private Pane navbar; // Referencia al nodo raíz de la escena

    private ObservableList<Tarea> tareas;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navbar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
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

    public void newTarea(){
        Tarea newTarea = new Tarea( 1, "Tarea", "aasdas", "Incompleto");
        this.tareas.add(newTarea);
        this.tabla.setItems(tareas);
        reloadNTarea();
    }

    @FXML
    private void backToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reloadNTarea(){
        nTareas.setText(Integer.toString(tareas.size()));
    }
}