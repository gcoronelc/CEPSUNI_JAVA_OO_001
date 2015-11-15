/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sys.core.dto.EmpresaDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author admin
 */
@Service
public class EmpresaManager extends ComunManager<EmpresaDto> implements Serializable {

    @Resource
    private DAOGenerico<EmpresaDto> springHibernateDao;

    private static Logger logger = Logger.getLogger(EmpresaManager.class);

    public List<EmpresaDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(EmpresaDto.class);
    }

    public List<EmpresaDto> obtenerTodosPorEstado(Long estado) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estadoDto.id", estado);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(EmpresaDto.class, filtros, orden);
    }

    public List<EmpresaDto> obtenerTodosPorEstado(Long estado, String descripcion) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estadoDto.id", estado);
        filtros.put("descripcion", descripcion);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(EmpresaDto.class, filtros, orden);
    }

    public List<EmpresaDto> obtenerEmpresaPorId(Long id) {
        List<EmpresaDto> l = new ArrayList<EmpresaDto>();
        try {
            Map<String, Object> filtros = new HashMap<String, Object>();
            filtros.put("id", id);
            l = springHibernateDao.obtenerDtosConFiltros(EmpresaDto.class, filtros);
        } catch (DAOException ex) {

        }
        return l;
    }

    public List<EmpresaDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(EmpresaDto.class, filtros);
    }

    public List<EmpresaDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(EmpresaDto.class, filtros, orden);
    }

    public List<EmpresaDto> obtenerEmpresas(EmpresaDto dtoFiltro) throws DAOException {
        List<EmpresaDto> lista = null;
        Map<String, Object> mapFiltro = new HashMap<String, Object>();

        if (dtoFiltro.getId() != null && !dtoFiltro.getId().equals(0L)) {
            mapFiltro.put("id", dtoFiltro.getId());
        } else {
            dtoFiltro.setId(null);
        }

        if (dtoFiltro.getDescripcion() != null && dtoFiltro.getDescripcion().length() != 0) {
            mapFiltro.put("descripcion", dtoFiltro.getDescripcion());
        }
        Map<String, String> mapOrden = new HashMap<String, String>();
        mapOrden.put("descripcion", "asc");
        try {
            lista = obtenerConFiltroConOrden(mapFiltro, mapOrden);
        } catch (DAOException ex) {
            logger.error("ERROR DE SISTEMA", ex);

        }
        return lista;
    }

    public EmpresaDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(EmpresaDto.class, id);
    }
}
