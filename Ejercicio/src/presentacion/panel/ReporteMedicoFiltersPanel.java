package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.service.MedicoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basepanel.FiltersBasePanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class ReporteMedicoFiltersPanel extends FiltersBasePanel {
	private final MedicoService medicoService;
	private JComboBox<ComboItem<Integer>> medicoComboBox;
	private FechaSeleccionPanel fechaDesde;
	private FechaSeleccionPanel fechaHasta;
	private JButton buscarBtn;

	public ReporteMedicoFiltersPanel(PanelManager panelManager) {
		super(panelManager);
		this.medicoService = new MedicoService();
		inicializarPanel();
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel filtroPanel = new JPanel();
		filtroPanel.setLayout(new FlowLayout());
		
		JPanel medicoFieldPanel = new JPanel();
		medicoFieldPanel.setLayout(new BoxLayout(medicoFieldPanel, BoxLayout.X_AXIS));
		JLabel medicoFieldLbl = new JLabel("Seleccione un Médico:");
		medicoComboBox = new JComboBox<ComboItem<Integer>>();
		medicoComboBox.setModel(generarMedicoComboBoxModel());
		medicoFieldPanel.add(medicoFieldLbl);
		medicoFieldPanel.add(medicoComboBox);
		filtroPanel.add(medicoFieldPanel);
		
		fechaDesde = new FechaSeleccionPanel("Fecha Desde:");
		filtroPanel.add(fechaDesde);
		
		fechaHasta = new FechaSeleccionPanel("Fecha Hasta:");
		filtroPanel.add(fechaHasta);

		buscarBtn = new JButton("Buscar");
		filtroPanel.add(buscarBtn);
		
		this.add(filtroPanel, BorderLayout.NORTH);
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

	public JComboBox<ComboItem<Integer>> getMedicoComboBox() {
		return medicoComboBox;
	}
	
	public FechaSeleccionPanel getFechaDesde() {
		return fechaDesde;
	}
	
	public FechaSeleccionPanel getFechaHasta() {
		return fechaHasta;
	}

	public JButton getBuscarBtn() {
		return buscarBtn;
	}
}

