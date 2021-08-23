/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author davidperez
 */
public class Oferta implements Serializable{
    private String placa_vehiculo;
    private double precio_ofertado;
    private Vehiculo vehiculo;
    private Comprador comprador;
    private String correo_comprador;
    
    //constructor

    public Oferta(String placa_vehiculo, double precio_ofertado, String correo_comprador) {
        this.placa_vehiculo = placa_vehiculo;
        this.precio_ofertado = precio_ofertado;
        this.correo_comprador = correo_comprador;
    }
    
    //getters y setters

    public double getPrecio_ofertado() {
        return precio_ofertado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public String getCorreo_comprador() {
        return correo_comprador;
    }

    public void setPrecio_ofertado(double precio_ofertado) {
        this.precio_ofertado = precio_ofertado;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setCorreo_comprador(String correo_comprador) {
        this.correo_comprador = correo_comprador;
    }
    
    //funciones de serializacion
    
    public static ArrayList<Oferta> recuperarOfertas(String nomArchivo) {
        try (FileInputStream fin = new FileInputStream(nomArchivo);ObjectInputStream oin = new ObjectInputStream(fin);) {
            ArrayList<Oferta> ofertas = (ArrayList<Oferta>) oin.readObject();
        return ofertas;
        }catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null; 
    }
    
    public void guardarOfertas(String nomArchivo, ArrayList<Oferta> ofertas) {
        try(FileOutputStream fous = new FileOutputStream (nomArchivo);ObjectOutputStream out = new ObjectOutputStream (fous);){
            out.writeObject(ofertas);
            out.flush();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //funciones de link
    /*
    public static void link(ArrayList<Comprador> compradores, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas) {
        for(Oferta o : ofertas){
            Comprador c = Comprador.searchByID(compradores, o.getId_Comprador());
            Vehiculo v = Vehiculo.searchByID(vehiculos, o.getId_Vehiculo());
            c.getOfertas().add(o);
            v.getOfertas().add(o);
            o.setComprador(c);
            o.setVehiculo(v);
        }
    }
    */
    //sobreescrituras
    
    @Override
    public String toString() {
        return "Correo comprador: " + correo_comprador + "\nPrecio Ofertado: " + precio_ofertado;
    }
}
