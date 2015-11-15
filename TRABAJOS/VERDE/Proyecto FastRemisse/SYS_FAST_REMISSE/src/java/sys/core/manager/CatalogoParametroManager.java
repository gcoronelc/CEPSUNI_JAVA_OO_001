/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.dto.CatalogoParametroDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.util.ConstantesCore;

/**
 *
 * @author Indra
 */
@Service
public class CatalogoParametroManager extends ComunManager<CatalogoParametroDto> implements Serializable{
    @Resource
    private DAOGenerico<CatalogoParametroDto> springHibernateDao;
    
    public List<CatalogoParametroDto> obtenerTodos() throws DAOException {        
        return springHibernateDao.listarTodosDtos(CatalogoParametroDto.class);
    }    
    
    public List<CatalogoParametroDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String,String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(CatalogoParametroDto.class, filtros,orden);
    }
    
    
    public List<CatalogoParametroDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(CatalogoParametroDto.class, filtros);
    }
    
    public List<CatalogoParametroDto> obtenerConFiltroConOrden(Map<String, Object> filtros,Map<String,String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(CatalogoParametroDto.class, filtros,orden);
    }
    
    public List<CatalogoParametroDto> obtenerCatalogos(CatalogoParametroDto dtoFiltro) throws DAOException {
        List<CatalogoParametroDto> lista = null;
        Map<String, Object> mapFiltro = new HashMap<String, Object>();                
        if (dtoFiltro.getId()!=null && !dtoFiltro.getId().equals(0L)) {
            mapFiltro.put("id", dtoFiltro.getId());
        }else {
            dtoFiltro.setId(null);
        }
        if (dtoFiltro.getDescripcion()!=null && dtoFiltro.getDescripcion().length()!=0) {
            mapFiltro.put("descripcion", dtoFiltro.getDescripcion());
        }
        
        Map<String, String> mapOrden = new HashMap<String, String>();
        mapOrden.put("descripcion", "asc");
        
        
        lista = obtenerConFiltroConOrden(mapFiltro, mapOrden);
        
        return lista;
                
    }
    
    public List<CatalogoParametroDto> obtenerCatalogosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String,String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(CatalogoParametroDto.class, filtros,orden);
    }
    public CatalogoParametroDto obtenerPorId(Long id) throws DAOException {
       return springHibernateDao.obtenerDtoPorId(CatalogoParametroDto.class, id);
    } 
}
