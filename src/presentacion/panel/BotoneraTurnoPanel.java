package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.BotoneraPanel;

public class BotoneraTurnoPanel extends BotoneraPanel {
	private JButton asignarBtn;
	private JButton confirmarBtn;

	public BotoneraTurnoPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel botoneraPanel = new JPanel();
		botoneraPanel.setLayout(new FlowLayout());
		asignarBtn = new JButton("Asignar Turno");
		confirmarBtn  = new JButton("Confirmar Turno");

		botoneraPanel.add(asignarBtn);
		botoneraPanel.add(confirmarBtn);
		
		this.add(botoneraPanel, BorderLayout.SOUTH);
	}

	public JButton getAsignarBtn() {
		return asignarBtn;
	}

	public void setAsignarBtn(JButton asignarBtn) {
		this.asignarBtn = asignarBtn;
	}

	public JButton getConfirmarBtn() {
		return confirmarBtn;
	}

	public void setConfirmarBtn(JButton confirmarBtn) {
		this.confirmarBtn = confirmarBtn;
	}
}

