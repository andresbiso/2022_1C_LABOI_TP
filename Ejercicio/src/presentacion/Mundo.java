package presentacion;

import aplicacion.DatosModeloCreator;
import aplicacion.exception.DatosModeloCreatorException;

public class Mundo {

	public static void main(String[] args) {
		
		DatosModeloCreator datosModeloCreator = new DatosModeloCreator();
		try {
			datosModeloCreator.inicializarModelo();
		} catch (DatosModeloCreatorException e) {
			System.err.println(e.getMessage());
		}
		
		PanelManager manager = new PanelManager();
		manager.mostrarInicio();
		manager.showFrame();
	}
}
