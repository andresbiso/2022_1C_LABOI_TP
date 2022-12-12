package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.ActionsPanel;

@SuppressWarnings("serial")
public class ListadoActionsPanel extends ActionsPanel {
	private JButton agregarBtn;
	private JButton editarBtn;
	private JButton borrarBtn;
	private JButton volverBtn;

	public ListadoActionsPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel botoneraPanel = new JPanel();
		botoneraPanel.setLayout(new FlowLayout());
		agregarBtn = new JButton("Agregar");
		editarBtn  = new JButton("Editar");
		borrarBtn  = new JButton("Borrar");
		volverBtn = new JButton("Volver");

		botoneraPanel.add(agregarBtn);
		botoneraPanel.add(editarBtn);
		botoneraPanel.add(borrarBtn);
		botoneraPanel.add(volverBtn);
		
		this.add(botoneraPanel, BorderLayout.SOUTH);
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

