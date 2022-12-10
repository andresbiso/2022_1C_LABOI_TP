package datos.dao;

import java.util.ArrayList;

import aplicacion.model.Paciente;
import aplicacion.model.Usuario;
import datos.exception.DAOException;

public interface PacienteDAO {
	int crearPaciente(Paciente paciente) throws DAOException;
	
	int borrarPaciente(Paciente paciente) throws DAOException;
	
	int borrarLosPacientes() throws DAOException;

	int actualizarPaciente(Paciente pacienteModificado, Paciente pacienteOriginal) throws DAOException;
	
	ArrayList<Paciente> listarTodosLosPacientes(ArrayList<Usuario> usuariosPacientes) throws DAOException;

	Paciente consultarPaciente(Usuario usuarioPaciente) throws DAOException;
	
	Paciente consultarPaciente(int idPaciente) throws DAOException;
}
