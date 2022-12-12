package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class FiltersPanel extends JPanel {
	
	protected final PanelManager panelManager;

	public FiltersPanel(PanelManager panelManager) {
		this.panelManager = panelManager;
		inicializarPanel();
	}
	
	public abstract void inicializarPanel();
}
