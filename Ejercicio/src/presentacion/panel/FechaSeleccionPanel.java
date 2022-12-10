package presentacion.panel;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FechaSeleccionPanel extends JPanel implements ItemListener {
	private JComboBox<String> anioComboBox;
	private JComboBox<String> mesComboBox;
	private JComboBox<String> diaComboBox;
	private Calendar fechaInicio = Calendar.getInstance();
	
	private final String mensajeLabelDefault = "Seleccionar Fecha: ";
	private final int maxCantAniosDefault = 120;
	
	public FechaSeleccionPanel() {
		inicializarComponent(mensajeLabelDefault, maxCantAniosDefault);
	}
	
	public FechaSeleccionPanel(String mensajeLabel) {
		if  (mensajeLabel.isBlank()) {
			mensajeLabel = mensajeLabelDefault;
		}
		
		inicializarComponent(mensajeLabel, maxCantAniosDefault);
	}
	
	public FechaSeleccionPanel(String mensajeLabel, int maxCantAnios) {
		if  (mensajeLabel.isBlank()) {
			mensajeLabel = mensajeLabelDefault;
		}
		
		if (maxCantAnios < 0) {
			maxCantAnios = maxCantAniosDefault;
		}
		
		inicializarComponent(mensajeLabel, maxCantAnios);
	}

	public void inicializarComponent(String mensajeLabel, int maxCantAnios) {
		this.setLayout(new FlowLayout());
		JLabel fechaInicioLabel = new JLabel(mensajeLabel, SwingConstants.RIGHT);
		JPanel fechasPanel = new JPanel(new FlowLayout());
		anioComboBox = new JComboBox<String>();
		rellenarAniosComboBox(anioComboBox, maxCantAnios);
		anioComboBox.setSelectedIndex(anioComboBox.getItemCount() - 1);
		mesComboBox = new JComboBox<String>();
		rellenarMesesComboBox(mesComboBox);
		mesComboBox.setSelectedIndex(fechaInicio.get(Calendar.MONTH));
		diaComboBox = new JComboBox<String>();
		rellenarDiasComboBox(fechaInicio, diaComboBox, mesComboBox);
		diaComboBox.setSelectedItem(Integer.toString(fechaInicio.get(Calendar.DATE)));
		anioComboBox.addItemListener(this);
		mesComboBox.addItemListener(this);
		diaComboBox.addItemListener(this);
		fechasPanel.add(fechaInicioLabel);
		fechasPanel.add(diaComboBox);
		fechasPanel.add(mesComboBox);
		fechasPanel.add(anioComboBox);
		this.add(fechasPanel);
	}

	private void rellenarAniosComboBox(JComboBox<String> aniosComboBox, int maxCantAnios) {
		int anioActual = fechaInicio.get(Calendar.YEAR);
		int anioBase = anioActual - maxCantAnios;
		
		aniosComboBox.removeAllItems();

		for (int anioContador = anioBase; anioContador <= anioActual; anioContador++) {
			aniosComboBox.addItem(Integer.toString(anioContador));
		}
	}

	private void rellenarMesesComboBox(JComboBox<String> mesesComboBox) {
		mesesComboBox.removeAllItems();
		for (int mesesContador = 1; mesesContador <= 12; mesesContador++) {
			mesesComboBox.addItem(Integer.toString(mesesContador));
		}
	}

	private void rellenarDiasComboBox(Calendar fechaEn, JComboBox<String> diasComboBox, JComboBox<String> mesesComboBox) {
		diasComboBox.removeAllItems();
		fechaEn.set(Calendar.MONTH, mesesComboBox.getSelectedIndex());
		int ultimoDia = fechaInicio.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int diasContador = 1; diasContador <= ultimoDia; diasContador++) {
			diasComboBox.addItem(Integer.toString(diasContador));
		}
	}

	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == anioComboBox && event.getStateChange() == ItemEvent.SELECTED) {
			int anio = Integer.parseInt((String) anioComboBox.getSelectedItem());
			int mes = Integer.parseInt((String) mesComboBox.getSelectedItem());
			fechaInicio.set(Calendar.YEAR, anio);
			fechaInicio.set(Calendar.MONTH, mes);
			rellenarDiasComboBox(fechaInicio, diaComboBox, mesComboBox);
		} else if (event.getSource() == mesComboBox && event.getStateChange() == ItemEvent.SELECTED) {
			int mes = Integer.parseInt((String) mesComboBox.getSelectedItem());
			fechaInicio.set(Calendar.MONTH, mes);
			rellenarDiasComboBox(fechaInicio, diaComboBox, mesComboBox);
		} else if (event.getSource() == diaComboBox && event.getStateChange() == ItemEvent.SELECTED) {
			int dia = Integer.parseInt((String) diaComboBox.getSelectedItem());
			fechaInicio.set(Calendar.DATE, dia);
		}
	}
	
	public String getFecha() {
		String anio = anioComboBox.getSelectedItem().toString();
		String mes = mesComboBox.getSelectedItem().toString();
		String dia = diaComboBox.getSelectedItem().toString();
		return String.join("-", anio, mes, dia);
	}
	
	public void setFecha(Date fecha) {
		 LocalDate currentDate = fecha.toLocalDate();
		 String anio = String.valueOf(currentDate.getYear());
		 String mes = String.valueOf(currentDate.getMonth().ordinal() + 1);
		 String dia = String.valueOf(currentDate.getDayOfMonth());
	     anioComboBox.setSelectedItem(anio);
	     mesComboBox.setSelectedItem(mes);
	     diaComboBox.setSelectedItem(dia);
	}
}