/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.CorreoException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class LoginController implements Initializable {
    ArrayList<Usuario> compradores;
    ArrayList<Usuario> vendedores;
    @FXML
    private Button loginbtn;
    @FXML
    private Button registerbtn;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contraseña;

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

    @FXML
    private void login(MouseEvent event) {
        try {
            Usuario comp = Usuario.recuperarUsuario(correo.getText(), "compradores.ser");
            Usuario vend = Usuario.recuperarUsuario(correo.getText(), "vendedores.ser");
            
            if (comp != null || vend != null) {
                
                if(!Usuario.validarUsuario(correo.getText(),contraseña.getText(), "compradores.ser") && !Usuario.validarUsuario(correo.getText(),contraseña.getText(), "vendedores.ser"))
                    throw new CorreoException("ERROR! Contraseña equivocada");
                else {
                    FXMLLoader fxmlloader;
                    try {
                        fxmlloader = App.loadFXMLLoader("menu");
                        App.setRoot(fxmlloader);
                        MenuController mc = fxmlloader.getController();
                        mc.setCorreo(correo.getText());

                    } catch (IOException ex) {
                        Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
                        a.show();
                    }
                }
               
            }else {
                throw new CorreoException("ERROR! Este correo no esta registrado");
            }

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (CorreoException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void register(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("register");
            App.setRoot(fxmlloader);

        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
            a.show();
        }
    }
    
}
