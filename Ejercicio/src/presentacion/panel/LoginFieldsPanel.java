package presentacion.panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import presentacion.PanelManager;
import presentacion.basepanel.FieldsBasePanel;

@SuppressWarnings("serial")
public class LoginFieldsPanel extends FieldsBasePanel {
	private JTextField nombreUsuarioTxt;
	private JPasswordField usuarioPass;

	public LoginFieldsPanel(PanelManager panelManager) {
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
		
		JPanel usuarioPassFieldPanel = new JPanel();
		usuarioPassFieldPanel.setLayout(new BoxLayout(usuarioPassFieldPanel, BoxLayout.X_AXIS));
		JLabel usuarioPassLbl = new JLabel("Contraseña:");
		usuarioPass = new JPasswordField("");
		usuarioPassFieldPanel.add(usuarioPassLbl);
		usuarioPassFieldPanel.add(usuarioPass);
		this.add(usuarioPassFieldPanel);
	}
	
	public JTextField getNombreUsuarioTxt() {
		return nombreUsuarioTxt;
	}

	public JPasswordField getUsuarioPass() {
		return usuarioPass;
	}
}

