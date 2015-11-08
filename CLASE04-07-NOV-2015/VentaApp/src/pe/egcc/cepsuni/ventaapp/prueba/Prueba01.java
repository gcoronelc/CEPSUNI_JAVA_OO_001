package pe.egcc.cepsuni.ventaapp.prueba;

import java.util.Arrays;
import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;
import pe.egcc.cepsuni.ventaapp.service.BoletaService;
import pe.egcc.cepsuni.ventaapp.service.CompAbstract;

public class Prueba01 {

  public static void main(String[] args) {
    CompAbstract bean = new BoletaService();
    ConceptoDto[] repo = bean.procesar(356.78);
    Arrays.stream(repo).forEach(dto -> System.out.println(
        dto.getConcepto() + "\t" + dto.getValor()));
  }
  
}
