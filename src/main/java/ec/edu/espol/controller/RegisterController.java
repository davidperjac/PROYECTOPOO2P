/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.ContraseñaException;
import ec.edu.espol.exceptions.CorreoException;
import ec.edu.espol.model.Usuario;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class RegisterController implements Initializable {
    ArrayList<Usuario> compradores;
    ArrayList<Usuario> vendedores;
    String rol;
    @FXML
    private Button registratebtn;
    @FXML
    private TextField correo;
    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField org;
    @FXML
    private PasswordField conf_contra;
    @FXML
    private PasswordField contraseña;
    @FXML
    private ComboBox rolcbx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.compradores = Usuario.recuperarUsuarios("compradores.txt");
        this.vendedores = Usuario.recuperarUsuarios("vendedores.txt");
        
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Vendedor");
        roles.add("Comprador");
        roles.add("Ambos");
        
        rolcbx.setItems(FXCollections.observableArrayList(roles));

    }    
    // String item = (String)cbox.getValue();
    @FXML
    private void registrar(MouseEvent event) {
        try {
            if (!contraseña.getText().equals(conf_contra.getText())) {
                throw new ContraseñaException("ERROR! Las contraseñas no son iguales");
            }
            if(rol.equals("Vendedor")){
                Usuario u= Usuario.nextUsuario("vendedores.txt",correo.getText(),contraseña.getText(), nombres.getText(), apellidos.getText(), org.getText());
            
            }else if(rol.equals("Comprador")){
                Usuario u= Usuario.nextUsuario("compradores.txt",correo.getText(),contraseña.getText(), nombres.getText(), apellidos.getText(), org.getText());
            
            }else if (rol.equals("Ambos")) {
                
            }
            
            
        }catch (ContraseñaException ce) {
            Alert a = new Alert(Alert.AlertType.ERROR,ce.getMessage());
            a.show();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (CorreoException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,ex.getMessage());
            a.show();
        } catch (InputMismatchException in) {
            Alert a = new Alert(Alert.AlertType.ERROR,"ERROR! Escriba datos correctos");
            a.show();
        }
    }

    @FXML
    private void escogerRol(ActionEvent event) {
        this.rol = (String)rolcbx.getValue();
    }
    
}
