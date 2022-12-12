package aplicacion.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Turno {
	private int idTurno;
	private Medico medico;
	private Date fecha;
	private String horario;
	private Paciente paciente;
	private boolean asistioTurno;
	private float costo;
	
	public Turno() {
		this.medico = new Medico();
		this.paciente = new Paciente();
	}

	public Turno(Medico medico, Date fecha, String horario, Paciente paciente,
			boolean asistioTurno, float costo) {
		this.medico = medico;
		this.fecha = fecha;
		this.horario = horario;
		this.paciente = paciente;
		this.asistioTurno = asistioTurno;
		this.costo = costo;
	}

	public Turno(int idTurno, Medico medico, Date fecha, String horario, Paciente paciente,
			boolean asistioTurno, float costo) {
		this.idTurno = idTurno;
		this.medico = medico;
		this.fecha = fecha;
		this.horario = horario;
		this.paciente = paciente;
		this.asistioTurno = asistioTurno;
		this.costo = costo;
	}
	
	public Turno(Medico medico, Date fecha, String horario) {
		this.medico = medico;
		this.fecha = fecha;
		this.horario = horario;
		this.paciente = new Paciente();
		this.asistioTurno = false;
		this.costo = 0;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean getAsistioTurno() {
		return asistioTurno;
	}

	public void setAsistioTurno(boolean asistioTurno) {
		this.asistioTurno = asistioTurno;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public HashMap<String, String> obtenerHashMapTurno() {
		HashMap<String, String> turnoHashMap = new HashMap<String, String>();
		int idMedico = medico != null ? medico.getIdMedico() : 0;
		String idMedicoValue = idMedico > 0 ? "'" + String.valueOf(idMedico) + "'" : "NULL";
		turnoHashMap.put("idMedico", idMedicoValue);
		turnoHashMap.put("fecha", "'" + fecha.toString() + "'");
		turnoHashMap.put("horario", "'" + horario + "'");
		int idPaciente = paciente != null ? paciente.getIdPaciente() : 0;
		String idPacienteValue = idPaciente > 0 ? "'" + String.valueOf(idPaciente) + "'" : "NULL";
		turnoHashMap.put("idPaciente", idPacienteValue);
		turnoHashMap.put("asistioTurno", "'" + String.valueOf(asistioTurno) + "'");
		turnoHashMap.put("costo", "'" + String.valueOf(costo) + "'");
		return turnoHashMap;
	}

	public ArrayList<String> obtenerValoresTurno() {
		ArrayList<String> turnoValores = new ArrayList<String>();
		turnoValores.add(String.valueOf(medico.getIdMedico()));
		turnoValores.add("'" + fecha.toString() + "'");
		turnoValores.add("'" + horario + "'");
		int idPaciente = paciente.getIdPaciente();
		String idPacienteValue = idPaciente > 0 ? "'" + String.valueOf(idPaciente) + "'" : "NULL";
		turnoValores.add(idPacienteValue);
		turnoValores.add(String.valueOf(asistioTurno));
		turnoValores.add(String.valueOf(costo));
		return turnoValores;
	}

	public ArrayList<String> obtenerCamposTurno() {
		ArrayList<String> turnoCampos = new ArrayList<String>();
		turnoCampos.add("idMedico");
		turnoCampos.add("fecha");
		turnoCampos.add("horario");
		turnoCampos.add("idPaciente");
		turnoCampos.add("asistioTurno");
		turnoCampos.add("costo");
		return turnoCampos;
	}

	@Override
	public String toString() {
		return "Turno [idTurno=" + String.valueOf(idTurno) + ", idMedico=" + String.valueOf(medico.getIdMedico())
				+ ", fecha=" + fecha.toString() + ", horario=" + horario + ", idPaciente=" + String.valueOf(paciente.getIdPaciente())
				+ ", asistioTurno=" + String.valueOf(asistioTurno) + ", costo=" + String.valueOf(costo) + "]";
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Turno) {
			Turno otroTurno = (Turno) objeto;
			int turnoIdMedico = getMedico() != null ? getMedico().getIdMedico() : 0;
			int otroTurnoIdMedico = otroTurno.getMedico() != null ? otroTurno.getMedico().getIdMedico() : 0;
			int turnoIdPaciente = getPaciente() != null ? getPaciente().getIdPaciente() : 0;
			int otroTurnoIdPaciente = otroTurno.getPaciente() != null ? otroTurno.getPaciente().getIdPaciente() : 0;
			return Objects.equals(turnoIdMedico, otroTurnoIdMedico)
					&& Objects.equals(getFecha(), otroTurno.getFecha())
					&& Objects.equals(getHorario(), otroTurno.getHorario())
					&& Objects.equals(turnoIdPaciente, otroTurnoIdPaciente)
					&& Objects.equals(getAsistioTurno(), otroTurno.getAsistioTurno())
					&& Objects.equals(getCosto(), otroTurno.getCosto());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(medico.getIdMedico(), fecha, horario, paciente.getIdPaciente(), asistioTurno, costo);
	}
	
}
