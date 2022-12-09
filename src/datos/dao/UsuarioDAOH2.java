package datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import aplicacion.enums.UsuarioTipo;
import aplicacion.model.Usuario;
import datos.DBManager;
import datos.exception.DAOException;
import datos.exception.DBException;
import datos.exception.TableManagerException;
import datos.tablemanager.UsuarioTableManager;

public class UsuarioDAOH2 implements UsuarioDAO {

	private final UsuarioTableManager usuarioTableManager;

	public UsuarioDAOH2() {
		this.usuarioTableManager = new UsuarioTableManager();
	}

	public int crearUsuario(Usuario usuario) throws DAOException {
		if (usuario == null) {
			throw new DAOException("Hubo un error al querer crear el usuario");
		}

		ArrayList<String> usuarioCamposList = usuario.obtenerCamposUsuario();
		ArrayList<String> usuarioValoresList = usuario.obtenerValoresUsuario();

		int resultado = 0;
		try {
			resultado = usuarioTableManager.insertarUnRegistro(usuarioCamposList, usuarioValoresList);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int borrarUsuario(Usuario usuario) throws DAOException {
		if (usuario == null) {
			throw new DAOException("Hubo un error al querer borrar el usuario");
		}

		String condicion = "idUsuario = " + usuario.getIdUsuario();

		int resultado = 0;
		try {
			resultado = usuarioTableManager.eliminarRegistros(condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public int borrarLosUsuarios() throws DAOException {
		int resultado = 0;
		try {
			resultado = usuarioTableManager.eliminarTodosLosRegistros();
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public int borrarLosUsuariosPorTipo(UsuarioTipo usuarioTipo) throws DAOException {
		if (usuarioTipo == null) {
			throw new DAOException("Hubo un error al querer borrar los usuarios");
		}

		String condicion = "usuarioTipo = " + usuarioTipo.getTipo();

		int resultado = 0;
		try {
			resultado = usuarioTableManager.eliminarRegistros(condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public int actualizarUsuario(Usuario usuarioModificado, Usuario usuarioOriginal) throws DAOException {
		if (usuarioModificado == null || usuarioOriginal == null) {
			throw new DAOException("Hubo un error al querer actualizar el usuario");
		}
		
		HashMap<String, String> usuarioModificadoHashMap = usuarioModificado.obtenerHashMapUsuario();
		HashMap<String, String> usuarioOriginalHashMap = usuarioOriginal.obtenerHashMapUsuario();
		
		if (usuarioModificadoHashMap == null || usuarioOriginalHashMap == null) {
			throw new DAOException("Hubo un error al querer actualizar el usuario");
		}
		
		HashMap<String, String> usuarioHashMap = new HashMap<String, String>();
		
	    for (String key : usuarioOriginalHashMap.keySet()) {
	        if (!usuarioOriginalHashMap.get(key).equalsIgnoreCase(usuarioModificadoHashMap.get(key))) {
	        	usuarioHashMap.put(key, usuarioModificadoHashMap.get(key));
	        }
	    }

		String condicion = "idUsuario = " + usuarioOriginal.getIdUsuario();

		int resultado = 0;
		try {
			resultado = usuarioTableManager.actualizarRegistros(usuarioHashMap, condicion);
		} catch(TableManagerException e) {
			throw new DAOException(e);
		}
		return resultado;
	}

	public Usuario consultarUsuario(int idUsuario) throws DAOException {
		String condicion = "idUsuario = " + idUsuario;

		ArrayList<Usuario> resultados = consultarUsuarios(condicion);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}
	
	public Usuario consultarUsuario(String nombreUsuario) throws DAOException {
		nombreUsuario = "\'" + nombreUsuario.trim() + "\'";
		String condicion = "nombreUsuario = " + nombreUsuario;

		ArrayList<Usuario> resultados = consultarUsuarios(condicion);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados.get(0);
	}

	public ArrayList<Usuario> listarTodosLosUsuarios() throws DAOException {
		ArrayList<Usuario> resultados = consultarUsuarios("");

		if (resultados.size() == 0) {
			return null;
		}

		return resultados;
	}
	
	public ArrayList<Usuario> listarTodosLosUsuarios(UsuarioTipo usuarioTipo) throws DAOException {
		String condicion = "usuarioTipo = " + usuarioTipo.getTipo();

		ArrayList<Usuario> resultados = consultarUsuarios(condicion);

		if (resultados.size() == 0) {
			return null;
		}

		return resultados;
	}

	private ArrayList<Usuario> consultarUsuarios(String condicion) throws DAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append(usuarioTableManager.obtenerNombreTabla().toUpperCase());

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

		ArrayList<Usuario> registros = new ArrayList<>();
		try {
			while (respuestaEjecucion.next()) {
				int idUsuario = respuestaEjecucion.getInt("idUsuario");
				String nombreUsuario = respuestaEjecucion.getString("nombreUsuario");
				String contrasenia = respuestaEjecucion.getString("contrasenia");
				String nombre = respuestaEjecucion.getString("nombre");
				String apellido = respuestaEjecucion.getString("apellido");
				String email = respuestaEjecucion.getString("email");
				Date fechaNacimiento = respuestaEjecucion.getDate("fechaNacimiento");
				int dni = respuestaEjecucion.getInt("dni");
				UsuarioTipo usuarioTipo = UsuarioTipo.fromId(respuestaEjecucion.getInt("usuarioTipo"));
				Usuario registro = new Usuario(idUsuario, nombreUsuario, contrasenia,
						nombre, apellido, email, fechaNacimiento, dni, usuarioTipo);
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
