package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class ActionsPanel extends JPanel {
	
	protected final PanelManager panelManager;

	public ActionsPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
		inicializarPanel();
	}
	
	public abstract void inicializarPanel();
}
