package pe.egcc.cepsuni.caso01;

public class Clase1 {

  public Clase1() {
    System.out.println("Hola desde Clase1");
  }

  public Clase1(String nombre){
    System.out.println("Hola " + nombre);
  }
  
  public int sumar(int n1, int n2) {
    return (n1 + n2);
  }

  public long factorial(int n) {
    long f = 1;
    while (n > 1) {
      f *= n--;
    }
    return f;
  }

}
