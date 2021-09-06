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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
     private ArrayList<Oferta> oferta_vehiculo;
    @FXML
    private ComboBox cbxPlacas;
    @FXML
    private Button btnatras;
    @FXML
    private Button btnSearch;
    @FXML
    private ScrollPane scrollOfertas;
    @FXML
    private VBox vbox;

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
            this.ofertas = Oferta.recuperarOfertas("ofertas.ser");
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
     vbox.getChildren().clear();
       
        for(Vehiculo v : vehiculos){

            if( v.getPlaca().equals(this.placa) && !v.getOfertas().isEmpty() ){
                this.oferta_vehiculo= v.getOfertas();
                oferta_vehiculo.sort((Oferta of1,Oferta of2) -> of2.compareTo(of1));
                for(Oferta of : this.oferta_vehiculo){


                 Text tx_comprador = new Text("CORREO DEL COMPRADOR:  "+of.getCorreo_comprador());
                 tx_comprador.setFont(Font.font ("Verdana", 14));
    ;
                 Text tx_precio_ofertado = new Text("EL PRECIO OFERTADO ES:  "+of.getPrecio_ofertado());
                 Button btn = new Button();
                 btn.setText("ACEPTAR");

                 btn.setOnMouseClicked((MouseEvent e)->{
                    Usuario.enviarCorreo(of.getCorreo_comprador(),v.getMarca(), v.getModelo(), v.getMotor(), of.getPrecio_ofertado(), v.getPlaca());
                    ArrayList<Oferta> oferta_eliminada = v.getOfertas();
                    oferta_eliminada.clear();
                    v.setOfertas(oferta_eliminada);
                    ofertas.remove(of);
                    vehiculos.remove(v);

                    Vehiculo.guardarVehiculos("vehiculos.ser", vehiculos);
                    Oferta.guardarOfertas("ofertas.ser", ofertas);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Se acepto la oferta del usuario:  "+of.getCorreo_comprador());
                    a.show();
                    vbox.getChildren().clear();

                });

                vbox.getChildren().add(tx_precio_ofertado);
                vbox.getChildren().add(tx_comprador);
                vbox.getChildren().add(btn);
                vbox.setPadding(new Insets(30, 30, 30, 30));
                /* vbox.setPrefWidth(200);
                 vbox.setPrefHeight(200);
                 vbox.setPadding(new Insets(10, 10, 10, 10));
                */
                }
                   
                }else if( v.getPlaca().equals(this.placa) && v.getOfertas().isEmpty()){
                    Alert a = new Alert(Alert.AlertType.ERROR,"NO EXISTEN OFERTAS PARA ESTE VEHICULO ");
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
