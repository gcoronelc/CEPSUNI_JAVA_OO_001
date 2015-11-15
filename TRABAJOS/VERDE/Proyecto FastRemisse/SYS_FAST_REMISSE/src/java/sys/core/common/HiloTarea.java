/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.common;


import org.apache.log4j.Logger;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.EjecucionProcesoDto;
import sys.core.dto.EjecucionProcesoDtoPK;
import sys.core.dto.ProcesoDto;
import sys.core.exception.DAOException;
import sys.core.manager.EjecucionProcesoManager;
import sys.core.manager.ProcesoManager;

/**
 *
 * @author Indra
 */
public class HiloTarea extends Thread {

    private static Logger logger = Logger.getLogger(HiloTarea.class);
    private ProcesoDto procesoDto;
    private boolean procesoYaEjecutado;

    public HiloTarea(ProcesoDto procesoDto) {
        this.procesoDto = procesoDto;
    }

    @Override
    public void run() {
        ProcesoManager procesoManager = (ProcesoManager) WebServletContextListener.getApplicationContext().getBean("procesoManager");
        EjecucionProcesoManager ejecucionProcesoManager = (EjecucionProcesoManager) WebServletContextListener.getApplicationContext().getBean("ejecucionProcesoManager");
        EjecucionProcesoDto ep = new EjecucionProcesoDto();

        ep.setComentario(null);
        ep.setEstado("P");
        ep.setFechaHoraInicio(UtilCore.Fecha.obtenerFechaActualDate());
        ep.setProcesoDto(this.procesoDto);
        EjecucionProcesoDtoPK pk = new EjecucionProcesoDtoPK(this.procesoDto.getNombre(), this.procesoDto.getFechaEjecutada(), this.procesoDto.getHoraEjecutada());
        ep.setEjecucionProcesoDtoPK(pk);
        try {
            ejecucionProcesoManager.nuevo(ep);
            procesoYaEjecutado = false;
        } catch (Exception e) {
            procesoYaEjecutado = true;
        }

        if (!procesoYaEjecutado) {            
            try {
                procesoManager.ejecutarProceso("{CALL " + this.procesoDto.getProcedureDto().getProcedureName() + "()}");
                ep.setFechaHoraFin(UtilCore.Fecha.obtenerFechaActualDate());
                ep.setEstado("T");
                ejecucionProcesoManager.editar(ep);
            } catch (DAOException ex) {
                System.out.println("Entra al catch");
                logger.error(ex);
                ep.setFechaHoraFin(UtilCore.Fecha.obtenerFechaActualDate());
                ep.setEstado("E");
                if (ex.getMessage().length() < 4000) {
                    ep.setComentario(ex.getMessage());
                } else {
                    ep.setComentario(ex.getMessage().substring(0, 3900));
                }
            } finally {
                System.out.println("Entra al finally");
                try {
                    ejecucionProcesoManager.editar(ep);
                } catch (DAOException ex) {
                    logger.error(ex);
                }
            }


        }
    }

    public ProcesoDto getProcesoDto() {
        return procesoDto;
    }

    public void setProcesoDto(ProcesoDto procesoDto) {
        this.procesoDto = procesoDto;
    }
}
