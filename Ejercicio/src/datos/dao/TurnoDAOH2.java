package datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import datos.DBManager;
import datos.exception.DAOException;
import datos.exception.DBException;
import datos.exception.TableManagerException;
import datos.tablemanager.TurnoTableManager;

public class TurnoDAOH2 implements TurnoDAO {

	private final TurnoTableManager turnoTableManager;

	public TurnoDAOH2() {
		this.turnoTableManager = new TurnoTableManager();
	}

	public int crearTurno(Turno turno) throws DAOException {
		if (turno == null) {
			throw new DAOException("Hubo un error al querer crear el turno");
		}

		ArrayList<String> turnoCamposList = turno.obtenerCamposTurno();
		ArrayList<String> turnoValoresList = turno.obtenerValoresTurno();

		int resultado = 0;
		try {
			resultado = turnoTableManager.insertarUnRegistro(turnoCamposList, turnoValoresList);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int borrarTurno(Turno turno) throws DAOException {
		if (turno == null) {
			throw new DAOException("Hubo un error al querer borrar el turno");
		}

		String condicion = "idTurno = " + turno.getIdTurno();

		int resultado = 0;
		try {
			resultado = turnoTableManager.eliminarRegistros(condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public int borrarLosTurnos() throws DAOException {
		int resultado = 0;
		try {
			resultado = turnoTableManager.eliminarTodosLosRegistros();
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int actualizarTurno(Turno turnoModificado, Turno turnoOriginal) throws DAOException {
		if (turnoModificado == null || turnoOriginal == null) {
			throw new DAOException("Hubo un error al querer actualizar el turno");
		}
		
		HashMap<String, String> turnoModificadoHashMap = turnoModificado.obtenerHashMapTurno();
		HashMap<String, String> turnoOriginalHashMap = turnoOriginal.obtenerHashMapTurno();
		
		if (turnoModificadoHashMap == null || turnoOriginalHashMap == null) {
			throw new DAOException("Hubo un error al querer actualizar el turno");
		}
		
		HashMap<String, String> turnoHashMap = new HashMap<String, String>();
		
	    for (String key : turnoOriginalHashMap.keySet()) {
	        if (!turnoOriginalHashMap.get(key).equalsIgnoreCase(turnoModificadoHashMap.get(key))) {
	        	turnoHashMap.put(key, turnoModificadoHashMap.get(key));
	        }
	    }

		String condicion = "idTurno = " + turnoOriginal.getIdTurno();

		int resultado = 0;
		try {
			resultado = turnoTableManager.actualizarRegistros(turnoHashMap, condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public Turno consultarTurno(int idTurno) throws DAOException {
		String condicion = "idTurno = " + idTurno;

		ArrayList<Turno> resultados = consultarTurnos(condicion);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}
	
	public boolean validarTurno(Turno nuevoTurno) throws DAOException {
		if (nuevoTurno == null) {
			throw new DAOException("Hubo un error al querer validar el turno");
		}
		
		Medico medicoTurno = nuevoTurno.getMedico();
		
		if (medicoTurno == null || medicoTurno.getIdMedico() == 0) {
			throw new DAOException("Hubo un error al querer validar el turno");
		}

		StringBuilder condicion = new StringBuilder();
		condicion.append("idMedico = " + medicoTurno.getIdMedico());
		condicion.append(" AND ");
		condicion.append("fecha = ");
		condicion.append("'" + nuevoTurno.getFecha().toString() + "'");
		condicion.append(" AND ");
		condicion.append("horario = ");
		condicion.append("'" + nuevoTurno.getHorario() + "'");

		ArrayList<Turno> resultados = consultarTurnos(condicion.toString());

		if (resultados.size() == 0) {
			return true;
		}

		return false;
	}
	
	public boolean validarTurnoPaciente(Turno nuevoTurno) throws DAOException {
		if (nuevoTurno == null) {
			throw new DAOException("Hubo un error al querer validar el turno");
		}
		
		Paciente pacienteTurno = nuevoTurno.getPaciente();
		
		if (pacienteTurno == null || pacienteTurno.getIdPaciente() == 0) {
			throw new DAOException("Hubo un error al querer validar el turno");
		}

		StringBuilder condicion = new StringBuilder();
		condicion.append("fecha = ");
		condicion.append("'" + nuevoTurno.getFecha().toString() + "'");
		condicion.append(" AND ");
		condicion.append("horario = ");
		condicion.append("'" + nuevoTurno.getHorario() + "'");
		condicion.append(" AND ");
		condicion.append("idPaciente = " + pacienteTurno.getIdPaciente());
	
		ArrayList<Turno> resultados = consultarTurnos(condicion.toString());

		if (resultados.size() == 0) {
			return true;
		}

		return false;
	}

	public ArrayList<Turno> listarTodosLosTurnos() throws DAOException {
		ArrayList<Turno> resultados = consultarTurnos("");

		if (resultados.size() == 0) {
			return null;
		}

		return resultados;
	}
	
	public ArrayList<Turno> listarTodosLosTurnos(Date fecha) throws DAOException {
		String condicion = "fecha = " + fecha.toString();

		ArrayList<Turno> resultados = consultarTurnos(condicion);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados;
	}
	
	public ArrayList<Turno> listarTodosLosTurnos(int idMedico, Date fechaDesde, Date fechaHasta) throws DAOException {
		StringBuilder condicion = new StringBuilder();
		condicion.append("idMedico = " + idMedico);
		condicion.append(" AND ");
		condicion.append("fecha");
		condicion.append(" BETWEEN ");
		condicion.append("'" + fechaDesde.toString() + "'");
		condicion.append(" AND ");
		condicion.append("'" + fechaHasta.toString() + "'");
		condicion.append(" AND ");
		condicion.append("asistioturno = TRUE");

		ArrayList<Turno> resultados = consultarTurnos(condicion.toString());

		if (resultados.size() == 0) {
			return null;
		}

		return resultados;
	}

	private ArrayList<Turno> consultarTurnos(String condicion) throws DAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append(turnoTableManager.obtenerNombreTabla().toUpperCase());

		if (!condicion.isBlank()) {
			sql.append(" WHERE ");
			sql.append(condicion);
		}
		
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet respuestaEjecucion = null;
		try {
			conexion = DBManager.abrirConexion();
			sentencia = DBManager.crearSentencia(conexion);
			respuestaEjecucion = DBManager.ejecutarConsulta(sentencia, sql.toString());
		} catch(DBException e) {
			throw new DAOException(e);
		}

		ArrayList<Turno> registros = new ArrayList<>();
		try {
			while (respuestaEjecucion.next()) {
				int idTurno = respuestaEjecucion.getInt("idTurno");
				int idMedico = respuestaEjecucion.getInt("idMedico");
				Date fecha = respuestaEjecucion.getDate("fecha");
				String horario = respuestaEjecucion.getString("horario");
				int idPaciente = respuestaEjecucion.getInt("idPaciente");
				if (respuestaEjecucion.wasNull()) {
					idPaciente = -1;
				}
				boolean asistioTurno = respuestaEjecucion.getBoolean("asistioTurno");
				float costo = respuestaEjecucion.getFloat("costo");
				Paciente pacienteTurno = idPaciente > -1 ? new Paciente(idPaciente) : null; 
				Turno registro = new Turno(idTurno, new Medico(idMedico), fecha, horario,
						pacienteTurno, asistioTurno, costo);
				registros.add(registro);
			}
		} catch (SQLException e1) {
			throw new DAOException("Error al intentar obtener valores");
		} finally {
			try {
				DBManager.cerrarResultSet(respuestaEjecucion);
				DBManager.cerrarSentencia(sentencia);
				DBManager.cerrarConexion(conexion);
			} catch(DBException e2) {
				throw new DAOException(e2);
			}
		}
		return registros;
	}

}
