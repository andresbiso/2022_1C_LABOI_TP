package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Turno;
import aplicacion.model.Usuario;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.panel.TurnosPacienteActionsPanel;
import presentacion.panel.TurnosPacienteTablePanel;

@SuppressWarnings("serial")
public class TurnosPacienteMainPanel extends JPanel {
	private PanelManager panelManager;

	private TurnosPacienteTablePanel tablePanel;
	private TurnosPacienteActionsPanel actionsPanel;
	
	private final TurnoService turnoService;
	private Usuario usuarioActual;

	public TurnosPacienteMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setTablePanel();
        this.setActionsPanel();
        inicializarPanel();
        this.turnoService = new TurnoService();
        this.usuarioActual = panelManager.getUsuarioActual();
        if (usuarioActual != null) {
        	inicializarTable();	
        }
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(tablePanel);		
		this.add(actionsPanel);	

		this.actionsPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}

	private void setActionsPanel() {
		this.actionsPanel = new TurnosPacienteActionsPanel(this.panelManager);
	}

	public void setTablePanel() {
		this.tablePanel = new TurnosPacienteTablePanel(panelManager);	
	}
	
	public void volverAction() {
		panelManager.mostrarInicio();
	}
	
	private void inicializarTable() {
		ArrayList<Turno> listaTurnos = null;
		try {
			Usuario usuarioActual = panelManager.getUsuarioActual();
			String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			Date nuevoTurnoFecha = Date.valueOf(fechaActual);
			listaTurnos = turnoService.obtenerTurnos(usuarioActual, nuevoTurnoFecha);
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		this.tablePanel.inicializarPanel(listaTurnos);
	}
}
