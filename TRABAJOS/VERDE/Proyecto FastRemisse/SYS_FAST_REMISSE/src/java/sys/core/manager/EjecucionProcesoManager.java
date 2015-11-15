/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.dto.EjecucionProcesoDto;
import sys.core.dto.EjecucionProcesoDtoPK;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author Indra
 */
@Service
public class EjecucionProcesoManager extends ComunManager<EjecucionProcesoDto>  implements Serializable{
    @Resource
    private DAOGenerico<EjecucionProcesoDto> springHibernateDao;
    
    public List<EjecucionProcesoDto> obtenerTodos() throws DAOException {        
        return springHibernateDao.listarTodosDtos(EjecucionProcesoDto.class);
    }       
    
    public List<EjecucionProcesoDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(EjecucionProcesoDto.class, filtros);
    }
    
    public List<EjecucionProcesoDto> obtenerConFiltroConOrden(Map<String, Object> filtros,Map<String,String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(EjecucionProcesoDto.class, filtros,orden);
    }
    public EjecucionProcesoDto obtenerPorId(EjecucionProcesoDtoPK id) throws DAOException {
       return springHibernateDao.obtenerDtoPorId(EjecucionProcesoDto.class, id);
    }  
}
