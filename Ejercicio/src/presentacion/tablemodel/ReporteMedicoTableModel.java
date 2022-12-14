package presentacion.tablemodel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacion.model.Turno;

@SuppressWarnings("serial")
public class ReporteMedicoTableModel extends AbstractTableModel {

	public static final int COLUMNA_FECHA = 0;
	public static final int COLUMNA_HORARIO = 1;
	public static final int COLUMNA_COSTO = 2;
	
	private String[] nombresColumnas = {"Fecha", "Horario", "Costo"};
	
	private Class<?>[] tiposColumnas = {Date.class, String.class, Float.class};

	private List<Turno> contenido;
	
    public List<Turno> getContenido() {
    	return contenido;
    }

    public void setContenido(List<Turno> contenido) {
    	this.contenido = contenido;
    }
	
	public ReporteMedicoTableModel() {
		contenido = new ArrayList<Turno>();
	}
	
	public ReporteMedicoTableModel(List<Turno> contenidoInicial) {
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
		case COLUMNA_FECHA:
			result = turno.getFecha();
			break;
		case COLUMNA_HORARIO:
			result = turno.getHorario();
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