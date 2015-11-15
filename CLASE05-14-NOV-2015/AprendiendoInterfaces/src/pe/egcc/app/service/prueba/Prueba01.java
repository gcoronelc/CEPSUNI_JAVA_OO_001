package pe.egcc.app.service.prueba;

import pe.egcc.app.service.espec.ICaso1;

public class Prueba01 {

  public static void main(String[] args) {
    ICaso1 caso1 = new ICaso1() {

      @Override
      public void metodo1() {
        System.out.println("Qué es esto?");
      }
    };
    
    caso1.metodo1();
  }
  
}
