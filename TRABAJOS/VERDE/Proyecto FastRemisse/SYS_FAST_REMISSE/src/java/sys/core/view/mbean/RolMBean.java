/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;



import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.ApplicationHelper;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.OpcionSistemaDto;
import sys.core.dto.PermisoDto;
import sys.core.dto.RolDto;
import sys.core.exception.DAOException;
import sys.core.manager.OpcionSistemaManager;
import sys.core.manager.PermisoManager;
import sys.core.manager.RecursosManager;
import sys.core.manager.RolManager;
import sys.core.util.ConstantesCore;
 
@Controller("rolMBean")
@Scope("session")
public class RolMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(RolMBean.class);
    
    @Resource
    private RolManager manager;
    @Resource
    private OpcionSistemaManager managerOS;
    @Resource
    private PermisoManager managerPermiso;
    @Resource
    private RecursosManager recursosManager;
     
    private List<RolDto> lista;
    private RolDto dto;
    private RolDto dtoFiltro;
    private String keyTitulo = "entidad.panel";
    private List<PermisoDto> listaPermisos;
    
    private TreeNode permisos;
    private TreeNode selectedOpcion;
    private OpcionSistemaDto osPrincipal;
    private OpcionSistemaDto osSeleccionado;
    
    @Autowired
    private ApplicationMBean applicationMBean;
    
    @Autowired
    private SessionMBean sessionMBean;

    public RolMBean() {
        this.dtoFiltro = new RolDto();        
    }
    
    public String iniciar () {
        this.lista = null;     
        this.dto = null;
        this.dtoFiltro = new RolDto();       
        this.dtoFiltro.setDescripcion("");
        return ConstantesCore.UrlNavegacion.URL_LISTA_ROLES;
    }
    
    private TreeNode cargarOpcionesSistema(OpcionSistemaDto osPrincipal) {        
        TreeNode nodoDefecto = new DefaultTreeNode(new PermisoDto(), null);
        TreeNode nodoInicial = new DefaultTreeNode(osPrincipal, nodoDefecto);
        recursividadArbol(nodoInicial);
        return nodoDefecto;
    }   
    

    TreeNode recursividadArbol(TreeNode tree) {
        OpcionSistemaDto inicial = (OpcionSistemaDto) tree.getData();
        List<OpcionSistemaDto> hijos = inicial.getListaHijos();
        if (hijos != null && hijos.size() != 0) { //tiene hijos            
            UtilCore.General.ordena(hijos, "descripcion");
            for (OpcionSistemaDto x : hijos) {       
                x.setPermiso(obtenerPermisoRol(x).getEstado());
                TreeNode t1 = new DefaultTreeNode(x, tree);
                recursividadArbol(t1);
            }
        }
        return null;
    }
    
    PermisoDto obtenerPermisoRol (OpcionSistemaDto os) {
     PermisoDto p = null;
     for (PermisoDto x : this.listaPermisos) {
         if (x.getOpcionSistemaDto().getId().equals(os.getId())) {
             p=x;
             break;
         }
     }
     return p;
    }

    public String nuevo() {
        sessionMBean.setAccion(ConstantesCore.Formulario.NUEVO);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));        
        
        this.dto = new RolDto();
        this.dto.setEstado(Boolean.TRUE);
        return ConstantesCore.UrlNavegacion.URL_ROL;
    }

    public String editar() {
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));  
     
        try {
            this.listaPermisos =  managerPermiso.permisosPorRol(Long.valueOf(this.dto.getId().toString()));
            this.osPrincipal = managerOS.obtenerPorId(0L);
            this.osPrincipal.setPermiso(obtenerPermisoRol(osPrincipal).getEstado());
            this.permisos = cargarOpcionesSistema(this.osPrincipal);            
        } catch (DAOException ex) {
            logger.error(ex);
        }
        
        return ConstantesCore.UrlNavegacion.URL_ROL;
    }
    
    

    public String ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
    /*    ApplicationHelper.cargarPropietarios(ConstantesCore.Entidad.ROL, this.dto.getId());
        ApplicationHelper.cargarBitacoraEntidad(ConstantesCore.Entidad.ROL, this.dto.getId()); */
        try {
            this.osPrincipal = managerOS.obtenerPorId(0L);
            cargarOpcionesSistema(this.osPrincipal);            
        } catch (DAOException ex) {
            logger.error(ex);
        }
        return ConstantesCore.UrlNavegacion.URL_ROL;
    }

    public void buscar() {
        try {
            /*
            Map<String,Object> mapFiltro = new HashMap<String, Object>();
            if (this.dtoFiltro.getDescripcion() != null && this.dtoFiltro.getDescripcion().length()>0)
                mapFiltro.put("descripcion", this.dtoFiltro.getDescripcion());
            Map<String,String> mapOrden = new HashMap<String, String>();
                mapOrden.put("descripcion", "asc");
            
            this.lista = this.manager.obtenerConFiltroConOrden(mapFiltro, mapOrden);*/
            this.lista = this.manager.obtenerRolesMantenimiento(this.dtoFiltro);
        } catch (DAOException ex) {
            showError("Error de Sistema", ex.getMessage());
        }
    }
    
    public void cambiarEstado(){
        System.out.println("Entro al metodo changeState");
        this.osSeleccionado = (OpcionSistemaDto) this.selectedOpcion.getData();
        if (!osSeleccionado.getId().equals(0L)) 
            this.osSeleccionado.setPermiso(!this.osSeleccionado.getPermiso());
        PermisoDto p = obtenerPermisoRol(this.osSeleccionado);
        try {
            p.setEstado(this.osSeleccionado.getPermiso());
            this.managerPermiso.editar(p);
            MenuMBean menuMBean = (MenuMBean) WebServletContextListener.getApplicationContext().getBean("menuMBean");
            menuMBean.getPrincipalMenuBar();
        } catch (DAOException ex) {
            logger.error(ex);
        }
    }

    public String guardar() {
        
        String to = null;
        String mensajeTrx = "";
        
        this.dto.setUsuario(this.sessionMBean.getSessionUsuarioDto().getId());
        this.dto.setTerminal(this.sessionMBean.getSessionTerminal());
        
      
        
        if (sessionMBean.getAccion() == ConstantesCore.Formulario.NUEVO) {            
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.nuevo(this.dto);  
                to = ConstantesCore.UrlNavegacion.URL_LISTA_ROLES;
            } catch (Exception ex) {          
                logger.error("ERROR DE SISTEMA", ex);
                mensajeTrx = ex.getMessage();
                to = null;
            }
        } if (sessionMBean.getAccion() == ConstantesCore.Formulario.EDITAR) {  
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.editar(this.dto);                
                to = ConstantesCore.UrlNavegacion.URL_LISTA_ROLES;
            } catch (Exception ex) {          
                logger.error("ERROR DE SISTEMA", ex);
                mensajeTrx = ex.getMessage();
                to = null;
            }
        }

        if (to == null) {            
            showError(mensajeTrx);
        } else {
            mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
            showMessage(mensajeTrx);
            
        }

        return to;
    }

    public String retroceder() {
        return ConstantesCore.UrlNavegacion.URL_LISTA_ROLES;
    }

    public ApplicationMBean getApplicationMBean() {
        return applicationMBean;
    }

    public void setApplicationMBean(ApplicationMBean applicationMBean) {
        this.applicationMBean = applicationMBean;
    }

    public RolDto getDto() {
        return dto;
    }

    public void setDto(RolDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<RolDto> getLista() {
        return lista;
    }

    public void setLista(List<RolDto> lista) {
        this.lista = lista;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RolMBean.logger = logger;
    }

    public RolManager getManager() {
        return manager;
    }

    public void setManager(RolManager manager) {
        this.manager = manager;
    }

    public RecursosManager getRecursosManager() {
        return recursosManager;
    }

    public void setRecursosManager(RecursosManager recursosManager) {
        this.recursosManager = recursosManager;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public RolDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(RolDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }

    public List<PermisoDto> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<PermisoDto> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public OpcionSistemaManager getManagerOS() {
        return managerOS;
    }

    public void setManagerOS(OpcionSistemaManager managerOS) {
        this.managerOS = managerOS;
    }

    public PermisoManager getManagerPermiso() {
        return managerPermiso;
    }

    public void setManagerPermiso(PermisoManager managerPermiso) {
        this.managerPermiso = managerPermiso;
    }
    public OpcionSistemaDto getOsPrincipal() {
        return osPrincipal;
    }

    public void setOsPrincipal(OpcionSistemaDto osPrincipal) {
        this.osPrincipal = osPrincipal;
    }

    public TreeNode getPermisos() {
        return permisos;
    }

    public void setPermisos(TreeNode permisos) {
        this.permisos = permisos;
    }

    public TreeNode getSelectedOpcion() {
        return selectedOpcion;
    }

    public void setSelectedOpcion(TreeNode selectedOpcion) {
        this.selectedOpcion = selectedOpcion;
    }

    public OpcionSistemaDto getOsSeleccionado() {
        return osSeleccionado;
    }

    public void setOsSeleccionado(OpcionSistemaDto osSeleccionado) {
        this.osSeleccionado = osSeleccionado;
    }

    
    
}
