package pe.egcc.cepsuni.ventaapp.prueba;

import java.util.Arrays;
import pe.egcc.cepsuni.ventaapp.dto.ConceptoDto;
import pe.egcc.cepsuni.ventaapp.service.CompAbstract;
import pe.egcc.cepsuni.ventaapp.service.CompFactory;

public class Prueba02 {

  public static void main(String[] args) {
    CompAbstract bean = CompFactory.getComprobante(CompFactory.COMP_BOLETA);
    ConceptoDto[] repo = bean.procesar(356.78);
    Arrays.stream(repo).forEach(dto -> System.out.println(
        dto.getConcepto() + "\t" + dto.getValor()));
  }
  
}
