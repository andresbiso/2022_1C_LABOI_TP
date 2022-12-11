package presentacion.panel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Paciente;
import aplicacion.service.PacienteService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basepanel.FieldsPanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class TurnoAsignacionFieldsPanel extends FieldsPanel {
	private final PacienteService pacienteService;
	private JComboBox<ComboItem<Integer>> pacienteComboBox;

	public TurnoAsignacionFieldsPanel(PanelManager panelManager) {
		super(panelManager);
		this.pacienteService = new PacienteService();
		inicializarPanel();
	}
	
	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pacienteFieldPanel = new JPanel();
		pacienteFieldPanel.setLayout(new BoxLayout(pacienteFieldPanel, BoxLayout.X_AXIS));
		JLabel pacienteFieldLbl = new JLabel("Seleccione un Paciente:");
		pacienteComboBox = new JComboBox<ComboItem<Integer>>();
		pacienteComboBox.setModel(generarPacienteComboBoxModel());
		pacienteFieldPanel.add(pacienteFieldLbl);
		pacienteFieldPanel.add(pacienteComboBox);
		this.add(pacienteFieldPanel);
	}

	private DefaultComboBoxModel<ComboItem<Integer>> generarPacienteComboBoxModel() {
		DefaultComboBoxModel<ComboItem<Integer>> pacienteComboBoxModel = new DefaultComboBoxModel<ComboItem<Integer>>();
		ArrayList<Paciente> pacientes = null;
		try {
			pacientes = this.pacienteService.obtenerPacientes();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de cargar los valores");
		}
		
		for (Paciente paciente : pacientes) {
			ComboItem<Integer> comboItem = new ComboItem<Integer>(paciente.getNombreCompleto(), paciente.getIdPaciente());
			pacienteComboBoxModel.addElement(comboItem);
		}
		return pacienteComboBoxModel;
	}

	public JComboBox<ComboItem<Integer>> getPacienteComboBox() {
		return pacienteComboBox;
	}
}

