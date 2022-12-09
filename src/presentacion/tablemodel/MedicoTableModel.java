package presentacion.tablemodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacion.model.Medico;

public class MedicoTableModel extends AbstractTableModel {
	
	public static final int COLUMNA_NOMBRE_USUARIO = 0;
	public static final int COLUMNA_CONTRASENIA = 1;
	public static final int COLUMNA_NOMBRE = 2;
	public static final int COLUMNA_APELLIDO = 3;
	public static final int COLUMNA_EMAIL = 4;
	public static final int COLUMNA_FECHA_NAC = 5;
	public static final int COLUMNA_DNI = 6;
	public static final int COLUMNA_COSTO_CONSULTA = 7;
	
	private String[] nombresColumnas = {"Nombre de Usuario", "Contrasenia", "Nombre", "Apellido", "Email", "Fecha Nacimiento", "DNI", "Costo Consulta"};
	
	private Class<?>[] tiposColumnas = {String.class, String.class, String.class, String.class, String.class, Date.class, Integer.class, Integer.class};

	private List<Medico> contenido;
	
    public List<Medico> getContenido() {
    	return contenido;
    }

    public void setContenido(List<Medico> contenido) {
    	this.contenido = contenido;
    }
	
	public MedicoTableModel() {
		contenido = new ArrayList<Medico>();
	}
	
	public MedicoTableModel(List<Medico> contenidoInicial) {
		contenido = contenidoInicial;
	}

	public int getColumnCount() {
		return nombresColumnas.length;
	}

	public int getRowCount() {
		int cantidadFilas = 0;
		if (contenido != null) {
			cantidadFilas = contenido.size();
		}
		return cantidadFilas;
	}
	
	public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    public Class<?> getColumnClass(int col) {
        return tiposColumnas[col];
    }
    
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medico medico = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case COLUMNA_NOMBRE_USUARIO:
			result = medico.getNombreUsuario();
			break;
		case COLUMNA_CONTRASENIA:
			result = medico.getContrasenia();
			break;
		case COLUMNA_NOMBRE:
			result = medico.getNombre();
			break;
		case COLUMNA_APELLIDO:
			result = medico.getApellido();
			break;
		case COLUMNA_EMAIL:
			result = medico.getEmail();
			break;
		case COLUMNA_FECHA_NAC:
			result = medico.getFechaNacimiento();
			break;
		case COLUMNA_DNI:
			result = medico.getDni();
			break;
		case COLUMNA_COSTO_CONSULTA:
			result = medico.getCostoConsulta();
			break;
		default:
			result = new String("");
		}
		
		return result;
	}
}
