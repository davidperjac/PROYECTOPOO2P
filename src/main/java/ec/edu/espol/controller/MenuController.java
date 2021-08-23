/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class MenuController implements Initializable {
    ArrayList<Usuario> compradores;
    ArrayList<Usuario> vendedores;
    private String correo;
    @FXML
    private Button cerrarsesionbtn;
    @FXML
    private Button micuentabtn;
    @FXML
    private Text lblbienvenida;
    @FXML
    private VBox botonesvbx;
    @FXML
    private Button menubtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (Usuario.recuperarUsuarios("compradores.ser") == null) {
            this.compradores = new ArrayList<Usuario>();
        }else {
            this.compradores = Usuario.recuperarUsuarios("compradores.ser");
        }
        
        if (Usuario.recuperarUsuarios("vendedores.ser") == null) {
            this.vendedores = new ArrayList<Usuario>();
        }else {
            this.vendedores = Usuario.recuperarUsuarios("vendedores.ser");
        }        
    }    

    
    public void setCorreo(String correo) {
        this.correo = correo;
        lblbienvenida.setText("Bienvenido "+correo);
    }
   
    @FXML
    private void cerrarSesion(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("login");
            App.setRoot(fxmlloader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    @FXML
    private void configurarCuenta(MouseEvent event) {
    }

    @FXML
    private void abrirMenu(MouseEvent event) {
        botonesvbx.getChildren().clear();
        botonesvbx.setAlignment(Pos.CENTER);
        botonesvbx.setSpacing(15);      
        
        for (Usuario v : this.vendedores) {
            if (v.getCorreo().equals(correo)) {

                //registrar un vehiculo
                //aceptar ofertas 
                Button registrarbtn = new Button("Registrar Vehiculo");
                Button aceptarOfbtn = new Button("Aceptar Ofertas");
                
                botonesvbx.getChildren().add(registrarbtn);
                botonesvbx.getChildren().add(aceptarOfbtn);
                
            }
        }
        for (Usuario c : this.compradores) {
            if (c.getCorreo().equals(correo)) {

                //oferta por un vehiculo
                Button ofertarbtn = new Button("Ofertar por un vehiculo");
                botonesvbx.getChildren().add(ofertarbtn);
            }
        }
    }
    
}
