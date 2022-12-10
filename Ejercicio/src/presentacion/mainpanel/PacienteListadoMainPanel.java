package presentacion.mainpanel;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.exception.ServiceException;
import aplicacion.model.Paciente;
import aplicacion.service.PacienteService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.ListadoMainPanel;
import presentacion.panel.PacienteTablePanel;

@SuppressWarnings("serial")
public class PacienteListadoMainPanel extends ListadoMainPanel {
	
	private final PacienteService pacienteService;
	
	public PacienteListadoMainPanel(PanelManager panelManager) {
		super(panelManager);
		this.pacienteService = new PacienteService();
		this.tablePanel.inicializarPanel(obtenerPacientes());
	}

	@Override
	public void setTablePanel() {
		this.tablePanel = new PacienteTablePanel(panelManager);	
	}

	@Override
	public void agregarAction() {
		panelManager.mostrarAltaPaciente();
	}

	@Override
	public void editarAction() {
		PacienteTablePanel pacienteTablePanel = (PacienteTablePanel)this.tablePanel;
		
		int filaSeleccionada = pacienteTablePanel.getPacienteTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Paciente pacienteEditar = pacienteTablePanel.getPacienteTableModel().getContenido().get(filaSeleccionada);
			
			panelManager.mostrarEdicionPaciente(pacienteEditar);
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a editar");
		}
	}

	@Override
	public void borrarAction() {
		PacienteTablePanel pacienteTablePanel = (PacienteTablePanel)this.tablePanel;
		
		int filaSeleccionada = pacienteTablePanel.getPacienteTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Paciente pacienteBorrar = pacienteTablePanel.getPacienteTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea eliminar el paciente?") == JOptionPane.YES_OPTION) {
				try {
					pacienteService.borrarPaciente(pacienteBorrar);
					DialogManager.MostrarMensajeExito(this, "El paciente fue eliminado con éxito");
					pacienteTablePanel.getPacienteTableModel().getContenido().remove(pacienteBorrar);
					pacienteTablePanel.getPacienteTableModel().fireTableDataChanged();
				} catch (ServiceException e) {
					DialogManager.MostrarMensajeError(this);
				}
			}
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a borrar");
		}
	}

	@Override
	public void volverAction() {
		panelManager.mostrarInicio();
	}
	
	private ArrayList<Paciente> obtenerPacientes() {
		ArrayList<Paciente> listaPacientes = null;
		try {
			listaPacientes = pacienteService.obtenerPacientes();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		return listaPacientes;
	}

}
