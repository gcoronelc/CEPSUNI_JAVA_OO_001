
package Prueba;

import Service.Operacion;
import java.util.Scanner;

public class PruebaSumar {
    public static void main(String[] args) {
    
        Scanner lector = new Scanner (System.in);  
        double[][] x,y,sxy,pxy,invM,cofM;
        double detx;
        int limite,i,j;
    

        System.out.println("Ingrese el tamaño de las matrices:");
        limite = lector.nextInt();
        x = new double[limite][limite];
        y = new double[limite][limite];
        
        System.out.println("Ingrese la Matriz X:"+"X["+limite+"x"+limite+"]");
        for (i = 0; i < limite; i++) {
            for (j = 0; j < limite; j++) {
                System.out.println("x["+(i+1)+"," +(j+1)+"]=");
                x[i][j] = lector.nextInt();                
            }            
        }
        
        System.out.println("Ingrese la Matriz Y:"+"Y["+limite+"x"+limite+"]");
        for (i = 0; i < limite; i++) {
            for (j = 0; j < limite; j++) {
                System.out.println("y["+(i+1)+"," +(j+1)+"]=");
                y[i][j] = lector.nextInt();                
            }            
        }
    
    Operacion operacion;
    operacion = new Operacion();

    System.out.println("La Suma es:");
    sxy = operacion.sumarM(x,y);
    
    System.out.println("El Producto es:");
    pxy = operacion.productoM(x,y);
    
    System.out.println("El Determinante es:");
    detx = operacion.detM(x);
    
    System.out.println("La matriz Cofatores es:");
    cofM = operacion.matrizCofactores(x);
    
    
    
    
    }
    
}
