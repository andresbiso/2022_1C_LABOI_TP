package presentacion.mainpanel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;

import aplicacion.exception.FechaValidatorException;
import aplicacion.exception.ServiceException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Medico;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import aplicacion.validator.FechaValidator;
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
		this.turnoEdicion = null;
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
//		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
//		String fecha = turnoFieldsPanel.getFechaSeleccionPanel().getFecha();
//		String horario = turnoFieldsPanel.getHorarioSeleccionPanel().getHorario();
//		
//		try {
//			FechaValidator.ValidarFechaFutura(fecha, 120);
//		} catch (FechaValidatorException e) {
//			String mensaje = "Revisar campo: Fecha \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
		
		try {
			Turno nuevoTurno = crearNuevoTurno();
			turnoService.validarTurno(nuevoTurno);
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
		Turno nuevoTurno = null;
		try {
			nuevoTurno = crearNuevoTurno();
		} catch (ValoresValidationException e) {
			throw new ValoresValidationException(e.getMessage());
		}

		if (nuevoTurno != null && modoCreacion) {
			try {
				turnoService.crearTurno(nuevoTurno);
				DialogManager.MostrarMensajeExito(this, "El turno fue creado con éxito");
				volverAction();
			} catch (ServiceException e) {
				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de crear el turno");
			}
		} else {
			if (!esIgual(nuevoTurno, turnoEdicion)) {
				try {
					turnoService.actualizarTurno(nuevoTurno, turnoEdicion);
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
	
	private Turno crearNuevoTurno() throws ValoresValidationException {
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
		return new Turno(medicoTurno, fechaTurno, horario);
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
