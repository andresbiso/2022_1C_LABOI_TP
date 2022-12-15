package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.panel.ReporteMedicoActionsPanel;
import presentacion.panel.ReporteMedicoFieldsPanel;
import presentacion.panel.ReporteMedicoFiltersPanel;
import presentacion.panel.ReporteMedicoTablePanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class ReporteMedicoMainPanel extends JPanel {
	private PanelManager panelManager;

	private ReporteMedicoFiltersPanel filtersPanel;
	private ReporteMedicoFieldsPanel fieldsPanel;
	private ReporteMedicoTablePanel tablePanel;
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
		
		this.filtersPanel.getBuscarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerReporte();
			}
		});

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
	
	private void obtenerReporte() {
		ReporteMedicoFiltersPanel reporteMedicoFiltersPanel = (ReporteMedicoFiltersPanel) this.filtersPanel;

		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) reporteMedicoFiltersPanel.getMedicoComboBox().getSelectedItem();
		int medicoId = selectedItem.getValue(); 
		String fechaDesde = reporteMedicoFiltersPanel.getFechaDesde().getFecha();
		String fechaHasta = reporteMedicoFiltersPanel.getFechaHasta().getFecha();
		
		Date fechaDesdeDate = null;
		Date fechaHastaDate = null;
		try {
			fechaDesdeDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fechaDesde).getTime()) ;
			fechaHastaDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fechaHasta).getTime()) ;
		} catch (ParseException e) {
			String mensaje = "Hubo un error al querer obtener la fecha \r\n";
			DialogManager.MostrarMensajeError(this, mensaje);
		}
		
		ArrayList<Turno> listaTurnos = null;
		try {
			listaTurnos = turnoService.obtenerTurnos(medicoId, fechaDesdeDate, fechaHastaDate);

			if (listaTurnos != null) {
				this.fieldsPanel.getTotalConsultasValueLbl().setText(String.valueOf(listaTurnos.size()));
				float honorarios = 0;
				for (Turno turno : listaTurnos) {
					honorarios += turno.getCosto();
				}
				
				this.fieldsPanel.getTotalHonorariosValueLbl().setText("$" + String.valueOf(honorarios));
				this.tablePanel.getReporteMedicoTableModel().setContenido(listaTurnos);
				this.tablePanel.getReporteMedicoTableModel().fireTableDataChanged();
			} else {
				String mensaje = "No se encontraron resultados para la búsqueda \r\n";
				DialogManager.MostrarMensajeInformacion(this, mensaje);
			}
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
		}
	}
}
