package presentacion.tablemodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacion.model.Paciente;

public class PacienteTableModel extends AbstractTableModel {
	
	public static final int COLUMNA_NOMBRE_USUARIO = 0;
	public static final int COLUMNA_CONTRASENIA = 1;
	public static final int COLUMNA_NOMBRE = 2;
	public static final int COLUMNA_APELLIDO = 3;
	public static final int COLUMNA_EMAIL = 4;
	public static final int COLUMNA_FECHA_NAC = 5;
	public static final int COLUMNA_DNI = 6;
	
	private String[] nombresColumnas = {"Nombre de Usuario", "Contrasenia", "Nombre", "Apellido", "Email", "Fecha Nacimiento", "DNI"};
	
	private Class<?>[] tiposColumnas = {String.class, String.class, String.class, String.class, String.class, Date.class, Integer.class};

	private List<Paciente> contenido;
	
    public List<Paciente> getContenido() {
    	return contenido;
    }

    public void setContenido(List<Paciente> contenido) {
    	this.contenido = contenido;
    }
	
	public PacienteTableModel() {
		contenido = new ArrayList<Paciente>();
	}
	
	public PacienteTableModel(List<Paciente> contenidoInicial) {
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
		Paciente paciente = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case COLUMNA_NOMBRE_USUARIO:
			result = paciente.getNombreUsuario();
			break;
		case COLUMNA_CONTRASENIA:
			result = paciente.getContrasenia();
			break;
		case COLUMNA_NOMBRE:
			result = paciente.getNombre();
			break;
		case COLUMNA_APELLIDO:
			result = paciente.getApellido();
			break;
		case COLUMNA_EMAIL:
			result = paciente.getEmail();
			break;
		case COLUMNA_FECHA_NAC:
			result = paciente.getFechaNacimiento();
			break;
		case COLUMNA_DNI:
			result = paciente.getDni();
			break;
		default:
			result = new String("");
		}
		
		return result;
	}
}
