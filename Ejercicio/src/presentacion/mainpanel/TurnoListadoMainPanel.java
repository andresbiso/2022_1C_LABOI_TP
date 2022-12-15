package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.model.Turno;
import aplicacion.model.Usuario;
import aplicacion.service.MedicoService;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.panel.ListadoActionsPanel;
import presentacion.panel.TurnoActionsPanel;
import presentacion.panel.TurnoFiltersPanel;
import presentacion.panel.TurnoTablePanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class TurnoListadoMainPanel extends JPanel {
	private PanelManager panelManager;

	private TurnoTablePanel tablePanel;
	private TurnoFiltersPanel filtersPanel;
	private ListadoActionsPanel listadoActionsPanel;
	private TurnoActionsPanel turnoActionsPanel;
	
	private final TurnoService turnoService;
	private final MedicoService medicoService;
	private Usuario usuarioActual;

	public TurnoListadoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setFiltersPanel();
        this.setTablePanel();
        this.setActionsPanel();
        inicializarPanel();
        this.turnoService = new TurnoService();
        this.medicoService = new MedicoService();
        this.usuarioActual = panelManager.getUsuarioActual();
        if (usuarioActual != null) {
        	inicializarTable();
        	setSelectedMedico();
        }
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(filtersPanel);
		this.add(tablePanel);		
		this.add(turnoActionsPanel);		
		this.add(listadoActionsPanel);
		
		this.filtersPanel.getBuscarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarAction();
			}
		});
		
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
	
	private void setFiltersPanel() {
		this.filtersPanel = new TurnoFiltersPanel(this.panelManager);
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
			if (!turnoSeleccion.getAsistioTurno()) {
				panelManager.mostrarAsignacionTurno(turnoSeleccion);
			} else {
				DialogManager.MostrarMensajeError(this, "No es posible reasignar un turno confirmado");
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción");
		}
	}
	
	public void desasignarAction() {
		TurnoTablePanel turnoTablePanel = (TurnoTablePanel)this.tablePanel;
		
		int filaSeleccionada = turnoTablePanel.getTurnoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Turno turnoSeleccion = turnoTablePanel.getTurnoTableModel().getContenido().get(filaSeleccionada);
			if (!turnoSeleccion.getAsistioTurno()) {
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
				DialogManager.MostrarMensajeError(this, "No es posible desasignar un turno confirmado");
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
		panelManager.mostrarAltaTurno(true);
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
				try {
					turnoService.borrarTurno(turnoBorrar);
					DialogManager.MostrarMensajeExito(this, "El turno fue eliminado con éxito");
					turnoTablePanel.getTurnoTableModel().getContenido().remove(turnoBorrar);
					turnoTablePanel.getTurnoTableModel().fireTableDataChanged();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, e.getMessage());
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a borrar");
		}
	}
	
	public void buscarAction() {
		TurnoFiltersPanel turnoFiltersPanel = (TurnoFiltersPanel) this.filtersPanel;
		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) turnoFiltersPanel.getMedicoComboBox().getSelectedItem();
		int medicoId = selectedItem.getValue(); 
		String fecha = turnoFiltersPanel.getFecha().getFecha();
		Date fechaDate = null;
		try {
			fechaDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fecha).getTime());
		} catch (ParseException e) {
			String mensaje = "Hubo un error al querer obtener la fecha \r\n";
			DialogManager.MostrarMensajeError(this, mensaje);
		}
		
		ArrayList<Turno> listaTurnos = null;
		try {
			listaTurnos = turnoService.obtenerTurnos(medicoId, fechaDate);
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		
		if (listaTurnos != null) {
			this.tablePanel.getTurnoTableModel().setContenido(listaTurnos);
			this.tablePanel.getTurnoTableModel().fireTableDataChanged();
		} else {
			String mensaje = "No se encontraron resultados para la búsqueda \r\n";
			DialogManager.MostrarMensajeInformacion(this, mensaje);
		}
	}

	public void volverAction() {
		panelManager.mostrarInicio();
	}
	
	private void inicializarTable() {
		ArrayList<Turno> listaTurnos = null;
		try {
			String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			Date nuevoTurnoFecha = Date.valueOf(fechaActual);
			listaTurnos = turnoService.obtenerTurnos(usuarioActual, nuevoTurnoFecha);
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		this.tablePanel.inicializarPanel(listaTurnos);
	}
	
	private void setSelectedMedico()
    {
		TurnoFiltersPanel turnoFiltersPanel = (TurnoFiltersPanel) this.filtersPanel;
		JComboBox<ComboItem<Integer>> comboBox = turnoFiltersPanel.getMedicoComboBox();
		Medico medico = null;
		try {
			medico = medicoService.obtenerMedico(usuarioActual);
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeExito(this, "Hubo un error al tratar seleccionar el médico de la lista");
		}

		if (medico != null) {
			ComboItem<Integer> item;
	        for (int i = 0; i < comboBox.getItemCount(); i++)
	        {
	            item = comboBox.getItemAt(i);
	            if (item.getValue().equals(medico.getIdMedico()))
	            {
	                comboBox.setSelectedIndex(i);
	                break;
	            }
	        }
		}

    }
}
