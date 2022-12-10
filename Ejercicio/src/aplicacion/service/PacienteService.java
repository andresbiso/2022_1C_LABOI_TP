package aplicacion.service;

import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.ServiceException;
import aplicacion.model.Paciente;
import aplicacion.model.Usuario;
import datos.dao.PacienteDAO;
import datos.dao.PacienteDAOH2;
import datos.exception.DAOException;

public class PacienteService {
	
	private final PacienteDAO pacienteDAO;
	private final UsuarioService usuarioService;
	
	public PacienteService() {
		this.pacienteDAO = new PacienteDAOH2();
		this.usuarioService = new UsuarioService();
	}
	
	public ArrayList<Paciente> obtenerPacientes() throws ServiceException {

		ArrayList<Usuario> usuariosDB = null;
		try {
			usuariosDB = usuarioService.obtenerUsuarios(UsuarioTipo.Paciente);
		} catch (ServiceException e) {
			throw e;
		}
			
		ArrayList<Paciente> pacientesDB = null;
		if (usuariosDB != null) {
			try {
				pacientesDB = pacienteDAO.listarTodosLosPacientes(usuariosDB);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return pacientesDB;
	}
	
	public Paciente obtenerPaciente(int idPaciente) throws ServiceException {
		try {
			Paciente paciente = pacienteDAO.consultarPaciente(idPaciente);
			Usuario usuarioPaciente = usuarioService.obtenerUsuario(paciente.getIdUsuario());
			paciente.setUsuario(usuarioPaciente);
			return paciente;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void crearPaciente(Paciente nuevoPaciente) throws ServiceException {
		try {
			usuarioService.crearUsuario((Usuario)nuevoPaciente);
			Usuario nuevoUsuario = usuarioService.obtenerUsuario(nuevoPaciente.getNombreUsuario());
			nuevoPaciente.setIdUsuario(nuevoUsuario.getIdUsuario());
			pacienteDAO.crearPaciente(nuevoPaciente);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void actualizarPaciente(Paciente pacienteModificado, Paciente pacienteOriginal) throws ServiceException {
		try {
			Usuario usuarioPaciente = usuarioService.obtenerUsuario(pacienteOriginal.getNombreUsuario());
			pacienteOriginal.setIdUsuario(usuarioPaciente.getIdUsuario());
			usuarioService.actualizarUsuario((Usuario)pacienteModificado, (Usuario)pacienteOriginal);
			// pacienteDAO.actualizarPaciente(pacienteModificado, pacienteOriginal);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void borrarPaciente(Paciente paciente) throws ServiceException {
		try {
			Usuario usuarioPaciente = usuarioService.obtenerUsuario(paciente.getNombreUsuario());
			paciente.setIdUsuario(usuarioPaciente.getIdUsuario());
			pacienteDAO.borrarPaciente(paciente);
			usuarioService.borrarUsuario((Usuario)paciente);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	public void borrarLosPacientes() throws ServiceException {
		try {
			pacienteDAO.borrarLosPacientes();
			usuarioService.borrarLosUsuariosPorTipo(UsuarioTipo.Paciente);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (ServiceException e) {
			throw e;
		}
	}

}
