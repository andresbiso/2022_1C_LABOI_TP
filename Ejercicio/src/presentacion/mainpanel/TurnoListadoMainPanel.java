package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basepanel.TablePanel;
import presentacion.panel.BotoneraListadoPanel;
import presentacion.panel.BotoneraTurnoPanel;
import presentacion.panel.TurnoTablePanel;

@SuppressWarnings("serial")
public class TurnoListadoMainPanel extends JPanel {
	protected PanelManager panelManager;

	protected TablePanel tablePanel;
	protected BotoneraListadoPanel botoneraListadoPanel;
	protected BotoneraTurnoPanel botoneraTurnoPanel;
	
	private final TurnoService turnoService;

	public TurnoListadoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setTablePanel();
        this.setBotoneraPanel();
        inicializarPanel();
        this.turnoService = new TurnoService();
		this.tablePanel.inicializarPanel(obtenerTurnos());
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(tablePanel);
		
		this.add(botoneraTurnoPanel);
		
		this.add(botoneraListadoPanel);
		
		this.botoneraTurnoPanel.getAsignarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarAction();
			}
		});
		
		this.botoneraTurnoPanel.getDesasignarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desasignarAction();
			}
		});
		
		this.botoneraTurnoPanel.getConfirmarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarAction();
			}
		});

		this.botoneraListadoPanel.getAgregarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarAction();
			}
		});
		
		this.botoneraListadoPanel.getEditarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarAction();
			}
		});
		
		this.botoneraListadoPanel.getBorrarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarAction();
			}
		});

		this.botoneraListadoPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}

	private void setBotoneraPanel() {
		this.botoneraTurnoPanel = new BotoneraTurnoPanel(this.panelManager);
		this.botoneraListadoPanel = new BotoneraListadoPanel(this.panelManager);
	}

	public void setTablePanel() {
		this.tablePanel = new TurnoTablePanel(panelManager);	
	}
	
	public void asignarAction() {
		volverAction();
	}
	
	public void desasignarAction() {
		volverAction();
	}
	
	public void confirmarAction() {
		volverAction();
	}

	public void agregarAction() {
		panelManager.mostrarAltaTurno();
	}

	public void editarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoEditar = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			
			panelManager.mostrarEdicionTurno(turnoEditar);
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a editar");
		}
	}

	public void borrarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoBorrar = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea eliminar el turno?") == JOptionPane.YES_OPTION) {
				try {
					turnoService.borrarTurno(turnoBorrar);
					DialogManager.MostrarMensajeExito(this, "El turno fue eliminado con éxito");
					turnoTablePanel.getTurnoTableModel().getContenido().remove(turnoBorrar);
					turnoTablePanel.getTurnoTableModel().fireTableDataChanged();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this);
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a borrar");
		}
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
