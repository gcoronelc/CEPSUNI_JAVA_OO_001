package pe.egcc.cepsuni.mateapp.util;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog   http://gcoronelc.blogspot.pe
 * @email  gcoronelc@gmail.com
 */
public final class MyMath {

  private MyMath() {
  }

  public static long factorial(int n){
    long f = 1;
    while(n > 1){
      f *= n--;
    }
    return f;
  }
  
  public static int mcd(int n1, int n2){
    boolean sonIguales = false;
    while( !sonIguales ){
      if(n1 == n2){
        sonIguales = true;
      } else if(n1>n2){
        n1 -= n2; // n1 = n1 - n2
      } else {
        n2 -= n1; // n2 = n2 - n1
      }
    }
    return n1;
  }
  
  public static int mcm(int n1, int n2){
    int value;
    value = n1 * n2 / mcd(n1, n2);
    return value;
  }
  
  public static String fibonacci(int n){
    
    return "";
  }
  
  public static boolean esPrimo(int n){
    
    return false;
  }
}
