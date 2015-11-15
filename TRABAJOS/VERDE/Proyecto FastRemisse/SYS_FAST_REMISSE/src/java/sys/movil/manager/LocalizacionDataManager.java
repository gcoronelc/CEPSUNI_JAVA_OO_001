/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.manager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.validator.internal.util.logging.Log_$logger;
import org.springframework.stereotype.Service;
import sys.core.common.UtilCore;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.manager.ComunManager;
import sys.movil.dto.LocalizacionDataDto;

/**
 *
 * @author admin
 */
@Service
public class LocalizacionDataManager extends ComunManager<LocalizacionDataDto> implements Serializable {

    @Resource
    private DAOGenerico<LocalizacionDataDto> springHibernateDao;

    public LocalizacionDataDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(LocalizacionDataDto.class, id);
    }

    public List<LocalizacionDataDto> obtenerChoferesActivos() throws DAOException {
        //select * from sys_movil_localizacion_data e where e.id IN (select max(f.id) from sys_movil_localizacion_data f group by f.usuario)
        String hsql = "select e from LocalizacionDataDto e where e.id IN (select max(f.id) from LocalizacionDataDto f where f.usuarioExternoDto.tipoUsuario = 2 group by f.usuarioExternoDto.id)";
        hsql += " and to_char(e.fecha,'dd')= " + UtilCore.Fecha.obtenerDiaActual() + " and to_char(e.fecha,'MM') = " + UtilCore.Fecha.obtenerMesActual() + " and to_char(e.fecha,'yyyy') = " + UtilCore.Fecha.obtenerAnioActual() + " and e.estado = 1";
        return springHibernateDao.ejecutarQuery(hsql);
    }

    public List<LocalizacionDataDto> obtenerPosicionChoferCercano() throws DAOException {
        //select * from sys_movil_localizacion_data e where e.id IN (select max(f.id) from sys_movil_localizacion_data f group by f.usuario)
        String hsql = "select min(e.longitude) , min(e.latitude) from LocalizacionDataDto e where e.id IN (select max(f.id) from LocalizacionDataDto f where f.usuarioExternoDto.tipoUsuario = 2 group by f.usuarioExternoDto.id)";
        hsql += " and to_char(e.fecha,'dd')= " + UtilCore.Fecha.obtenerDiaActual() + " and to_char(e.fecha,'MM') = " + UtilCore.Fecha.obtenerMesActual() + " and to_char(e.fecha,'yyyy') = " + UtilCore.Fecha.obtenerAnioActual() + "  and e.estado = 1";
        return springHibernateDao.ejecutarQuery(hsql);
    }

    public String obtenerxUsuario(Long id) throws DAOException {
        String l = "";
        //select * from sys_movil_localizacion_data e where e.id IN (select max(f.id) from sys_movil_localizacion_data f group by f.usuario)
        String hsql = "select e.id from LocalizacionDataDto e where  e.usuarioExternoDto.id = " + id;
        hsql += " and to_char(e.fecha,'dd')= " + UtilCore.Fecha.obtenerDiaActual() + " and to_char(e.fecha,'MM') = " + UtilCore.Fecha.obtenerMesActual() + " and to_char(e.fecha,'yyyy') = " + UtilCore.Fecha.obtenerAnioActual() + " and rownum >= 1 order by e.fecha desc ";
        try {
            l = springHibernateDao.ejecutarQueryGeneric(hsql).get(0).toString();
        } catch (Exception ex) {
            l = "";
            System.out.println(ex.getMessage());
        }
        if (!l.equals("")) {
            return l;
        }
        return l;

    }

    public List<LocalizacionDataDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(LocalizacionDataDto.class);
    }

    public List<LocalizacionDataDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(LocalizacionDataDto.class, filtros);
    }

    public List<LocalizacionDataDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(LocalizacionDataDto.class, filtros, orden);
    }

    public DAOGenerico<LocalizacionDataDto> getSpringHibernateDao() {
        return springHibernateDao;
    }

    public void setSpringHibernateDao(DAOGenerico<LocalizacionDataDto> springHibernateDao) {
        this.springHibernateDao = springHibernateDao;
    }
}
