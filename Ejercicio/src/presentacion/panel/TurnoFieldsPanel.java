package presentacion.panel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.service.MedicoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basepanel.FieldsPanel;
import presentacion.panelmodel.ComboItem;

public class TurnoFieldsPanel extends FieldsPanel {
	private final MedicoService medicoService;
	private JComboBox<ComboItem<Integer>> medicoComboBox;
	private FechaFuturaSeleccionPanel fechaSeleccionPanel;
	private HorarioSeleccionPanel horarioSeleccionPanel;

	public TurnoFieldsPanel(PanelManager panelManager) {
		super(panelManager);
		this.medicoService = new MedicoService();
		inicializarPanel();
	}
	
	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel medicoFieldPanel = new JPanel();
		medicoFieldPanel.setLayout(new BoxLayout(medicoFieldPanel, BoxLayout.X_AXIS));
		JLabel medicoFieldLbl = new JLabel("Seleccione un Médico:");
		medicoComboBox = new JComboBox<ComboItem<Integer>>();
		medicoComboBox.setModel(generarMedicoComboBoxModel());
		medicoFieldPanel.add(medicoFieldLbl);
		medicoFieldPanel.add(medicoComboBox);
		this.add(medicoFieldPanel);
		
		fechaSeleccionPanel = new FechaFuturaSeleccionPanel();
		this.add(fechaSeleccionPanel);
		
		horarioSeleccionPanel = new HorarioSeleccionPanel();
		this.add(horarioSeleccionPanel);
	}

	private DefaultComboBoxModel<ComboItem<Integer>> generarMedicoComboBoxModel() {
		DefaultComboBoxModel<ComboItem<Integer>> medicoComboBoxModel = new DefaultComboBoxModel<ComboItem<Integer>>();
		ArrayList<Medico> medicos = null;
		try {
			medicos = this.medicoService.obtenerMedicos();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de cargar los valores");
		}
		
		for (Medico medico : medicos) {
			ComboItem<Integer> comboItem = new ComboItem<Integer>(medico.getNombreCompleto(), medico.getIdMedico());
			medicoComboBoxModel.addElement(comboItem);
		}
		return medicoComboBoxModel;
	}

}

