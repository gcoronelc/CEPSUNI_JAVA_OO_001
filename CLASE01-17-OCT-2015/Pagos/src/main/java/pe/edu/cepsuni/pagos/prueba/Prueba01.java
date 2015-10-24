package pe.edu.cepsuni.pagos.prueba;

import pe.edu.cepsuni.pagos.service.PagoService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.pe
 * @email gcoronelc@gmail.com
 *
 */
public class Prueba01 {
  
  public static void main(String[] args) {
    // Datos
    double importe = 1000.00;
    // Proceso
    PagoService pagoService;
    pagoService = new PagoService();
    double impuesto = pagoService.calcularImpuesto(importe);
    double total = pagoService.calcularTotal(importe);
    // Reporte
    System.out.println("Importe: " + importe);
    System.out.println("Impuesto: " + impuesto);
    System.out.println("Total: " + total);
  }

}
