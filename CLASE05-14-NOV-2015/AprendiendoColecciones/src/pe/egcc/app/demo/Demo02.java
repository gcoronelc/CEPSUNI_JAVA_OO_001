package pe.egcc.app.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {

  
  public static void main(String[] args) {
    List<String> ciudades = new ArrayList<>();
    
    ciudades.add("Chiclayo");
    ciudades.add("Cuzco");
    ciudades.add("Arequipa");
    ciudades.add("Iquitos");
    ciudades.add("Huancayo");
    
    ciudades.add(1, "Trujillo");
    
    ciudades.set(4, "Tarapoto");
    
    ciudades.remove(3);
    
    for (String ciudad : ciudades) {
      System.out.println(ciudad);
    }
    
  }
}
