package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class BotoneraPanel extends JPanel {
	
	protected final PanelManager panelManager;

	public BotoneraPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
		inicializarPanel();
	}
	
	public abstract void inicializarPanel();
}
