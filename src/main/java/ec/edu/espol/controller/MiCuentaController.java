/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.net.URL;
import ec.edu.espol.util.GFG;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    @FXML
    private VBox info;
    @FXML
    private Text nombrestxt;
    @FXML
    private Text apellidostxt;
    @FXML
    private Text correotxt;
    @FXML
    private Text clavetxt;
    @FXML
    private Button atrasbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        if (cuenta != null) {
            Text nombres = new Text("Nombres : "+this.cuenta.getNombres().toString());
            Text apellidos = new Text("Apellidos : "+cuenta.getApellidos());
            Text correotxt = new Text ("Correo : "+cuenta.getCorreo());      

            Text clave = new Text ("Contraseña : "+cuenta.getClave());
            Text org = new Text ("Organizacion : "+cuenta.getOrganizacion());

            info.getChildren().add(nombres);
            info.getChildren().add(apellidos);
            info.getChildren().add(correotxt);
            info.getChildren().add(clave);
            info.getChildren().add(org);

            info.setAlignment(Pos.CENTER);
            info.setSpacing(15);
        }
        */
    }

    public void setCuenta (String correo, String contraseña) {
        this.cuenta = Usuario.recuperarUsuario(correo, "usuarios.ser");
        this.contraseña = contraseña;
        
        Text nombres = new Text("Nombres : "+this.cuenta.getNombres().toString());
        Text apellidos = new Text("Apellidos : "+cuenta.getApellidos());
        Text correotxt = new Text ("Correo : "+cuenta.getCorreo());      

        Text clave = new Text ("Contraseña : "+contraseña);
        Text org = new Text ("Organizacion : "+cuenta.getOrganizacion());

        info.getChildren().add(nombres);
        info.getChildren().add(apellidos);
        info.getChildren().add(correotxt);
        info.getChildren().add(clave);
        info.getChildren().add(org);

        info.setAlignment(Pos.CENTER);
        info.setSpacing(15);
        /*
        nombrestxt.setText("Nombres : "+this.cuenta.getNombres());
        info.getChildren().add(nombrestxt);
        
        apellidostxt.setText("Apellidos : "+this.cuenta.getApellidos());
        info.getChildren().add(apellidostxt);
        
        correotxt.setText("Correo : "+correo);
        info.getChildren().add(correotxt);
        
        clavetxt.setText("Contraseña : "+this.cuenta.getClave());
        info.getChildren().add(clavetxt);
        */
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
    
}
