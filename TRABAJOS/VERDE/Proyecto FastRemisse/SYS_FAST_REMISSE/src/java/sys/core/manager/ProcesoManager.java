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
import sys.core.dto.ProcesoDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author Indra
 */
@Service
public class ProcesoManager extends ComunManager<ProcesoDto> implements Serializable {

    @Resource
    private DAOGenerico<ProcesoDto> springHibernateDao;

    public int ejecutarProceso(String procedure) throws DAOException {
        return springHibernateDao.executeProcedure(procedure);
    }

    public List<ProcesoDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("nombre", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ProcesoDto.class, filtros, orden);
    }

    public List<ProcesoDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(ProcesoDto.class);
    }

    public List<ProcesoDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(ProcesoDto.class, filtros);
    }

    public List<ProcesoDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(ProcesoDto.class, filtros, orden);
    }

    public List<ProcesoDto> obtenerProcesos(ProcesoDto dtoFiltro) throws DAOException {
        List<ProcesoDto> lista = null;
        Map<String, Object> filtro = new HashMap<String, Object>();
        if (dtoFiltro.getNombre() != null && dtoFiltro.getNombre().length() > 0) {
            filtro.put("nombre", dtoFiltro.getNombre());
        }
        if (dtoFiltro.getProcedureDto().getProcedureName() != null && dtoFiltro.getProcedureDto().getProcedureName().length() > 0) {
            filtro.put("procedureDto.procedureName", dtoFiltro.getProcedureDto().getProcedureName());
        }

        filtro.put("estado", dtoFiltro.getEstado());
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("estado", "desc");
        orden.put("nombre", "asc");
        orden.put("procedureDto.procedureName", "asc");
        return lista;
    }

    public ProcesoDto obtenerPorId(String id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(ProcesoDto.class, id);
    }
}
