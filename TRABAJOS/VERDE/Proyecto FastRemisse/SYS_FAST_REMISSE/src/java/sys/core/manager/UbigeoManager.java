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

import sys.core.dto.UbigeoDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.util.ConstantesCore;

/**
 *
 * @author admin
 */
@Service
public class UbigeoManager extends ComunManager<UbigeoDto> implements Serializable {

    @Resource
    private DAOGenerico<UbigeoDto> springHibernateDao;

    public List<UbigeoDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(UbigeoDto.class);
    }

    public List<UbigeoDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UbigeoDto.class, filtros, orden);
    }

    public List<UbigeoDto> obtenerPaisesActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("tipo", ConstantesCore.Parametro.PARAMETRO_TIPO_UBIGEO_PAIS);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UbigeoDto.class, filtros, orden);
    }

    public List<UbigeoDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(UbigeoDto.class, filtros);
    }

    public List<UbigeoDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UbigeoDto.class, filtros, orden);
    }

    public UbigeoDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(UbigeoDto.class, id);
    }

}
