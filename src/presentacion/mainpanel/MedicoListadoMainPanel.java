package presentacion.mainpanel;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.service.MedicoService;
import presentacion.DialogManager;
import presentacion.PanelManager;
import presentacion.basemainpanel.ListadoMainPanel;
import presentacion.panel.MedicoTablePanel;

public class MedicoListadoMainPanel extends ListadoMainPanel {
	
	private final MedicoService medicoService;
	
	public MedicoListadoMainPanel(PanelManager panelManager) {
		super(panelManager);
		this.medicoService = new MedicoService();
		this.tablePanel.inicializarPanel(obtenerMedicos());
	}

	@Override
	public void setTablePanel() {
		this.tablePanel = new MedicoTablePanel(panelManager);	
	}

	@Override
	public void agregarAction() {
		panelManager.mostrarAltaMedico();
	}

	@Override
	public void editarAction() {
		MedicoTablePanel medicoTablePanel = (MedicoTablePanel)this.tablePanel;
		
		int filaSeleccionada = medicoTablePanel.getMedicoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Medico medicoEditar = medicoTablePanel.getMedicoTableModel().getContenido().get(filaSeleccionada);
			
			panelManager.mostrarEdicionMedico(medicoEditar);
		} else {
			DialogManager.MostrarMensajeAdvertencia(this, "Debe seleccionar una opción a editar");
		}
	}

	@Override
	public void borrarAction() {
		MedicoTablePanel medicoTablePanel = (MedicoTablePanel)this.tablePanel;
		
		int filaSeleccionada = medicoTablePanel.getMedicoTable().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			Medico medicoBorrar = medicoTablePanel.getMedicoTableModel().getContenido().get(filaSeleccionada);
			if (DialogManager.MostrarMensajeConfirmacion(this, "¿Desea eliminar el médico?") == JOptionPane.YES_OPTION) {
				try {
					medicoService.borrarMedico(medicoBorrar);
					DialogManager.MostrarMensajeExito(this, "El médico fue eliminado con éxito");
					medicoTablePanel.getMedicoTableModel().getContenido().remove(medicoBorrar);
					medicoTablePanel.getMedicoTableModel().fireTableDataChanged();
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
	
	private ArrayList<Medico> obtenerMedicos() {
		ArrayList<Medico> listaMedicos = null;
		try {
			listaMedicos = medicoService.obtenerMedicos();
		} catch (ServiceException e) {
			DialogManager.MostrarMensajeError(this);
		}
		return listaMedicos;
	}

}
