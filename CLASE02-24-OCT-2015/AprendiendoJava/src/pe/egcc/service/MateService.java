package pe.egcc.service;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog   http://gcoronelc.blogspot.pe
 * @email  gcoronelc@gmail.com
 */
public class MateService {
  
  public int sumar(int n1, int n2){
    System.out.println("Suma entera.");
    return (n1 + n2);
  }
  
  public long sumar(long n1, long n2){
    return (n1 + n2);
  }
  
  public double sumar(double n1, double n2){
    System.out.println("Suma double.");
    return (n1 + n2);
  }

  public float sumar(float n1, float n2){
    return (n1 + n2);
  }

}
