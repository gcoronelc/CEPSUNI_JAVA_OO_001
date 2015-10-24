package pe.edu.cepsuni.mayorapp.prueba;

import pe.edu.cepsuni.mayorapp.service.MateService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.pe
 * @email gcoronelc@gmail.com
 *
 */
public class Prueba01 {

  public static void main(String[] args) {
    
    int n1 = 56, n2 = 98, n3 = 12, n4 = 51;
    
    MateService mateService = new MateService();
    
    System.out.println(mateService.elMayorCaso1(n1, n2, n3, n4));
    System.out.println(mateService.elMayorCaso2(n1, n2, n3, n4));
    System.out.println(mateService.elMayorCaso3(n1, n2, n3, n4));
    System.out.println(mateService.elMayorCaso4(n1, n2, n3, n4));
    
  }
  
}
