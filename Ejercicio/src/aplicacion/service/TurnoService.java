package aplicacion.service;

import java.sql.Date;
import java.util.ArrayList;

import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
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
			Medico turnoMedico = medicoService.obtenerMedico(turno.getMedico().getIdMedico());
			turno.setMedico(turnoMedico);
			if (turno.getPaciente() != null) {
				Paciente turnoPaciente = pacienteService.obtenerPaciente(turno.getPaciente().getIdPaciente());
				turno.setPaciente(turnoPaciente);
			}
		} catch (ServiceException e) {
			throw e;
		}
	}
}
