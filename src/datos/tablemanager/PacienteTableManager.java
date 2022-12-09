package datos.tablemanager;

import java.util.ArrayList;

import datos.exception.TableManagerException;

public class PacienteTableManager extends TableManager {
	
	public PacienteTableManager() {
		super("PACIENTE");
	}

	// DDL
	public boolean crearTabla() throws TableManagerException {
		ArrayList<String> campoTipoList = new ArrayList<>();
		campoTipoList.add("idPaciente INTEGER AUTO_INCREMENT NOT NULL");
		campoTipoList.add("idUsuario INTEGER NOT NULL");
		campoTipoList.add("PRIMARY KEY (idPaciente)");
		campoTipoList.add("FOREIGN KEY (idUsuario) REFERENCES USUARIO (idUsuario) ON DELETE NO ACTION ON UPDATE CASCADE");
		return crearTabla(campoTipoList);
	}
}
