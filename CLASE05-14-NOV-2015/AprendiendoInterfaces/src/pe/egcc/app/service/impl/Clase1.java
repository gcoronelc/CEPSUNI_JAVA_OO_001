package pe.egcc.app.service.impl;

import pe.egcc.app.service.espec.ICaso1;
import pe.egcc.app.service.espec.ICaso2;

public class Clase1 implements ICaso1, ICaso2{

  @Override
  public void metodo1() {
    System.out.println("Hola desde Clase1.metodo1");
  }

  @Override
  public void metodo2() {
    System.out.println("Hola desde Clase1.metodo2");
  }
  
}
