package pe.egcc.cepsuni.ventaapp.dto;

public class ConceptoDto {

  private String concepto;
  private double valor;

  public ConceptoDto() {
  }

  public ConceptoDto(String concepto, double valor) {
    this.concepto = concepto;
    this.valor = valor;
  }

  public String getConcepto() {
    return concepto;
  }

  public void setConcepto(String concepto) {
    this.concepto = concepto;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

}
