/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.exceptions.AtributosException;
import ec.edu.espol.exceptions.PlacaException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class RegistrarVehiculoController implements Initializable {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    
    private String correo;
    private String contraseña;
    private String tipo;
    private String combustible;
    
    private TextField transmisionfield;
    private TextField vidriosfield;
    private TextField traccionfield;
    private String ruta;
    
    @FXML
    private ComboBox cbxtipo;
    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField año;
    @FXML
    private TextField motor;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField color;
    @FXML
    private TextField precio;
    @FXML
    private ComboBox cbxcombustible;
    @FXML
    private GridPane datosizq;
    @FXML
    private GridPane datosder;
    @FXML
    private Button atrasbtn;
    @FXML
    private GridPane datosnomoto;
    @FXML
    private Button registrarbtn;
    @FXML
    private ImageView foto;
    @FXML
    private Button subirfotobtn;

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
        
        ArrayList<String> combustibles = new ArrayList<String>();
        combustibles.add("Super");
        combustibles.add("Extra");
        combustibles.add("Eco-Pais");
        combustibles.add("Diesel");
        
        cbxcombustible.setItems(FXCollections.observableArrayList(combustibles));
        
        
    }    

    @FXML
    private void escogerTipo(ActionEvent event) {
        tipo = (String)cbxtipo.getValue();
        //VBox datosNoMoto = new VBox();



        if (!tipo.equals("Moto")) {
            
            Text vidrios = new Text("VIDRIOS");
            vidrios.setTextAlignment(TextAlignment.RIGHT);
            vidrios.setFont(Font.font ("Futura", 13));

            vidriosfield = new TextField();

            Text transmision = new Text("TRANSMISION");
            transmision.setTextAlignment(TextAlignment.RIGHT);
            transmision.setFont(Font.font ("Futura", 13));

            transmisionfield = new TextField();
           
            datosnomoto.add(vidrios,0,0);
            datosnomoto.add(vidriosfield,1,0);
            
            datosnomoto.add(transmision,0,1);
            datosnomoto.add(transmisionfield,1,1);
            
            if (tipo.equals("Camioneta")) {
                Text traccion = new Text("TRACCION");
                traccion.setTextAlignment(TextAlignment.RIGHT);
                traccion.setFont(Font.font ("Futura", 13));

                traccionfield = new TextField();
                
                datosnomoto.add(traccion,0,2);
                datosnomoto.add(traccionfield,1,2);
            }
        }else {
            datosnomoto.getChildren().clear();
        }
    }

    @FXML
    private void escogerCombustible(ActionEvent event) {
        this.combustible = (String)cbxcombustible.getValue();
    }

    @FXML
    private void atras(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("menu");
            App.setRoot(fxmlloader);
            MenuController mc = fxmlloader.getController();
            mc.setCorreo(correo,contraseña);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    @FXML
    private void registrar(MouseEvent event) {
        String nomArchivo = "vehiculos.ser";

        try {
            String placatxt = placa.getText();
            String marcatxt = marca.getText();
            String modelotxt = modelo.getText();
            int aniotxt = Integer.parseInt(año.getText());
            String motortxt = motor.getText();
            int recorridotxt = Integer.parseInt(recorrido.getText());
            String colortxt = color.getText();
            int preciotxt = Integer.parseInt(precio.getText());
            Vehiculo v ;
            
            if (placa == null || marca == null || modelo == null || año == null || motor == null || recorrido == null || color == null || precio == null ){
                throw new AtributosException("ERROR! Llene todos los campos requeridos");
            }
            if(!tipo.equals("MOTO")) {
                String transmisiontxt = transmisionfield.getText();
                String vidriostxt = vidriosfield.getText();
                
                if (transmisiontxt.equals("") ||  vidriostxt.equals("")   ) {
                    throw new AtributosException("ERROR! Llene todos los campos requeridos");
                }
                
                if(tipo.equals("CAMIONETA")) {
                    String tracciontxt = traccionfield.getText();
                    
                    if (tracciontxt.equals("")   ) {
                        throw new AtributosException("ERROR! Llene todos los campos requeridos");
                    }
                    
                    Vehiculo camioneta = Vehiculo.nextCamioneta(ruta,nomArchivo,correo,placatxt, marcatxt, motortxt, aniotxt, modelotxt, recorridotxt, colortxt, combustible, preciotxt, vidriostxt, transmisiontxt, tracciontxt);
                    v = camioneta;
                    this.vehiculos.add(camioneta);
                }else {
                    Vehiculo carro = Vehiculo.nextCarro(ruta,nomArchivo,correo,placatxt, marcatxt, motortxt, aniotxt, modelotxt, recorridotxt, colortxt, combustible, preciotxt, vidriostxt, transmisiontxt);
                    v = carro;
                    this.vehiculos.add(carro);
                }
            }else {
                Vehiculo moto = Vehiculo.nextMoto(ruta,nomArchivo,correo,placatxt, marcatxt, motortxt, aniotxt, modelotxt, recorridotxt, colortxt, combustible, preciotxt);
                v = moto;
                this.vehiculos.add(moto);
            }
            
            for (Usuario u : usuarios) {
              if (u.getCorreo().equals(correo)) {
                ArrayList<Vehiculo> vehiculosU = u.getVehiculos();
                vehiculosU.add(v);
                u.setVehiculos(vehiculosU);
              }  
            }
           
            Usuario.guardarUsuarios("usuarios.ser", usuarios);
            Vehiculo.guardarVehiculos(nomArchivo, vehiculos);
            
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Se ha agregado su vehiculo exitosamente");
            a.show();
            
        }catch (AtributosException ax) {
            Alert a = new Alert(Alert.AlertType.ERROR,ax.getMessage());
            a.show();
        } catch (PlacaException px) {
            Alert a = new Alert(Alert.AlertType.ERROR,px.getMessage());
            a.show();
        }catch (NumberFormatException ne) {
            Alert a = new Alert(Alert.AlertType.ERROR,"ERROR! Escriba datos numericos");
            a.show();
        }
    }

    @FXML
    private void subirFoto(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen del Vehiculo");
        
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        File imgFile = fileChooser.showOpenDialog(null);
        
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            foto.setImage(image);
            ruta = imgFile.getAbsolutePath();
            
            //copiando archivos y moviendolos a img
            /*
            File ignorar = new File("vehiculos.ser");
            File ignorar2 = new File (ignorar.getParent());
            
            System.out.println(ignorar2);

            Path origen = Path.of(imgFile.getAbsolutePath());
            Path destino = Path.of(ignorar2.getAbsolutePath());
            
            try {
                Path copiar = Files.copy(origen,destino.resolve(origen.getFileName()),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            */
        }
       
        
      
    }

    
}
