package presentacion.panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsBasePanel;

@SuppressWarnings("serial")
public class FormActionsPanel extends ActionsBasePanel {
	private JButton aceptarBtn;
	private JButton limpiarBtn;
	private JButton volverBtn;

	public FormActionsPanel(PanelManager panelManager) {
		super(panelManager);
	}
	
	public void inicializarPanel() {
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.X_AXIS));
		aceptarBtn = new JButton("Aceptar");
		limpiarBtn  = new JButton("Limpiar");
		volverBtn = new JButton("Volver");

		actionsPanel.add(aceptarBtn);
		actionsPanel.add(limpiarBtn);
		actionsPanel.add(volverBtn);
		
		this.add(actionsPanel);
	}

	public JButton getAceptarBtn() {
		return aceptarBtn;
	}

	public JButton getLimpiarBtn() {
		return limpiarBtn;
	}

	public JButton getVolverBtn() {
		return volverBtn;
	}
}

