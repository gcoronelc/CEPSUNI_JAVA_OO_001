package pe.egcc.app.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo01 {

  public static void main(String[] args) {
    // Lista generica
    List lista = new ArrayList();
    
    lista.add(3456);
    lista.add(0345);
    lista.add(4356.78);
    lista.add("Hola");
    lista.add(new Random());
    
    
    for (int i = 0; i < lista.size(); i++) {
      System.out.println(lista.get(i));      
    }
  }
  
}
