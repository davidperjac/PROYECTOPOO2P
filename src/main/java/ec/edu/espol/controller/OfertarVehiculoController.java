/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class OfertarVehiculoController implements Initializable {

    private String correo;
    private String contraseña;
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    
    
    @FXML
    private Button atrasbtn;
    @FXML
    private TableView tablaVehiculos;
    @FXML
    private ComboBox cbxtipo;
    @FXML
    private TextField minAnio;
    @FXML
    private TextField maxAnio;
    @FXML
    private TextField minRecorrido;
    @FXML
    private TextField maxRecorrido;
    @FXML
    private TextField minPrecio;
    @FXML
    private TextField maxPrecio;
    @FXML
    private Button buscarbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (Usuario.recuperarUsuarios("usuarios.ser") == null) {
            this.usuarios = new ArrayList<Usuario>();
        }else {
            this.usuarios = Usuario.recuperarUsuarios("usuarios.ser");
        }
        
        if (Vehiculo.recuperarVehiculos("vehiculos.ser") == null ) {
            this.vehiculos = new ArrayList<Vehiculo>();
        }else {
            this.vehiculos = Vehiculo.recuperarVehiculos("vehiculos.ser");
        } 
        
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("Moto");
        tipos.add("Carro");
        tipos.add("Camioneta");
        
        cbxtipo.setItems(FXCollections.observableArrayList(tipos));
    }    

    //tipo
    //recorrido
    //año
    //precio
    
    
    @FXML
    private void atras(MouseEvent event) {
        FXMLLoader fxmlloader; 
        try {
            fxmlloader = App.loadFXMLLoader("menu");
            App.setRoot(fxmlloader);
            MenuController mc = fxmlloader.getController();
            mc.setCorreo(correo,contraseña);
        } catch (IOException ex) {
            System.out.println(ex);
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    @FXML
    private void elegirTipo(ActionEvent event) {
    }

    @FXML
    private void buscarVehiculos(MouseEvent event) {
    }
    
    public void setCorreo(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
}
