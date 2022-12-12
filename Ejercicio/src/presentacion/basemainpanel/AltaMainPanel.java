package presentacion.basemainpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.FieldsBasePanel;
import presentacion.panel.FormActionsPanel;

@SuppressWarnings("serial")
public abstract class AltaMainPanel extends JPanel {
    protected PanelManager panelManager;

    protected FieldsBasePanel fieldsPanel;
    protected FormActionsPanel botoneraFormPanel;

    public AltaMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setFieldsPanel();
        this.setBotoneraPanel();
        inicializarPanel();
    }

    public void inicializarPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(fieldsPanel);

		this.add(botoneraFormPanel);

		this.botoneraFormPanel.getAceptarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarAction();
            }
        });
		
		this.botoneraFormPanel.getLimpiarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	limpiarAction();
            }
        });
		
        
		this.botoneraFormPanel.getVolverBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAction();
			}
		});

    }

    private void setBotoneraPanel() {
        this.botoneraFormPanel = new FormActionsPanel(this.panelManager);
    }


    public abstract void setFieldsPanel();

    public abstract void aceptarAction();
    public abstract void limpiarAction();
    public abstract void volverAction();
}
