package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsBasePanel;

@SuppressWarnings("serial")
public class ListadoActionsPanel extends ActionsBasePanel {
	private JButton agregarBtn;
	private JButton editarBtn;
	private JButton borrarBtn;
	private JButton volverBtn;

	public ListadoActionsPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new FlowLayout());
		agregarBtn = new JButton("Agregar");
		editarBtn  = new JButton("Editar");
		borrarBtn  = new JButton("Borrar");
		volverBtn = new JButton("Volver");

		actionsPanel.add(agregarBtn);
		actionsPanel.add(editarBtn);
		actionsPanel.add(borrarBtn);
		actionsPanel.add(volverBtn);
		
		this.add(actionsPanel, BorderLayout.SOUTH);
	}

	public JButton getAgregarBtn() {
		return agregarBtn;
	}

	public JButton getEditarBtn() {
		return editarBtn;
	}

	public JButton getBorrarBtn() {
		return borrarBtn;
	}

	public JButton getVolverBtn() {
		return volverBtn;
	}
}

