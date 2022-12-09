package aplicacion;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import aplicacion.enums.UsuarioTipo;
import aplicacion.exception.DatosModeloCreatorException;
import aplicacion.exception.ServiceException;
import aplicacion.model.Medico;
import aplicacion.model.Paciente;
import aplicacion.model.Turno;
import aplicacion.model.Usuario;
import aplicacion.service.MedicoService;
import aplicacion.service.PacienteService;
import aplicacion.service.TurnoService;
import datos.exception.TableManagerException;
import datos.tablemanager.MedicoTableManager;
import datos.tablemanager.PacienteTableManager;
import datos.tablemanager.TurnoTableManager;
import datos.tablemanager.UsuarioTableManager;

public class DatosModeloCreator {
	private MedicoService medicoService;
	private PacienteService pacienteService;
	private TurnoService turnoService;
	
	
	public DatosModeloCreator() {
		this.medicoService = new MedicoService();
		this.pacienteService = new PacienteService();
		this.turnoService = new TurnoService();
	}

	public void inicializarModelo() throws DatosModeloCreatorException {
		inicializarTablas();
		inicializarModeloMedico();
		inicializarModeloPaciente();
		inicializarModeloTurno();
	}
	
	private void inicializarTablas() throws DatosModeloCreatorException {
		UsuarioTableManager usuarioTableManager = new UsuarioTableManager();
		MedicoTableManager medicoTableManager = new MedicoTableManager();
		PacienteTableManager pacienteTableManager = new PacienteTableManager();
		TurnoTableManager turnoTableManager = new TurnoTableManager();

		try {
			turnoTableManager.borrarTabla();
			medicoTableManager.borrarTabla();
			pacienteTableManager.borrarTabla();
			usuarioTableManager.borrarTabla();
		} catch (TableManagerException e) {
			throw new DatosModeloCreatorException(e);
		}

		try {
			usuarioTableManager.crearTabla();
			medicoTableManager.crearTabla();
			pacienteTableManager.crearTabla();
			turnoTableManager.crearTabla();
		} catch (TableManagerException e) {
			throw new DatosModeloCreatorException(e);
		}
	}

	private void inicializarModeloMedico() throws DatosModeloCreatorException {
		ArrayList<Medico> medicosDB;
		try {
			medicosDB = medicoService.obtenerMedicos();
		} catch (ServiceException e) {
			throw new DatosModeloCreatorException(e);
		}

		if (medicosDB == null || medicosDB.size() <= 0) {
			Date nuevoMedicoFechaNac = Date.valueOf("1990-05-05");
			Date nuevoMedico2FechaNac = Date.valueOf("1979-05-04");

			Medico nuevoMedico = new Medico(new Usuario("jdiaz", "pass1234", "Jorge", "Diaz", "jorge.diaz@email.com",
					nuevoMedicoFechaNac, 12345678, UsuarioTipo.Medico), 100);
			Medico nuevoMedico2 = new Medico(new Usuario("pramirez", "superpass", "Pedro", "Ramirez",
					"p.ramirez@email.com", nuevoMedico2FechaNac, 13345678, UsuarioTipo.Medico), 1000);
			try {
				medicoService.crearMedico(nuevoMedico);
				medicoService.crearMedico(nuevoMedico2);
			} catch (ServiceException e) {
				throw new DatosModeloCreatorException(e);
			}
		}
	}
	
	private void inicializarModeloPaciente() throws DatosModeloCreatorException {
		ArrayList<Paciente> pacientesDB;
		try {
			pacientesDB = pacienteService.obtenerPacientes();
		} catch (ServiceException e) {
			throw new DatosModeloCreatorException(e);
		}

		if (pacientesDB == null || pacientesDB.size() <= 0) {
			Date nuevoPacienteFechaNac = Date.valueOf("1990-05-05");
			Date nuevoPaciente2FechaNac = Date.valueOf("1979-05-04");

			Paciente nuevoPaciente = new Paciente(new Usuario("jalvarez", "pass1234", "Jorge", "Álvarez", "jorge.alvarez@email.com",
					nuevoPacienteFechaNac, 12345688, UsuarioTipo.Paciente));
			Paciente nuevoPaciente2 = new Paciente(new Usuario("ppaz", "superpass", "Pedro", "Paz",
					"p.paz@email.com", nuevoPaciente2FechaNac, 13348878, UsuarioTipo.Paciente));
			try {
				pacienteService.crearPaciente(nuevoPaciente);
				pacienteService.crearPaciente(nuevoPaciente2);
			} catch (ServiceException e) {
				throw new DatosModeloCreatorException(e);
			}
		}
	}
	
	private void inicializarModeloTurno() throws DatosModeloCreatorException {
		ArrayList<Turno> turnosDB;
		try {
			turnosDB = turnoService.obtenerTurnos();
		} catch (ServiceException e) {
			throw new DatosModeloCreatorException(e);
		}

		if (turnosDB == null || turnosDB.size() <= 0) {
			String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
			Date nuevoTurnoFecha = Date.valueOf(fechaActual);
			
			ArrayList<Medico> medicosDB;
			try {
				medicosDB = medicoService.obtenerMedicos();
			} catch (ServiceException e) {
				throw new DatosModeloCreatorException(e);
			}

			Turno nuevoTurno = new Turno(medicosDB.get(0), nuevoTurnoFecha, "08:00AM");
			Turno nuevoTurno2 = new Turno(medicosDB.get(1), nuevoTurnoFecha, "05:00PM");
			try {
				turnoService.crearTurno(nuevoTurno);
				turnoService.crearTurno(nuevoTurno2);
			} catch (ServiceException e) {
				throw new DatosModeloCreatorException(e);
			}
		}
	}
}
