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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AceptarOfertaController implements Initializable {
    private String correo;
    private String contraseña;
    private ArrayList<Usuario> usuarios;

    @FXML
    private ComboBox cbxPlacas;
    @FXML
    private FlowPane flowOfertas;
    @FXML
    private Button btnatras;
   

    /**
     * Initializes the controller class.
     */
    
    
    public void setCorreo(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          if (Usuario.recuperarUsuarios("usuarios.ser") == null) {
            this.usuarios = new ArrayList<Usuario>();
        }else {
            this.usuarios = Usuario.recuperarUsuarios("usuarios.ser");
        }
        // TODO
        for(int i= 0; i<5; i++){
            TextField txt = new TextField();
            txt.setText("hola");
            
            
            
        }
    }    

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

        
        
        
    }
    

