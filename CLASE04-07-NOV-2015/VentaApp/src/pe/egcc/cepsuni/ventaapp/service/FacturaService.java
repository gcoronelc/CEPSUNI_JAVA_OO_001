package pe.egcc.cepsuni.ventaapp.service;

import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;

public class FacturaService extends CompAbstract{

  @Override
  public ConceptoDto[] procesar(double total) {
    // Variables
    double consumo, impuesto, servicio, totalGeneral;
    // Proceso
    consumo = total / (1 + IGV);
    impuesto = total - consumo;
    servicio = total * SERVICIO;
    totalGeneral = total + servicio;
    // Reporte
    ConceptoDto[] repo = {
      new ConceptoDto("Consumo", consumo),
      new ConceptoDto("Impuesto", impuesto),
      new ConceptoDto("Total", total),
      new ConceptoDto("Servicio", servicio),
      new ConceptoDto("Total General", totalGeneral)
    };
    return repo;
  }

  
  
}
