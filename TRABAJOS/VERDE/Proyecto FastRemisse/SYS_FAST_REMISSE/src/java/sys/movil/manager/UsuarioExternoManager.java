/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.manager.ComunManager;
import sys.movil.dto.UsuarioExternoDto;

/**
 *
 * @author admin
 */
@Service
public class UsuarioExternoManager extends ComunManager<UsuarioExternoDto> implements Serializable {

    @Resource
    private DAOGenerico<UsuarioExternoDto> springHibernateDao;

    public UsuarioExternoDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(UsuarioExternoDto.class, id);
    }

    public List<UsuarioExternoDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(UsuarioExternoDto.class);
    }

    public List<UsuarioExternoDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(UsuarioExternoDto.class, filtros);
    }

    public List<UsuarioExternoDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UsuarioExternoDto.class, filtros, orden);
    }

    public DAOGenerico<UsuarioExternoDto> getSpringHibernateDao() {
        return springHibernateDao;
    }

    public List<UsuarioExternoDto> validarUsuario(String strUsuario) throws DAOException {
        String hql = "select u from UsuarioExternoDto u where upper(u.login) ='" + strUsuario.toUpperCase() + "'";
        return springHibernateDao.ejecutarQuery(hql);
    }

    public List<UsuarioExternoDto> obtenerUsuariosExternos(UsuarioExternoDto dtoFiltro) throws DAOException {
        String hqsql = "select e from UsuarioExternoDto e where  e.id = e.id ";
        if (dtoFiltro.getId() != null && !dtoFiltro.getId().equals(0L)) {
            hqsql += " and e.id = " + dtoFiltro.getId();

        } else {
            if (dtoFiltro.getNombresCompletos() != null && dtoFiltro.getNombresCompletos().length() != 0) {
                hqsql += " and upper(e.nombresCompletos) like '%" + dtoFiltro.getNombresCompletos().toUpperCase() + "%'";
            }

            if (dtoFiltro.getLogin() != null && dtoFiltro.getLogin().length() != 0) {
                hqsql += " and upper(e.login) like '%" + dtoFiltro.getLogin().toUpperCase() + "%'";
            }

        }
        hqsql += " order by e.id  desc";
        return springHibernateDao.ejecutarQuery(hqsql);
    }

    public List<UsuarioExternoDto> validarEmail(String strEmail) throws DAOException {
        String hql = "select u from UsuarioExternoDto u where upper(u.email) ='" + strEmail + "'";
        return springHibernateDao.ejecutarQuery(hql);
    }

    public void setSpringHibernateDao(DAOGenerico<UsuarioExternoDto> springHibernateDao) {
        this.springHibernateDao = springHibernateDao;
    }

    public List<UsuarioExternoDto> obtener(String nombresCompletos) throws DAOException {
        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("estado", Boolean.TRUE);
        filtros.put("nombresCompletos", nombresCompletos);
        filtros.put("tipoUsuario", 2L);
        Map<String, String> orden = new HashMap<String, String>();
        orden.put("nombresCompletos", "asc");
        return springHibernateDao.obtenerDtosConFiltrosConOrden(UsuarioExternoDto.class, filtros, orden);
    }
}
