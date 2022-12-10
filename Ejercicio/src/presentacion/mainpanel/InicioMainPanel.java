package presentacion.mainpanel;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.PanelManager;


public class InicioMainPanel extends JPanel {
    protected PanelManager panelManager;
    private JButton medicosBtn;
    private JButton pacientesBtn;
    private JButton turnosBtn;

    public InicioMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        inicializarPanel();
    }

    public void inicializarPanel() {
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder
                ("Secciones"));

        this.medicosBtn = new JButton("Medicos");
        this.add(medicosBtn);

        this.medicosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListaMedico(false);
            }
        });
        
        this.pacientesBtn = new JButton("Pacientes");
        this.add(pacientesBtn);

        this.pacientesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListaPaciente(false);
            }
        });
        
        this.turnosBtn = new JButton("Turnos");
        this.add(turnosBtn);

        this.turnosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelManager.mostrarListaTurno(false);
            }
        });
    }
}
