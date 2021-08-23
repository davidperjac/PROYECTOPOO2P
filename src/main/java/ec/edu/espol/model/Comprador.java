/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.UsuarioException;
import ec.edu.espol.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author davidperez
 */
public class Comprador extends Usuario{
    private ArrayList<Oferta> ofertas;
    
    //constructores
    
    public Comprador(String correo, String clave, String nombres, String apellidos, String organizacion){
        super(correo, clave, nombres, apellidos, organizacion);
        this.ofertas = new ArrayList<>();
    }
    
    public Comprador(Usuario u){
        super(u.getCorreo(), u.getClave(), u.getNombres(),u.getApellidos(),u.getOrganizacion());
        this.ofertas = new ArrayList<>();   
    }
    
    //getters y setters

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    //comportamientos
    
    
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
    
    
    //extras
    
    
    public static Comprador inicioSesionC(String correo, String clave, String nomfile) throws NoSuchAlgorithmException, UsuarioException{
        
        if (!Usuario.validarUsuario(correo,clave, nomfile)) 
            throw new UsuarioException("ERROR! El usuario no se encuentra registrado");
        
        Comprador comp = new Comprador(Usuario.recuperarUsuario(correo,nomfile));
        return comp;
    }
    
}
