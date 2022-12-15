package presentacion.mainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import aplicacion.exception.ServiceException;
import aplicacion.exception.TextoValidatorException;
import aplicacion.exception.ValoresValidationException;
import aplicacion.model.Usuario;
import aplicacion.service.UsuarioService;
import aplicacion.validator.TextoValidator;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.panel.LoginActionsPanel;
import presentacion.panel.LoginFieldsPanel;

@SuppressWarnings("serial")
public class LoginMainPanel extends JPanel {
	private PanelManager panelManager;

	private LoginFieldsPanel fieldsPanel;
	private LoginActionsPanel actionsPanel;
	
	private final UsuarioService usuarioService;

	public LoginMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setFieldsPanel();
        this.setActionsPanel();
        inicializarPanel();
        this.usuarioService = new UsuarioService();
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(fieldsPanel);
		this.add(actionsPanel);


		this.actionsPanel.getIngresarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ingresarAction();
			}
		});

	}
	
	public void setFieldsPanel() {
		this.fieldsPanel = new LoginFieldsPanel(panelManager);
	}

	private void setActionsPanel() {
		this.actionsPanel = new LoginActionsPanel(this.panelManager);
	}

	public void ingresarAction() {
		try {
			obtenerUsuario();
			panelManager.mostrarInicio();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this, "Hubo un problema al tratar de obtener el usuario");
		} catch (ValoresValidationException e) {
			DialogManager.MostrarMensajeError(this, e.getMessage());
		}
	}
	
	private void obtenerUsuario() throws ServiceException, ValoresValidationException {
		LoginFieldsPanel loginFieldsPanel = (LoginFieldsPanel) this.fieldsPanel;
		String userName = loginFieldsPanel.getNombreUsuarioTxt().getText();
		String userPass = String.valueOf(loginFieldsPanel.getUsuarioPass().getPassword());
		
		// Test Values - Medico
//		String userName = "jdiaz";
//		String userPass = "pass1234";
		
		// Test Values - Paciente
//		String userName = "jalvarez";
//		String userPass = "pass1234";
	
		try {
			TextoValidator.ValidarTextoNoVacio(userName);
			TextoValidator.ValidarSoloLetras(userName);
			TextoValidator.ValidarLongitudMaxima(userName, 64);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Nombre Usuario \r\n";
			throw new ValoresValidationException(mensaje);
		}

		try {
			TextoValidator.ValidarTextoNoVacio(userPass);
			TextoValidator.ValidarLongitudMaxima(userPass, 32);
		} catch (TextoValidatorException e) {
			String mensaje = "Revisar campo: Contraseña \r\n";
			throw new ValoresValidationException(mensaje);
		}
		
		Usuario usuario = usuarioService.obtenerUsuario(userName, userPass);
		
		if (usuario == null) {
			throw new ValoresValidationException("Revise nombre de usuario y contraseña");
		}
		
		panelManager.setUsuarioActual(usuario);
	}
}
