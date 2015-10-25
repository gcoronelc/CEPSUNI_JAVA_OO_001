package pe.egcc.pagoapp.prueba;

import pe.egcc.pagoapp.dto.PagoDto;
import pe.egcc.pagoapp.service.PagoService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog   http://gcoronelc.blogspot.pe
 * @email  gcoronelc@gmail.com
 */
public class Prueba01 {
  
  public static void main(String[] args) {
    // Dato
    PagoDto pagoDto = new PagoDto();
    pagoDto.setHorasDia(6);
    pagoDto.setDias(20);
    pagoDto.setPagoHora(80.0);
    // Proceso
    PagoService pagoService = new PagoService();
    pagoService.procesar(pagoDto);
    // Reporte
    System.out.println("Ingresos: " + pagoDto.getIngresos());
    System.out.println("Renta: " + pagoDto.getRenta());
    System.out.println("Neto: " + pagoDto.getNeto());
  }

}
