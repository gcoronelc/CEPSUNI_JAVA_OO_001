/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;
import sys.core.configuracion.WebServletContextListener;
import sys.core.view.mbean.ApplicationMBean;
import sys.movil.dto.UsuarioExternoDto;
import sys.movil.manager.UsuarioExternoManager;


/**
 *
 * @author Indra
 */
@FacesConverter("usuarioExternoConvert")
public class UsuarioExternoConvert implements Converter {
    private static Logger logger = Logger.getLogger(UsuarioExternoConvert.class);
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
                UsuarioExternoManager manager = (UsuarioExternoManager) WebServletContextListener.getApplicationContext().getBean("usuarioExternoManager");                              
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
            return String.valueOf(((UsuarioExternoDto) value).getId());
        }
    }
}
