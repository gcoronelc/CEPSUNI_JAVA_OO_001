package pe.edu.cepsuni.pagos;

import pe.edu.cepsuni.pagos.view.PagoView;

public class ClasePrincipal {

  public static void main(String[] args) {
    System.out.println("Hola CEPS.");
    
    // Tecnica 1
    /*
    PagoView pagoView;
    pagoView = new PagoView();
    pagoView.setVisible(true);
    */
    
    // Tecnica 2
    PagoView.main(null);
    
  }
  
}
