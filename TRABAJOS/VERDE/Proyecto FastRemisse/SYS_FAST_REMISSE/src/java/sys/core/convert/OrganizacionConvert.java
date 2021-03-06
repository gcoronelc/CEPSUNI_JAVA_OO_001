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
import sys.core.dto.EmpresaDto;
import sys.core.manager.EmpresaManager;
import sys.core.view.mbean.ApplicationMBean;


/**
 *
 * @author Indra
 */
@FacesConverter("organizacionConvert")
public class OrganizacionConvert implements Converter {
    private static Logger logger = Logger.getLogger(OrganizacionConvert.class);
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
                EmpresaManager manager = (EmpresaManager) WebServletContextListener.getApplicationContext().getBean("organizacionManager");                              
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
            return String.valueOf(((EmpresaDto) value).getId());
        }
    }
}
