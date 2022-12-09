package datos.tablemanager;

import java.util.ArrayList;

import datos.exception.TableManagerException;

public class UsuarioTableManager extends TableManager {
	
	public UsuarioTableManager() {
		super("USUARIO");
	}

	// DDL
	public boolean crearTabla() throws TableManagerException {
		ArrayList<String> campoTipoList = new ArrayList<>();
		campoTipoList.add("idUsuario INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY");
		campoTipoList.add("nombreUsuario VARCHAR(64) NOT NULL UNIQUE");
		campoTipoList.add("contrasenia VARCHAR(32) NOT NULL");
		campoTipoList.add("nombre VARCHAR(32) NOT NULL");
		campoTipoList.add("apellido VARCHAR(32) NOT NULL");
		campoTipoList.add("email VARCHAR(128) NOT NULL");
		campoTipoList.add("fechaNacimiento DATE NOT NULL");
		campoTipoList.add("dni INTEGER NOT NULL UNIQUE");
		campoTipoList.add("usuarioTipo INTEGER NOT NULL");
		return crearTabla(campoTipoList);
	}
}
