package presentacion.mainpanel;

import java.sql.Date;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.FechaValidatorException;
import aplicacion.exception.ServiceException;
import aplicacion.exception.TextoValidatorException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Medico;
import aplicacion.model.Usuario;
import aplicacion.service.MedicoService;
import aplicacion.validator.FechaValidator;
import aplicacion.validator.NumeroValidator;
import aplicacion.validator.TextoValidator;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.AltaMainPanel;
import presentacion.panel.MedicoFieldsPanel;

public class MedicoAltaMainPanel extends AltaMainPanel {

	private final MedicoService medicoService;
	private final boolean modoCreacion;
	private final Medico medicoEdicion;

	public MedicoAltaMainPanel(PanelManager panelManager) {
		super(panelManager);
		this.medicoService = new MedicoService();
		this.modoCreacion = true;
		this.medicoEdicion = null;
	}

	public MedicoAltaMainPanel(PanelManager panelManager, Medico medicoEdicion) {
		super(panelManager);
		this.medicoService = new MedicoService();
		this.modoCreacion = false;
		this.medicoEdicion = medicoEdicion;
		rellenarFields(medicoEdicion);
	}

	@Override
	public void setFieldsPanel() {
		this.fieldsPanel = new MedicoFieldsPanel(panelManager);
	}

	@Override
	public void aceptarAction() {
		try {
			validarMedico();
		} catch (ValoresValidationException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
			return;
		}
		agregarOActualizar();
	}

	@Override
	public void limpiarAction() {
		MedicoFieldsPanel medicoFieldsPanel = (MedicoFieldsPanel) this.fieldsPanel;
		medicoFieldsPanel.getNombreUsuarioTxt().setText("");
		medicoFieldsPanel.getContraseniaTxt().setText("");
		medicoFieldsPanel.getNombreTxt().setText("");
		medicoFieldsPanel.getApellidoTxt().setText("");
		medicoFieldsPanel.getEmailTxt().setText("");
		medicoFieldsPanel.getDniTxt().setText("");
		medicoFieldsPanel.getCostoConsultaTxt().setText("");
		Date fechaActual = new Date(System.currentTimeMillis());
		medicoFieldsPanel.getFechaSeleccionPanel().setFecha(fechaActual);
	}

	@Override
	public void volverAction() {
		limpiarAction();
		panelManager.mostrarListaMedico(true);
	}

	private void rellenarFields(Medico medico) {
		if (medico != null) {
			MedicoFieldsPanel medicoFieldsPanel = (MedicoFieldsPanel) this.fieldsPanel;
			medicoFieldsPanel.getNombreUsuarioTxt().setText(medico.getNombreUsuario());
			medicoFieldsPanel.getContraseniaTxt().setText(medico.getContrasenia());
			medicoFieldsPanel.getNombreTxt().setText(medico.getNombre());
			medicoFieldsPanel.getApellidoTxt().setText(medico.getApellido());
			medicoFieldsPanel.getEmailTxt().setText(medico.getEmail());
			medicoFieldsPanel.getDniTxt().setText(String.valueOf(medico.getDni()));
			medicoFieldsPanel.getCostoConsultaTxt().setText(String.valueOf(medico.getCostoConsulta()));
			medicoFieldsPanel.getFechaSeleccionPanel().setFecha(medico.getFechaNacimiento());
		}
	}

	private void validarMedico() throws ValoresValidationException {
		MedicoFieldsPanel medicoFieldsPanel = (MedicoFieldsPanel) this.fieldsPanel;
		String nombreUsuario = medicoFieldsPanel.getNombreUsuarioTxt().getText();
		String contrasenia = medicoFieldsPanel.getContraseniaTxt().getText();
		String nombre = medicoFieldsPanel.getNombreTxt().getText();
		String apellido = medicoFieldsPanel.getApellidoTxt().getText();
		String email = medicoFieldsPanel.getEmailTxt().getText();
		String dni = medicoFieldsPanel.getDniTxt().getText();
		String costoConsulta = medicoFieldsPanel.getCostoConsultaTxt().getText();
		String fecha = medicoFieldsPanel.getFechaSeleccionPanel().getFecha();

		int maximoDNI = 99999999;
		int maximoCostoConsulta = 10000;
		int minimoDNI = 1;
		int minimoCostoConsulta = 0;

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
			TextoValidator.ValidarTextoNoVacio(costoConsulta);
			TextoValidator.ValidarNumerico(costoConsulta);
			NumeroValidator.ValidarNumeroPositivo(Integer.valueOf(costoConsulta));
			NumeroValidator.ValidarNumeroEnRango(Integer.valueOf(costoConsulta), minimoCostoConsulta,
					maximoCostoConsulta);
		} catch (ValoresValidationException e) {
			String mensaje = "Revisar campo: Costo Consulta \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}

		try {
			FechaValidator.ValidarEdadMinima(fecha, 21);
			FechaValidator.ValidarEdadMaxima(fecha, 90);
		} catch (FechaValidatorException e) {
			String mensaje = "Revisar campo: Fecha de Nacimiento \r\n" + e.getMessage();
			throw new ValoresValidationException(mensaje);
		}
	}

	private boolean esIgual(Medico medicoModificado, Medico medicoEdicion) {
		return medicoModificado.equals(medicoEdicion);
	}

	private void agregarOActualizar() {
		MedicoFieldsPanel medicoFieldsPanel = (MedicoFieldsPanel) this.fieldsPanel;
		String nombreUsuario = medicoFieldsPanel.getNombreUsuarioTxt().getText();
		String contrasenia = medicoFieldsPanel.getContraseniaTxt().getText();
		String nombre = medicoFieldsPanel.getNombreTxt().getText();
		String apellido = medicoFieldsPanel.getApellidoTxt().getText();
		String email = medicoFieldsPanel.getEmailTxt().getText();
		String dni = medicoFieldsPanel.getDniTxt().getText();
		String costoConsulta = medicoFieldsPanel.getCostoConsultaTxt().getText();
		String fecha = medicoFieldsPanel.getFechaSeleccionPanel().getFecha();

		Medico nuevoMedico = new Medico(new Usuario(nombreUsuario, contrasenia, nombre, apellido, email,
				Date.valueOf(fecha), Integer.valueOf(dni), UsuarioTipo.Medico), Integer.valueOf(costoConsulta));

		if (modoCreacion) {
			try {
				medicoService.crearMedico(nuevoMedico);
				DialogManager.MostrarMensajeExito(this, "El médico fue creado con éxito");
				volverAction();
			} catch (ServiceException e) {
				DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de crear el médico");
			}
		} else {
			if (!esIgual(nuevoMedico, medicoEdicion)) {
				try {
					medicoService.actualizarMedico(nuevoMedico, medicoEdicion);
					DialogManager.MostrarMensajeExito(this, "El médico fue actualizado con éxito");
					volverAction();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de actualizar el médico");
				}
			} else {
				DialogManager.MostrarMensajeAdvertencia(this,
						"El médico debe ser modificado para poder ser actualizado");
			}
		}
	}
}
