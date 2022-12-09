package aplicacion.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import aplicacion.enums.UsuarioTipo;

public class Medico extends Usuario {

	private int idMedico;
	private int costoConsulta;

	public Medico() {
	}
	
	public Medico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Medico(Usuario usuario, int costoConsulta) {
		super(usuario.getNombreUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getDni(), UsuarioTipo.Medico);
		this.setCostoConsulta(costoConsulta);
	}

	public Medico(Usuario usuario, int idMedico, int costoConsulta) {
		super(usuario.getNombreUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getDni(), UsuarioTipo.Medico);
		this.idMedico = idMedico;
		this.setCostoConsulta(costoConsulta);
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public int getCostoConsulta() {
		return costoConsulta;
	}

	public void setCostoConsulta(int costoConsulta) {
		this.costoConsulta = costoConsulta;
	}
	
	public void setMedico(Medico medico) {
		setUsuario((Usuario)medico);
		this.setIdMedico(medico.getIdMedico());
		this.setCostoConsulta(medico.getCostoConsulta());
	}

	public HashMap<String, String> obtenerHashMapMedico() {
		HashMap<String, String> medicoHashMap = new HashMap<String, String>();
		medicoHashMap.put("costoConsulta", String.valueOf(getCostoConsulta()));
		return medicoHashMap;
	}

	public ArrayList<String> obtenerValoresMedico() {
		ArrayList<String> medicoValores = new ArrayList<String>();
		medicoValores.add(String.valueOf(getIdUsuario()));
		medicoValores.add(String.valueOf(getCostoConsulta()));
		return medicoValores;
	}

	public ArrayList<String> obtenerCamposMedico() {
		ArrayList<String> medicoCampos = new ArrayList<String>();
		medicoCampos.add("idUsuario");
		medicoCampos.add("costoConsulta");
		return medicoCampos;
	}

	@Override
	public String toString() {
		return "Medico [idMedico=" + String.valueOf(getIdMedico()) + ", idUsuario=" + String.valueOf(getIdUsuario())
				+ ", costoConsulta=" + String.valueOf(getCostoConsulta()) + "]";
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Medico) {
			Medico otroMedico = (Medico) objeto;
			return Objects.equals(this.getNombreUsuario(), otroMedico.getNombreUsuario())
					&& Objects.equals(getContrasenia(), otroMedico.getContrasenia())
					&& Objects.equals(getNombre(), otroMedico.getNombre())
					&& Objects.equals(getApellido(), otroMedico.getApellido())
					&& Objects.equals(getEmail(), otroMedico.getEmail())
					&& Objects.equals(getFechaNacimiento(), otroMedico.getFechaNacimiento())
					&& Objects.equals(getDni(), otroMedico.getDni())
					&& Objects.equals(getCostoConsulta(), otroMedico.getCostoConsulta());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getNombreUsuario(), this.getContrasenia(), this.getNombre(), this.getApellido(),
				this.getEmail(), this.getFechaNacimiento(), this.getDni(), this.getCostoConsulta());
	}
}
