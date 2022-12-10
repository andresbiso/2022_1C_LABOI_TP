package presentacion.panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.PanelManager;
import presentacion.basepanel.FieldsPanel;

public class PacienteFieldsPanel extends FieldsPanel {
	private JTextField nombreUsuarioTxt;
	private JTextField contraseniaTxt;
	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	private JTextField emailTxt;
	private FechaSeleccionPanel fechaSeleccionPanel;
	private JTextField dniTxt;

	public PacienteFieldsPanel(PanelManager panelManager) {
		super(panelManager);
		inicializarPanel();
	}
	
	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel nombreUsuarioFieldPanel = new JPanel();
		nombreUsuarioFieldPanel.setLayout(new BoxLayout(nombreUsuarioFieldPanel, BoxLayout.X_AXIS));
		JLabel nombreUsuarioLbl = new JLabel("Nombre Usuario:");
		nombreUsuarioTxt = new JTextField("");
		nombreUsuarioFieldPanel.add(nombreUsuarioLbl);
		nombreUsuarioFieldPanel.add(nombreUsuarioTxt);
		this.add(nombreUsuarioFieldPanel);
		
		JPanel contraseniaFieldPanel = new JPanel();
		contraseniaFieldPanel.setLayout(new BoxLayout(contraseniaFieldPanel, BoxLayout.X_AXIS));
		JLabel contraseniaLbl = new JLabel("Contraseña:");
		contraseniaTxt = new JTextField("");
		contraseniaFieldPanel.add(contraseniaLbl);
		contraseniaFieldPanel.add(contraseniaTxt);
		this.add(contraseniaFieldPanel);
		
		JPanel nombreFieldPanel = new JPanel();
		nombreFieldPanel.setLayout(new BoxLayout(nombreFieldPanel, BoxLayout.X_AXIS));
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreTxt = new JTextField("");
		nombreFieldPanel.add(nombreLbl);
		nombreFieldPanel.add(nombreTxt);
		this.add(nombreFieldPanel);
		
		JPanel apellidoFieldPanel = new JPanel();
		apellidoFieldPanel.setLayout(new BoxLayout(apellidoFieldPanel, BoxLayout.X_AXIS));
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoTxt = new JTextField("");
		apellidoFieldPanel.add(apellidoLbl);
		apellidoFieldPanel.add(apellidoTxt);
		this.add(apellidoFieldPanel);
		
		JPanel emailFieldPanel = new JPanel();
		emailFieldPanel.setLayout(new BoxLayout(emailFieldPanel, BoxLayout.X_AXIS));
		JLabel emailLbl = new JLabel("Email:");
		emailTxt = new JTextField("");
		emailFieldPanel.add(emailLbl);
		emailFieldPanel.add(emailTxt);
		this.add(emailFieldPanel);
		
		JPanel dniFieldPanel = new JPanel();
		dniFieldPanel.setLayout(new BoxLayout(dniFieldPanel, BoxLayout.X_AXIS));
		JLabel dniLbl = new JLabel("DNI:");
		dniTxt = new JTextField("");
		dniFieldPanel.add(dniLbl);
		dniFieldPanel.add(dniTxt);
		this.add(dniFieldPanel);
		
		fechaSeleccionPanel = new FechaSeleccionPanel("Fecha de Nacimiento:");
		this.add(fechaSeleccionPanel);
	}

	public FechaSeleccionPanel getFechaSeleccionPanel() {
		return fechaSeleccionPanel;
	}

	public JTextField getNombreUsuarioTxt() {
		return nombreUsuarioTxt;
	}

	public JTextField getContraseniaTxt() {
		return contraseniaTxt;
	}


	public JTextField getNombreTxt() {
		return nombreTxt;
	}


	public JTextField getApellidoTxt() {
		return apellidoTxt;
	}


	public JTextField getEmailTxt() {
		return emailTxt;
	}

	public JTextField getDniTxt() {
		return dniTxt;
	}
}

