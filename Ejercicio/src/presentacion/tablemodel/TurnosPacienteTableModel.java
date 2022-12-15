package presentacion.tablemodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacion.model.Medico;
import aplicacion.model.Turno;

@SuppressWarnings("serial")
public class TurnosPacienteTableModel extends AbstractTableModel {
	
	public static final int COLUMNA_MEDICO = 0;
	public static final int COLUMNA_FECHA = 1;
	public static final int COLUMNA_HORARIO = 2;
	
	private String[] nombresColumnas = {"Medico", "Fecha", "Horario"};
	
	private Class<?>[] tiposColumnas = {String.class, Date.class, String.class};

	private List<Turno> contenido;
	
    public List<Turno> getContenido() {
    	return contenido;
    }

    public void setContenido(List<Turno> contenido) {
    	this.contenido = contenido;
    }
	
	public TurnosPacienteTableModel() {
		contenido = new ArrayList<Turno>();
	}
	
	public TurnosPacienteTableModel(List<Turno> contenidoInicial) {
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
			result = "";
			if (medico != null && medico.getIdMedico() != 0) {
				result = medico.getNombreCompleto();
			}
			break;
		case COLUMNA_FECHA:
			result = turno.getFecha();
			break;
		case COLUMNA_HORARIO:
			result = turno.getHorario();
			break;
		default:
			result = new String("");
		}
		
		return result;
	}
}
