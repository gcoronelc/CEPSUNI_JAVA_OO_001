/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.json;

import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.core.dto.UsuarioDto;
import sys.core.exception.DAOException;
import sys.core.manager.UsuarioManager;

@Controller
@RequestMapping(value = "usuarioJson")
public class UsuarioJson implements Serializable {

    @Resource
    private UsuarioManager usuarioManager;
    private UsuarioDto usuarioDto;

    @RequestMapping(value = "/obtenerTodos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String obtenerUsuarios() throws DAOException {
        String cadena = "";
        if (!usuarioManager.obtenerTodos().isEmpty()) {
            for (int i = 0; i < usuarioManager.obtenerTodos().size(); i++) {
                cadena += usuarioManager.obtenerTodos().get(i).getNombresCompletos();
            }

        }
        return cadena;
    }

    @RequestMapping(value = "/obtenerPorId/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    UsuarioDto obtenerPorId(@PathVariable Long id) throws DAOException {
        usuarioDto = usuarioManager.obtenerPorId(id);
        System.out.println("ENTRO AL METODO DE OBTENER X ID" + id + usuarioDto);
        return usuarioDto;
    }

    public UsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public void setUsuarioManager(UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

}
