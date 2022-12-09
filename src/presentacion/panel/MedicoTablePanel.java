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

import aplicacion.model.Medico;
import presentacion.PanelManager;
import presentacion.basepanel.TablePanel;
import presentacion.cellrenderer.FechaCellRenderer;
import presentacion.cellrenderer.MonedaCellRenderer;
import presentacion.tablemodel.MedicoTableModel;

public class MedicoTablePanel extends TablePanel {

	private JTable medicoTable;
	private MedicoTableModel medicoTableModel;
	private JScrollPane medicoTableScrollPane;

	public MedicoTablePanel(PanelManager panelManager) {
		super(panelManager);
	}

	@Override
	public void inicializarPanel(List<?> lista) {
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		for (Object valorLista : lista) {
			medicos.add((Medico)valorLista);
		}
		this.setLayout(new BorderLayout());
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		medicoTableModel = new MedicoTableModel();
		medicoTable = new JTable(medicoTableModel);

		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_NOMBRE_USUARIO);
		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_CONTRASENIA);
		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_NOMBRE);
		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_APELLIDO);
		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_EMAIL);
		alinearCeldaDerecha(medicoTable, MedicoTableModel.COLUMNA_DNI);
		
		asignarCeldaRenderer(medicoTable, MedicoTableModel.COLUMNA_FECHA_NAC, new FechaCellRenderer());
		asignarCeldaRenderer(medicoTable, MedicoTableModel.COLUMNA_COSTO_CONSULTA, new MonedaCellRenderer());
		
		medicoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		medicoTableScrollPane = new JScrollPane(medicoTable);
		tablePanel.add(medicoTableScrollPane);
		this.add(tablePanel, BorderLayout.CENTER);

		medicoTableModel.setContenido(medicos);

		medicoTableModel.fireTableDataChanged();
	}

	public JTable getMedicoTable() {
		return medicoTable;
	}

	public void setMedicoTable(JTable medicoTable) {
		this.medicoTable = medicoTable;
	}

	public MedicoTableModel getMedicoTableModel() {
		return medicoTableModel;
	}

	public void setMedicoTableModel(MedicoTableModel medicoTableModel) {
		this.medicoTableModel = medicoTableModel;
	}

	public JScrollPane getMedicoTableScrollPane() {
		return medicoTableScrollPane;
	}

	public void setMedicoTableScrollPane(JScrollPane medicoTableScrollPane) {
		this.medicoTableScrollPane = medicoTableScrollPane;
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
