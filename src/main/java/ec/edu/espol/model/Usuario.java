/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.CorreoException;
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
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author davidperez
 */
public class Usuario implements Serializable{
    protected String correo;
    protected String clave;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    
    //constructor
    
    public Usuario(String correo, String clave, String nombres, String apellidos, String organizacion){
        this.correo = correo;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
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
    public static Usuario nextUsuario(String nomfile, String correo, String clave, String nombres, String apellidos, String organizacion) throws NoSuchAlgorithmException, CorreoException, InputMismatchException{        
        boolean corrExis;
        boolean corrValid;
        do{
            corrExis = correoExistente(correo, nomfile);
            corrValid = validarCorreo(correo);
            if (!corrValid)
                throw new CorreoException("ERROR! Ese correo no es valido");
            else if (corrExis)
                throw new CorreoException("ERROR! Ese correo ya existe");
        }while(!corrValid || corrExis);
        
        String hashclave = GFG.toHexString(GFG.getSHA(clave));
        
        if (!Util.isAlpha(nombres) || !Util.isAlpha(apellidos) || !Util.isAlpha(organizacion) ) {
            throw new InputMismatchException();
        }
        
        Usuario u = new Usuario(correo, hashclave, nombres, apellidos, organizacion);
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
        String s = "USUARIO\nNombres: " +this.nombres + "\nApellidos: " + this.apellidos+ "\nCorreo Electrónico: " + this.correo + "\nOrganización " + this.organizacion;
        return s;
    }
}
