/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author davidperez
 */
public class OfertarVehiculoController implements Initializable {

    private String correo;
    private String contraseña;
    private String tipo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    
    
    @FXML
    private Button atrasbtn;
    @FXML
    private TableView<Vehiculo> tablaVehiculos;
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
    @FXML
    private TableColumn<Vehiculo,String> columnPlaca;
    @FXML
    private TableColumn<Vehiculo,String> columnMarca;
    @FXML
    private TableColumn<Vehiculo,String> columnModelo;
    @FXML
    private TableColumn<Vehiculo,String> columnMotor;
    @FXML
    private TableColumn<Vehiculo,String> columnColor;
    @FXML
    private TableColumn<Vehiculo,Integer> columnAnio;
    @FXML
    private TableColumn<Vehiculo,Double> columnRecorrido;
    @FXML
    private TableColumn<Vehiculo,Double> columnPrecio;

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
        
        columnPlaca.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlaca()));
        columnMarca.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMarca()));
        columnModelo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModelo()));
        columnMotor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotor()));
        columnColor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getColor()));
        /*
        columnAnio.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAnio()));
        columnRecorrido.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getRecorrido()));
        columnPrecio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlaca()));
          */    
        
       
        
        
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
        ArrayList<Vehiculo> lista_vehiculo_fitrado = new ArrayList<>();
        ArrayList<Vehiculo>lista_precio= Vehiculo.searchByPrecio(Double.parseDouble(minPrecio.getText()),Double.parseDouble(maxPrecio.getText()), vehiculos);
         ArrayList<Vehiculo>lista_anio= Vehiculo.searchByAnio(Integer.parseInt(minAnio.getText()),Integer.parseInt(maxAnio.getText()), vehiculos);
         ArrayList<Vehiculo>lista_recorrido= Vehiculo.searchByRecorrido(Double.parseDouble(minRecorrido.getText()),Double.parseDouble(maxRecorrido.getText()), vehiculos);
        for(Vehiculo v : vehiculos){
        if(lista_precio.contains(v) && lista_anio.contains(v) && lista_recorrido.contains(v) ){
             lista_vehiculo_fitrado.add(v);
             tablaVehiculos.setItems(FXCollections.observableArrayList( lista_vehiculo_fitrado)); 
            
        }  
        }
        
        
        
        
                }
                
    public void setCorreo(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
}
