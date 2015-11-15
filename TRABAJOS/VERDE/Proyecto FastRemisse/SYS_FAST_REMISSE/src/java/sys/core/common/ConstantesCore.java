package sys.core.common;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Calendar;

public class ConstantesCore {

    public static final String FORMATO_FECHA_HORA = "dd-MM-yyyy HH:mm:ss";
    public static final String SEPARADOR_LOG = "::";
    public static final boolean logError = true;
    public static final boolean logDebug = true;
    public static final boolean logWarn = true;
    public static final boolean logInfo = true;
    public static final int nuevo = 1;
    public static final int editar = 2;
    public static final int ver = 3;
    public static final int lista = 0;
    public static final int listaDetalle = 4;
    public static final int cancelar = 5;
    public static final int reportar = 6;
    public static final BigInteger trxOk = new BigInteger("1");
    public static final BigInteger trxError = new BigInteger("-1");
    public static final String separadorLog = "::";
    public static String formatoFechaHora = "dd-MM-yyyy HH:mm:ss";
    public static String formatoFecha = "dd-MM-yyyy";
    public static String formatoHora = "HH:mm:ss";
    public static final String directorioPrincipal = "";
    public static final String separadorArchivoAbrir = "[";
    public static final String separadorArchivoCerrar = "]";
    public static final String DataSourceInitContext = "java:/comp/env";
    public static final String DataSourceEnvContext = "jdbc/siondb";
    public static DecimalFormat dfDouble, dfInteger;
    public static Calendar calendario = Calendar.getInstance();
    public static final String ES_FORMATO_EMAIL = "[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[[a-zA-Z0-9_]+.[a-zA-Z0-9]+]+";
    public static final String ES_FORMATO_ENTERO = "[0-9]+";
    public static final String ES_FORMATO_REAL = "[0-9]+|[0-9]+[.][0-9]+";
    public static final String ES_FORMATO_ENTERO_SIGNO = "[+-]*[0-9]+";
    public static final String ES_FORMATO_REAL_SIGNO = "[+-]*[0-9]+|[+-]*[0-9]+[.][0-9]+";
    public static final String ES_FORMATO_FECHA = "";
    public static final String ES_FORMATO_HORA = "[0-9]{2}:[0-9]{2}";
    public static final String ES_FORMATO_TEXTO = "[a-zA-Z������������ ]+";
    public static final String ES_FORMATO_ALFA_NUMERICO = "[0-9a-zA-Z������������ ]+";
    public static final String ES_FORMATO_SOLO_ALFA_NUMERICO = "[^0-9a-zA-Z������������ ]+";
    public static final String[] UNIDADES = {"", "UN ", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
        "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE"};
    public static final String[] DECENAS = {"VENTI", "TREINTA ", "CUARENTA ",
        "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
        "CIEN "};
    public static final String[] CENTENAS = {"CIENTO ", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS "};
    public static final String[] DIAS_SEMANA = {"DOMINGO","LUNES", "MARTES",
        "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
    public static final String[] MESES_ANIO = {"ENERO","FEBRERO", "MARZO",
        "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    
    
    public static class Form {

        public final static int NUEVO = 1;
        public final static int EDITAR = 2;
        public final static int VER = 3;
        public final static int LISTA = 0;
    }
    
    public static class ExpresionQuartzCodigos {
        public final static int TODOS = -1;
        public final static int INCOGNITA = -2;
        public final static int ULTIMO = -20;
        public final static int PRIMERO = -10;
    }
    
    public static class ExpresionQuartzEtiquetas {
        public final static String TODOS = " * ";
        public final static String INCOGNITA = " ? ";
        public final static String ULTIMO = " L ";
        public final static String PRIMERO = " P ";
    }
}
