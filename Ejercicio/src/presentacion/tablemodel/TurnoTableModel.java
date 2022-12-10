package presentacion.tablemodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;

@SuppressWarnings("serial")
public class TurnoTableModel extends AbstractTableModel {
	
	public static final int COLUMNA_MEDICO = 0;
	public static final int COLUMNA_FECHA = 1;
	public static final int COLUMNA_HORARIO = 2;
	public static final int COLUMNA_PACIENTE = 3;
	public static final int COLUMNA_ASISTIO_TURNO = 4;
	public static final int COLUMNA_COSTO = 5;
	
	private String[] nombresColumnas = {"Medico", "Fecha", "Horario", "Paciente", "Asistio Turno", "Costo"};
	
	private Class<?>[] tiposColumnas = {String.class, Date.class, String.class, String.class, Boolean.class, Float.class};

	private List<Turno> contenido;
	
    public List<Turno> getContenido() {
    	return contenido;
    }

    public void setContenido(List<Turno> contenido) {
    	this.contenido = contenido;
    }
	
	public TurnoTableModel() {
		contenido = new ArrayList<Turno>();
	}
	
	public TurnoTableModel(List<Turno> contenidoInicial) {
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
		Turno turno = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case COLUMNA_MEDICO:
			Medico medico = turno.getMedico();
			result = medico.getNombreCompleto();
			break;
		case COLUMNA_FECHA:
			result = turno.getFecha();
			break;
		case COLUMNA_HORARIO:
			result = turno.getHorario();
			break;
		case COLUMNA_PACIENTE:
			Paciente paciente = turno.getPaciente();
			result = paciente != null ? paciente.getNombreCompleto() : "";
			break;
		case COLUMNA_ASISTIO_TURNO:
			result = turno.getAsistioTurno();
			break;
		case COLUMNA_COSTO:
			result = turno.getCosto();
			break;
		default:
			result = new String("");
		}
		
		return result;
	}
}
