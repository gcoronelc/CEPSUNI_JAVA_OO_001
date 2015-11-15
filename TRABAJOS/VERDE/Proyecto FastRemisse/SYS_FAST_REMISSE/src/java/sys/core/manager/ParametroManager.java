/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;

import sys.core.dto.ParametroDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.util.ConstantesCore;

/**
 *
 * @author Indra
 */
@Service
public class ParametroManager extends ComunManager<ParametroDto> implements Serializable {

    @Resource
    private DAOGenerico<ParametroDto> springHibernateDao;

    public ParametroDto obtenerParametro(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(ParametroDto.class, id);
    }

    public List<ParametroDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerTodosActivos(String query) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("descripcion", query.toUpperCase());
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerTodosActivos(String query, Long catalogoParametro) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("descripcion", query.toUpperCase());

        filtros.put("catalogoParametroDto.id", catalogoParametro);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        orden.put("parametroPadreDto.descripcion", "asc");
        orden.put("parametroPadreDto.parametroPadreDto.descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerTodosActivosPorTresNiveles(String query, Long catalogoParametro) throws DAOException {

        String hqsql = "select e from ParametroDto as e "
                + "where e.estado = ? "
                + " and catalogoParametroDto.id = ? "
                + " and (upper(e.descripcion) like '%" + query.toUpperCase() + "%' "
                + " or upper(e.parametroPadreDto.descripcion) like '%" + query.toUpperCase() + "%' "
                + " or upper(e.parametroPadreDto.parametroPadreDto.descripcion) like '%" + query.toUpperCase() + "%' )"
                + " order by e.descripcion asc, e.parametroPadreDto.descripcion asc, e.parametroPadreDto.parametroPadreDto.descripcion ";
        List<Object> listaParametros = new ArrayList<Object>();
        listaParametros.add(Boolean.TRUE);
        listaParametros.add(catalogoParametro);

        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoconExcepcion(Long catalogo, Long parametro) throws DAOException {
        String hqsql = "select e from ParametroDto e where e.catalogoParametroDto.id = ? and e.estado = ? and e.id != ? ";
        List<Object> listaParametros = new ArrayList<Object>();
        listaParametros.add(catalogo);
        listaParametros.add(Boolean.TRUE);
        listaParametros.add(parametro);
        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(Long catalogo) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("catalogoParametroDto.id", catalogo);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoOrdenManual(Long catalogo, String tipoDato, String tipoOrden) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("catalogoParametroDto.id", catalogo);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put(tipoDato, tipoOrden);
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(Long catalogo, String tipoDato, String tipoOrden) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("catalogoParametroDto.id", catalogo);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put(tipoDato, tipoOrden);
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(Long catalogo, Long... listaParametrosExcepcion) throws DAOException {
        List<ParametroDto> lista = obtenerParametrosActivosPorCatalogo(catalogo);
        List<ParametroDto> listaRemover = new ArrayList<ParametroDto>();
        for (Long l : listaParametrosExcepcion) {
            listaRemover.add(new ParametroDto(l));
        }
        lista.removeAll(listaRemover);
        return lista;
    }

    public List<ParametroDto> obtenerTodosParametros() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);

    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoPorParametroPadre(Long catalogo, Long padre) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("catalogoParametroDto.id", catalogo);
        filtros.put("parametroPadreDto.id", padre);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ParametroDto.class, filtros, orden);
    }

    public ParametroDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(ParametroDto.class, id);
    }
}
