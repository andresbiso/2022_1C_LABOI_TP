package datos.tablemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import datos.DBManager;
import datos.exception.DBManagerException;
import datos.exception.TableManagerException;

import java.util.Set;

public class TableManager {

	private final String nombreTabla;
	
	TableManager(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	
	public String obtenerNombreTabla() {
		return nombreTabla;
	}

	// DDL
	public boolean crearTabla(ArrayList<String> campoTipoList) throws TableManagerException {
		if (nombreTabla.isBlank() || campoTipoList.isEmpty()) {
			throw new TableManagerException("Error al intentar crear tabla");
		}
		
		StringBuilder sql = new StringBuilder();
	 	sql.append("CREATE TABLE ");
	 	sql.append("IF NOT EXISTS ");
	 	sql.append(nombreTabla.toUpperCase() + " ");
	 	sql.append("(");
	 	
	 	int totalTamanioCampoTipoList = campoTipoList.size();
	 	int numeroEntryActual = 0;
	 	for (String campoTipo : campoTipoList) {
	 		sql.append(campoTipo);
	 		if (numeroEntryActual < totalTamanioCampoTipoList - 1) {
	 			sql.append(",");	
	 		}
	 		numeroEntryActual++;
		}
	 	sql.append(")");

		try {
			return DBManager.realizarSentenciaDDL(sql.toString());
		} catch(DBManagerException e) {
			throw new TableManagerException("Error al intentar crear tabla");
		}
	}
	
	public boolean borrarTabla() throws TableManagerException {
		if (nombreTabla.isBlank()) {
			throw new TableManagerException("Error al intentar borrar tabla");
		}

		StringBuilder sql = new StringBuilder();
	 	sql.append("DROP TABLE ");
	 	sql.append("IF EXISTS ");
	 	sql.append(nombreTabla.toUpperCase());

		try {
			return DBManager.realizarSentenciaDDL(sql.toString());
		} catch(DBManagerException e) {
			throw new TableManagerException("Error al intentar borrar tabla");
		}
	}
	
	// DML
	public int insertarRegistros(ArrayList<String> nombreColumnas, ArrayList<String[]> listaValores) throws TableManagerException {
		if (nombreTabla.isBlank() || nombreColumnas.isEmpty() || listaValores.isEmpty()) {
			throw new TableManagerException("Error al intentar insertar registros");
		}

		StringBuilder sql = new StringBuilder();
	 	sql.append("INSERT INTO ");
	 	sql.append(nombreTabla.toUpperCase() + " ");
	 	sql.append("( ");
	 	
	 	// Agrego columnas a script insert into
		int totalTamanioNombreColumnas = nombreColumnas.size();
	 	int numeroNombreColumnaActual = 0;
		for (String nombreColumna : nombreColumnas) {
	 		sql.append(nombreColumna);
	 		if (numeroNombreColumnaActual < totalTamanioNombreColumnas - 1) {
	 			sql.append(",");	
	 		}
	 		numeroNombreColumnaActual++;
		}

		sql.append(" )");
		sql.append(" VALUES ");

		// Agrego valores a insertar con soporte de más de un registro
		int totalTamanioListaValores = listaValores.size();
	 	int numeroListaValoresActual = 0;
		for (String[] valoresRegistro : listaValores) {
			int totalTamanioValoresRegistro = valoresRegistro.length;
			if (totalTamanioValoresRegistro != totalTamanioNombreColumnas) {
				throw new TableManagerException("Error al generar script de inserción. Revisar cantidad de valores por registro.");
			}
		 	int numeroValoresRegistroActual = 0;
		 	sql.append("( ");
			for (String valorCampo : valoresRegistro) {
				sql.append(valorCampo);
		 		if (numeroValoresRegistroActual < totalTamanioValoresRegistro - 1) {
		 			sql.append(",");	
		 		}
		 		numeroValoresRegistroActual++;
			}
			sql.append(" )");
			if (numeroListaValoresActual < totalTamanioListaValores - 1) {
	 			sql.append(",");	
	 		}
			numeroListaValoresActual++;
		}

		try {
			return DBManager.realizarSentenciaDML(sql.toString());
		} catch(DBManagerException e) {
			throw new TableManagerException("Error al intentar insertar registros");
		}
	}
	
	public int actualizarRegistros(HashMap<String, String> campoValorMap, String condicion) throws TableManagerException {
		if (nombreTabla.isBlank() || campoValorMap.isEmpty()) {
			throw new TableManagerException("Error al intentar actualizar registros");
		}
		
		StringBuilder sql = new StringBuilder();
	 	sql.append("UPDATE ");
	 	sql.append(nombreTabla.toUpperCase() + " ");
	 	sql.append("set ");
	 	
	 	Set<Entry<String, String>> campoValorEntrySet = campoValorMap.entrySet();
	 	int totalTamanioEntrySet = campoValorEntrySet.size();
	 	int numeroEntryActual = 0;
		for (Entry<String, String> campoValorEntry : campoValorEntrySet) {
	 		sql.append(campoValorEntry.getKey() + " = " + campoValorEntry.getValue());
	 		if (numeroEntryActual < totalTamanioEntrySet - 1) {
	 			sql.append(",");	
	 		}
	 		numeroEntryActual++;
		}
		
		if (!condicion.isBlank()) {
			sql.append(" WHERE ");
			sql.append(condicion);	
		}

		try {
			return DBManager.realizarSentenciaDML(sql.toString());
		} catch(DBManagerException e) {
			throw new TableManagerException("Error al intentar actualizar registros");
		}
	}
	
	public int eliminarRegistros(String condicion) throws TableManagerException {
		if (nombreTabla.isBlank()) {
			throw new TableManagerException("Error al intentar eliminar registros");
		}
		
		StringBuilder sql = new StringBuilder();
	 	sql.append("DELETE FROM ");
	 	sql.append(nombreTabla.toUpperCase() + " ");
	 	
	 	if (!condicion.isBlank()) {
			sql.append("WHERE ");
			sql.append(condicion);	
		}

		try {
			return DBManager.realizarSentenciaDML(sql.toString());
		} catch(DBManagerException e) {
			throw new TableManagerException("Error al intentar eliminar registros");
		}
	}
	
	public int insertarUnRegistro(ArrayList<String> nombreColumnas, ArrayList<String> listaValores) throws TableManagerException {
		String[] valores = listaValores.toArray(new String[0]);;
		ArrayList<String[]> nuevaListaValores = new ArrayList<>();
		nuevaListaValores.add(valores);
		
		return insertarRegistros(nombreColumnas, nuevaListaValores);
	}

	public int actualizarTodosLosRegistros(HashMap<String, String> campoValorMap) throws TableManagerException {
		return actualizarRegistros(campoValorMap, "");
	}

	public int eliminarTodosLosRegistros() throws TableManagerException {
		return eliminarRegistros("");
	}
}
