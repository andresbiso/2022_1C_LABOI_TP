package datos.tablemanager;

import java.util.ArrayList;

import datos.exception.TableManagerException;

public class MedicoTableManager extends TableManager {
	
	public MedicoTableManager() {
		super("MEDICO");
	}

	// DDL
	public boolean crearTabla() throws TableManagerException {
		ArrayList<String> campoTipoList = new ArrayList<>();
		campoTipoList.add("idMedico INTEGER AUTO_INCREMENT NOT NULL");
		campoTipoList.add("idUsuario INTEGER NOT NULL");
		campoTipoList.add("costoConsulta INTEGER NOT NULL");
		campoTipoList.add("PRIMARY KEY (idMedico)");
		campoTipoList.add("FOREIGN KEY (idUsuario) REFERENCES USUARIO (idUsuario) ON DELETE NO ACTION ON UPDATE CASCADE");
		return crearTabla(campoTipoList);
	}
}
