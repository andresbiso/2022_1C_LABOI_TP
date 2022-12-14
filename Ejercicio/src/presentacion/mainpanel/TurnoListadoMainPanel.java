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
import presentacion.basepanel.TableBasePanel;
import presentacion.panel.ListadoActionsPanel;
import presentacion.panel.TurnoActionsPanel;
import presentacion.panel.TurnoTablePanel;

@SuppressWarnings("serial")
public class TurnoListadoMainPanel extends JPanel {
	private PanelManager panelManager;

	private TableBasePanel tablePanel;
	private ListadoActionsPanel listadoActionsPanel;
	private TurnoActionsPanel turnoActionsPanel;
	
	private final TurnoService turnoService;

	public TurnoListadoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setTablePanel();
        this.setActionsPanel();
        inicializarPanel();
        this.turnoService = new TurnoService();
		this.tablePanel.inicializarPanel(obtenerTurnos());
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(tablePanel);
		
		this.add(turnoActionsPanel);
		
		this.add(listadoActionsPanel);
		
		this.turnoActionsPanel.getAsignarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarAction();
			}
		});
		
		this.turnoActionsPanel.getDesasignarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desasignarAction();
			}
		});
		
		this.turnoActionsPanel.getConfirmarAsistenciaBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarAsistenciaAction();
			}
		});
		
		this.turnoActionsPanel.getAnularAsistenciaBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anularAsistenciaAction();
			}
		});

		this.listadoActionsPanel.getAgregarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarAction();
			}
		});
		
		this.listadoActionsPanel.getEditarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarAction();
			}
		});
		
		this.listadoActionsPanel.getBorrarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarAction();
			}
		});

		this.listadoActionsPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}

	private void setActionsPanel() {
		this.turnoActionsPanel = new TurnoActionsPanel(this.panelManager);
		this.listadoActionsPanel = new ListadoActionsPanel(this.panelManager);
	}

	public void setTablePanel() {
		this.tablePanel = new TurnoTablePanel(panelManager);	
	}
	
	public void asignarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoSeleccion = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			panelManager.mostrarAsignacionTurno(turnoSeleccion);
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción");
		}
	}
	
	public void desasignarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoSeleccion = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea desasignar el turno?") == JOptionPane.YES_OPTION) {
				try {
					Turno turnoActualizado = new Turno();
					turnoActualizado.setMedico(turnoSeleccion.getMedico());
					turnoActualizado.setFecha(turnoSeleccion.getFecha());
					turnoActualizado.setHorario(turnoSeleccion.getHorario());
					turnoActualizado.setIdTurno(turnoSeleccion.getIdTurno());

					turnoService.actualizarTurno(turnoActualizado, turnoSeleccion);
					DialogManager.MostrarMensajeExito(this, "El turno fue desasignado con éxito");
					panelManager.mostrarListaTurno(true);
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this);
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción");
		}
	}
	
	public void confirmarAsistenciaAction() {
	TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoSeleccion = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea confirmar el turno?") == JOptionPane.YES_OPTION) {
				try {
					turnoService.confirmarTurno(turnoSeleccion, true);
					DialogManager.MostrarMensajeExito(this, "El turno fue confirmado con éxito");
					panelManager.mostrarListaTurno(true);
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, e.getMessage());
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción");
		}
	}
	
	public void anularAsistenciaAction() {
	TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoSeleccion = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea anular el turno?") == JOptionPane.YES_OPTION) {
				try {
					turnoService.confirmarTurno(turnoSeleccion, false);
					DialogManager.MostrarMensajeExito(this, "El turno fue anulado con éxito");
					panelManager.mostrarListaTurno(true);
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, e.getMessage());
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción");
		}
	}

	public void agregarAction() {
		panelManager.mostrarAltaTurno();
	}

	public void editarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoEditar = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (!turnoEditar.getAsistioTurno()) {
				panelManager.mostrarEdicionTurno(turnoEditar);	
			} else {
				DialogManager.MostrarMensajeError(this, "No es posible editar un turno confirmado");
			}
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
				if (!turnoBorrar.getAsistioTurno()) {
					try {
						turnoService.borrarTurno(turnoBorrar);
						DialogManager.MostrarMensajeExito(this, "El turno fue eliminado con éxito");
						turnoTablePanel.getTurnoTableModel().getContenido().remove(turnoBorrar);
						turnoTablePanel.getTurnoTableModel().fireTableDataChanged();
					} catch (ServiceException e) {
						DialogManager.MostrarMensajeError(this);
					}	
				} else {
					DialogManager.MostrarMensajeError(this, "No es posible borrar un turno confirmado");
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
