/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.AtributosException;
import ec.edu.espol.exceptions.PlacaException;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author davidperez
 */
public class Vehiculo implements Serializable{
    private String ruta;
    private String tipo;
    private String placa;
    private String marca;
    private String motor;
    private int anio;
    private String modelo;
    private double recorrido;
    private String color; 
    private String combustible; 
    private double precio;  
    private String vidrios;  
    private String transmision;
    private String traccion; 
    private String correo_vendedor;
    private Usuario vendedor;
    private ArrayList<Oferta> ofertas;
    
    // Constructor de Autos
 
    public Vehiculo(String ruta,String tipo, String correo_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision){
        this.ruta = ruta;
        this.tipo = tipo;
        this.correo_vendedor = correo_vendedor;
        this.placa = placa;
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de Camionetas
    
    public Vehiculo(String ruta, String tipo, String correo_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String traccion){
        this.ruta = ruta;
        this.tipo = tipo;
        this.correo_vendedor = correo_vendedor;
        this.placa = placa;
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.traccion = traccion;
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de motos
    
    public Vehiculo(String ruta, String tipo,String correo_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio){
        this.ruta = ruta;
        this.tipo = tipo;
        this.correo_vendedor = correo_vendedor;
        this.placa = placa;
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }

    //getters y setters

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public String getCorreo_vendedor() {
        return correo_vendedor;
    }

    public void setCorreo_vendedor(String correo_vendedor) {
        this.correo_vendedor = correo_vendedor;
    }
    
    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    
    
    //comportamientos
    
    //funciones de oferta

    /*
    agregarVehiculos(arreglo de strings con atributos de vehiculos, lista de vehiculos)
    Filtra por tipo de Vehiculo, crea una intancia de Vehiculo y lo añade a una lista de Vehiculos
    Es usada en la funcion readFileVehiculo
    */
    
    //funciones de nextvehiculo
    
    
    public static Vehiculo nextCarro(String ruta,String nomArchivo, String correo_vendedor, String placa, String marca, String motor, int anio,String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision ) throws AtributosException, PlacaException {

        
        if ( (!Util.isAlpha(marca)) || (!Util.isAlpha(modelo)) || (!Util.isAlpha(vidrios)) || (!Util.isAlpha(transmision))) {
           throw new AtributosException("ERROR! Ingrese valores no numericos");
        }else if (recorrido < 0 || precio < 0 ) {
            throw new AtributosException("ERROR! Los campos no pueden ser negativos");
        }else if ( anio < 1886 ) {
            throw new AtributosException("ERROR! Año no valido");
        }
       
        Vehiculo vehiculo = new Vehiculo(ruta,"CARRO", correo_vendedor,Vehiculo.recuperarPlaca(placa,nomArchivo), marca,motor,anio,modelo,recorrido,color,combustible,precio,vidrios,transmision );
        return vehiculo;

    }
    
    public static Vehiculo nextCamioneta(String ruta,String nomArchivo,String correo_vendedor, String placa, String marca, String motor, int anio,String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String traccion ) throws AtributosException, PlacaException {

        
        if ( (!Util.isAlpha(marca)) || (!Util.isAlpha(modelo)) || (!Util.isAlpha(vidrios)) || (!Util.isAlpha(transmision))  ) {
           throw new AtributosException("ERROR! Ingrese valores no numericos");
        }else if (recorrido < 0 || precio < 0 ) {
            throw new AtributosException("ERROR! Los campos no pueden ser negativos");
        }else if ( anio < 1886 ) {
            throw new AtributosException("ERROR! Año no valido");
        }
       
        Vehiculo vehiculo = new Vehiculo(ruta,"CAMIONETA", correo_vendedor, Vehiculo.recuperarPlaca(placa,nomArchivo), marca,motor,anio,modelo,recorrido,color,combustible,precio,vidrios,transmision,traccion );
        return vehiculo;

    }
    
    public static Vehiculo nextMoto(String ruta, String nomArchivo,String correo_vendedor, String placa, String marca, String motor, int anio,String modelo, double recorrido, String color, String combustible, double precio) throws AtributosException, PlacaException {

        
        if ( (!Util.isAlpha(marca)) || (!Util.isAlpha(modelo))) {
           throw new AtributosException("ERROR! Ingrese valores no numericos");
        }else if (recorrido < 0 || precio < 0 ) {
            throw new AtributosException("ERROR! Los campos no pueden ser negativos");
        }else if ( anio < 1886 ) {
            throw new AtributosException("ERROR! Año no valido");
        }
       
        Vehiculo vehiculo = new Vehiculo(ruta,"MOTO", correo_vendedor, Vehiculo.recuperarPlaca(placa,nomArchivo), marca,motor,anio,modelo,recorrido,color,combustible,precio);
        return vehiculo;

    }
    
    //validaciones
    
    
    public static boolean validarPlaca(String placa){
        String regex = "^[A-Z][A-Z][A-Z]-[0-9]{3,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }
    
    //funciones de file
    
    public static ArrayList<Vehiculo> recuperarVehiculos(String nomArchivo) {
        try (FileInputStream fin = new FileInputStream(nomArchivo);ObjectInputStream oin = new ObjectInputStream(fin);) {
            ArrayList<Vehiculo> vehiculos = (ArrayList<Vehiculo>) oin.readObject();
        return vehiculos;
        }catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null; 
    }
    
    public static void guardarVehiculos(String nomArchivo, ArrayList<Vehiculo> vehiculos) {
        try(FileOutputStream fous = new FileOutputStream (nomArchivo);ObjectOutputStream out = new ObjectOutputStream (fous);){
            out.writeObject(vehiculos);
            out.flush();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    } 

    //funciones link
    /*
    public static ArrayList<Vehiculo> linkVehiculo(String nomFile, int id_vendedor){
        ArrayList<Vehiculo> vehiculos = readFile(nomFile);
        ArrayList<Vehiculo> vehiculos2 = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.getId_vendedor() == id_vendedor){
                vehiculos2.add(v);
            }
        }
        return vehiculos2;
    }
   
    public static void link(ArrayList<Vehiculo> vehiculos, ArrayList<Vendedor> vendedores){
        for(Vehiculo v : vehiculos){
            Vendedor vend = Vendedor.searchByID(vendedores, v.getId_vendedor());
            v.setVendedor(vend);
            vend.getVehiculos().add(v);           
        }
    }
    
    public static void linkOfertas(ArrayList<Vehiculo> vehiculos) {
        ArrayList<Oferta> ofertas = Oferta.readFile("ofertas.txt");
        for (Vehiculo v : vehiculos){
            for (Oferta o : ofertas) {
                if (v.id == o.getId_Vehiculo() ) {
                    v.getOfertas().add(o);
                    o.setVehiculo(v);
                }
            }

        }
    }
    */
    
    //funcion recuperar
    
    public static String recuperarPlaca(String placa,String nomArchivo) throws PlacaException {
        
        boolean formatoPlaca = Vehiculo.validarPlaca(placa);
        ArrayList<Vehiculo> vehiculos = Vehiculo.recuperarVehiculos(nomArchivo);
        
        Vehiculo vehiculoExis = Vehiculo.searchByPlaca(vehiculos, placa);
       
        if (vehiculoExis == null) {
            return placa;
        }
        
        if(!formatoPlaca)
            throw new PlacaException ("ERROR! Esta placa no es valida");
        else if (vehiculoExis.getPlaca() != null ){
            throw new PlacaException ("ERROR! Esta placa ya existe en el sistema");
        }
        return placa;
    }
    
    //funciones searchby
    
    
    // Busca un Vehiculo por placa
    public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        
        if (vehiculos == null ) {
            return null;
        }
        for(Vehiculo v : vehiculos){
            if(v.placa.equals(placa))
                return v;
        }
        return null;
    }
    
    public static ArrayList<Vehiculo> searchByTipo(String tipo, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byTipo = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(tipo.equals(v.getTipo()))
                byTipo.add(v);
        }
        return byTipo;
    }
    
    public static ArrayList<Vehiculo> searchByRecorrido(double rmin, double rmax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byRecorrido = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.recorrido >= rmin && v.recorrido <= rmax)
                byRecorrido.add(v);
        }
        return byRecorrido;
    }
    
    public static ArrayList<Vehiculo> searchByPrecio(double pmin, double pmax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byPrecio = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.precio >= pmin && v.precio <= pmax)
                byPrecio.add(v);
        }
        return byPrecio;
    }
    
    public static ArrayList<Vehiculo> searchByAnio(int amin, int amax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byAnio = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.anio >= amin && v.anio <= amax)
                byAnio.add(v);
        }
        return byAnio;
    }
    
    //sobreescrituras
    @Override
    public String toString() {
        return "Tipo: " + this.tipo + "\nPlaca: " + placa + "\nMarca: " + marca + "\nMotor: " + motor + "\nAño: " + anio + "\nModelo: " + modelo + "\nRecorrido: " + recorrido + "\nColor: " + color + "\nCombustible: " + combustible + "\nPrecio: " + precio+"\nRuta: " + ruta;
             
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(o.getClass() != this.getClass())
            return false;
        
        Vehiculo other = (Vehiculo)o;
        
        return (this.placa.equals(other.placa)); 
    }
}