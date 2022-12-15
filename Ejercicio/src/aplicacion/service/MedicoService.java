package aplicacion.service;

import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.model.Usuario;
import datos.dao.MedicoDAO;
import datos.dao.MedicoDAOH2;
import datos.exception.DAOException;

public class MedicoService {
	
	private final MedicoDAO medicoDAO;
	private final UsuarioService usuarioService;
	
	public MedicoService() {
		this.medicoDAO = new MedicoDAOH2();
		this.usuarioService = new UsuarioService();
	}
	
	public ArrayList<Medico> obtenerMedicos() throws ServiceException {

		ArrayList<Usuario> usuariosDB = null;
		try {
			usuariosDB = usuarioService.obtenerUsuarios(UsuarioTipo.Medico);
		} catch (ServiceException e) {
			throw e;
		}
			
		ArrayList<Medico> medicosDB = null;
		if (usuariosDB != null) {
			try {
				medicosDB = medicoDAO.listarTodosLosMedicos(usuariosDB);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return medicosDB;
	}
	
	public Medico obtenerMedico(int idMedico) throws ServiceException {		
		try {
			Medico medico = medicoDAO.consultarMedico(idMedico);
			Usuario usuarioMedico = usuarioService.obtenerUsuario(medico.getIdUsuario());
			medico.setUsuario(usuarioMedico);
			return medico;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public Medico obtenerMedico(Usuario usuario) throws ServiceException {		
		try {
			Medico medico = medicoDAO.consultarMedico(usuario);
			medico.setUsuario(usuario);
			return medico;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void crearMedico(Medico nuevoMedico) throws ServiceException {
		try {
			usuarioService.crearUsuario((Usuario)nuevoMedico);
			Usuario nuevoUsuario = usuarioService.obtenerUsuario(nuevoMedico.getNombreUsuario());
			nuevoMedico.setIdUsuario(nuevoUsuario.getIdUsuario());
			medicoDAO.crearMedico(nuevoMedico);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void actualizarMedico(Medico medicoModificado, Medico medicoOriginal) throws ServiceException {
		try {
			Usuario usuarioMedico = usuarioService.obtenerUsuario(medicoOriginal.getNombreUsuario());
			medicoOriginal.setIdUsuario(usuarioMedico.getIdUsuario());
			usuarioService.actualizarUsuario((Usuario)medicoModificado, (Usuario)medicoOriginal);
			medicoDAO.actualizarMedico(medicoModificado, medicoOriginal);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void borrarMedico(Medico medico) throws ServiceException {
		try {
			Usuario usuarioMedico = usuarioService.obtenerUsuario(medico.getNombreUsuario());
			medico.setIdUsuario(usuarioMedico.getIdUsuario());
			medicoDAO.borrarMedico(medico);
			usuarioService.borrarUsuario((Usuario)medico);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void borrarLosMedicos() throws ServiceException {
		try {
			medicoDAO.borrarLosMedicos();
			usuarioService.borrarLosUsuariosPorTipo(UsuarioTipo.Medico);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}

}
