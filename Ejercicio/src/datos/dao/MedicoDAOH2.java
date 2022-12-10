package datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import aplicacion.model.Medico;
import aplicacion.model.Usuario;
import datos.DBManager;
import datos.exception.DAOException;
import datos.exception.DBException;
import datos.exception.TableManagerException;
import datos.tablemanager.MedicoTableManager;

public class MedicoDAOH2 implements MedicoDAO {

	private final MedicoTableManager medicoTableManager;

	public MedicoDAOH2() {
		this.medicoTableManager = new MedicoTableManager();
	}

	public int crearMedico(Medico medico) throws DAOException {
		if (medico == null) {
			throw new DAOException("Hubo un error al querer crear el médico");
		}

		ArrayList<String> medicoCamposList = medico.obtenerCamposMedico();
		ArrayList<String> medicoValoresList = medico.obtenerValoresMedico();

		int resultado = 0;
		try {
			resultado = medicoTableManager.insertarUnRegistro(medicoCamposList, medicoValoresList);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int borrarMedico(Medico medico) throws DAOException {
		if (medico == null) {
			throw new DAOException("Hubo un error al querer borrar el médico");
		}

		String condicion = "idMedico = " + medico.getIdMedico();

		int resultado = 0;
		try {
			resultado = medicoTableManager.eliminarRegistros(condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public int borrarLosMedicos() throws DAOException {
		int resultado = 0;
		try {
			resultado = medicoTableManager.eliminarTodosLosRegistros();
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int actualizarMedico(Medico medicoModificado, Medico medicoOriginal) throws DAOException {
		if (medicoModificado == null || medicoOriginal == null) {
			throw new DAOException("Hubo un error al querer actualizar el médico");
		}
		
		HashMap<String, String> medicoModificadoHashMap = medicoModificado.obtenerHashMapMedico();
		HashMap<String, String> medicoOriginalHashMap = medicoOriginal.obtenerHashMapMedico();
		
		if (medicoOriginalHashMap == null || medicoModificadoHashMap == null) {
			throw new DAOException("Hubo un error al querer actualizar el médico");
		}
		
		HashMap<String, String> medicoHashMap = new HashMap<String, String>();
		
	    for (String key : medicoOriginalHashMap.keySet()) {
	        if (!medicoOriginalHashMap.get(key).equalsIgnoreCase(medicoModificadoHashMap.get(key))) {
	        	medicoHashMap.put(key, medicoModificadoHashMap.get(key));
	        }
	    }
	
		String condicion = "idMedico = " + medicoOriginal.getIdMedico();

		int resultado = 0;
		try {
			resultado = medicoTableManager.actualizarRegistros(medicoHashMap, condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public Medico consultarMedico(Usuario usuarioMedico) throws DAOException {
		if (usuarioMedico == null) {
			throw new DAOException("Hubo un error al querer consultar el médico");
		}
		
		String condicion = "idUsuario = " + usuarioMedico.getIdUsuario();

		ArrayList<Medico> resultados = consultarMedicos(condicion, usuarioMedico);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}
	
	public Medico consultarMedico(int idMedico) throws DAOException {
		if (idMedico < 0) {
			throw new DAOException("Hubo un error al querer consultar el médico");
		}
		
		String condicion = "idMedico = " + String.valueOf(idMedico);

		ArrayList<Medico> resultados = consultarMedicos(condicion, new Usuario());

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}

	public ArrayList<Medico> listarTodosLosMedicos(ArrayList<Usuario> usuariosMedicos) throws DAOException {
		if (usuariosMedicos == null || usuariosMedicos.size() == 0) {
			throw new DAOException("Hubo un error al querer consultar los médico");
		}
		
		ArrayList<Medico> resultadoTotal = new ArrayList<Medico>();
		
		for (Usuario usuario : usuariosMedicos) {
			Medico resultado = consultarMedico(usuario);
			resultadoTotal.add(resultado);
		}

		if (resultadoTotal.size() == 0) {
			return null;
		}

		return resultadoTotal;
	}

	private ArrayList<Medico> consultarMedicos(String condicion, Usuario usuarioMedico) throws DAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append(medicoTableManager.obtenerNombreTabla().toUpperCase());

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

		ArrayList<Medico> registros = new ArrayList<>();
		try {
			while (respuestaEjecucion.next()) {
				int idUsuario = respuestaEjecucion.getInt("idUsuario");
				int idMedico = respuestaEjecucion.getInt("idMedico");
				int costoConsulta = respuestaEjecucion.getInt("costoConsulta");
				Medico registro = new Medico(usuarioMedico, idMedico, costoConsulta);
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
