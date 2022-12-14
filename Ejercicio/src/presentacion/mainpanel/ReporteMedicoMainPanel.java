package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basepanel.FieldsBasePanel;
import presentacion.basepanel.FiltersBasePanel;
import presentacion.basepanel.TableBasePanel;
import presentacion.panel.ReporteMedicoActionsPanel;
import presentacion.panel.ReporteMedicoFieldsPanel;
import presentacion.panel.ReporteMedicoFiltersPanel;
import presentacion.panel.ReporteMedicoTablePanel;

@SuppressWarnings("serial")
public class ReporteMedicoMainPanel extends JPanel {
	private PanelManager panelManager;

	private FiltersBasePanel filtersPanel;
	private FieldsBasePanel fieldsPanel;
	private TableBasePanel tablePanel;
	private ReporteMedicoActionsPanel actionsPanel;
	
	private final TurnoService turnoService;

	public ReporteMedicoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setFiltersPanel();
        this.setFieldsPanel();
        this.setTablePanel();
        this.setActionsPanel();
        inicializarPanel();
        this.turnoService = new TurnoService();
		this.tablePanel.inicializarPanel(new ArrayList<Turno>());
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(filtersPanel);
		this.add(fieldsPanel);
		this.add(tablePanel);
		this.add(actionsPanel);

		this.actionsPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}
	
	private void setFiltersPanel() {
		this.filtersPanel = new ReporteMedicoFiltersPanel(this.panelManager);
	}
	
	public void setFieldsPanel() {
		this.fieldsPanel = new ReporteMedicoFieldsPanel(panelManager);
	}
	
	public void setTablePanel() {
		this.tablePanel = new ReporteMedicoTablePanel(panelManager);	
	}

	private void setActionsPanel() {
		this.actionsPanel = new ReporteMedicoActionsPanel(this.panelManager);
	}

	public void volverAction() {
		panelManager.mostrarInicio();
	}
	
	private ArrayList<Turno> obtenerTurnos() {
		ArrayList<Turno> listaTurnos = null;
		try {
			listaTurnos = turnoService.obtenerTurnos();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		return listaTurnos;
	}
}
