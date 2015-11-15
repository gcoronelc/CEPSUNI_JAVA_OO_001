/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.util.ConstantesCore;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.UsuarioDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author Indra
 */
@Service
public class UsuarioManager extends ComunManager<UsuarioDto> implements Serializable {

    @Resource
    private DAOGenerico<UsuarioDto> springHibernateDao;

    public List<UsuarioDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(UsuarioDto.class);
    }

    public List<UsuarioDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(UsuarioDto.class, filtros);
    }

    public List<UsuarioDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UsuarioDto.class, filtros, orden);
    }

    public List<UsuarioDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UsuarioDto.class, filtros, orden);
    }

    public List<UsuarioDto> obtenerConsultores() throws DAOException {
        Long entidad = sys.core.util.ConstantesCore.Entidad.USUARIO;
        SessionMBean sessionMBean = (SessionMBean) WebServletContextListener.getApplicationContext().getBean("sessionMBean");
        String hqsql = "select e from UsuarioDto as e, PropietarioDto as pro where "
                + " pro.estado = ? and pro.propietarioDtoPK.entidad = ? and pro.propietarioDtoPK.organizacionDto.id = ?"
                + " and pro.propietarioDtoPK.registro = e.id and e.estado=? and e.rolDto.esConsultorOferta=?";
        List<Object> listaParametros = new ArrayList<Object>();
        listaParametros.add(Boolean.TRUE);
        listaParametros.add(entidad);
        listaParametros.add(sessionMBean.getSessionEmpresaDto().getId());
        listaParametros.add(Boolean.TRUE);
        listaParametros.add(Boolean.TRUE);

        hqsql += " order by e.descripcion asc";
        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public UsuarioDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(UsuarioDto.class, id);
    }

    public List<UsuarioDto> obtenerUsuarios(UsuarioDto dtoFiltro) throws DAOException {
        Long entidad = ConstantesCore.Entidad.USUARIO;
        SessionMBean sessionMBean = (SessionMBean) WebServletContextListener.getApplicationContext().getBean("sessionMBean");
        String hqsql = "select e from UsuarioDto as e where e.id = e.id ";
        List<Object> listaParametros = new ArrayList<Object>();
        if (dtoFiltro.getId() != null && !dtoFiltro.getId().equals(0L)) {
            hqsql += " and e.id = ?";
            listaParametros.add(dtoFiltro.getId());
        } else {
            dtoFiltro.setId(null);
        }
        if (dtoFiltro.getNombresCompletos() != null && dtoFiltro.getNombresCompletos().length() != 0) {
            hqsql += " and upper(e.descripcion) like '%" + dtoFiltro.getNombresCompletos().toUpperCase() + "%'";
        }

        if (dtoFiltro.getLogin() != null && dtoFiltro.getLogin().length() != 0) {
            hqsql += " and upper(e.login) like '%" + dtoFiltro.getLogin().toUpperCase() + "%'";
        }

        hqsql += " order by e.descripcion asc";
        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public List<UsuarioDto> obtenerUsuariosMantenimiento(UsuarioDto dtoFiltro) throws DAOException {
        String hqsql = "select e from UsuarioDto as e where e.id=e.id ";
        List<Object> listaParametros = new ArrayList<Object>();
        if (dtoFiltro.getId() != null && !dtoFiltro.getId().equals(0L)) {
            hqsql += " and e.id = ?";
            listaParametros.add(dtoFiltro.getId());
        } else {
            if (dtoFiltro.getNombresCompletos() != null && dtoFiltro.getNombresCompletos().length() != 0) {
                hqsql += " and upper(e.nombresCompletos) like '%" + dtoFiltro.getNombresCompletos().toUpperCase() + "%'";
            }

            if (dtoFiltro.getLogin() != null && dtoFiltro.getLogin().length() != 0) {
                hqsql += " and upper(e.login) like '%" + dtoFiltro.getLogin().toUpperCase() + "%'";
            }

            if (dtoFiltro.getRolDto().getDescripcion() != null && dtoFiltro.getRolDto().getDescripcion().length() != 0) {
                hqsql += " and upper(e.rolDto.descripcion) like '%" + dtoFiltro.getRolDto().getDescripcion().toUpperCase() + "%'";
            }
        }
        hqsql += " order by e.id asc";
        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public String obtenerCorreoUsuarioxRol(Long idRol) throws DAOException {
        String hqsql = "select dto.emailLaboral from UsuarioDto dto where dto.rolDto.id = " + idRol + " and dto.estado =" + Boolean.TRUE;
        return (springHibernateDao.ejecutarQuery(hqsql)).toString();
    }

    public List<UsuarioDto> obtenerCorreoUsuariosxRol(Long idRol, Long idRol1) throws DAOException {
        String hqsql = "select dto from UsuarioDto dto where dto.rolDto.id = " + idRol + " or dto.rolDto.id = " + idRol1 + " and dto.estado =" + Boolean.TRUE;
        return springHibernateDao.ejecutarQuery(hqsql);
    }

    public List<UsuarioDto> obtenerCorreoEmailStockMinimo(Long idRol, Long idRol1, Long idRol2) throws DAOException {
        String hqsql = "select dto from UsuarioDto dto where dto.rolDto.id = " + idRol + " or dto.rolDto.id = " + idRol1 + " or dto.rolDto.id = " + idRol2 + "and dto.estado =" + Boolean.TRUE + " and dto.id != 1067 and dto.id != 1069";
        return springHibernateDao.ejecutarQuery(hqsql);
    }

    public DAOGenerico<UsuarioDto> getSpringHibernateDao() {
        return springHibernateDao;
    }

    public void setSpringHibernateDao(DAOGenerico<UsuarioDto> springHibernateDao) {
        this.springHibernateDao = springHibernateDao;
    }
}
