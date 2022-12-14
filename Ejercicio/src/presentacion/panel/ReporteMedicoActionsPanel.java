package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsBasePanel;

@SuppressWarnings("serial")
public class ReporteMedicoActionsPanel extends ActionsBasePanel {
	private JButton volverBtn;

	public ReporteMedicoActionsPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel botoneraPanel = new JPanel();
		botoneraPanel.setLayout(new FlowLayout());
		volverBtn = new JButton("Volver");

		botoneraPanel.add(volverBtn);
		
		this.add(botoneraPanel, BorderLayout.SOUTH);
	}

	public JButton getVolverBtn() {
		return volverBtn;
	}
}

