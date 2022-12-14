package aplicacion.service;

import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.ServiceException;
import aplicacion.model.Usuario;
import datos.dao.UsuarioDAO;
import datos.dao.UsuarioDAOH2;
import datos.exception.DAOException;

public class UsuarioService {
	
	private final UsuarioDAO usuarioDAO;
	
	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAOH2();
	}
	
	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public ArrayList<Usuario> obtenerUsuarios() throws ServiceException {
		ArrayList<Usuario> usuariosDB = null;
		try {
			usuariosDB = usuarioDAO.listarTodosLosUsuarios();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return usuariosDB;
	}
	
	public ArrayList<Usuario> obtenerUsuarios(UsuarioTipo usuarioTipo) throws ServiceException {
		ArrayList<Usuario> usuariosDB = null;
		try {
			usuariosDB = usuarioDAO.listarTodosLosUsuarios(usuarioTipo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return usuariosDB;
	}
	
	public Usuario obtenerUsuario(int idUsuario) throws ServiceException {
		Usuario usuarioDB = null;
		try {
			usuarioDB = usuarioDAO.consultarUsuario(idUsuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return usuarioDB;
	}
	
	public Usuario obtenerUsuario(String nombreUsuario) throws ServiceException {
		Usuario usuarioDB = null;
		try {
			usuarioDB = usuarioDAO.consultarUsuario(nombreUsuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return usuarioDB;
	}
	
	public Usuario obtenerUsuario(String nombreUsuario, String password) throws ServiceException {
		Usuario usuarioDB = null;
		try {
			usuarioDB = usuarioDAO.consultarUsuario(nombreUsuario, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return usuarioDB;
	}
	
	public void crearUsuario(Usuario nuevoUsuario) throws ServiceException {
		try {
			usuarioDAO.crearUsuario(nuevoUsuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void actualizarUsuario(Usuario usuarioModificado, Usuario usuarioOriginal) throws ServiceException {
		try {
			usuarioDAO.actualizarUsuario(usuarioModificado, usuarioOriginal);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void borrarUsuario(Usuario nuevoUsuario) throws ServiceException {
		try {
			usuarioDAO.borrarUsuario(nuevoUsuario);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void borrarLosUsuarios() throws ServiceException {
		try {
			usuarioDAO.borrarLosUsuarios();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void borrarLosUsuariosPorTipo(UsuarioTipo usuarioTipo) throws ServiceException {
		try {
			usuarioDAO.borrarLosUsuariosPorTipo(usuarioTipo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
