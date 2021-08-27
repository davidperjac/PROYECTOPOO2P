/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.CorreoException;
import ec.edu.espol.exceptions.UsuarioException;
import ec.edu.espol.util.GFG;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author davidperez
 */
public class Usuario implements Serializable{
    private String rol;
    private String correo;
    private String clave;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private ArrayList<Oferta> ofertas;
    private ArrayList<Vehiculo> vehiculos;
    
    //constructor
    
    public Usuario(String rol, String correo, String clave, String nombres, String apellidos, String organizacion){
        this.rol = rol;
        this.correo = correo;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.ofertas = new ArrayList<Oferta>();
        this.vehiculos = new ArrayList<Vehiculo>();       
    }

    //getters y setters

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
    //nuevo
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
    
    //funciones de vendedor
    
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
    
    public static Usuario inicioSesionV(String correo, String clave, String nomfile) throws NoSuchAlgorithmException, UsuarioException{
        
        if (!Usuario.validarUsuario(correo,clave,nomfile)) 
            throw new UsuarioException("ERROR! El usuario no se encuentra registrado");
        
        Usuario usuarioV = Usuario.recuperarUsuario(correo, nomfile);
        return usuarioV;
    }
    
    
    
    //funciones de comprador
    
    public static ArrayList<Vehiculo> busquedaVehiculo(Scanner sc, ArrayList<Vehiculo> vehiculos){
        String tipo;
        double rmin, rmax, pmin, pmax;
        int amin, amax;
        ArrayList<Vehiculo> filtroVehiculos;
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        System.out.println("BÚSQUEDA DE VEHÍCULOS");
        System.out.println(" -------------------------------------------------------------------------------- ");
        System.out.println("NOTA: Para separar los decimales usar el punto."+"\n");
        
        int p1;
        do{
            System.out.println("¿Desea especificar el tipo de vehículo?"+"\n");
            System.out.println("1.- Sí");
            System.out.println("2.- No");
            p1 = sc.nextInt();
        }
        while (p1 != 1 && p1 != 2);
        if (p1 == 1){
            System.out.println("Ingrese el tipo de vehículo a buscar (MOTO, CARRO, CAMIONETA): "+"\n");
            tipo = sc.next().toUpperCase();
            while (!tipo.equals("MOTO") && !tipo.equals("CAMIONETA") && !tipo.equals("CARRO")){
                System.out.println("El tipo de vehículo no es valido, por favor ingresarlo de nuevo (MOTO, CARRO, CAMIONETA): "+"\n");
                tipo = sc.next().toUpperCase();
            }
        }
        else
            tipo = "n";
        
        System.out.println(" -------------------------------------------------------------------------------- ");
        int p2;
        do{
            System.out.println("¿Desea especificar el recorrido del vehículo?"+"\n");
            System.out.println("1.- Sí");
            System.out.println("2.- No");
            p2 = sc.nextInt();
        }
        while (p2 != 1 && p2 != 2);
        if (p2 == 1){
            System.out.println("Recorrido mínimo del vehículo: "+"\n");
            rmin = sc.nextDouble();
            while (rmin < 0){
                System.out.println("Por favor ingrese un número positivo: "+"\n");
                rmin = sc.nextDouble();
            }
            System.out.println("Recorrido máximo del vehículo: "+"\n");
            rmax = sc.nextDouble();
            while (rmax < rmin){
                System.out.println("Por favor ingrese un número mayor al recorrido mínimo: "+"\n");
                rmax = sc.nextDouble();
            }
        }
        else
            rmin = rmax = -1;
        
        System.out.println(" -------------------------------------------------------------------------------- ");
        int p3;
        do{
            System.out.println("¿Desea especificar el año del vehículo?"+"\n");
            System.out.println("1.- Sí");
            System.out.println("2.- No");
            p3 = sc.nextInt();
        }
        while (p3 != 1 && p3 != 2);
        if (p3 == 1){
            System.out.println("Año más antiguo de modelo del vehículo: "+"\n");
            amin = sc.nextInt();
            while (amin < 1800){
                System.out.println("Por favor ingrese un año mayor a 1856: "+"\n");
                amin = sc.nextInt();
            }
            System.out.println("Año más reciente de modelo del vehículo: "+"\n");
            amax = sc.nextInt();
            while (amax < amin){
                System.out.println("Por favor ingrese un año mayor al año mínimo: "+"\n");
                amax = sc.nextInt();
            }
        }
        else
            amin =amax = -1;
        
        System.out.println(" -------------------------------------------------------------------------------- ");
        int p4;
        do{
            System.out.println("¿Desea especificar rango de precio del vehículo?"+"\n");
            System.out.println("1.- Sí");
            System.out.println("2.- No");
            p4 = sc.nextInt();
        }
        while (p4 != 1 && p4 != 2);
        if (p4 == 1){
            System.out.println("Precio mínimo del vehículo: "+"\n");
            pmin = sc.nextDouble();
            while (pmin < 0){
                System.out.println("Por favor ingrese un precio positivo: "+"\n");
                pmin = sc.nextDouble();
            }
            System.out.println("Precio máximo del vehículo: "+"\n");
            pmax = sc.nextDouble();
            while (pmax < pmin){
                System.out.println("Por favor ingrese un precio mayor al mínimo: "+"\n");
                pmax = sc.nextDouble();
            }
        }
        else
            pmin = pmax = -1;
        System.out.println(" -------------------------------------------------------------------------------- ");
        filtroVehiculos = vehiculos;
        if (!tipo.equals("n"))
            filtroVehiculos = Vehiculo.searchByTipo(tipo, filtroVehiculos);
        if (rmin != -1)
            filtroVehiculos = Vehiculo.searchByRecorrido(rmin, rmax, filtroVehiculos);
        if (amin != -1)
            filtroVehiculos = Vehiculo.searchByAnio(amin, amax, filtroVehiculos);
        if (pmin != -1)
            filtroVehiculos = Vehiculo.searchByPrecio(pmin, pmax, filtroVehiculos);
        return filtroVehiculos;
    }   
    
    
    public static Usuario inicioSesionC(String correo, String clave, String nomfile) throws NoSuchAlgorithmException, UsuarioException{
        
        if (!Usuario.validarUsuario(correo,clave, nomfile)) 
            throw new UsuarioException("ERROR! El usuario no se encuentra registrado");
        
        Usuario usuarioC = Usuario.recuperarUsuario(correo,nomfile);
        return usuarioC;
    }
    
    
    
    
    // funciones recuperadoras
    
