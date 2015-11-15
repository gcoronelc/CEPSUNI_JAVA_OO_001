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
import sys.core.dto.OpcionSistemaDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author Indra
 */
@Service
public class OpcionSistemaManager extends ComunManager<OpcionSistemaDto>  implements Serializable {
    @Resource
    private DAOGenerico<OpcionSistemaDto> springHibernateDao;
    
    public List<OpcionSistemaDto> obtenerTodos() throws DAOException {        
        return springHibernateDao.listarTodosDtos(OpcionSistemaDto.class);
    }    
    
    public List<OpcionSistemaDto> obtenerTodosActivo() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String,String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(OpcionSistemaDto.class, filtros,orden);
    }
    
    public List<OpcionSistemaDto> obtenerHijosMenu (OpcionSistemaDto os) throws DAOException{
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("padreDto.id", os.getId());
        filtros.put("tipoMenu", "M");
        Map<String,String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(OpcionSistemaDto.class, filtros,orden);
    }
    
    public List<OpcionSistemaDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(OpcionSistemaDto.class, filtros);
    }
    
    public List<OpcionSistemaDto> obtenerConFiltroConOrden(Map<String, Object> filtros,Map<String,String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(OpcionSistemaDto.class, filtros,orden);
    }   
    
    public OpcionSistemaDto obtenerPorId (Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(OpcionSistemaDto.class, id);
    }
  
}
