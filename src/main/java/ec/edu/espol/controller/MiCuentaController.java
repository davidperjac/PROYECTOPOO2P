/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.ContraseñaException;
import ec.edu.espol.exceptions.CorreoException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.net.URL;
import ec.edu.espol.util.GFG;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class MiCuentaController implements Initializable {
    private Usuario cuenta;
    private String contraseña;
    private ComboBox cbxrol;
    private ArrayList<Usuario> usuarios;
    private Text mostrarClave;
    
    @FXML
    private VBox info;
    @FXML
    private Button atrasbtn;
    @FXML
    private HBox menuRol;
    @FXML
    private VBox menuContraseña;
    @FXML
    private Button btncontrasena;
    @FXML
    private Button btnrol;

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
    }

    public void setCuenta (String correo, String contraseña) {
        this.cuenta = Usuario.recuperarUsuario(correo, "usuarios.ser");
        this.contraseña = contraseña;
        
        Text nombres = new Text("Nombres : "+this.cuenta.getNombres().toString());
        Text apellidos = new Text("Apellidos : "+cuenta.getApellidos());
        Text correotxt = new Text ("Correo : "+cuenta.getCorreo());      
        this.mostrarClave = new Text ("Contraseña : "+contraseña);
        Text org = new Text ("Organizacion : "+cuenta.getOrganizacion());
        
        nombres.setFont(Font.font ("Futura", 16));
        apellidos.setFont(Font.font ("Futura", 16));
        correotxt.setFont(Font.font ("Futura", 16));
        mostrarClave.setFont(Font.font ("Futura", 16));
        org.setFont(Font.font ("Futura", 16));

        info.getChildren().add(nombres);
        info.getChildren().add(apellidos);
        info.getChildren().add(correotxt);
        info.getChildren().add(mostrarClave);
        info.getChildren().add(org);

        info.setAlignment(Pos.TOP_CENTER);
        info.setSpacing(15);
}

    @FXML
    private void atras(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("menu");
            App.setRoot(fxmlloader);
            MenuController mc = fxmlloader.getController();
            mc.setCorreo(cuenta.getCorreo(),contraseña);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    @FXML
    private void mostrarMenuContrasena(MouseEvent event) {
        menuContraseña.getChildren().clear();
        menuContraseña.setAlignment(Pos.BOTTOM_CENTER);
        menuContraseña.setSpacing(10);
       
        HBox viejaClave = new HBox();
        Text viejaClavetxt = new Text("Ingrese su contraseña actual");
        viejaClavetxt.setFont(Font.font ("Futura", 14));
        
        PasswordField viejaClavefield = new PasswordField();
        viejaClavefield.setFont(Font.font("verdana", 11));
        
        viejaClavefield.setMinWidth(150);
       
        viejaClave.getChildren().add(viejaClavetxt);
        viejaClave.getChildren().add(viejaClavefield);
        
        viejaClave.setSpacing(10);
       
        menuContraseña.getChildren().add(viejaClave);
        
        HBox nuevaClave = new HBox();
        Text nuevaClavetxt = new Text("Ingrese su nueva contraseña");
        nuevaClavetxt.setFont(Font.font ("Futura", 14));

        
        PasswordField nuevaClavefield = new PasswordField();
        nuevaClavefield.setFont(Font.font("verdana", 11));
        
        nuevaClavefield.setMinWidth(150);
       
        nuevaClave.getChildren().add(nuevaClavetxt);
        nuevaClave.getChildren().add(nuevaClavefield);
        
        nuevaClave.setSpacing(10);
       
        menuContraseña.getChildren().add(nuevaClave);
        
        HBox confnuevaClave = new HBox();
        Text confnuevaClavetxt = new Text("Confirme su nueva contraseña");
        confnuevaClavetxt.setFont(Font.font ("Futura", 14));

        
        PasswordField confnuevaClavefield = new PasswordField();
        confnuevaClavefield.setFont(Font.font("verdana", 11));
        
        confnuevaClavefield.setMinWidth(150);
       
        confnuevaClave.getChildren().add(confnuevaClavetxt);
        confnuevaClave.getChildren().add(confnuevaClavefield);
        
        confnuevaClave.setSpacing(10);
       
        menuContraseña.getChildren().add(confnuevaClave);
        
        
        Text confirmarbtn = new Text ("CONFIRMAR");
        confirmarbtn.setFont(Font.font ("Futura", 14));
        Button confirmar = new Button(confirmarbtn.getText());
        
        confirmar.setOnMouseClicked((MouseEvent e)->{
            try {
                if (viejaClavefield.getText().equals("") || nuevaClavefield.getText().equals("") || confnuevaClavefield.getText().equals("")) {
                    throw new ContraseñaException("ERROR! LLene todos los campos obligatorios");
                }else if(!Usuario.validarUsuario(cuenta.getCorreo(),viejaClavefield.getText(), "usuarios.ser")){
                    throw new ContraseñaException("ERROR! Contraseña equivocada");
                }else if (!nuevaClavefield.getText().equals(confnuevaClavefield.getText())) {
                    throw new ContraseñaException("ERROR! Las contraseñas no son iguales");
                }
               
                mostrarClave.setText("Contraseña : "+nuevaClavefield.getText());
                mostrarClave.setFont(Font.font ("Futura", 16));
                
                this.contraseña = nuevaClavefield.getText();
                
                for (Usuario u: usuarios) {
                    if (u.getCorreo().equals(cuenta.getCorreo())){
                        u.setClave(GFG.toHexString(GFG.getSHA(nuevaClavefield.getText())));
                    }
                }
                Usuario.guardarUsuarios("usuarios.ser", usuarios);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Se ha realizado el cambio exitosamente");
                a.show();
                
            }catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }catch (ContraseñaException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,ex.getMessage());
                a.show();
            }});
        
        menuContraseña.getChildren().add(confirmar);
        
        //HBox nuevaClave = new HBox();
        
    }

    @FXML
    private void mostrarMenuRol(MouseEvent event) {
        this.cbxrol = new ComboBox();
        
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Vendedor");
        roles.add("Comprador");
        roles.add("Ambos");
        
        cbxrol.setItems(FXCollections.observableArrayList(roles));
        //cbxrol.setValue(cuenta.getRol());
       
        //comboBox.getSelectionModel().selectFirst();
        
        menuRol.getChildren().clear();
        menuRol.getChildren().add(cbxrol);
        menuRol.setAlignment(Pos.CENTER); 
        menuRol.setSpacing(20);
        
        Text confirmarbtn = new Text ("CONFIRMAR");
        confirmarbtn.setFont(Font.font ("Futura", 14));
        Button confirmar = new Button(confirmarbtn.getText());
        
        confirmar.setOnMouseClicked((MouseEvent e)->{
            String item = (String)cbxrol.getValue();
            try {
                if (item == null) {
                    throw new InputMismatchException("ERROR! Ingrese un campo valido");
                }else if (item.equals(cuenta.getRol())) {
                    throw new InputMismatchException("ERROR! Escoja un rol diferente");
                }
                
                for (Usuario u: usuarios) {
                    if (u.getCorreo().equals(cuenta.getCorreo())){
                        u.setRol(item);
                    }
                }
                Usuario.guardarUsuarios("usuarios.ser", usuarios);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Se ha realizado el cambio exitosamente");
                a.show();
            }catch (InputMismatchException in) {
                Alert a = new Alert(Alert.AlertType.ERROR,in.getMessage());
                a.show();
        }});
        
        
        menuRol.getChildren().add(confirmar);
    }

    
}
