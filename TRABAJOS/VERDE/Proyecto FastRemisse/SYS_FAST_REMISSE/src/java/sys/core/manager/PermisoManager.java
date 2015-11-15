/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.dto.PermisoDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author admin
 */
@Service
public class PermisoManager extends ComunManager<PermisoDto> implements Serializable {

    @Resource
    private DAOGenerico<PermisoDto> springHibernateDao;

    public List<PermisoDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(PermisoDto.class);
    }

    public List<PermisoDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(PermisoDto.class, filtros);
    }

    public List<PermisoDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(PermisoDto.class, filtros, orden);
    }

    public List<PermisoDto> permisosPorRol(Long rolId) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("rolDto.id", rolId);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("opcionSistemaDto.descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(PermisoDto.class, filtros, orden);
    }

    public PermisoDto obtenerPermisoOpcionRol(Long rolId, Long opcionSistemaId) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("rolDto.id", rolId);
        filtros.put("opcionSistemaDto.id", opcionSistemaId);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("opcionSistemaDto.descripcion", "asc");
        List<PermisoDto> l = springHibernateDao.obtenerDtosConFiltrosConOrden(PermisoDto.class, filtros, orden);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public PermisoDto obtenerPermisoRolReferencia(Long rolId, Long referenciaId) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("rolDto.id", rolId);
        filtros.put("opcionSistemaDto.referencia", referenciaId);
        filtros.put("opcionSistemaDto.estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("opcionSistemaDto.descripcion", "asc");
        List<PermisoDto> l = springHibernateDao.obtenerDtosConFiltrosConOrden(PermisoDto.class, filtros, orden);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public PermisoDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(PermisoDto.class, id);
    }

}
