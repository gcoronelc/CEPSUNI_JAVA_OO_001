/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sys.core.dto.EmpresaDto;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;
import sys.core.manager.ComunManager;
import sys.movil.dto.SolicitudServicioDto;

/**
 *
 * @author admin
 */
@Service
public class SolicitudServicioManager extends ComunManager<SolicitudServicioDto> implements Serializable {
    
    @Resource
    private DAOGenerico<SolicitudServicioDto> springHibernateDao;
    private static Logger logger = Logger.getLogger(SolicitudServicioManager.class);
    
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
    
    public List<SolicitudServicioDto> obtener(SolicitudServicioDto dto) throws DAOException {
        String hql = "select e from SolicitudServicioDto e where e.id = e.id ";
        List<Object> listaParametros = new ArrayList<Object>();
        if (dto.getId() != null && !dto.getId().equals(0L)) {
            hql += " and e.id = ?";
            listaParametros.add(dto.getId());
        } else {
            dto.setId(null);
        }
        if (dto.getChoferExternoDto() != null && dto.getChoferExternoDto().getNombresCompletos().length() != 0 && !dto.getChoferExternoDto().getNombresCompletos().equals("")) {
            hql += " and e.choferExternoDto.nombresCompletos like '%?%'";
            listaParametros.add(dto.getChoferExternoDto().getNombresCompletos().toUpperCase());
        }
        if (dto.getUsuarioExternoDto() != null && dto.getUsuarioExternoDto().getNombresCompletos().length() != 0 && !dto.getUsuarioExternoDto().getNombresCompletos().equals("")) {
            hql += " and e.usuarioExternoDto.nombresCompletos like '%?%'";
            listaParametros.add(dto.getUsuarioExternoDto().getNombresCompletos().toUpperCase());
        }
        if (dto.getFechaInicio() != null && dto.getFechaFin() != null) {
            
            hql += " and dto.fecha between ? and ?";
            Calendar c = Calendar.getInstance();
            c.setTime(dto.getFechaInicio());
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            listaParametros.add(c.getTime());
            c.setTime(dto.getFechaFin());
            c.set(Calendar.HOUR, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            listaParametros.add(c.getTime());
        }
        hql += " order by e.id desc";
        return springHibernateDao.ejecutarQuery(hql, listaParametros);
    }
    
    public void setSpringHibernateDao(DAOGenerico<SolicitudServicioDto> springHibernateDao) {
        this.springHibernateDao = springHibernateDao;
    }
    
    public static Logger getLogger() {
        return logger;
    }
    
    public static void setLogger(Logger logger) {
        SolicitudServicioManager.logger = logger;
    }
}
