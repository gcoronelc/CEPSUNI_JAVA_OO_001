package pe.egcc.prueba;

import pe.egcc.service.MateService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog   http://gcoronelc.blogspot.pe
 * @email  gcoronelc@gmail.com
 */
public class Prueba03 {

  
  public static void main(String[] args) {
    
    MateService mateService = new MateService();
    
    System.out.println("7 + 8 = " + mateService.sumar(7, 8));
    
    System.out.println("7.0 + 8 = " + mateService.sumar(7.0, 8));
    
  }
}
