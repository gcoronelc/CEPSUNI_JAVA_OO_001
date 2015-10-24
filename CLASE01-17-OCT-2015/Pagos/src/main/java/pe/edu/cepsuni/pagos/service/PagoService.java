package pe.edu.cepsuni.pagos.service;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.pe
 * @email gcoronelc@gmail.com
 *
 */
public class PagoService {
  
  public double calcularImpuesto(double importe){
    double impuesto;
    impuesto = importe * 0.18;
    return impuesto;
  }
  
  public double calcularTotal(double importe){
    double total;
    total = importe + calcularImpuesto(importe);
    return total;
  }
  
}
