package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsPanel;

@SuppressWarnings("serial")
public class TurnoActionsPanel extends ActionsPanel {
	private JButton asignarBtn;
	private JButton desasignarBtn;
	private JButton confirmarAsistenciaBtn;
	private JButton anularAsistenciaBtn;

	public TurnoActionsPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel botoneraPanel = new JPanel();
		botoneraPanel.setLayout(new FlowLayout());
		asignarBtn = new JButton("Asignar Turno");
		desasignarBtn = new JButton("Desasignar Turno");
		confirmarAsistenciaBtn  = new JButton("Confirmar Asistencia Turno");
		anularAsistenciaBtn  = new JButton("Anular Asistencia Turno");

		botoneraPanel.add(asignarBtn);
		botoneraPanel.add(desasignarBtn);
		botoneraPanel.add(confirmarAsistenciaBtn);
		botoneraPanel.add(anularAsistenciaBtn);
		
		this.add(botoneraPanel, BorderLayout.SOUTH);
	}

	public JButton getAsignarBtn() {
		return asignarBtn;
	}
	
	public JButton getDesasignarBtn() {
		return desasignarBtn;
	}

	public JButton getConfirmarAsistenciaBtn() {
		return confirmarAsistenciaBtn;
	}
	
	public JButton getAnularAsistenciaBtn() {
		return anularAsistenciaBtn;
	}
}

