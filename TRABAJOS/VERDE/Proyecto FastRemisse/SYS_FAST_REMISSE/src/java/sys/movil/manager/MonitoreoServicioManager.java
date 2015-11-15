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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sys.core.common.UtilCore;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.manager.ComunManager;
import sys.movil.dto.SolicitudServicioDto;

/**
 *
 * @author admin
 */
@Service
public class MonitoreoServicioManager extends ComunManager<SolicitudServicioDto> implements Serializable {

    @Resource
    private DAOGenerico<SolicitudServicioDto> springHibernateDao;
    private static Logger logger = Logger.getLogger(MonitoreoServicioManager.class);

    public SolicitudServicioDto obtenerPorId(Long id) throws DAOException {
        return springHibernateDao.obtenerDtoPorId(SolicitudServicioDto.class, id);
    }

    public List<SolicitudServicioDto> obtenerTodos() throws DAOException {
        return springHibernateDao.listarTodosDtos(SolicitudServicioDto.class);
    }

    public List<SolicitudServicioDto> obtenerConFiltro(Map<String, Object> filtros) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltros(SolicitudServicioDto.class, filtros);
    }

    public List<SolicitudServicioDto> obtenerConFiltroConOrden(Map<String, Object> filtros, Map<String, String> orden) throws DAOException {
        return springHibernateDao.obtenerDtosConFiltrosConOrden(SolicitudServicioDto.class, filtros, orden);
    }

    public DAOGenerico<SolicitudServicioDto> getSpringHibernateDao() {
        return springHibernateDao;
    }

    public List<SolicitudServicioDto> obtenerServicios(Long idUsuario, String strDireccion, String strReferencia, String strNroReferencia) throws DAOException {
        String hql = "select e from SolicitudServicioDto where e.usuarioDto.id =" + idUsuario;
        hql += "and e.referencia = " + strReferencia + " and e.direccion =" + strDireccion + " and e.nroReferencia =" + strNroReferencia;
        return springHibernateDao.ejecutarQuery(hql);
    }

    public List<SolicitudServicioDto> obtenerServiciosEnCola() throws DAOException {
        String hql = "select e from SolicitudServicioDto e where e.estado = " + 0;
         hql += " and TO_CHAR(e.fecha,'dd')= " + UtilCore.Fecha.obtenerDiaActual() + " and TO_CHAR(e.fecha,'MM') = " + UtilCore.Fecha.obtenerMesActual() + " and TO_CHAR(e.fecha,'yyyy') = " + UtilCore.Fecha.obtenerAnioActual() + "";
        return springHibernateDao.ejecutarQuery(hql);
    }

    public List<SolicitudServicioDto> obtenerServiciosRealizados(Long idChofer) throws DAOException {
        String hql = "select e from SolicitudServicioDto e where e.choferExternoDto.id =" + idChofer;
        hql += " and rownum <= 20";
        return springHibernateDao.ejecutarQuery(hql);
    }

    public void setSpringHibernateDao(DAOGenerico<SolicitudServicioDto> springHibernateDao) {
        this.springHibernateDao = springHibernateDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MonitoreoServicioManager.logger = logger;
    }
}
