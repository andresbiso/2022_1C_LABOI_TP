package presentacion.basemainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.TableBasePanel;
import presentacion.panel.ListadoActionsPanel;

@SuppressWarnings("serial")
public abstract class ListadoMainPanel extends JPanel {
	protected PanelManager panelManager;

	protected TableBasePanel tablePanel;
	protected ListadoActionsPanel listadoActionsPanel;

	public ListadoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setTablePanel();
        this.setActionsPanel();
        inicializarPanel();
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(tablePanel);

		this.add(listadoActionsPanel);

		this.listadoActionsPanel.getAgregarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarAction();
			}
		});
		
		this.listadoActionsPanel.getEditarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarAction();
			}
		});
		
		this.listadoActionsPanel.getBorrarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarAction();
			}
		});

		this.listadoActionsPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}

	private void setActionsPanel() {
		this.listadoActionsPanel = new ListadoActionsPanel(this.panelManager);
	}

	public abstract void setTablePanel();

	public abstract void agregarAction();

	public abstract void editarAction();

	public abstract void borrarAction();

	public abstract void volverAction();
}
