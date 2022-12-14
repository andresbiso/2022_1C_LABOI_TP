package presentacion.basepanel;

import java.util.List;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class TableBasePanel extends JPanel {
	
	protected final PanelManager panelManager;

	public TableBasePanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	public abstract void inicializarPanel(List<?> lista);
}
