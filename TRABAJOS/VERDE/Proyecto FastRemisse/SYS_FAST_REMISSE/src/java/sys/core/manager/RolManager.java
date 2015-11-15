package sys.core.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.RolDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author admin
 */
@Service
public class RolManager extends ComunManager<RolDto> implements Serializable {
 @Resource
    private DAOGenerico<RolDto> springHibernateDao;

    public List<RolDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(RolDto.class);
    }

    public List<RolDto> obtenerTodosActivos() throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("descripcion", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(RolDto.class, filtros, orden);
    }

    public List<RolDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(RolDto.class, filtros);
    }

    public List<RolDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(RolDto.class, filtros, orden);
    }

    public RolDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(RolDto.class, id);
    }

    public List<RolDto> obtenerRoles(RolDto dtoFiltro) throws DAOException {


        Long entidad = ConstantesCore.Entidad.ROL;
        SessionMBean sessionMBean = (SessionMBean) WebServletContextListener.getApplicationContext().getBean("sessionMBean");

        String hqsql = "select e from RolDto as e, PropietarioDto as pro where "
                + " pro.estado = ? ";
        List<Object> listaParametros = new ArrayList<Object>();
        listaParametros.add(Boolean.TRUE);
        listaParametros.add(entidad);
        if (dtoFiltro.getDescripcion() != null && dtoFiltro.getDescripcion().length() > 0) {
            hqsql += " and upper(e.descripcion) like '%" + dtoFiltro.getDescripcion().toUpperCase() + "%'";
        }

        hqsql += " order by e.descripcion asc";
        return springHibernateDao.ejecutarQuery(hqsql, listaParametros);
    }

    public List<RolDto> obtenerRolesMantenimiento(RolDto dtoFiltro) throws DAOException {

        String hqsql = "select e from RolDto as e where e.id=e.id ";

        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        if (dtoFiltro.getDescripcion() != null && dtoFiltro.getDescripcion().length() > 0) {
            mapFiltro.put("descripcion", dtoFiltro.getDescripcion());
        }
        Map<String, String> mapOrden = new HashMap<String, String>();
        mapOrden.put("descripcion", "asc");
        List<RolDto> lista = obtenerConFiltroConOrden(mapFiltro, mapOrden);
        return lista;
    }
}
