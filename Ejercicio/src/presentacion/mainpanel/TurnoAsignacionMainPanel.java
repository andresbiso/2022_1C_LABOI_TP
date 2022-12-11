package presentacion.mainpanel;

import javax.swing.JComboBox;

import aplicacion.exception.ServiceException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import aplicacion.service.TurnoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.TurnoAsignacionFieldsPanel;
import presentacion.panelmodel.ComboItem;

@SuppressWarnings("serial")
public class TurnoAsignacionMainPanel extends AltaMainPanel {

	private final TurnoService turnoService;
	private final Turno turnoAsignacion;
	
	public TurnoAsignacionMainPanel(PanelManager panelManager) {
		this(panelManager, new Turno(), true);
	}
	
	public TurnoAsignacionMainPanel(PanelManager panelManager, Turno turnoAsignacion) {
		this(panelManager, turnoAsignacion, true);
	}

	public TurnoAsignacionMainPanel(PanelManager panelManager, Turno turnoAsignacion, boolean sinAsignacion) {
		super(panelManager);
		this.turnoService = new TurnoService();
		this.turnoAsignacion = turnoAsignacion;
		if (!sinAsignacion) {
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
			setSelectedPaciente(pacienteComboBox, turno.getMedico().getIdMedico());
		}
	}

	private void validarTurno() throws ValoresValidationException {		
		try {
			Turno turnoActualizado = actualizarTurno(turnoAsignacion);
			turnoService.validarTurno(turnoActualizado);
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
			turnoActualizado = actualizarTurno(turnoAsignacion);
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
	
	private Turno actualizarTurno(Turno turnoActualizar) throws ValoresValidationException {
		TurnoAsignacionFieldsPanel turnoAsignacionFieldsPanel = (TurnoAsignacionFieldsPanel) this.fieldsPanel;

		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) turnoAsignacionFieldsPanel.getPacienteComboBox().getSelectedItem();
		int pacienteId = selectedItem.getValue(); 
		
		Paciente pacienteTurno = new Paciente(pacienteId);
		
		turnoActualizar.setPaciente(pacienteTurno);
		
		return turnoActualizar;
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
