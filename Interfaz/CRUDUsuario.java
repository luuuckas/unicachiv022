
package Interfaz;

import java.util.List;

import Modelo.DTOusuario;

public interface CRUDUsuario {
    DTOusuario verificarUsuario(DTOusuario user) throws Exception;
    void editarUsuario(DTOusuario user) throws Exception;
    void copiarUsuario(String idUsuarioOriginal, String nuevoIdUsuario) throws Exception;
    void borrarUsuario(String idUsuario) throws Exception;
    List<DTOusuario> listarUsuarios() throws Exception;
}
