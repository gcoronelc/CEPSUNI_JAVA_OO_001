package pe.egcc.model;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog http://gcoronelc.blogspot.pe
 * @email gcoronelc@gmail.com
 */
public class Producto {

  private int codigo;
  private String nombre;
  private double precio;
  private boolean activo;

  public Producto() {
    this.codigo = 1000;
    this.nombre = "Cafe";
    this.precio = 2.0;
    this.activo = true;
    System.out.println("Objeto creado");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); 
    System.err.println("Chau objeto.");
  }
  
  

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

}
