package datos.dao;

import java.sql.Date;
import java.util.ArrayList;

import aplicacion.model.Turno;
import datos.exception.DAOException;

public interface TurnoDAO {
	int crearTurno(Turno turno) throws DAOException;
	
	int borrarTurno(Turno turno) throws DAOException;
	
	int borrarLosTurnos() throws DAOException;

	int actualizarTurno(Turno turnoModificado, Turno turnoOriginal) throws DAOException;

	Turno consultarTurno(int idTurno) throws DAOException;
	
	boolean validarTurno(Turno turno) throws DAOException;

	ArrayList<Turno> listarTodosLosTurnos() throws DAOException;
	
	ArrayList<Turno> listarTodosLosTurnos(Date fecha) throws DAOException;
	
	// ArrayList<Turno> listarTodosLosTurnos(Date fechaInicio, Date fechaFin) throws DAOException;
	
	// ArrayList<Turno> listarTodosLosTurnos(int idMedico) throws DAOException;
}
