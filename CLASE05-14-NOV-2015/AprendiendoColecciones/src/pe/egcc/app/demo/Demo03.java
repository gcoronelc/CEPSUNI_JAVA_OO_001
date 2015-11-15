package pe.egcc.app.demo;

import java.util.HashMap;
import java.util.Map;

public class Demo03 {

  public static void main(String[] args) {
    
    Map<String,Object> datos = new HashMap<>();
    
    datos.put("A1", "Cebiche");
    datos.put("A2", "Seco de Cabrito");
    datos.put("A3", "Espesado");
    datos.put("A4", "Causa Ferreñafana");
    datos.put("A5", "Cambar Trujillano");
    
    datos.put("A3", "Pepian de Pavita");
    
    datos.remove("A5");
    
    for(String key: datos.keySet()){
      System.out.println(key + " - " + datos.get(key));
    }
    
  }
}
