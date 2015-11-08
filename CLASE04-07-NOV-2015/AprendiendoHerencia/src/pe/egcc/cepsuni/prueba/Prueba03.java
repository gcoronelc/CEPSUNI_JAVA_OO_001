package pe.egcc.cepsuni.prueba;

public class Prueba03 {

  
  public static void main(String[] args) {
    int[] lista = {25,47,69,36,14,52};
    
    for (int i = 0; i < lista.length; i++) {
      int m = lista[i];
      System.out.println(m);
    }
    
    System.out.println("---------");
    for (int m : lista) {
      System.out.println(m);
    }
  }
}
