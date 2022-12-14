package presentacion.mainpanel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;

import aplicacion.exception.ServiceException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Medico;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.TurnoFieldsPanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class TurnoAltaMainPanel extends AltaMainPanel {

	private final TurnoService turnoService;
	private final boolean modoCreacion;
	private final Turno turnoEdicion;

	public TurnoAltaMainPanel(PanelManager panelManager) {
		super(panelManager);
		this.turnoService = new TurnoService();
		this.modoCreacion = true;
		this.turnoEdicion = new Turno();
	}

	public TurnoAltaMainPanel(PanelManager panelManager, Turno turnoEdicion) {
		super(panelManager);
		this.turnoService = new TurnoService();
		this.modoCreacion = false;
		this.turnoEdicion = turnoEdicion;
		rellenarFields(turnoEdicion);
	}

	@Override
	public void setFieldsPanel() {
		this.fieldsPanel = new TurnoFieldsPanel(panelManager);
	}

	@Override
	public void aceptarAction() {
		try {
			validarTurno();
			agregarOActualizar();
		} catch (ValoresValidationException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
			return;
		}
	}

	@Override
	public void limpiarAction() {
		Date fechaActual = new Date(System.currentTimeMillis());

		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
		turnoFieldsPanel.getMedicoComboBox().setSelectedIndex(0);
		turnoFieldsPanel.getFechaSeleccionPanel().setFecha(fechaActual);
		turnoFieldsPanel.getHorarioSeleccionPanel().actualizarHorario("08:00 AM");
	}

	@Override
	public void volverAction() {
		limpiarAction();
		panelManager.mostrarListaTurno(true);
	}

	private void rellenarFields(Turno turno) {
		if (turno != null) {
			TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
			JComboBox<ComboItem<Integer>> medicoComboBox = turnoFieldsPanel.getMedicoComboBox();
			setSelectedMedico(medicoComboBox, turno.getMedico().getIdMedico());

			turnoFieldsPanel.getFechaSeleccionPanel().setFecha(turno.getFecha());
			turnoFieldsPanel.getHorarioSeleccionPanel().actualizarHorario(turno.getHorario());
		}
	}

	private void validarTurno() throws ValoresValidationException {
		try {
			Turno turnoActualizado = crearTurnoActualizado(turnoEdicion);
			turnoService.validarTurno(turnoActualizado);
		} catch (ServiceException e) {
			String mensaje = "El médico ya tiene un turno en esa fecha y horario \r\n";
			throw new ValoresValidationException(mensaje);
		} catch (ValoresValidationException e) {
			throw new ValoresValidationException(e.getMessage());
		}
		
	}

	private boolean esIgual(Turno turnoModificado, Turno turnoEdicion) {
		return turnoModificado.equals(turnoEdicion);
	}

	private void agregarOActualizar() throws ValoresValidationException {
		Turno turnoActualizado = null;
		try {
			turnoActualizado = crearTurnoActualizado(turnoEdicion);
		} catch (ValoresValidationException e) {
			throw new ValoresValidationException(e.getMessage());
		}

		if (turnoActualizado != null && modoCreacion) {
			try {
				turnoService.crearTurno(turnoActualizado);
				DialogManager.MostrarMensajeExito(this, "El turno fue creado con éxito");
				volverAction();
			} catch (ServiceException e) {
				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de crear el turno");
			}
		} else {
			if (!esIgual(turnoActualizado, turnoEdicion)) {
				try {
					turnoService.actualizarTurno(turnoActualizado, turnoEdicion);
					DialogManager.MostrarMensajeExito(this, "El turno fue actualizado con éxito");
					volverAction();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de actualizar el turno");
				}
			} else {
				DialogManager.MostrarMensajeAdvertencia(this,
						"El turno debe ser modificado para poder ser actualizado");
			}
		}
	}
	
	private Turno crearTurnoActualizado(Turno turno) throws ValoresValidationException {
		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;

		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) turnoFieldsPanel.getMedicoComboBox().getSelectedItem();
		int medicoId = selectedItem.getValue(); 
		String fecha = turnoFieldsPanel.getFechaSeleccionPanel().getFecha();
		String horario = turnoFieldsPanel.getHorarioSeleccionPanel().getHorario();
		
		Medico medicoTurno = new Medico(medicoId);
		Date fechaTurno;
		try {
			fechaTurno = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fecha).getTime()) ;
		} catch (ParseException e) {
			String mensaje = "Hubo un error al querer obtener la fecha \r\n";
			throw new ValoresValidationException(mensaje);
		}
		
		Turno turnoActualizado = new Turno();
		
		turnoActualizado.setMedico(medicoTurno);
		turnoActualizado.setFecha(fechaTurno);
		turnoActualizado.setHorario(horario);
		turnoActualizado.setIdTurno(turno.getIdTurno());
		turnoActualizado.setPaciente(turno.getPaciente());
		turnoActualizado.setAsistioTurno(turno.getAsistioTurno());
		turnoActualizado.setCosto(turno.getCosto());

		return turnoActualizado;
	}
	
	private void setSelectedMedico(JComboBox<ComboItem<Integer>> comboBox, int value)
    {
		ComboItem<Integer> item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = comboBox.getItemAt(i);
            if (item.getValue().equals(value))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
}
