package presentacion.panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.PanelManager;
import presentacion.basepanel.FieldsBasePanel;

@SuppressWarnings("serial")
public class ReporteMedicoFieldsPanel extends FieldsBasePanel {
	private JLabel totalConsultasValueLbl;
	private JLabel totalHonorariosValueLbl;

	public ReporteMedicoFieldsPanel(PanelManager panelManager) {
		super(panelManager);
		inicializarPanel();
	}
	
	public void inicializarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel totalConsultasFieldPanel = new JPanel();
		totalConsultasFieldPanel.setLayout(new BoxLayout(totalConsultasFieldPanel, BoxLayout.X_AXIS));
		JLabel totalConsultasLbl = new JLabel("Total Consultas:");
		totalConsultasValueLbl = new JLabel("");
		totalConsultasFieldPanel.add(totalConsultasLbl);
		totalConsultasFieldPanel.add(totalConsultasValueLbl);
		this.add(totalConsultasFieldPanel);
		
		JPanel totalHonorariosFieldPanel = new JPanel();
		totalHonorariosFieldPanel.setLayout(new BoxLayout(totalHonorariosFieldPanel, BoxLayout.X_AXIS));
		JLabel totalHonorariosLbl = new JLabel("Total Honorarios:");
		totalHonorariosValueLbl = new JLabel("");
		totalHonorariosFieldPanel.add(totalHonorariosLbl);
		totalHonorariosFieldPanel.add(totalHonorariosValueLbl);
		this.add(totalHonorariosFieldPanel);
	}
	
	public JLabel getTotalConsultasValueLbl() {
		return totalConsultasValueLbl;
	}

	public JLabel getTotalHonorariosValueLbl() {
		return totalHonorariosValueLbl;
	}
}

