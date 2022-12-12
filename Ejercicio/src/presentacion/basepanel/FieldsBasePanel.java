package presentacion.basepanel;

import javax.swing.JPanel;

import presentacion.PanelManager;

@SuppressWarnings("serial")
public abstract class FieldsBasePanel extends JPanel {
	
	protected final PanelManager panelManager;

	public FieldsBasePanel(PanelManager panelManager) {
		this.panelManager = panelManager;
	}
	
	public abstract void inicializarPanel();
}
