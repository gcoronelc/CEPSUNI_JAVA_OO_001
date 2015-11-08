package pe.egcc.cepsuni.ventaapp.service;

public class CompFactory {

  private CompFactory() {
  }
  
  public static final String COMP_FACTURA = "Factura";
  public static final String COMP_BOLETA = "Boleta";
  
  public static CompAbstract getComprobante(String tipo){
    CompAbstract comp = null;
    switch(tipo){
      case COMP_FACTURA:
        comp = new FacturaService();
        break;
      case COMP_BOLETA:
        comp = new BoletaService();
        break;
    }
    return comp;
  }
  
}
