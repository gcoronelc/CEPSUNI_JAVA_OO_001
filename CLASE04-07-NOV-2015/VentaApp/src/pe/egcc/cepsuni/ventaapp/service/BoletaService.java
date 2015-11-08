package pe.egcc.cepsuni.ventaapp.service;

import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;

public class BoletaService extends CompAbstract {

  @Override
  public ConceptoDto[] procesar(double total) {
    // Variables
    double servicio, totalGeneral;
    // Proceso
    servicio = total * SERVICIO;
    totalGeneral = total + servicio;
    // Reporte
    ConceptoDto[] repo = {
      new ConceptoDto("Total", total),
      new ConceptoDto("Servicio", servicio),
      new ConceptoDto("Total General", totalGeneral)
    };
    return repo;
  }

}
