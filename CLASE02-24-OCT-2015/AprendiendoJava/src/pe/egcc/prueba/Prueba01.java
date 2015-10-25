package pe.egcc.prueba;

import pe.egcc.model.Producto;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog   http://gcoronelc.blogspot.pe
 * @email  gcoronelc@gmail.com
 */
public class Prueba01 {

  public static void main(String[] args) {
    
    Producto producto = new Producto();
    
    mostrar(producto);
    
    producto.setCodigo(400);
    producto.setNombre("Free Tea");
    producto.setPrecio(2.00);
    producto.setActivo(true);
    
    mostrar(producto);
    
    Producto pAux = producto;
    
    mostrar(pAux);
    
    pAux.setNombre("Coca Cola");
    
    mostrar(producto);
    
  }

  private static void mostrar(Producto producto) {
    System.out.println("--------------------------------");
    System.out.println("Codigo: " + producto.getCodigo());
    System.out.println("Nombre: " + producto.getNombre());
    System.out.println("Precio: " + producto.getPrecio());
    System.out.println("Activo: " + producto.isActivo());
    System.out.println("--------------------------------");
  }
  
}
