/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Oferta;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AceptarOfertaController implements Initializable {
    private String correo;
    private String contraseña; 
    private ArrayList<String> placas;
    private String placa;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Vehiculo> vehiculos_ofertados;
     private ArrayList<Oferta> ofertas;
    @FXML
    private FlowPane flowOfertas;
    @FXML
    private ComboBox cbxPlacas;
    @FXML
    private Button btnatras;
    @FXML
    private Button btnSearch;

    /**
     * Initializes the controller class.
     */
    
     
    public void setCorreo(String correo, String contraseña) {
        this.correo = correo;
        System.out.println(this.correo);
        this.contraseña = contraseña;
        System.out.println(this.contraseña);
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
       
        
          if (Usuario.recuperarUsuarios("usuarios.ser") == null) {
            this.usuarios = new ArrayList<Usuario>();
        }else {
            this.usuarios = Usuario.recuperarUsuarios("usuarios.ser");
           
        }
          
          if (Oferta.recuperarOfertas("ofertas.ser") == null) {
            this.ofertas = new ArrayList<Oferta>();
        }else {
            this.ofertas = Oferta.recuperarOfertas("usuarios.ser");
        }  
        
         if (Vehiculo.recuperarVehiculos("vehiculos.ser") == null ) {
            this.vehiculos = new ArrayList<Vehiculo>();
        }else {
            this.vehiculos = Vehiculo.recuperarVehiculos("vehiculos.ser");
        } 
         
        if (Vehiculo.recuperarVehiculos("vehiculos_ofertas.ser") == null ) {
            this.vehiculos_ofertados = new ArrayList<Vehiculo>();
        }else {
            this.vehiculos_ofertados = Vehiculo.recuperarVehiculos("vehiculos_ofertas.ser");
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
    



 
          
        
          
     
    @FXML
    private void elegirPlaca(ActionEvent event) {
           this.placa = (String)cbxPlacas.getValue();
        
    }

    @FXML
    private void filtrarPlacas(MouseEvent event) {
         placas = new ArrayList<>();
         
        for(Vehiculo v: vehiculos){
              
         if(v.getCorreo_vendedor().equals(this.correo)){
             
            this.placas.add(v.getPlaca());   
             System.out.println(v.getPlaca());
           
         }
         
           
        }
          
          
          cbxPlacas.setItems(FXCollections.observableArrayList(this.placas)); 
         
    }   

    @FXML
    private void buscarOferta(MouseEvent event) {
        
        
        for(Vehiculo v : vehiculos){
         
               if( v.getPlaca().equals(this.placa) && !v.getOfertas().isEmpty() ){
                   
                   Text tx = new Text("EXISTE UNA OFERTA");
                   Alert a = new Alert(Alert.AlertType.ERROR,"HAY UNA OFERTA");
                   a.show();
                   
               }
               
               
               
             
             
               
           }
        
        
    }
        
        
        
    }






/* public void setCorreo(String correo, String contraseña) {
        this.correo = correo;
         
        this.contraseña = contraseña;
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        placas = new ArrayList<>();
        
          if (Usuario.recuperarUsuarios("usuarios.ser") == null) {
            this.usuarios = new ArrayList<Usuario>();
        }else {
            this.usuarios = Usuario.recuperarUsuarios("usuarios.ser");
           
        }
          
          if (Oferta.recuperarOfertas("ofertas.ser") == null) {
            this.usuarios = new ArrayList<Usuario>();
        }else {
            this.usuarios = Usuario.recuperarUsuarios("usuarios.ser");
        }  
        
         if (Vehiculo.recuperarVehiculos("vehiculos.ser") == null ) {
            this.vehiculos = new ArrayList<Vehiculo>();
        }else {
            this.vehiculos = Vehiculo.recuperarVehiculos("vehiculos.ser");
        } 
         
       
         setItems(this.vehiculos);
          
     
        
     
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


      private void setItems(ArrayList<Vehiculo> vehiculos)  {
          System.out.println(correo);
          for(Vehiculo v: vehiculos){
              
         if(v.getCorreo_vendedor().equals(correo)){
             
            this.placas.add(v.getPlaca());   
             System.out.println(v.getPlaca());
           
         }
         
           
        }
          
          
          cbxPlacas.setItems(FXCollections.observableArrayList(this.placas)); 
          
      }
        
        */
