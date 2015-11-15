package sys.core.util;

/**
 *
 * @author admin
 */
public class ConstantesCore {

    
    public static final String VARIABLE_ID_MENU = "SubMenu";
    public static final String VARIABLE_ID_MENU_ITEM = "MenuItem";
    public static final String LOG_REAUNI = "/reauni.log";

    public static class Entidad {

        public static final Long CATALOGO_PARAMETRO = 2L;
        public static final Long PARAMETRO = 3L;
        public static final Long EMPRESA = 4L;
        public static final Long OPCION_SISTEMA = 5L;
        public static final Long UBIGEO = 6L;
        public static final Long USUARIO = 7L;
        public static final Long PERMISO = 8L;
        public static final Long ROL = 9L;
        public static final Long BITACORA = 10L;
    }

    public static class Parametro {

        public static final Long PARAMETRO_USUARIO_ACTIVO = 1L;
        public static final Long PARAMETRO_USUARIO_DESACTIVO = 2L;
        public static final Long PARAMETRO_CATALOGO_PARAMETRO_ACTIVO = 9L;
        public static final Long EMPRESA_ESTADO_ACTIVO = 11L;
        public static final Long EMPRESA_FAST_REMISSE_ID = 1L;
        public static final Long PARAMETRO_GENERAL_RUTA_ARCHIVOS = 70L;
        public static final Long PARAMETRO_GENERAL_LOG = 14L;
        public static final Long PARAMETRO_GENERAL_RUTA_JASPERS = 30024L;
        public static final Long PARAMETRO_GENERAL_FORMATO_MONTOS_OFERTA = 30025L;
        public static final Long PARAMETRO_GENERAL_PRECISION_DECIMAL = 30026L;
        public static final Long PARAMETRO_GENERAL_PRECISION_DECIMAL_OFERTA = 30027L;
        public static final Long PARAMETRO_GENERAL_FORMATO_MONTOS_TIPO_CAMBIO = 30028L;
        public static final Long PARAMETRO_GENERAL_FORMATO_MONTOS = 30029L;
        public static final Long PARAMETRO_GENERAL_FORMATO_HORA = 30030L;
        public static final Long PARAMETRO_GENERAL_FORMATO_FECHA = 30031L;
        public static final Long PARAMETRO_GENERAL_FORMATO_FECHA_HORA_SEGUNDO = 30032L;
        public static final Long PARAMETRO_GENERAL_FORMATO_FECHA_HORA_MINUTO = 30033L;
        public static final Long PARAMETRO_TIPO_UBIGEO_PAIS = 1L;
        public static final Long PARAMETRO_TIPO_UBIGEO_DEPARTAMENTO = 30033L;
        public static final Long PARAMETRO_TIPO_UBIGEO_DISTRITO = 30033L;
        public static final Long PARAMETROS_GENERALES = 0L;
        public static final Long SELECCIONAR = 13L;
        public static final Long ESTADO_ACTIVO = 1L;
           
    }

    public static class UrlNavegacion {

        public static final String URL_INICIO = "/inicio.xhtml";
        //CORE USUARIO
        public static final String URL_USUARIO_FORMATEAR_CONTRASENA = "/pages/core/seguridad/formatearContrasena.xhtml";
        public static final String URL_USUARIO_CAMBIAR_CONTRASENA = "/pages/seguridad/usuarios/cambiarContrasena.xhtml";
        public static final String URL_USUARIO = "/pages/seguridad/usuarios/usuario.xhtml";
        public static final String URL_LISTA_USUARIOS = "/pages/seguridad/usuarios/listaUsuarios.xhtml";
        public static final String URL_USUARIO_EXTERNO = "/pages/movil/usuarioExterno/usuarioExterno.xhtml";
        public static final String URL_LISTA_USUARIOS_EXTERNOS = "/pages/movil/usuarioExterno/listaUsuariosExternos.xhtml";
        public static final String URL_LISTA_SOLICITUDES_SERVICIOS = "/pages/movil/solicitudServicio/listaSolicitudesServicios.xhtml";
        public static final String URL_SOLICITUD_SERVICIO = "/pages/movil/solicitudServicio/solicitudServicio.xhtml";
        public static final String URL_LISTA_MONITOREO_SERVICIOS = "/pages/movil/solicitudServicio/listaMonitoreoServicio.xhtml";
        public static final String URL_ROL = "/pages/seguridad/roles/rol.xhtml";
        public static final String URL_LISTA_ROLES = "/pages/seguridad/roles/listaRoles.xhtml";
        public static final String URL_CATALOGO_PARAMETRO = "/pages/core/catalogoParametro/catalogoParametro.xhtml";
        public static final String URL_LISTA_CATALOGO_PARAMETRO = "/pages/core/catalogoParametro/listaCatalogosParametro.xhtml";
        public static final String URL_PARAMETRO = "/pages/core/catalogoParametro/parametro.xhtml";
        public static final String URL_EMPRESA = "/pages/core/empresas/empresa.xhtml";
        public static final String URL_LISTA_EMPRESAS = "/pages/core/empresas/listaEmpresas.xhtml";
        public static final String URL_LISTA_UBIGEOS = "/pages/core/ubigeo/listaUbigeos.xhtml";
    }

    public static class Formulario {

        public static final int LISTA = 5;
        public static final int NUEVO = 1;
        public static final int EDITAR = 2;
        public static final int VER = 3;
        public static final int HABILITAR = 0;
        public static final int DESHABILITAR = 1;
    }

    public static class CatalogoParametro {

        public static final Long USUARIO_ESTADOS = 1L;
        public static final Long PARAMETRO_ESTADOS = 2L;
        public static final Long ROL_ESTADOS = 3L;
        public static final Long UBIGEO_ESTADOS = 4L;
        public static final Long CATALOGO_PARAMETRO_ESTADOS = 5L;
        public static final Long EMPRESA_ESTADOS = 6L;
        public static final Long PARAMETROS_GENERALES = 20L;
        public static final Long TIPO_DOCUMENTOS = 1002L;
        public static final Long MARCA_AUTOS = 1001L;
        public static final Long TIPO_AUTOS = 1003L;
    }
    
    public static class Imagenes {
        public static final String URL_LOGO_MARKERS = "resources/images/icons/fast_remisse_logo_icon.png";
    }
}
