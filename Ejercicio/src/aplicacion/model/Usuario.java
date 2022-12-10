package aplicacion.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import aplicacion.enums.UsuarioTipo;

public class Usuario {

	private int idUsuario;
	private String nombreUsuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNacimiento;
	private int dni;
	private UsuarioTipo usuarioTipo;

	public Usuario() {
	}
	
	public Usuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(String nombreUsuario, String contrasenia, String nombre, String apellido, String email,
			Date fechaNacimiento, int dni, UsuarioTipo usuarioTipo) {
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.setUsuarioTipo(usuarioTipo);
	}

	public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String nombre, String apellido,
			String email, Date fechaNacimiento, int dni, UsuarioTipo usuarioTipo) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.setUsuarioTipo(usuarioTipo);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}
	
	public String getNombreCompleto() {
		return apellido + "," + nombre;
	}
	
	public void setUsuario(Usuario usuario) {
		this.setIdUsuario(usuario.getIdUsuario());
		this.setNombreUsuario(usuario.getNombreUsuario());
		this.setNombre(usuario.getNombre());
		this.setApellido(usuario.getApellido());
		this.setEmail(usuario.getEmail());
		this.setFechaNacimiento(usuario.getFechaNacimiento());
		this.setDni(usuario.getDni());
		this.setUsuarioTipo(usuario.getUsuarioTipo());
	}

	public HashMap<String, String> obtenerHashMapUsuario() {
		HashMap<String, String> usuarioHashMap = new HashMap<String, String>();
		usuarioHashMap.put("nombreUsuario", "'" + nombreUsuario + "'");
		usuarioHashMap.put("contrasenia", "'" + contrasenia + "'");
		usuarioHashMap.put("nombre", "'" + nombre + "'");
		usuarioHashMap.put("apellido", "'" + apellido + "'");
		usuarioHashMap.put("email", "'" + email + "'");
		usuarioHashMap.put("fechaNacimiento", "'" + fechaNacimiento.toString() + "'");
		usuarioHashMap.put("dni", String.valueOf(dni));
		usuarioHashMap.put("usuarioTipo", String.valueOf(usuarioTipo.getTipo()));
		return usuarioHashMap;
	}

	public ArrayList<String> obtenerValoresUsuario() {
		ArrayList<String> usuarioValores = new ArrayList<String>();
		usuarioValores.add("'" + nombreUsuario + "'");
		usuarioValores.add("'" + contrasenia + "'");
		usuarioValores.add("'" + nombre + "'");
		usuarioValores.add("'" + apellido + "'");
		usuarioValores.add("'" + email + "'");
		usuarioValores.add("'" + fechaNacimiento.toString() + "'");
		usuarioValores.add(String.valueOf(dni));
		usuarioValores.add(String.valueOf(usuarioTipo.getTipo()));
		return usuarioValores;
	}

	public ArrayList<String> obtenerCamposUsuario() {
		ArrayList<String> usuarioCampos = new ArrayList<String>();
		usuarioCampos.add("nombreUsuario");
		usuarioCampos.add("contrasenia");
		usuarioCampos.add("nombre");
		usuarioCampos.add("apellido");
		usuarioCampos.add("email");
		usuarioCampos.add("fechaNacimiento");
		usuarioCampos.add("dni");
		usuarioCampos.add("usuarioTipo");
		return usuarioCampos;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + String.valueOf(getIdUsuario()) + ", nombreUsuario=" + getNombreUsuario()
				+ ", contrasenia=" + getContrasenia() + ", nombre=" + getNombre() + ", apellido=" + getApellido()
				+ ", email=" + getEmail() + ", fechaNacimiento=" + getFechaNacimiento().toString() + ", dni="
				+ String.valueOf(getDni()) + "]";
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Usuario) {
			Usuario otroUsuario = (Usuario) objeto;
			return Objects.equals(getNombreUsuario(), otroUsuario.getNombreUsuario())
					&& Objects.equals(getContrasenia(), otroUsuario.getContrasenia())
					&& Objects.equals(getNombre(), otroUsuario.getNombre())
					&& Objects.equals(getApellido(), otroUsuario.getApellido())
					&& Objects.equals(getEmail(), otroUsuario.getEmail())
					&& Objects.equals(getFechaNacimiento(), otroUsuario.getFechaNacimiento())
					&& Objects.equals(getDni(), otroUsuario.getDni());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNombreUsuario(), getContrasenia(), getNombre(), getApellido(), getEmail(),
				getFechaNacimiento(), getDni());
	}
}
