/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.mbean;


import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.dto.UbigeoDto;
import sys.core.manager.RecursosManager;
import sys.core.manager.UbigeoManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author Indra
 */
@Controller("ubigeoMBean")
@Scope("session")
public class UbigeoMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(UbigeoMBean.class);
    @Resource
    private UbigeoManager manager;
    @Resource
    private RecursosManager recursosManager;
    private List<UbigeoDto> lista;
    private UbigeoDto dto;
    private String keyTitulo = "ubigeo.panel";
    private TreeNode ubigeos;
    private TreeNode selectedUbigeo;
    private UbigeoDto ubigeoPrincipal;
    private UbigeoDto ubigeoSeleccionadoDto;
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;

    public UbigeoMBean() {
    }

    public String iniciar () {
        this.lista = null;     
        this.dto = null;
            
        return ConstantesCore.UrlNavegacion.URL_LISTA_UBIGEOS;
    }
/*    
    public void buscar() {
        try {
            this.ubigeoPrincipal = this.manager.obtenerPorId(ConstantesCore.ValoresDefecto.UBIGEO_PRINCIPAL);            
            this.ubigeos = cargarUbigeos(this.ubigeoPrincipal);
        } catch (DAOException ex) {
            showError("Error de Sistema", ex.getMessage());
        }
    }
 
    private TreeNode cargarUbigeos(UbigeoDto ubigeoPrincipal) {

        TreeNode nodoDefecto = new DefaultTreeNode(new UbigeoDto(), null);
        TreeNode nodoInicial = new DefaultTreeNode(ubigeoPrincipal, nodoDefecto);
        recursividadArbol(nodoInicial);

        return nodoDefecto;
    }

    /*TreeNode recursividadArbol(TreeNode tree) {
        UbigeoDto inicial = (UbigeoDto) tree.getData();
        List<UbigeoDto> hijos = inicial.getListaHijos();
        if (hijos != null && hijos.size() != 0) { //tiene hijos
            
            UtilCore.General.ordena(hijos, "descripcion");
            for (UbigeoDto x : hijos) {
                
                TreeNode t1 = new DefaultTreeNode(x, tree);
                recursividadArbol(t1);
            }
        }
        return null;
    }
 
    public void editarUbigeo() {
        this.ubigeoSeleccionadoDto = (UbigeoDto) selectedUbigeo.getData();
        this.dto = this.ubigeoSeleccionadoDto;
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
    }

    public void nuevaUbigeo() {
        this.ubigeoSeleccionadoDto = (UbigeoDto) selectedUbigeo.getData();
        this.dto = new UbigeoDto();
        this.dto.setEstado(Boolean.TRUE);
        this.dto.setUbigeoPadreDto(this.ubigeoSeleccionadoDto);
        
        if (this.ubigeoSeleccionadoDto.getId().equals(ConstantesCore.ValoresDefecto.UBIGEO_PRINCIPAL)) {
            this.dto.setTipo("PAIS");            
        }else if (this.ubigeoSeleccionadoDto.getTipo().equals("PAIS")) {
            this.dto.setTipo("DEPARTAMENTO");            
        }else if (this.ubigeoSeleccionadoDto.getTipo().equals("DEPARTAMENTO")) {
            this.dto.setTipo("PROVINCIA");            
        }else if (this.ubigeoSeleccionadoDto.getTipo().equals("PROVINCIA")) {
            this.dto.setTipo("DISTRITO");            
        }else if (this.ubigeoSeleccionadoDto.getTipo().equals("DISTRITO")) {
            this.dto.setTipo("ZONA");            
        }else {
            this.dto.setTipo("OTRO");            
        }
        
        sessionMBean.setAccion(ConstantesCore.Formulario.NUEVO);
    }

    public void guardar() {

        String to = null;
        String mensajeTrx = "";

        this.dto.setUsuario(sessionMBean.getSessionUsuarioDto().getId());
        this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
        this.dto.setTerminal(sessionMBean.getSessionTerminal());
        
        //Descripcion completa
        if (this.dto.getUbigeoPadreDto().getId().equals(ConstantesCore.ValoresDefecto.UBIGEO_PRINCIPAL)) {
            this.dto.setDescripcionCompleta(this.dto.getDescripcion());
        }else {
            this.dto.setDescripcionCompleta(this.dto.getDescripcion() + ","  + this.dto.getUbigeoPadreDto().getDescripcionCompleta());
        }
        
        if (sessionMBean.getAccion() == ConstantesCore.Formulario.NUEVO) {
            try {
                this.dto.setUsuarioCreacion(sessionMBean.getSessionUsuarioDto().getId());
                this.dto.setFechaCreacion(UtilCore.Fecha.obtenerFechaActualDate());
                this.dto.setTerminalCreacion(sessionMBean.getSessionTerminal());
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.nuevo(this.dto);
                to = "";
            } catch (Exception ex) {
                logger.error("ERROR DE SISTEMA", ex);
                showError(ex.getMessage());
                to = null;
            }
        }
        if (sessionMBean.getAccion() == ConstantesCore.Formulario.EDITAR) {
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.editar(this.dto);
                to = "";
            } catch (Exception ex) {
                logger.error("ERROR DE SISTEMA", ex);
                showError(ex.getMessage());
                to = null;
            }
        }

        if (to == null) {
            mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.error");
            showError(mensajeTrx);
        } else {
            this.buscar();
            mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
            showMessage(mensajeTrx);

        }

    }
*/
    public ApplicationMBean getApplicationMBean() {
        return applicationMBean;
    }

    public void setApplicationMBean(ApplicationMBean applicationMBean) {
        this.applicationMBean = applicationMBean;
    }

    public UbigeoDto getDto() {
        return dto;
    }

    public void setDto(UbigeoDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<UbigeoDto> getLista() {
        return lista;
    }

    public void setLista(List<UbigeoDto> lista) {
        this.lista = lista;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UbigeoMBean.logger = logger;
    }

    public UbigeoManager getManager() {
        return manager;
    }

    public void setManager(UbigeoManager manager) {
        this.manager = manager;
    }

    public RecursosManager getRecursosManager() {
        return recursosManager;
    }

    public void setRecursosManager(RecursosManager recursosManager) {
        this.recursosManager = recursosManager;
    }

    public TreeNode getSelectedUbigeo() {
        return selectedUbigeo;
    }

    public void setSelectedUbigeo(TreeNode selectedUbigeo) {
        this.selectedUbigeo = selectedUbigeo;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public UbigeoDto getUbigeoPrincipal() {
        return ubigeoPrincipal;
    }

    public void setUbigeoPrincipal(UbigeoDto ubigeoPrincipal) {
        this.ubigeoPrincipal = ubigeoPrincipal;
    }

    public UbigeoDto getUbigeoSeleccionadoDto() {
        return ubigeoSeleccionadoDto;
    }

    public void setUbigeoSeleccionadoDto(UbigeoDto ubigeoSeleccionadoDto) {
        this.ubigeoSeleccionadoDto = ubigeoSeleccionadoDto;
    }

    public TreeNode getUbigeos() {
        return ubigeos;
    }

    public void setUbigeos(TreeNode ubigeos) {
        this.ubigeos = ubigeos;
    }
    
    
}
