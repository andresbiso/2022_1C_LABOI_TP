package presentacion.mainpanel;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import aplicacion.enums.UsuarioTipo;
import aplicacion.model.Usuario;
import presentacion.PanelManager;

@SuppressWarnings("serial")
public class InicioMainPanel extends JPanel {
    protected PanelManager panelManager;
    private JButton medicosBtn;
    private JButton pacientesBtn;
    private JButton turnosBtn;
    private JButton reporteMedicoBtn;
    private JButton logoutBtn;
    private JButton turnosPacienteBtn;

    public InicioMainPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        inicializarPanel();
    }

    public void inicializarPanel() {
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder
                ("Secciones"));

        this.medicosBtn = new JButton("Medicos");
        this.medicosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListaMedico(true);
            }
        });
        
        this.pacientesBtn = new JButton("Pacientes");
        this.pacientesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListaPaciente(true);
            }
        });
        
        this.turnosBtn = new JButton("Turnos");
        this.turnosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelManager.mostrarListaTurno(true);
            }
        });
        
        this.reporteMedicoBtn = new JButton("Reporte Médico");
        this.reporteMedicoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelManager.mostrarReporteMedico();
            }
        });
        
        this.logoutBtn = new JButton("Cerrar Sesión");
        this.logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelManager.mostrarLogin();
            }
        });
        
        this.turnosPacienteBtn = new JButton("Mis Turnos");
        this.turnosPacienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panelManager.mostrarTurnosPaciente();
            }
        });
        
        Usuario usuarioActual = this.panelManager.getUsuarioActual();

        if (usuarioActual != null && usuarioActual.getUsuarioTipo() == UsuarioTipo.Medico) {
            this.add(turnosBtn);
        	this.add(medicosBtn);
            this.add(pacientesBtn);
            this.add(reporteMedicoBtn);	
        } else {
        	this.add(turnosPacienteBtn);
        }

        this.add(logoutBtn);
        
    }
}

