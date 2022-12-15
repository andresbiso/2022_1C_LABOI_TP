package datos.tablemanager;

import java.util.ArrayList;

import datos.exception.TableManagerException;

public class TurnoTableManager extends TableManager {
	
	public TurnoTableManager() {
		super("TURNO");
	}

	// DDL
	public boolean crearTabla() throws TableManagerException {
		ArrayList<String> campoTipoList = new ArrayList<>();
		campoTipoList.add("idTurno INTEGER AUTO_INCREMENT NOT NULL");
		campoTipoList.add("idMedico INTEGER NOT NULL");
		campoTipoList.add("fecha DATE NOT NULL");
		campoTipoList.add("horario VARCHAR(10) NOT NULL");
		campoTipoList.add("idPaciente INTEGER DEFAULT NULL");
		campoTipoList.add("asistioTurno BOOLEAN DEFAULT FALSE");
		campoTipoList.add("costo NUMERIC(8, 2) DEFAULT 0");
		campoTipoList.add("PRIMARY KEY (idTurno)");
		campoTipoList.add("FOREIGN KEY (idMedico) REFERENCES MEDICO (idMedico) ON DELETE SET NULL ON UPDATE CASCADE");
		campoTipoList.add("FOREIGN KEY (idPaciente) REFERENCES PACIENTE (idPaciente) ON DELETE SET NULL ON UPDATE CASCADE");
		return crearTabla(campoTipoList);
	}
}
