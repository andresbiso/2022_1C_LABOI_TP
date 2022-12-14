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
import presentacion.basepanel.TableBasePanel;
import presentacion.cellrenderer.MonedaCellRenderer;
import presentacion.tablemodel.ReporteMedicoTableModel;

@SuppressWarnings("serial")
public class ReporteMedicoTablePanel extends TableBasePanel {

	private JTable reporteMedicoTable;
	private ReporteMedicoTableModel reporteMedicoTableModel;
	private JScrollPane reporteMedicoTableScrollPane;

	public ReporteMedicoTablePanel(PanelManager panelManager) {
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
		reporteMedicoTableModel = new ReporteMedicoTableModel();
		reporteMedicoTable = new JTable(reporteMedicoTableModel);

		alinearCeldaDerecha(reporteMedicoTable, ReporteMedicoTableModel.COLUMNA_FECHA);
		alinearCeldaDerecha(reporteMedicoTable, ReporteMedicoTableModel.COLUMNA_HORARIO);
		alinearCeldaDerecha(reporteMedicoTable, ReporteMedicoTableModel.COLUMNA_COSTO);

		asignarCeldaRenderer(reporteMedicoTable, ReporteMedicoTableModel.COLUMNA_COSTO, new MonedaCellRenderer());
		
		reporteMedicoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		reporteMedicoTableScrollPane = new JScrollPane(reporteMedicoTable);
		tablePanel.add(reporteMedicoTableScrollPane);
		this.add(tablePanel, BorderLayout.CENTER);

		reporteMedicoTableModel.setContenido(turnos);

		reporteMedicoTableModel.fireTableDataChanged();
	}

	public JTable getReporteMedicoTable() {
		return reporteMedicoTable;
	}

	public ReporteMedicoTableModel getReporteMedicoTableModel() {
		return reporteMedicoTableModel;
	}

	public JScrollPane getReporteMedicoTableScrollPane() {
		return reporteMedicoTableScrollPane;
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
