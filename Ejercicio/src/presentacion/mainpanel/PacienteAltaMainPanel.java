package presentacion.mainpanel;

import java.sql.Date;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.FechaValidatorException;
import aplicacion.exception.ServiceException;
import aplicacion.exception.TextoValidatorException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Paciente;
import aplicacion.model.Usuario;
import aplicacion.service.PacienteService;
import aplicacion.validator.FechaValidator;
import aplicacion.validator.NumeroValidator;
import aplicacion.validator.TextoValidator;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.PacienteFieldsPanel;

@SuppressWarnings("serial")
public class PacienteAltaMainPanel extends AltaMainPanel {

	private final PacienteService pacienteService;
	private final boolean modoCreacion;
	private final Paciente pacienteEdicion;

	public PacienteAltaMainPanel(PanelManager panelManager) {
		super(panelManager);
		this.pacienteService = new PacienteService();
		this.modoCreacion = true;
		this.pacienteEdicion = null;
	}

	public PacienteAltaMainPanel(PanelManager panelManager, Paciente pacienteEdicion) {
		super(panelManager);
		this.pacienteService = new PacienteService();
		this.modoCreacion = false;
		this.pacienteEdicion = pacienteEdicion;
		rellenarFields(pacienteEdicion);
	}

	@Override
	public void setFieldsPanel() {
		this.fieldsPanel = new PacienteFieldsPanel(panelManager);
	}

	@Override
	public void aceptarAction() {
		try {
			validarPaciente();
		} catch (ValoresValidationException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
			return;
		}
		agregarOActualizar();
	}

	@Override
	public void limpiarAction() {
		PacienteFieldsPanel pacienteFieldsPanel = (PacienteFieldsPanel) this.fieldsPanel;
		pacienteFieldsPanel.getNombreUsuarioTxt().setText("");
		pacienteFieldsPanel.getContraseniaTxt().setText("");
		pacienteFieldsPanel.getNombreTxt().setText("");
		pacienteFieldsPanel.getApellidoTxt().setText("");
		pacienteFieldsPanel.getEmailTxt().setText("");
		pacienteFieldsPanel.getDniTxt().setText("");
		Date fechaActual = new Date(System.currentTimeMillis());
		pacienteFieldsPanel.getFechaSeleccionPanel().setFecha(fechaActual);
	}

	@Override
	public void volverAction() {
		limpiarAction();
		panelManager.mostrarListaPaciente(true);
	}

	private void rellenarFields(Paciente paciente) {
		if (paciente != null) {
			PacienteFieldsPanel pacienteFieldsPanel = (PacienteFieldsPanel) this.fieldsPanel;
			pacienteFieldsPanel.getNombreUsuarioTxt().setText(paciente.getNombreUsuario());
			pacienteFieldsPanel.getContraseniaTxt().setText(paciente.getContrasenia());
			pacienteFieldsPanel.getNombreTxt().setText(paciente.getNombre());
			pacienteFieldsPanel.getApellidoTxt().setText(paciente.getApellido());
			pacienteFieldsPanel.getEmailTxt().setText(paciente.getEmail());
			pacienteFieldsPanel.getDniTxt().setText(String.valueOf(paciente.getDni()));
			pacienteFieldsPanel.getFechaSeleccionPanel().setFecha(paciente.getFechaNacimiento());
		}
	}

	private void validarPaciente() throws ValoresValidationException {
		PacienteFieldsPanel pacienteFieldsPanel = (PacienteFieldsPanel) this.fieldsPanel;
		String nombreUsuario = pacienteFieldsPanel.getNombreUsuarioTxt().getText();
		String contrasenia = pacienteFieldsPanel.getContraseniaTxt().getText();
		String nombre = pacienteFieldsPanel.getNombreTxt().getText();
		String apellido = pacienteFieldsPanel.getApellidoTxt().getText();
		String email = pacienteFieldsPanel.getEmailTxt().getText();
		String dni = pacienteFieldsPanel.getDniTxt().getText();
		String fecha = pacienteFieldsPanel.getFechaSeleccionPanel().getFecha();

		int maximoDNI = 99999999;
		int minimoDNI = 1;

		try {
			TextoValidator.ValidarTextoNoVacio(nombreUsuario);
			TextoValidator.ValidarSoloLetras(nombreUsuario);
			TextoValidator.ValidarLongitudMaxima(nombreUsuario, 64);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Nombre Usuario \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(contrasenia);
			TextoValidator.ValidarLongitudMaxima(contrasenia, 32);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Contraseña \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(nombre);
			TextoValidator.ValidarSoloLetras(nombre);
			TextoValidator.ValidarLongitudMaxima(nombre, 32);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Nombre \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(apellido);
			TextoValidator.ValidarSoloLetras(apellido);
			TextoValidator.ValidarLongitudMaxima(apellido, 32);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Apellido \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(email);
			TextoValidator.ValidarEmail(email);
			TextoValidator.ValidarLongitudMaxima(email, 128);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Email \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(dni);
			TextoValidator.ValidarNumerico(dni);
			TextoValidator.ValidarLongitudMaxima(dni, 8);
			NumeroValidator.ValidarNumeroPositivo(Integer.valueOf(dni));
			NumeroValidator.ValidarNumeroEnRango(Integer.valueOf(dni), minimoDNI, maximoDNI);
		} catch (ValoresValidationException e) {
			String mensaje = "Revisar campo: DNI \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			FechaValidator.ValidarEdadMaxima(fecha, 120);
		} catch (FechaValidatorException e) {
			String mensaje = "Revisar campo: Fecha de Nacimiento \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}
	}

	private boolean esIgual(Paciente pacienteModificado, Paciente pacienteEdicion) {
		return pacienteModificado.equals(pacienteEdicion);
	}

	private void agregarOActualizar() {
		PacienteFieldsPanel pacienteFieldsPanel = (PacienteFieldsPanel) this.fieldsPanel;
		String nombreUsuario = pacienteFieldsPanel.getNombreUsuarioTxt().getText();
		String contrasenia = pacienteFieldsPanel.getContraseniaTxt().getText();
		String nombre = pacienteFieldsPanel.getNombreTxt().getText();
		String apellido = pacienteFieldsPanel.getApellidoTxt().getText();
		String email = pacienteFieldsPanel.getEmailTxt().getText();
		String dni = pacienteFieldsPanel.getDniTxt().getText();
		String fecha = pacienteFieldsPanel.getFechaSeleccionPanel().getFecha();

		Paciente nuevoPaciente = new Paciente(new Usuario(nombreUsuario, contrasenia, nombre, apellido, email,
				Date.valueOf(fecha), Integer.valueOf(dni), UsuarioTipo.Paciente));

		if (modoCreacion) {
			try {
				pacienteService.crearPaciente(nuevoPaciente);
				DialogManager.MostrarMensajeExito(this, "El paciente fue creado con éxito");
				volverAction();
			} catch (ServiceException e) {
				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de crear el paciente");
			}
		} else {
			if (!esIgual(nuevoPaciente, pacienteEdicion)) {
				try {
					pacienteService.actualizarPaciente(nuevoPaciente, pacienteEdicion);
					DialogManager.MostrarMensajeExito(this, "El paciente fue actualizado con éxito");
					volverAction();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de actualizar el paciente");
				}
			} else {
				DialogManager.MostrarMensajeAdvertencia(this,
						"El paciente debe ser modificado para poder ser actualizado");
			}
		}
	}
}
