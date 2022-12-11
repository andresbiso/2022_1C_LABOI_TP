package presentacion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import presentacion.mainpanel.InicioMainPanel;
import presentacion.mainpanel.MedicoAltaMainPanel;
import presentacion.mainpanel.MedicoListadoMainPanel;
import presentacion.mainpanel.PacienteAltaMainPanel;
import presentacion.mainpanel.PacienteListadoMainPanel;
import presentacion.mainpanel.TurnoAltaMainPanel;
import presentacion.mainpanel.TurnoAsignacionMainPanel;
import presentacion.mainpanel.TurnoListadoMainPanel;

public class PanelManager {
	private JFrame mainFrame;

	private InicioMainPanel inicioMainPanel;
	private MedicoListadoMainPanel medicoListadoMainPanel;
	private MedicoAltaMainPanel medicoAltaMainPanel;
	private PacienteListadoMainPanel pacienteListadoMainPanel;
	private PacienteAltaMainPanel pacienteAltaMainPanel;
	private TurnoListadoMainPanel turnoListadoMainPanel;
	private TurnoAltaMainPanel turnoAltaMainPanel;

	public PanelManager() {
		inicializarManager();
	}

	private void inicializarManager() {
		mainFrame = new JFrame();
		mainFrame.setSize(1024, 400);
		mainFrame.setLocationRelativeTo(null);
		
		mainFrame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent evt) {
		    	int respuesta = DialogManager.MostrarMensajeConfirmacion(null, "¿Está seguro que quiere salir?");

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        } else {
		        	mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        }
		    }
		});

		this.inicioMainPanel = new InicioMainPanel(this);
		this.medicoListadoMainPanel = new MedicoListadoMainPanel(this);
		this.medicoAltaMainPanel = new MedicoAltaMainPanel(this);
		this.pacienteListadoMainPanel = new PacienteListadoMainPanel(this);
		this.pacienteAltaMainPanel = new PacienteAltaMainPanel(this);
		this.turnoListadoMainPanel = new TurnoListadoMainPanel(this);
		this.turnoAltaMainPanel = new TurnoAltaMainPanel(this);
		new TurnoAsignacionMainPanel(this);
	}

	public void showFrame() {
		mainFrame.setVisible(true);
	}

	public void mostrarInicio() {
		mostrarMainPanel(inicioMainPanel, "Inicio");
	}
	
	// Medico

	public void mostrarListaMedico(boolean refrescar) {
		if (refrescar) {
			medicoListadoMainPanel = new MedicoListadoMainPanel(this);
		}
		mostrarMainPanel(medicoListadoMainPanel, "Lista Médico");
	}
	
	public void mostrarAltaMedico() {
		mostrarMainPanel(medicoAltaMainPanel, "Agregar Médico");
	}
	
	public void mostrarEdicionMedico(Medico medico) {
		mostrarMainPanel(new MedicoAltaMainPanel(this, medico), "Editar Médico");
	}
	

	// Paciente

	public void mostrarListaPaciente(boolean refrescar) {
		if (refrescar) {
			pacienteListadoMainPanel = new PacienteListadoMainPanel(this);
		}
		mostrarMainPanel(pacienteListadoMainPanel, "Lista Paciente");
	}
	
	public void mostrarAltaPaciente() {
		mostrarMainPanel(pacienteAltaMainPanel, "Agregar Paciente");
	}
	
	public void mostrarEdicionPaciente(Paciente paciente) {
		mostrarMainPanel(new PacienteAltaMainPanel(this, paciente), "Editar Paciente");
	}
	
	// Turno

	public void mostrarListaTurno(boolean refrescar) {
		if (refrescar) {
			turnoListadoMainPanel = new TurnoListadoMainPanel(this);
		}
		mostrarMainPanel(turnoListadoMainPanel, "Lista Turno");
	}
	
	public void mostrarAltaTurno() {
		mostrarMainPanel(turnoAltaMainPanel, "Agregar Turno");
	}
	
	public void mostrarEdicionTurno(Turno turno) {
		mostrarMainPanel(new TurnoAltaMainPanel(this, turno), "Editar Turno");
	}
	
	public void mostrarAsignacionTurno(Turno turno) {
		mostrarMainPanel(new TurnoAsignacionMainPanel(this, turno), "Asignar Turno");
	}
	
	private void mostrarMainPanel(JPanel mainPanel, String titulo) {
		mainFrame.setTitle(titulo);
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.getContentPane().validate();
		mainFrame.getContentPane().repaint();
	}
}
