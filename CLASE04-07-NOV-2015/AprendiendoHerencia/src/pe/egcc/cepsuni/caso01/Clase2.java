package pe.egcc.cepsuni.caso01;

public class Clase2 extends Clase1{

  public Clase2() {
    super("Gustavo");
  }
  
  

  @Override
  public int sumar(int n1, int n2) {
    int suma;
    suma = (n1 + n2) * (n1 - n2);
    return suma;
  }
    
  
  
}
