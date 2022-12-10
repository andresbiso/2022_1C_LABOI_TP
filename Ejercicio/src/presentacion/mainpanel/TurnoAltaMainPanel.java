package presentacion.mainpanel;

import java.sql.Date;

//import aplicacion.enums.UsuarioTipo;
//import aplicacion.exception.FechaValidatorException;
//import aplicacion.exception.ServiceException;
//import aplicacion.exception.TextoValidatorException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Turno;
//import aplicacion.model.Turno;
//import aplicacion.model.Usuario;
//import aplicacion.service.TurnoService;
//import aplicacion.validator.FechaValidator;
//import aplicacion.validator.NumeroValidator;
//import aplicacion.validator.TextoValidator;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.TurnoFieldsPanel;
import presentacion.panelmodel.ComboItem;

public class TurnoAltaMainPanel extends AltaMainPanel {

//	private final TurnoService turnoService;
//	private final boolean modoCreacion;
//	private final Turno turnoEdicion;

	public TurnoAltaMainPanel(PanelManager panelManager) {
		super(panelManager);
//		this.turnoService = new TurnoService();
//		this.modoCreacion = true;
//		this.turnoEdicion = null;
	}

	public TurnoAltaMainPanel(PanelManager panelManager, Turno turnoEdicion) {
		super(panelManager);
//		this.turnoService = new TurnoService();
//		this.modoCreacion = false;
//		this.turnoEdicion = turnoEdicion;
		rellenarFields(null);
	}

	@Override
	public void setFieldsPanel() {
		this.fieldsPanel = new TurnoFieldsPanel(panelManager);
	}

	@Override
	public void aceptarAction() {
		try {
			validarTurno();
		} catch (ValoresValidationException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
			return;
		}
		agregarOActualizar();
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
			turnoFieldsPanel.getMedicoComboBox().setSelectedIndex(turno.getMedico().getIdMedico());
			turnoFieldsPanel.getFechaSeleccionPanel().setFecha(turno.getFecha());
			turnoFieldsPanel.getHorarioSeleccionPanel().actualizarHorario(turno.getHorario());
		}
	}

	private void validarTurno() throws ValoresValidationException {
		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;

		// Suprimo Warning ya que este combobox siempre devuelve un ComboItem<Integer>
		@SuppressWarnings("unchecked")
		ComboItem<Integer> selectedItem = (ComboItem<Integer>) turnoFieldsPanel.getMedicoComboBox().getSelectedItem();
		int medicoId = selectedItem.getValue(); 
		String fecha = turnoFieldsPanel.getFechaSeleccionPanel().getFecha();
		String horario = turnoFieldsPanel.getHorarioSeleccionPanel().getHorario();

		// validar que ese médico no tenga otro turno en esa fecha y horario
		try {
//			TextoValidator.ValidarTextoNoVacio(nombreUsuario);
//			TextoValidator.ValidarSoloLetras(nombreUsuario);
//			TextoValidator.ValidarLongitudMaxima(nombreUsuario, 64);
		} catch (Exception e) {
			String mensaje = "El médico ya tiene un turno en esa fecha y horario \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}
	}

	private boolean esIgual(Turno turnoModificado, Turno turnoEdicion) {
		return turnoModificado.equals(turnoEdicion);
	}

	private void agregarOActualizar() {
//		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
//		String nombreUsuario = turnoFieldsPanel.getNombreUsuarioTxt().getText();
//		String contrasenia = turnoFieldsPanel.getContraseniaTxt().getText();
//		String nombre = turnoFieldsPanel.getNombreTxt().getText();
//		String apellido = turnoFieldsPanel.getApellidoTxt().getText();
//		String email = turnoFieldsPanel.getEmailTxt().getText();
//		String dni = turnoFieldsPanel.getDniTxt().getText();
//		String costoConsulta = turnoFieldsPanel.getCostoConsultaTxt().getText();
//		String fecha = turnoFieldsPanel.getFechaSeleccionPanel().getFecha();
//
//		Turno nuevoTurno = new Turno(new Usuario(nombreUsuario, contrasenia, nombre, apellido, email,
//				Date.valueOf(fecha), Integer.valueOf(dni), UsuarioTipo.Turno), Integer.valueOf(costoConsulta));
//
//		if (modoCreacion) {
//			try {
//				turnoService.crearTurno(nuevoTurno);
//				DialogManager.MostrarMensajeExito(this, "El turno fue creado con éxito");
//				volverAction();
//			} catch (ServiceException e) {
//				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de crear el turno");
//			}
//		} else {
//			if (!esIgual(nuevoTurno, turnoEdicion)) {
//				try {
//					turnoService.actualizarTurno(nuevoTurno, turnoEdicion);
//					DialogManager.MostrarMensajeExito(this, "El turno fue actualizado con éxito");
//					volverAction();
//				} catch (ServiceException e) {
//					DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de actualizar el turno");
//				}
//			} else {
//				DialogManager.MostrarMensajeAdvertencia(this,
//						"El turno debe ser modificado para poder ser actualizado");
//			}
//		}
	}
}
