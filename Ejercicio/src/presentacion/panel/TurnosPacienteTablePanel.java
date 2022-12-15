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
import presentacion.tablemodel.TurnoTableModel;
import presentacion.tablemodel.TurnosPacienteTableModel;

@SuppressWarnings("serial")
public class TurnosPacienteTablePanel extends TableBasePanel {

	private JTable turnosPacienteTable;
	private TurnosPacienteTableModel turnosPacienteTableModel;
	private JScrollPane turnosPacienteTableScrollPane;

	public TurnosPacienteTablePanel(PanelManager panelManager) {
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
		turnosPacienteTableModel = new TurnosPacienteTableModel();
		turnosPacienteTable = new JTable(turnosPacienteTableModel);

		alinearCeldaDerecha(turnosPacienteTable, TurnoTableModel.COLUMNA_MEDICO);
		alinearCeldaDerecha(turnosPacienteTable, TurnoTableModel.COLUMNA_FECHA);
		alinearCeldaDerecha(turnosPacienteTable, TurnoTableModel.COLUMNA_HORARIO);
		
		turnosPacienteTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		turnosPacienteTableScrollPane = new JScrollPane(turnosPacienteTable);
		tablePanel.add(turnosPacienteTableScrollPane);
		this.add(tablePanel, BorderLayout.CENTER);

		turnosPacienteTableModel.setContenido(turnos);

		turnosPacienteTableModel.fireTableDataChanged();
	}

	public JTable getTurnosPacienteTable() {
		return turnosPacienteTable;
	}

	public TurnosPacienteTableModel getTurnosPacienteTableModel() {
		return turnosPacienteTableModel;
	}

	public JScrollPane getTurnosPacienteTableScrollPane() {
		return turnosPacienteTableScrollPane;
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
