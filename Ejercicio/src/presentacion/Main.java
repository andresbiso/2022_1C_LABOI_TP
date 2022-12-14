package presentacion;

import aplicacion.DatosModeloCreator;
import aplicacion.exception.DatosModeloCreatorException;

public class Main {

	public static void main(String[] args) {
		
		DatosModeloCreator datosModeloCreator = new DatosModeloCreator();
		try {
			datosModeloCreator.inicializarModelo();
		} catch (DatosModeloCreatorException e) {
			System.err.println(e.getMessage());
		}
		
		PanelManager manager = new PanelManager();
		manager.mostrarLogin();
		manager.showFrame();
	}
}
