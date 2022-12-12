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

import aplicacion.model.Paciente;
import presentacion.PanelManager;
import presentacion.basepanel.TableBasePanel;
import presentacion.cellrenderer.FechaCellRenderer;
import presentacion.tablemodel.PacienteTableModel;

@SuppressWarnings("serial")
public class PacienteTablePanel extends TableBasePanel {

	private JTable pacienteTable;
	private PacienteTableModel pacienteTableModel;
	private JScrollPane pacienteTableScrollPane;

	public PacienteTablePanel(PanelManager panelManager) {
		super(panelManager);
	}

	@Override
	public void inicializarPanel(List<?> lista) {
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		for (Object valorLista : lista) {
			pacientes.add((Paciente)valorLista);
		}
		this.setLayout(new BorderLayout());
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		pacienteTableModel = new PacienteTableModel();
		pacienteTable = new JTable(pacienteTableModel);

		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_NOMBRE_USUARIO);
		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_CONTRASENIA);
		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_NOMBRE);
		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_APELLIDO);
		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_EMAIL);
		alinearCeldaDerecha(pacienteTable, PacienteTableModel.COLUMNA_DNI);
		
		asignarCeldaRenderer(pacienteTable, PacienteTableModel.COLUMNA_FECHA_NAC, new FechaCellRenderer());
		
		pacienteTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		pacienteTableScrollPane = new JScrollPane(pacienteTable);
		tablePanel.add(pacienteTableScrollPane);
		this.add(tablePanel, BorderLayout.CENTER);

		pacienteTableModel.setContenido(pacientes);

		pacienteTableModel.fireTableDataChanged();
	}

	public JTable getPacienteTable() {
		return pacienteTable;
	}

	public void setPacienteTable(JTable pacienteTable) {
		this.pacienteTable = pacienteTable;
	}

	public PacienteTableModel getPacienteTableModel() {
		return pacienteTableModel;
	}

	public void setPacienteTableModel(PacienteTableModel pacienteTableModel) {
		this.pacienteTableModel = pacienteTableModel;
	}

	public JScrollPane getPacienteTableScrollPane() {
		return pacienteTableScrollPane;
	}

	public void setPacienteTableScrollPane(JScrollPane pacienteTableScrollPane) {
		this.pacienteTableScrollPane = pacienteTableScrollPane;
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
