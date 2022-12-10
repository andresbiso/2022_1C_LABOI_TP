package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

public abstract class FieldsPanel extends JPanel {
	
	protected final PanelManager panelManager;

	public FieldsPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	public abstract void inicializarPanel();
}
