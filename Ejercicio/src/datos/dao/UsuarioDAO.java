package datos.dao;

import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.model.Usuario;
import datos.exception.DAOException;

public interface UsuarioDAO {
	int crearUsuario(Usuario usuario) throws DAOException;
	
	int borrarUsuario(Usuario usuario) throws DAOException;
	
	int borrarLosUsuarios() throws DAOException;
	
	int borrarLosUsuariosPorTipo(UsuarioTipo usuarioTipo) throws DAOException;

	int actualizarUsuario(Usuario usuarioModificado, Usuario usuarioOriginal) throws DAOException;

	Usuario consultarUsuario(int idUsuario) throws DAOException;
	
	Usuario consultarUsuario(String nombreUsuario) throws DAOException;
	
	Usuario consultarUsuario(String nombreUsuario, String password) throws DAOException;

	ArrayList<Usuario> listarTodosLosUsuarios() throws DAOException;
	
	ArrayList<Usuario> listarTodosLosUsuarios(UsuarioTipo usuarioTipo) throws DAOException;
}
