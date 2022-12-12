package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class FiltersBasePanel extends JPanel {
	
	protected final PanelManager panelManager;

	public FiltersBasePanel(PanelManager panelManager) {
		this.panelManager = panelManager;
		inicializarPanel();
	}
	
	public abstract void inicializarPanel();
}
