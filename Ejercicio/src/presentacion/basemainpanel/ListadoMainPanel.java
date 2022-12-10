package presentacion.basemainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.TablePanel;
import presentacion.panel.BotoneraListadoPanel;

@SuppressWarnings("serial")
public abstract class ListadoMainPanel extends JPanel {
	protected PanelManager panelManager;

	protected TablePanel tablePanel;
	protected BotoneraListadoPanel botoneraListadoPanel;

	public ListadoMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setTablePanel();
        this.setBotoneraPanel();
        inicializarPanel();
    }

	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(tablePanel);

		this.add(botoneraListadoPanel);

		this.botoneraListadoPanel.getAgregarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarAction();
			}
		});
		
		this.botoneraListadoPanel.getEditarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarAction();
			}
		});
		
		this.botoneraListadoPanel.getBorrarBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarAction();
			}
		});

		this.botoneraListadoPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

	}

	private void setBotoneraPanel() {
		this.botoneraListadoPanel = new BotoneraListadoPanel(this.panelManager);
	}

	public abstract void setTablePanel();

	public abstract void agregarAction();

	public abstract void editarAction();

	public abstract void borrarAction();

	public abstract void volverAction();
}
