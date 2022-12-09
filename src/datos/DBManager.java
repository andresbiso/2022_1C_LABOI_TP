package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import datos.exception.DBManagerException;

public class DBManager {

	private static final String DB_DRIVER = "org.h2.Driver";
	// Ruta Absoluta y acceso múltiple
	private static final String DB_BASE_URL = "jdbc:h2:file:C:/h2/db/h2db;AUTO_SERVER=TRUE";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "sa";

	// Conexión

	public static Connection abrirConexion() throws DBManagerException {
		return abrirConexion(false);
	}

	public static Connection abrirConexion(boolean autoCommit) throws DBManagerException {
		Connection conexion = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new DBManagerException("Error al intentar cargar el driver jdbc");
		}
		try {
			conexion = DriverManager.getConnection(DB_BASE_URL, DB_USERNAME, DB_PASSWORD);
			conexion.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			throw new DBManagerException("Error al intentar conectar a la base de datos");
		}

		return conexion;
	}

	public static void cerrarConexion(Connection conexion) throws DBManagerException {
		if(verificarConexion(conexion)) {
			try {
				conexion.close();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar cerrar la conexión");
			}	
		}
	}
	
	public static boolean verificarConexion(Connection conexion) throws DBManagerException {
		if (conexion != null) {
			try {
				return !conexion.isClosed();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar verificar conexión a la base de datos");
			}
		}
		return true;
	}
	
	// Sentencia

	public static Statement crearSentencia(Connection conexion) throws DBManagerException {
		if(verificarConexion(conexion)) {
			try {
				return conexion.createStatement();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar crear sentencia");
			}	
		}
		return null;
	}

	public static void cerrarSentencia(Statement sentencia) throws DBManagerException {
		if(verificarSentencia(sentencia)) {
			try {
				sentencia.close();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar cerrar la sentencia");
			}	
		}
	}

	public static boolean verificarSentencia(Statement sentencia) throws DBManagerException {
		if (sentencia != null) {
			try {
				return !sentencia.isClosed();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar verificar estado de sentencia");
			}
		}
		return true;
	}

	// Ejecución de Sentencias

	public static boolean ejecutarSentenciaDDL(Statement sentencia, String sql) throws DBManagerException {
		boolean respuestaEjecucion = false;
		if(verificarSentencia(sentencia)) {
			try {
				respuestaEjecucion = sentencia.execute(sql);
				commitSentencia(sentencia);
			} catch (SQLTimeoutException e) {
				rollbackSentencia(sentencia);
				throw new DBManagerException("Timeout al intentar ejecutar sentencia DDL");
			} catch (SQLException e) {
				rollbackSentencia(sentencia);
				throw new DBManagerException("Error al intentar ejecutar sentencia DDL");
			}
		}
		return respuestaEjecucion;
	}
	
	public static int ejecutarSentenciaDML(Statement sentencia, String sql) throws DBManagerException {
		int respuestaEjecucion = 0;
		if(verificarSentencia(sentencia)) {
			try {
				respuestaEjecucion = sentencia.executeUpdate(sql);
				commitSentencia(sentencia);
			} catch (SQLTimeoutException e) {
				rollbackSentencia(sentencia);
				throw new DBManagerException("Timeout al intentar ejecutar sentencia DML");
			} catch (SQLException e) {
				rollbackSentencia(sentencia);
				throw new DBManagerException("Error al intentar ejecutar sentencia DML");
			}
		}
		return respuestaEjecucion;
	}
	
	public static ResultSet ejecutarConsulta(Statement sentencia, String sql) throws DBManagerException {
		ResultSet respuestaEjecucion = null;
		if(verificarSentencia(sentencia)) {
			try {
				respuestaEjecucion = sentencia.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				throw new DBManagerException("Timeout al intentar ejecutar consulta");
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar ejecutar consulta");
			}	
		}
		return respuestaEjecucion;
	}
	
	// Realizar Sentencias
	
	public static boolean realizarSentenciaDDL(String sql) throws DBManagerException {
		Connection conexion = DBManager.abrirConexion();
		Statement sentencia = DBManager.crearSentencia(conexion);
		boolean respuestaEjecucion = DBManager.ejecutarSentenciaDDL(sentencia, sql);
		DBManager.cerrarSentencia(sentencia);	
		DBManager.cerrarConexion(conexion);	
		return respuestaEjecucion;
	}
	
	public static int realizarSentenciaDML(String sql) throws DBManagerException {
		Connection conexion = DBManager.abrirConexion();
		Statement sentencia = DBManager.crearSentencia(conexion);
		int respuestaEjecucion = DBManager.ejecutarSentenciaDML(sentencia, sql);
		DBManager.cerrarSentencia(sentencia);	
		DBManager.cerrarConexion(conexion);	
		return respuestaEjecucion;
	}
	
	// ResultSet
	
	public static void cerrarResultSet(ResultSet resultSet) throws DBManagerException {
		if(verificarResultSet(resultSet)) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar cerrar el ResultSet");
			}	
		}
	}

	public static boolean verificarResultSet(ResultSet resultSet) throws DBManagerException {
		if (resultSet != null) {
			try {
				return !resultSet.isClosed();
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar verificar estado de ResultSet");
			}
		}
		return true;
	}
	
	// Commit y Rollback
	
	public static boolean commitSentencia(Statement sentencia) throws DBManagerException {
		if(verificarSentencia(sentencia)) {
			try {
				Connection conexion = sentencia.getConnection();
				if(!conexion.getAutoCommit()) {
					try {
						conexion.commit();
					} catch(SQLException e) {
						throw new DBManagerException("Error al intentar realizar commit");
					}
				}
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar consultar auto commit");
			}
		}
		return false;
	}
	
	public static boolean rollbackSentencia(Statement sentencia) throws DBManagerException {
		if(verificarSentencia(sentencia)) {
			try {
				Connection conexion = sentencia.getConnection();
				try {
					conexion.rollback();
				} catch(SQLException e) {
					throw new DBManagerException("Error al intentar realizar rollback");
				}
			} catch (SQLException e) {
				throw new DBManagerException("Error al intentar obtener conexión");
			}
		}
		return false;
	}
	
	// Liberar instancia de Base de Datos

	public static void apagarDB() throws DBManagerException {
		Connection conexion = abrirConexion();
		Statement sentencia = crearSentencia(conexion);
		ejecutarSentenciaDDL(sentencia, "SHUTDOWN");
		cerrarSentencia(sentencia);
		cerrarConexion(conexion);
	}

}