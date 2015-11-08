package pe.egcc.cepsuni.prueba;

import pe.egcc.cepsuni.caso01.Clase1;
import pe.egcc.cepsuni.caso01.Clase2;
import pe.egcc.cepsuni.caso01.Clase3;
import pe.egcc.cepsuni.caso01.Clase4;

public class Prueba02 {
  
  public static void main(String[] args) {
    Clase1 objA = new Clase4();
    Clase4 objB = (Clase4) objA;
    
    if(objA instanceof Clase1){
      System.out.println("Compatible con Clase1.");
    }
    if(objA instanceof Clase2){
      System.out.println("Compatible con Clase2.");
    }
    if(objA instanceof Clase3){
      System.out.println("Compatible con Clase3.");
    }
    if(objA instanceof Clase4){
      System.out.println("Compatible con Clase4.");
    }
  }
}
