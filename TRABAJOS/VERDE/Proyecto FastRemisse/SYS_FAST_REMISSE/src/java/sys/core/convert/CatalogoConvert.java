/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.CatalogoParametroDto;
import sys.core.manager.CatalogoParametroManager;
import sys.core.view.mbean.ApplicationMBean;


/**
 *
 * @author Indra
 */
@FacesConverter("catalogoConvert")
public class CatalogoConvert implements Converter {
    private static Logger logger = Logger.getLogger(CatalogoConvert.class);
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
                CatalogoParametroManager manager = (CatalogoParametroManager) WebServletContextListener.getApplicationContext().getBean("catalogoParametroManager");                              
                return manager.obtenerPorId(id);
            } catch (Exception exception) {
                logger.error(exception);
                return null;
            }
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((CatalogoParametroDto) value).getId());
        }
    }
}
