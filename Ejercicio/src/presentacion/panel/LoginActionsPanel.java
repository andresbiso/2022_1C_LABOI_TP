package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsBasePanel;

@SuppressWarnings("serial")
public class LoginActionsPanel extends ActionsBasePanel {
	private JButton ingresarBtn;

	public LoginActionsPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new FlowLayout());
		ingresarBtn = new JButton("Ingresar");

		actionsPanel.add(ingresarBtn);
		
		this.add(actionsPanel, BorderLayout.SOUTH);
	}

	public JButton getIngresarBtn() {
		return ingresarBtn;
	}
}

