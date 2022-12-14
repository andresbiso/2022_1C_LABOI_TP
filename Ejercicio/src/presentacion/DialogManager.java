package presentacion;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DialogManager extends JFrame {
	public static void MostrarMensajeError(Component componente) {
		JOptionPane.showMessageDialog(componente, "Se ha producido un error inesperado",
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void MostrarMensajeError(Component componente, String mensaje) {
		JOptionPane.showMessageDialog(componente, mensaje,
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void MostrarMensajeAdvertencia(Component componente, String mensaje) {
		JOptionPane.showMessageDialog(componente, mensaje,
				"Advertencia", JOptionPane.WARNING_MESSAGE);
	}
	
	public static int MostrarMensajeConfirmacion(Component componente, String mensaje) {
		return JOptionPane.showConfirmDialog(componente,
				mensaje, "¿Está seguro?", JOptionPane.YES_NO_OPTION);
	}
	
	public static void MostrarMensajeExito(Component componente, String mensaje) {
		 JOptionPane.showMessageDialog(componente, mensaje,
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void MostrarMensajeInformacion(Component componente, String mensaje) {
		 JOptionPane.showMessageDialog(componente, mensaje,
				"Información", JOptionPane.INFORMATION_MESSAGE);
	}
}
