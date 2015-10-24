package pe.edu.cepsuni.mayorapp.service;

import java.util.Arrays;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.pe
 * @email gcoronelc@gmail.com
 *
 */
public class MateService {

  public int elMayorCaso1(int n1, int n2, int n3, int n4){
    // Punto de partida
    // n1 es el numero mayor
    int mayor = n1;
    // Proceso
    if(mayor < n2){
      mayor = n2;
    }
    if(mayor < n3){
      mayor = n3;
    }
    if(mayor < n4){
      mayor = n4;
    }
    // Retornar el mayor
    return mayor;
  }
  
  public int elMayorCaso2(int n1, int n2, int n3, int n4){
    // Punto de partida
    // de n1 y n2 obtendo el numero mayor
    int mayor = Math.max(n1, n2);
    // Proceso
    mayor = Math.max(mayor, n3);
    mayor = Math.max(mayor, n4);
    // Retornar el mayor
    return mayor;
  }
  
  public int elMayorCaso3(int n1, int n2, int n3, int n4){
    // crear un arreglo con los números
    int[] datos = {n1,n2,n3,n4};
    // Ordeno el arreglo
    Arrays.sort(datos);
    // Obtener el mayor
    int mayor = datos[3];
    // Retornar el mayor
    return mayor;
  }
  
  public int elMayorCaso4(int ... datos){
    // Ordeno el arreglo
    Arrays.sort(datos);
    // Obtener el mayor
    int mayor = datos[3];
    // Retornar el mayor
    return mayor;
  }
  
}
