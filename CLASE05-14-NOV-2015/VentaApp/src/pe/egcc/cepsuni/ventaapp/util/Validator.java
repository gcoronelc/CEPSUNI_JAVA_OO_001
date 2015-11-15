package pe.egcc.cepsuni.ventaapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

  private Validator() {
  }

  public static boolean esSoloDigitos(String valor) {
    String patron = "[0-9]*";
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(valor);
    return matcher.matches();
  }

}
