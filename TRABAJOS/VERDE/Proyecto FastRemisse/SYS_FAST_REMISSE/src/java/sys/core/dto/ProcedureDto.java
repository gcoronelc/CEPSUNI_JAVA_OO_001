/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Indra
 */
@Entity
@Table(name = "VCORE_PROCEDURE")
public class ProcedureDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PROCEDURE_NAME", insertable = false, updatable = false)
    private String procedureName;

    public ProcedureDto() {
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }
    
}
