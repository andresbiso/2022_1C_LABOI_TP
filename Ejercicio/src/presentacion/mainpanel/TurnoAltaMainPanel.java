package presentacion.mainpanel;

//import java.sql.Date;

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
//		TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
//		turnoFieldsPanel.getNombreUsuarioTxt().setText("");
//		turnoFieldsPanel.getContraseniaTxt().setText("");
//		turnoFieldsPanel.getNombreTxt().setText("");
//		turnoFieldsPanel.getApellidoTxt().setText("");
//		turnoFieldsPanel.getEmailTxt().setText("");
//		turnoFieldsPanel.getDniTxt().setText("");
//		turnoFieldsPanel.getCostoConsultaTxt().setText("");
//		Date fechaActual = new Date(System.currentTimeMillis());
//		turnoFieldsPanel.getFechaSeleccionPanel().setFecha(fechaActual);
	}

	@Override
	public void volverAction() {
		limpiarAction();
		panelManager.mostrarListaTurno(true);
	}

	private void rellenarFields(Turno turno) {
		if (turno != null) {
//			TurnoFieldsPanel turnoFieldsPanel = (TurnoFieldsPanel) this.fieldsPanel;
//			turnoFieldsPanel.getNombreUsuarioTxt().setText(turno.getNombreUsuario());
//			turnoFieldsPanel.getContraseniaTxt().setText(turno.getContrasenia());
//			turnoFieldsPanel.getNombreTxt().setText(turno.getNombre());
//			turnoFieldsPanel.getApellidoTxt().setText(turno.getApellido());
//			turnoFieldsPanel.getEmailTxt().setText(turno.getEmail());
//			turnoFieldsPanel.getDniTxt().setText(String.valueOf(turno.getDni()));
//			turnoFieldsPanel.getCostoConsultaTxt().setText(String.valueOf(turno.getCostoConsulta()));
//			turnoFieldsPanel.getFechaSeleccionPanel().setFecha(turno.getFechaNacimiento());
		}
	}

	private void validarTurno() throws ValoresValidationException {
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
//		int maximoDNI = 99999999;
//		int maximoCostoConsulta = 10000;
//		int minimoDNI = 1;
//		int minimoCostoConsulta = 0;
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(nombreUsuario);
//			TextoValidator.ValidarSoloLetras(nombreUsuario);
//			TextoValidator.ValidarLongitudMaxima(nombreUsuario, 64);
//		} catch (TextoValidatorException e) {
//			String mensaje = "Revisar campo: Nombre Usuario \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(contrasenia);
//			TextoValidator.ValidarLongitudMaxima(contrasenia, 32);
//		} catch (TextoValidatorException e) {
//			String mensaje = "Revisar campo: Contraseña \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(nombre);
//			TextoValidator.ValidarSoloLetras(nombre);
//			TextoValidator.ValidarLongitudMaxima(nombre, 32);
//		} catch (TextoValidatorException e) {
//			String mensaje = "Revisar campo: Nombre \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(apellido);
//			TextoValidator.ValidarSoloLetras(apellido);
//			TextoValidator.ValidarLongitudMaxima(apellido, 32);
//		} catch (TextoValidatorException e) {
//			String mensaje = "Revisar campo: Apellido \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(email);
//			TextoValidator.ValidarEmail(email);
//			TextoValidator.ValidarLongitudMaxima(email, 128);
//		} catch (TextoValidatorException e) {
//			String mensaje = "Revisar campo: Email \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(dni);
//			TextoValidator.ValidarNumerico(dni);
//			TextoValidator.ValidarLongitudMaxima(dni, 8);
//			NumeroValidator.ValidarNumeroPositivo(Integer.valueOf(dni));
//			NumeroValidator.ValidarNumeroEnRango(Integer.valueOf(dni), minimoDNI, maximoDNI);
//		} catch (ValoresValidationException e) {
//			String mensaje = "Revisar campo: DNI \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			TextoValidator.ValidarTextoNoVacio(costoConsulta);
//			TextoValidator.ValidarNumerico(costoConsulta);
//			NumeroValidator.ValidarNumeroPositivo(Integer.valueOf(costoConsulta));
//			NumeroValidator.ValidarNumeroEnRango(Integer.valueOf(costoConsulta), minimoCostoConsulta,
//					maximoCostoConsulta);
//		} catch (ValoresValidationException e) {
//			String mensaje = "Revisar campo: Costo Consulta \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
//
//		try {
//			FechaValidator.ValidarEdadMinima(fecha, 21);
//			FechaValidator.ValidarEdadMaxima(fecha, 90);
//		} catch (FechaValidatorException e) {
//			String mensaje = "Revisar campo: Fecha de Nacimiento \r\n" + e.getMessage();
//			throw new ValoresValidationException(mensaje);
//		}
	}

//	private boolean esIgual(Turno turnoModificado, Turno turnoEdicion) {
//		return turnoModificado.equals(turnoEdicion);
//	}

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
