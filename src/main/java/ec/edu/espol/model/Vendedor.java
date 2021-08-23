/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.UsuarioException;
import java.util.ArrayList;
import java.util.Scanner;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author davidperez
 */
// mvn install:install-file -Dfile=target/javax.mail.jar -DgroupId=ec.edu.espol -DartifactId=proyecto2P -Dversion=1.0-SNAPSHOT -Dpackaging=jar
public class Vendedor extends Usuario{
    
    private ArrayList<Vehiculo> vehiculos;    
    
    //Constructor
    
    public Vendedor ( String correo, String clave, String nombres, String apellidos, String organizacion) {
        super(correo,clave,nombres,apellidos,organizacion);
        this.vehiculos = new ArrayList<>();
    }
    
    public Vendedor(Usuario u){
        super(u.getCorreo(), u.getClave(), u.getNombres(),u.getApellidos(),u.getOrganizacion());
        this.vehiculos = new ArrayList<>();   
    }
    
    //getters y setters

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    //comportamientos
    /*
    public void verOfertas (Scanner sc) {
        
        System.out.println("Ingrese la placa del vehiculo: ");
        String placa = sc.next();
        
        while (!Vehiculo.validarPlaca(placa)) {
            System.out.println("ERROR! Placa incorrecta, ingrese una placa valida: ");
            placa = sc.next();
        }
        
        this.vehiculos = Vehiculo.linkVehiculo("vehiculos.txt", this.id);
        Vehiculo.linkOfertas(vehiculos);
        for (Vehiculo v : this.vehiculos) {
            if (v.getPlaca().equals(placa)) {
                Oferta o = v.menuOfertas(sc);
                if(o != null){
                    // funcion de mandar email y necesito el correo del comprador.
                    //Vendedor.enviarCorreo(o.getCorreo_comprador(),v.getMarca(),v.getModelo(),v.getMotor(),o.getPrecio_ofertado(),v.getPlaca());
                    //borrar en base de datos
                    v.borrarVehiculo();
                }
            }
        }  
    }
    */
    //extras 
    
    public static void enviarCorreo(String destinatario, String marca, String modelo,String motor, double dinero, String placa) {

        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        //props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        
        props.put("mail.smtp.user", "sistema.dij.poo@gmail.com");
        props.put("mail.smtp.clave", "ProyectoPOO2P");    //La clave de la cuenta


        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject("Oferta aceptada");
            message.setText("Un gusto le saluda el sistema de la app SDF. Se ha aceptado su oferta de $"+dinero+" por el vehiculo "+marca+" "+modelo+" "+motor+" con la placa: "+placa);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "sistema.dij.poo@gmail.com", "ProyectoPOO2P");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se ha aceptado la oferta exitosamente y se ha notificado al comprador de su vehículo.");
            System.out.println(" -------------------------------------------------------------------------------- ");
        }
        catch (Exception e) {
            e.printStackTrace();   
        }
    }
    
    public static Vendedor inicioSesionV(String correo, String clave, String nomfile) throws NoSuchAlgorithmException, UsuarioException{
        
        if (!Usuario.validarUsuario(correo,clave,nomfile)) 
            throw new UsuarioException("ERROR! El usuario no se encuentra registrado");
        
        Vendedor v = new Vendedor(Usuario.recuperarUsuario(correo, nomfile));
        return v;
    }
    
}
