package pe.egcc.cepsuni.ventaapp.service;

import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;

public abstract class CompAbstract {
  
  public static final double IGV = 0.18;
  public static final double SERVICIO = 0.10;
  
  public abstract ConceptoDto[] procesar(double total);
  
}
