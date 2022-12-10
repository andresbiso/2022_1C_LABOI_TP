package aplicacion.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import aplicacion.enums.UsuarioTipo;

public class Paciente extends Usuario {

	private int idPaciente;

	public Paciente() {
	}
	
	public Paciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Paciente(Usuario usuario) {
		super(usuario.getNombreUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getDni(), UsuarioTipo.Paciente);
	}

	public Paciente(Usuario usuario, int idPaciente) {
		super(usuario.getNombreUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getDni(), UsuarioTipo.Paciente);
		this.idPaciente = idPaciente;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void setPaciente(Paciente paciente) {
		setUsuario((Usuario)paciente);
		this.setIdPaciente(paciente.getIdPaciente());
	}

	public HashMap<String, String> obtenerHashMapPaciente() {
		HashMap<String, String> pacienteHashMap = new HashMap<String, String>();
		return pacienteHashMap;
	}

	public ArrayList<String> obtenerValoresPaciente() {
		ArrayList<String> pacienteValores = new ArrayList<String>();
		pacienteValores.add(String.valueOf(getIdUsuario()));
		return pacienteValores;
	}

	public ArrayList<String> obtenerCamposPaciente() {
		ArrayList<String> pacienteCampos = new ArrayList<String>();
		pacienteCampos.add("idUsuario");
		return pacienteCampos;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + String.valueOf(getIdPaciente()) + ", idUsuario=" + String.valueOf(getIdUsuario())
				+ "]";
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Paciente) {
			Paciente otroPaciente = (Paciente) objeto;
			return Objects.equals(this.getNombreUsuario(), otroPaciente.getNombreUsuario())
					&& Objects.equals(getContrasenia(), otroPaciente.getContrasenia())
					&& Objects.equals(getNombre(), otroPaciente.getNombre())
					&& Objects.equals(getApellido(), otroPaciente.getApellido())
					&& Objects.equals(getEmail(), otroPaciente.getEmail())
					&& Objects.equals(getFechaNacimiento(), otroPaciente.getFechaNacimiento())
					&& Objects.equals(getDni(), otroPaciente.getDni());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getNombreUsuario(), this.getContrasenia(), this.getNombre(), this.getApellido(),
				this.getEmail(), this.getFechaNacimiento(), this.getDni());
	}
}
