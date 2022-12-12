package presentacion.mainpanel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;

import aplicacion.exception.ServiceException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.TurnoAsignacionFieldsPanel;
import presentacion.panel.TurnoFieldsPanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class TurnoAsignacionMainPanel extends AltaMainPanel {

	private final TurnoService turnoService;
	private final Turno turnoAsignacion;
	
	public TurnoAsignacionMainPanel(PanelManager panelManager) {
		this(panelManager, new Turno());
	}
	
	public TurnoAsignacionMainPanel(PanelManager panelManager, Turno turnoAsignacion) {
		super(panelManager);
		this.turnoService = new TurnoService();
		this.turnoAsignacion = turnoAsignacion;
		if (turnoAsignacion.getPaciente() != null
			&& turnoAsignacion.getPaciente().getIdPaciente() != 0) {
			rellenarFields(turnoAsignacion);	
		}
	}

	@Override
	public void setFieldsPanel() {
		this.fieldsPanel = new TurnoAsignacionFieldsPanel(panelManager);
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
		TurnoAsignacionFieldsPanel turnoAsignacionFieldsPanel = (TurnoAsignacionFieldsPanel) this.fieldsPanel;
		turnoAsignacionFieldsPanel.getPacienteComboBox().setSelectedIndex(0);
	}

	@Override
	public void volverAction() {
		limpiarAction();
		panelManager.mostrarListaTurno(true);
	}

	private void rellenarFields(Turno turno) {
		if (turno != null) {
			TurnoAsignacionFieldsPanel turnoAsignacionFieldsPanel = (TurnoAsignacionFieldsPanel) this.fieldsPanel;
			JComboBox<ComboItem<Integer>> pacienteComboBox = turnoAsignacionFieldsPanel.getPacienteComboBox();
			setSelectedPaciente(pacienteComboBox, turno.getPaciente().getIdPaciente());
		}
	}

	private void validarTurno() throws ValoresValidationException {		
		try {
			Turno turnoActualizado = crearTurnoActualizado(turnoAsignacion);
			turnoService.validarTurnoPaciente(turnoActualizado);
		} catch (ServiceException e) {
			String mensaje = "El paciente ya tiene un turno en esa fecha y horario \r\n";
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
			turnoActualizado = crearTurnoActualizado(turnoAsignacion);
		} catch (ValoresValidationException e) {
			throw new ValoresValidationException(e.getMessage());
		}

		if (!esIgual(turnoActualizado, turnoAsignacion)) {
			try {
				turnoService.actualizarTurno(turnoActualizado, turnoAsignacion);
				DialogManager.MostrarMensajeExito(this, "El turno fue asignado con éxito");
				volverAction();
			} catch (ServiceException e) {
				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar realizar la asignación del turno");
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this,
					"El turno debe ser modificado para poder ser reasignado");
		}
	}
	
	private Turno crearTurnoActualizado(Turno turno) throws ValoresValidationException {
		TurnoAsignacionFieldsPanel turnoAsignacionFieldsPanel = (TurnoAsignacionFieldsPanel) this.fieldsPanel;

		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) turnoAsignacionFieldsPanel.getPacienteComboBox().getSelectedItem();
		int pacienteId = selectedItem.getValue(); 
		Paciente pacienteTurno = new Paciente(pacienteId);
		
		Turno turnoActualizado = new Turno();
		turnoActualizado.setPaciente(pacienteTurno);
		turnoActualizado.setMedico(turno.getMedico());
		turnoActualizado.setFecha(turno.getFecha());
		turnoActualizado.setHorario(turno.getHorario());
		turnoActualizado.setIdTurno(turno.getIdTurno());
		turnoActualizado.setAsistioTurno(turno.getAsistioTurno());
		turnoActualizado.setCosto(turno.getCosto());
		
		return turnoActualizado;
	}
	
	private void setSelectedPaciente(JComboBox<ComboItem<Integer>> comboBox, int value)
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
