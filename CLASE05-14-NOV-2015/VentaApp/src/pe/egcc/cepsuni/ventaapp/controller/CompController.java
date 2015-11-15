package pe.egcc.cepsuni.ventaapp.controller;

import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;
import pe.egcc.cepsuni.ventaapp.service.CompFactory;

public class CompController {

  public static ConceptoDto[] procesar(String tipo, double total) {
    return CompFactory.getComprobante(tipo).procesar(total);
  }

  public String[] traerTipos() {
    String[] tipos = {
      CompFactory.COMP_BOLETA,
      CompFactory.COMP_FACTURA
    };
    return tipos;
  }
  
}
