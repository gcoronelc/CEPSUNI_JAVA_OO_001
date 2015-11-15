/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.hibernate.dao;

/**
 *
 * @author Indra
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import sys.core.exception.DAOException;

@Repository("daoGenericoCore")
public class DAOGenericoCore<Dto> extends HibernateDaoSupport implements DAOGenerico<Dto>, Serializable {

    private static final long serialVersionUID = 7924055871810120486L;

    @Autowired
    public DAOGenericoCore(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void saveOrUpdate(Dto dto) throws DAOException {
        super.getHibernateTemplate().saveOrUpdate(dto);
        super.getHibernateTemplate().flush();
    }

    public void save(Dto dto) throws DAOException {
        super.getHibernateTemplate().save(dto);
        super.getHibernateTemplate().flush();
    }

    @Override
    public void update(Dto dto) throws DAOException {
        super.getHibernateTemplate().update(dto);
        super.getHibernateTemplate().flush();
    }

    public void delete(Dto dto) throws DAOException {
        super.getHibernateTemplate().delete(dto);
        super.getHibernateTemplate().flush();
    }

    public <TID> Dto obtenerDtoPorId(Class c, TID id) throws DAOException {
        Dto dto = null;
        List list = null;
        if (id instanceof String) {
            list = super.getHibernateTemplate().find(
                    "from " + c.getSimpleName() + " where id='" + id + "'");
        } else {
            list = super.getHibernateTemplate().find(
                    "from " + c.getSimpleName() + " where id=" + id);
        }
        if (!list.isEmpty()) {
            dto = (Dto) list.get(0);
        }
        return dto;
    }

    public Dto obtenerDtoPorId(Class c, Serializable id) throws DAOException {
        return (Dto) super.getHibernateTemplate().get(c, id);
    }

    public List<Dto> obtenerDtosConFiltros(Class c, Map<String, Object> maparib_val) throws DAOException {
        List<Dto> list = null;
        String criterio = " ";
        String query = "from " + c.getSimpleName() + " ";
        String orders = " order by ";
        if (maparib_val != null && !maparib_val.isEmpty()) {
            criterio = " where ";
            for (Entry<String, Object> entri : maparib_val.entrySet()) {
                if (entri.getValue() instanceof String) {
                    criterio = criterio + " upper(" + entri.getKey() + ") like '%"
                            + entri.getValue().toString().toUpperCase() + "%' and";
                } else {
                    criterio = criterio + " " + entri.getKey() + " = "
                            + entri.getValue() + " and";
                }
            }
            criterio = criterio.substring(0, criterio.length() - 3);

        } else {
            maparib_val = null;
        }
        list = super.getHibernateTemplate().find(query + (criterio == null ? "" : criterio));

        return list;
    }

    public List<Dto> obtenerDtosConFiltrosConOrden(Class c, Map<String, Object> maparib_val, Map<String, String> order) throws DAOException {
        List<Dto> list = null;
        String criterio = " ";
        String query = "from " + c.getSimpleName() + " ";
        String orders = " order by ";
        if (maparib_val != null && !maparib_val.isEmpty()) {
            criterio = " where ";
            for (Entry<String, Object> entri : maparib_val.entrySet()) {
                if (entri.getValue() instanceof String) {
                    criterio = criterio + " upper(" + entri.getKey()
                            + ") like '%"
                            + entri.getValue().toString().toUpperCase()
                            + "%' and";
                } else {
                    criterio = criterio + " " + entri.getKey() + " = "
                            + entri.getValue() + " and";
                }
            }
            criterio = criterio.substring(0, criterio.length() - 3);

        } else {
            criterio = null;
        }

        if (order != null && !order.isEmpty()) {
            boolean flagfirst = false;
            for (Entry<String, String> entri : order.entrySet()) {
                if (flagfirst) {
                    orders += " , " + entri.getKey() + " " + entri.getValue();
                } else {
                    flagfirst = true;
                    orders = orders + entri.getKey() + " " + entri.getValue();
                }
            }
        } else {
            orders = null;
        }

        list = super.getHibernateTemplate().find(
                query + (criterio == null ? "" : criterio)
                + (orders == null ? "" : orders));
        return list;
    }

    public List<Dto> obtenerDtosConFiltrosConOrdenPorAlgunCriterio(Class c, Map<String, Object> maparib_val, Map<String, String> order) throws DAOException {
        List<Dto> list = null;
        String criterio = " ";
        String query = "from " + c.getSimpleName() + " ";
        String orders = " order by ";
        if (maparib_val != null && !maparib_val.isEmpty()) {
            criterio = " where ";
            for (Entry<String, Object> entri : maparib_val.entrySet()) {
                if (entri.getValue() instanceof String) {
                    criterio = criterio + " upper(" + entri.getKey()
                            + ") like '%"
                            + entri.getValue().toString().toUpperCase()
                            + "%' or";
                } else {
                    criterio = criterio + " " + entri.getKey() + " = "
                            + entri.getValue() + " or";
                }
            }
            criterio = criterio.substring(0, criterio.length() - 2);

        } else {
            criterio = null;
        }

        if (order != null && !order.isEmpty()) {
            boolean flagfirst = false;
            for (Entry<String, String> entri : order.entrySet()) {
                if (flagfirst) {
                    orders += " , " + entri.getKey() + " " + entri.getValue();
                } else {
                    flagfirst = true;
                    orders = orders + entri.getKey() + " " + entri.getValue();
                }
            }
        } else {
            orders = null;
        }

        list = super.getHibernateTemplate().find(
                query + (criterio == null ? "" : criterio)
                + (orders == null ? "" : orders));
        return list;
    }

    public List<Dto> buscarObjetos(Dto dto) throws DAOException {
        // Method[] matrizMetodos = ((Class) t).getMethods();
        // Res res = matrizMetodos[0].invoke(null, null);
        List<Dto> lista = null;
        // SQLQuery sqlquery = null;
        // lista = super.getSession().createSQLQuery("").list();

        return lista;
    }

    public List ejecutarSQL(String sql) throws DAOException {
        List lista = null;
        Session session = super.getHibernateTemplate().getSessionFactory().openSession();
        lista = session.createSQLQuery(sql).list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lista;
    }

    public List ejecutarQueryGeneric(String sql) throws DAOException {
        List lista = null;
        Session session = super.getHibernateTemplate().getSessionFactory().openSession();
        lista = session.createQuery(sql).list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return lista;
    }

    public int ejecutarNombreQuery(String nameQuery, List parametros) throws DAOException {
        Session session = null;
        int res = 0;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.getNamedQuery(nameQuery);
            int i = 0;
            for (Object param : parametros) {
                query.setParameter(i++, param);
            }
            res = query.executeUpdate();

        } finally {
            session.close();
        }
        return res;
    }

    public List ejecutarListaNombresQuerys(String nameQuery, List parametros) throws DAOException {
        Session session = null;
        List lista = null;
        try {
            session = super.getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.getNamedQuery(nameQuery);
            int i = 0;
            for (Object param : parametros) {
                query.setParameter(i++, param);
            }
            lista = query.list();

        } finally {
            session.close();
        }
        return lista;
    }

    public List<Dto> listarNombreQuery(String nameQuery, List parametros) throws DAOException {
        Session session = null;
        List<Dto> lista = null;
        try {
            session = super.getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.getNamedQuery(nameQuery);
            int i = 0;
            for (Object param : parametros) {
                query.setParameter(i++, param);
            }
            lista = (List<Dto>) query.list();

        } finally {
            session.close();
        }
        return lista;
    }

    public void guardarColeccion(Collection<?> collection) throws DAOException {
        super.getHibernateTemplate().saveOrUpdateAll(collection);
    }

    public void borrarColeccion(Collection<?> collection) throws DAOException {
        super.getHibernateTemplate().deleteAll(collection);
    }

    public List<Dto> ejecutarQuery(String query, List<Object> params) throws DAOException {
        return (List<Dto>) super.getHibernateTemplate().find(query, params.toArray());
    }

    public List ejecutarQueryGeneric(String query, List<Object> params) throws DAOException {
        return super.getHibernateTemplate().find(query, params.toArray());
    }

    public List<Dto> ejecutarQuery(String query) throws DAOException {
        return (List<Dto>) super.getHibernateTemplate().find(query);
    }

    @Override
    public List<Dto> listarTodosDtos(Class<Dto> entityClass) throws DAOException {
        Session session = null;
        List<Dto> lista = null;
        try {
            session = super.getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from "
                    + entityClass.getSimpleName() + " as e");
            lista = (List<Dto>) query.list();

        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public void refresh(Dto dto) throws DAOException {
        Session session = null;
        session = super.getHibernateTemplate().getSessionFactory().openSession();
        session.refresh(dto);

        session.close();
    }

    @Override
    public void flush() throws DAOException {
        Session session = null;
        session = super.getHibernateTemplate().getSessionFactory().openSession();
        session.flush();
        session.close();
    }

    public int executeProcedure(String procedure) throws DAOException {
        Session session = null;
        int i = -1;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            i = session.createSQLQuery(procedure).executeUpdate();
        } finally {
            session.close();
        }
        return i;
    }

}