    public static ArrayList<String> recuperarCorreos(String nomfile){
        ArrayList<String> correos = new ArrayList<>();
        
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        
        if (usuarios == null) {
            return null;
        }else {
            for (Usuario u : usuarios) {
                correos.add(u.getCorreo());
            }
            return correos;
        }
    }
    //recuperar con correo
    
    public static Usuario recuperarUsuario(String correo, String nomfile){
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        
        if (usuarios == null) {
            return null;
        }
        
        for (Usuario u: usuarios){
            if (correo.equals(u.getCorreo()))
                return u;
        }
        return null;
    }
    //nuevas con serializacion
    
    public static ArrayList<Usuario> recuperarUsuarios (String nomArchivo){
        try (FileInputStream fin = new FileInputStream(nomArchivo);ObjectInputStream oin = new ObjectInputStream(fin);) {
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) oin.readObject();
            return usuarios;
        }catch(IOException e) {
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null; 
    }
    
    public static void guardarUsuarios(String nomArchivo, ArrayList<Usuario> usuarios){
        try(FileOutputStream fous = new FileOutputStream (nomArchivo);ObjectOutputStream out = new ObjectOutputStream (fous);){
            out.writeObject(usuarios);
            out.flush();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //comportamientos problema

    //constructor que valide cosas
    public static Usuario nextUsuario(String nomfile,String rol,String correo, String clave, String nombres, String apellidos, String organizacion) throws NoSuchAlgorithmException, CorreoException, InputMismatchException{        
        boolean corrExis;
        boolean corrValid;
        do{
            corrExis = correoExistente(correo, nomfile);
            corrValid = validarCorreo(correo);
            if (!corrValid)
                throw new CorreoException("ERROR! Ese correo no es valido");
            else if (corrExis)
                throw new CorreoException("ERROR! Ese correo ya existe en el sistema");
        }while(!corrValid || corrExis);
        
        String hashclave = GFG.toHexString(GFG.getSHA(clave));
        
        if (!Util.isAlpha(nombres) || !Util.isAlpha(apellidos) || !Util.isAlpha(organizacion) ) {
            throw new InputMismatchException();
        }
        
        Usuario u = new Usuario(rol,correo, hashclave, nombres, apellidos, organizacion);
        return u;
    }

    //funciones de validacion
    
    public static boolean validarUsuario(String correo, String clave,String nomfile) throws NoSuchAlgorithmException{
        String hashclave = GFG.toHexString(GFG.getSHA(clave));
        
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        
        if (usuarios == null) {
            return false;
        }
        for (Usuario u : usuarios){
            if (u.getCorreo().equals(correo) && u.getClave().equals(hashclave))
                return true;
        }
        return false;
    }
    
    public static boolean validarCorreo(String correo){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    
    //valida que el correo sea unico
    public static boolean correoExistente(String correo,String nomfile){
        ArrayList<String> correos = recuperarCorreos(nomfile);
        if (correos == null) {
            return false;
        }
        return correos.contains(correo);
    }
    
    //sobreescrituras
    @Override
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        Usuario other = (Usuario) o;
        return (this.correo.equals(other.correo));
    }
   
    @Override
    public String toString(){
        String s = "{ USUARIO\nNombres: " +this.nombres + "\nApellidos: " + this.apellidos+ "\nCorreo Electrónico: " + this.correo + "\nClave : "+this.clave+"\nOrganización " + this.organizacion+" } ";
        return s;
    }
}
