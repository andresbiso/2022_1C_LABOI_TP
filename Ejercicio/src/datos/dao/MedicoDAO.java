package datos.dao;

import java.util.ArrayList;

import aplicacion.model.Medico;
import aplicacion.model.Usuario;
import datos.exception.DAOException;

public interface MedicoDAO {
	int crearMedico(Medico medico) throws DAOException;
	
	int borrarMedico(Medico medico) throws DAOException;
	
	int borrarLosMedicos() throws DAOException;

	int actualizarMedico(Medico medicoModificado, Medico medicoOriginal) throws DAOException;
	
	ArrayList<Medico> listarTodosLosMedicos(ArrayList<Usuario> usuariosMedicos) throws DAOException;
	
	Medico consultarMedico(Usuario usuarioMedico) throws DAOException;
	
	Medico consultarMedico(int idMedico) throws DAOException;
}
