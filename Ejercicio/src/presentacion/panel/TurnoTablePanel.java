package presentacion.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import aplicacion.model.Turno;
import presentacion.PanelManager;
import presentacion.basepanel.TablePanel;
import presentacion.cellrenderer.MonedaCellRenderer;
import presentacion.tablemodel.TurnoTableModel;

@SuppressWarnings("serial")
public class TurnoTablePanel extends TablePanel {

	private JTable turnoTable;
	private TurnoTableModel turnoTableModel;
	private JScrollPane turnoTableScrollPane;

	public TurnoTablePanel(PanelManager panelManager) {
		super(panelManager);
	}

	@Override
	public void inicializarPanel(List<?> lista) {
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		if (lista != null) {
			for (Object valorLista : lista) {
				turnos.add((Turno)valorLista);
			}
		}
		this.setLayout(new BorderLayout());
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		turnoTableModel = new TurnoTableModel();
		turnoTable = new JTable(turnoTableModel);

		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_MEDICO);
		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_FECHA);
		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_HORARIO);
		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_PACIENTE);
		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_ASISTIO_TURNO);
		alinearCeldaDerecha(turnoTable, TurnoTableModel.COLUMNA_COSTO);

		asignarCeldaRenderer(turnoTable, TurnoTableModel.COLUMNA_COSTO, new MonedaCellRenderer());
		
		turnoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		turnoTableScrollPane = new JScrollPane(turnoTable);
		tablePanel.add(turnoTableScrollPane);
		this.add(tablePanel, BorderLayout.CENTER);

		turnoTableModel.setContenido(turnos);

		turnoTableModel.fireTableDataChanged();
	}

	public JTable getTurnoTable() {
		return turnoTable;
	}

	public void setTurnoTable(JTable turnoTable) {
		this.turnoTable = turnoTable;
	}

	public TurnoTableModel getTurnoTableModel() {
		return turnoTableModel;
	}

	public void setTurnoTableModel(TurnoTableModel turnoTableModel) {
		this.turnoTableModel = turnoTableModel;
	}

	public JScrollPane getTurnoTableScrollPane() {
		return turnoTableScrollPane;
	}

	public void setTurnoTableScrollPane(JScrollPane turnoTableScrollPane) {
		this.turnoTableScrollPane = turnoTableScrollPane;
	}
	
	private void alinearCeldaDerecha(JTable tabla, int columna) {
	    DefaultTableCellRenderer celdaRenderer = new DefaultTableCellRenderer();
	    celdaRenderer.setHorizontalAlignment(JLabel.RIGHT);
	    asignarCeldaRenderer(tabla, columna, celdaRenderer);
	}
	
	private void asignarCeldaRenderer(JTable tabla, int columna, DefaultTableCellRenderer renderer) {
		tabla.getColumnModel().getColumn(columna).setCellRenderer(renderer);
	}
	
}
