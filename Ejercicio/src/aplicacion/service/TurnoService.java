package aplicacion.service;

import java.sql.Date;
import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import aplicacion.model.Usuario;
import datos.dao.TurnoDAO;
import datos.dao.TurnoDAOH2;
import datos.exception.DAOException;

public class TurnoService {
	
	private final TurnoDAO turnoDAO;
	private final MedicoService medicoService;
	private final PacienteService pacienteService;
	
	public TurnoService() {
		this.turnoDAO = new TurnoDAOH2();
		this.medicoService = new MedicoService();
		this.pacienteService = new PacienteService();
	}
	
	public TurnoService(TurnoDAO turnoDAO) {
		this.turnoDAO = turnoDAO;
		this.medicoService = new MedicoService();
		this.pacienteService = new PacienteService();
	}
	
	public ArrayList<Turno> obtenerTurnos() throws ServiceException {
		ArrayList<Turno> turnosDB = null;
		try {
			turnosDB = turnoDAO.listarTodosLosTurnos();
			if (turnosDB != null) {
				asociarRelacionesTurnos(turnosDB);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turnosDB;
	}
	
	public ArrayList<Turno> obtenerTurnos(Date fecha) throws ServiceException {
		ArrayList<Turno> turnosDB = null;
		try {
			turnosDB = turnoDAO.listarTodosLosTurnos(fecha);
			if (turnosDB != null) {
				asociarRelacionesTurnos(turnosDB);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turnosDB;
	}
	
	public ArrayList<Turno> obtenerTurnos(int idMedico, Date fechaDesde, Date fechaHasta) throws ServiceException {
		if (fechaDesde.compareTo(fechaHasta) > 0) {
			throw new ServiceException("La fechaDesde no puede ser mayor a la fechaHasta");
		}
		
		ArrayList<Turno> turnosDB = null;
		try {
			turnosDB = turnoDAO.listarTodosLosTurnos(idMedico, fechaDesde, fechaHasta);
			if (turnosDB != null) {
				asociarRelacionesTurnos(turnosDB);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turnosDB;
	}
	
	public ArrayList<Turno> obtenerTurnos(int idMedico, Date fecha) throws ServiceException {
		ArrayList<Turno> turnosDB = null;
		try {
			turnosDB = turnoDAO.listarTodosLosTurnos(idMedico, fecha);
			if (turnosDB != null) {
				asociarRelacionesTurnos(turnosDB);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turnosDB;
	}
	
	public ArrayList<Turno> obtenerTurnos(Usuario usuario, Date fecha) throws ServiceException {
		ArrayList<Turno> turnosDB = null;
		try {
			if (usuario.getUsuarioTipo() == UsuarioTipo.Medico) {
				Medico turnoMedico = medicoService.obtenerMedico(usuario);
				turnosDB = turnoDAO.listarTodosLosTurnos(turnoMedico.getIdMedico(), fecha);
			} else {
				Paciente turnoPaciente = pacienteService.obtenerPaciente(usuario);
				turnosDB = turnoDAO.obtenerTurnosProximos(turnoPaciente.getIdPaciente(), fecha);
			}
		
			if (turnosDB != null) {
				asociarRelacionesTurnos(turnosDB);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turnosDB;
	}
	
	public Turno obtenerTurno(int idTurno) throws ServiceException {
		Turno turno = null;
		try {
			turno = turnoDAO.consultarTurno(idTurno);
			if (turno != null) {
				asociarRelacionesTurno(turno);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return turno;
	}
	
	public void validarTurno(Turno turno) throws ServiceException {
		try {
			asociarRelacionesTurno(turno);
			boolean esValido = turnoDAO.validarTurno(turno);
			if (!esValido) {
				throw new ServiceException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void validarTurnoPaciente(Turno turno) throws ServiceException {
		try {
			asociarRelacionesTurno(turno);
			boolean esValido = turnoDAO.validarTurnoPaciente(turno);
			if (!esValido) {
				throw new ServiceException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void crearTurno(Turno nuevoTurno) throws ServiceException {
		try {
			turnoDAO.crearTurno(nuevoTurno);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void actualizarTurno(Turno turnoModificado, Turno turnoOriginal) throws ServiceException {
		try {
			turnoDAO.actualizarTurno(turnoModificado, turnoOriginal);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void confirmarTurno(Turno turno, boolean confirmar) throws ServiceException {
		if (turno.getPaciente() == null) {
			throw new ServiceException("El turno no tiene un paciente asignado");
		}
		
		if (!confirmar && !turno.getAsistioTurno()) {
			throw new ServiceException("No se puede anular la asistencia de un turno que no ha sido confirmado");
		}
		
		Turno turnoModificado = new Turno();
		
		turnoModificado.setPaciente(turno.getPaciente());
		turnoModificado.setMedico(turno.getMedico());
		turnoModificado.setFecha(turno.getFecha());
		turnoModificado.setHorario(turno.getHorario());
		turnoModificado.setIdTurno(turno.getIdTurno());
		turnoModificado.setAsistioTurno(confirmar);
		
		float costoTurno = confirmar ? turno.getMedico().getCostoConsulta() : 0;
		turnoModificado.setCosto(costoTurno);

		try {
			turnoDAO.actualizarTurno(turnoModificado, turno);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void borrarTurno(Turno nuevoTurno) throws ServiceException {
		try {
			turnoDAO.borrarTurno(nuevoTurno);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void borrarLosTurnos() throws ServiceException {
		try {
			turnoDAO.borrarLosTurnos();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	private void asociarRelacionesTurnos(ArrayList<Turno> turnosDB) throws ServiceException {
		try {
			for (Turno turno : turnosDB) {
				asociarRelacionesTurno(turno);
			}
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	private void asociarRelacionesTurno(Turno turno) throws ServiceException {
		try {
			Medico medico = turno.getMedico();
			Paciente paciente = turno.getPaciente();
			if (medico != null && medico.getIdMedico() != 0) {
				Medico turnoMedico = medicoService.obtenerMedico(turno.getMedico().getIdMedico());
				turno.setMedico(turnoMedico);
			}
			if (paciente != null && paciente.getIdPaciente() != 0) {
				Paciente turnoPaciente = pacienteService.obtenerPaciente(turno.getPaciente().getIdPaciente());
				turno.setPaciente(turnoPaciente);
			}
		} catch (ServiceException e) {
			throw e;
		}
	}
}
