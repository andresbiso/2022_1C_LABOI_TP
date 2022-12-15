package datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import aplicacion.model.Paciente;
import aplicacion.model.Usuario;
import datos.DBManager;
import datos.exception.DAOException;
import datos.exception.DBException;
import datos.exception.TableManagerException;
import datos.tablemanager.PacienteTableManager;

public class PacienteDAOH2 implements PacienteDAO {

	private final PacienteTableManager pacienteTableManager;

	public PacienteDAOH2() {
		this.pacienteTableManager = new PacienteTableManager();
	}

	public int crearPaciente(Paciente paciente) throws DAOException {
		if (paciente == null) {
			throw new DAOException("Hubo un error al querer crear el paciente");
		}

		ArrayList<String> pacienteCamposList = paciente.obtenerCamposPaciente();
		ArrayList<String> pacienteValoresList = paciente.obtenerValoresPaciente();

		int resultado = 0;
		try {
			resultado = pacienteTableManager.insertarUnRegistro(pacienteCamposList, pacienteValoresList);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int borrarPaciente(Paciente paciente) throws DAOException {
		if (paciente == null) {
			throw new DAOException("Hubo un error al querer borrar el paciente");
		}

		String condicion = "idPaciente = " + paciente.getIdPaciente();

		int resultado = 0;
		try {
			resultado = pacienteTableManager.eliminarRegistros(condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public int borrarLosPacientes() throws DAOException {
		int resultado = 0;
		try {
			resultado = pacienteTableManager.eliminarTodosLosRegistros();
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int actualizarPaciente(Paciente pacienteModificado, Paciente pacienteOriginal) throws DAOException {
		if (pacienteModificado == null || pacienteOriginal == null) {
			throw new DAOException("Hubo un error al querer actualizar el paciente");
		}
		
		HashMap<String, String> pacienteModificadoHashMap = pacienteModificado.obtenerHashMapPaciente();
		HashMap<String, String> pacienteOriginalHashMap = pacienteOriginal.obtenerHashMapPaciente();
		
		if (pacienteOriginalHashMap == null || pacienteModificadoHashMap == null) {
			throw new DAOException("Hubo un error al querer actualizar el paciente");
		}
		
		HashMap<String, String> pacienteHashMap = new HashMap<String, String>();
		
	    for (String key : pacienteOriginalHashMap.keySet()) {
	        if (!pacienteOriginalHashMap.get(key).equalsIgnoreCase(pacienteModificadoHashMap.get(key))) {
	        	pacienteHashMap.put(key, pacienteModificadoHashMap.get(key));
	        }
	    }
	
		String condicion = "idPaciente = " + pacienteOriginal.getIdPaciente();

		int resultado = 0;
		try {
			if (!pacienteHashMap.isEmpty()) {
				resultado = pacienteTableManager.actualizarRegistros(pacienteHashMap, condicion);	
			}
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public Paciente consultarPaciente(Usuario usuarioPaciente) throws DAOException {
		if (usuarioPaciente == null) {
			throw new DAOException("Hubo un error al querer consultar el paciente");
		}
		
		String condicion = "idUsuario = " + usuarioPaciente.getIdUsuario();

		ArrayList<Paciente> resultados = consultarPacientes(condicion, usuarioPaciente);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}
	
	public Paciente consultarPaciente(int idPaciente) throws DAOException {
		if (idPaciente < 0) {
			throw new DAOException("Hubo un error al querer consultar el paciente");
		}
		
		String condicion = "idPaciente = " + String.valueOf(idPaciente);

		ArrayList<Paciente> resultados = consultarPacientes(condicion, new Usuario());

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}

	public ArrayList<Paciente> listarTodosLosPacientes(ArrayList<Usuario> usuariosPacientes) throws DAOException {
		if (usuariosPacientes == null || usuariosPacientes.size() == 0) {
			throw new DAOException("Hubo un error al querer consultar los pacientes");
		}
		
		ArrayList<Paciente> resultadoTotal = new ArrayList<Paciente>();
		
		for (Usuario usuario : usuariosPacientes) {
			Paciente resultado = consultarPaciente(usuario);
			resultadoTotal.add(resultado);
		}

		if (resultadoTotal.size() == 0) {
			return null;
		}

		return resultadoTotal;
	}
	

	private ArrayList<Paciente> consultarPacientes(String condicion, Usuario usuarioPaciente) throws DAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append(pacienteTableManager.obtenerNombreTabla().toUpperCase());

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

		ArrayList<Paciente> registros = new ArrayList<>();
		try {
			while (respuestaEjecucion.next()) {
				int idUsuario = respuestaEjecucion.getInt("idUsuario");
				int idPaciente = respuestaEjecucion.getInt("idPaciente");
				Paciente registro = new Paciente(usuarioPaciente, idPaciente);
				registro.setIdUsuario(idUsuario);
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
