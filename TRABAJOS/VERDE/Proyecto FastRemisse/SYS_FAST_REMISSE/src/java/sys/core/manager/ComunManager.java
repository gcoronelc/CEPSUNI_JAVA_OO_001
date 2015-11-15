/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sys.core.manager;

import java.io.Serializable;
import javax.annotation.Resource;
import sys.core.exception.DAOException;
import sys.core.hibernate.dao.DAOGenerico;

/**
 *
 * @author admin
 */
public class ComunManager<Dto> implements Serializable {
    @Resource
    private DAOGenerico<Dto> springHibernateDao;   
    
    public void nuevo(Dto entidadDto) throws DAOException {
        springHibernateDao.save(entidadDto);
    }
    
    public void editar(Dto entidadDto) throws DAOException {
        springHibernateDao.update(entidadDto);
    }  
    public void eliminar(Dto entidadDto) throws DAOException {
        springHibernateDao.delete(entidadDto);
    }  
    public void refrescar(Dto entidadDto) throws DAOException {
        springHibernateDao.refresh(entidadDto);
    } 
    
    public void flush() throws DAOException {
        springHibernateDao.flush();
    }  
      public int ejecutarProceso (String procedure) throws DAOException  {
        return springHibernateDao.executeProcedure(procedure);
    }    
    
    
}
