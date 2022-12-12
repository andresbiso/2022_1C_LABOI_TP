package presentacion.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.FiltersBasePanel;

@SuppressWarnings("serial")
public class ReporteMedicoFiltersPanel extends FiltersBasePanel {
	private JButton buscarBtn;

	public ReporteMedicoFiltersPanel(PanelManager panelManager) {
		 super(panelManager);
	}
	
	public void inicializarPanel() {
		this.setLayout(new BorderLayout());
		JPanel filtroPanel = new JPanel();
		filtroPanel.setLayout(new FlowLayout());
		buscarBtn = new JButton("Buscar");

		filtroPanel.add(buscarBtn);
		
		this.add(filtroPanel, BorderLayout.NORTH);
	}

	public JButton getBuscarBtn() {
		return buscarBtn;
	}
}

