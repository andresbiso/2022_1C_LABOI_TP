package presentacion.basepanel;

import java.util.List;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class TablePanel extends JPanel {
	
	protected final PanelManager panelManager;

	public TablePanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	public abstract void inicializarPanel(List<?> lista);
}
