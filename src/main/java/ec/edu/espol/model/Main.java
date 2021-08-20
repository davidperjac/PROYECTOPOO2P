/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;
import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author davidperez
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
    Scanner sc = new Scanner(System.in);
    
    sc.useDelimiter("\n");
    

    int opPrincipal = 1;
    
    while (opPrincipal >= 1 && opPrincipal < 3) {
        
        Util.menuInicio();
        
        opPrincipal = sc.nextInt();
        System.out.println(" -------------------------------------------------------------------------------- ");
        
        if(opPrincipal == 1) {
            int opcionVend = 1;
            while (opcionVend >= 1 && opcionVend < 4) {
                opcionVend = Vendedor.menuVendedor(sc);
                if (opcionVend == 1) {
                    Usuario.nextUsuario(sc, "vendedores.txt");
                }else if(opcionVend == 2) {
 
                    Vendedor v = Vendedor.inicioSesionV(sc);
                    v.ingresarVehiculo(sc, "vehiculos.txt");
                    
                }else if(opcionVend == 3) {
                    
                    Vendedor v = Vendedor.inicioSesionV(sc);
                    v.verOfertas(sc);
                    
                }
                if(opcionVend > 4 || opcionVend < 1){
                    System.out.println("ERROR! Escoja una opcion correcta"+"\n");
                    opcionVend = 1;
                }
            }
        
        }else if(opPrincipal == 2) {
            int opComp = 1;
            while(opComp >= 1 && opComp < 3){
                opComp = Comprador.menuComprador(sc);
                if(opComp == 1){
                    Usuario.nextUsuario(sc, "compradores.txt");
                } else if(opComp == 2){
                    Comprador c = Comprador.inicioSesionC(sc);
                    ArrayList<Vehiculo> listaFiltrada = Comprador.busquedaVehiculo(sc, Vehiculo.readFile("vehiculos.txt"));
                    Vehiculo vehiculoEscogido = Comprador.elegirVehiculo(sc, listaFiltrada);
                    if(vehiculoEscogido != null)
                        c.ponerOferta(vehiculoEscogido,"ofertas.txt", sc);
                }
                if(opComp > 3 || opComp < 1){
                    System.out.println("ERROR! Escoja una opcion correcta"+"\n");
                    opComp = 1;
                }
                
            }
        
            
        }
            
        if(opPrincipal > 3 || opPrincipal < 1){
            System.out.println("ERROR! Escoja una opcion correcta"+"\n");
            opPrincipal = 1;
        }

        
    }
         
    }
    
}
