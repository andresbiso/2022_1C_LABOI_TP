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

@SuppressWarnings("serial")
public class FechaFuturaSeleccionPanel extends JPanel implements ItemListener {
	private JComboBox<String> anioComboBox;
	private JComboBox<String> mesComboBox;
	private JComboBox<String> diaComboBox;
	private Calendar fechaInicio = Calendar.getInstance();
	
	private final String mensajeLabelDefault = "Seleccionar Fecha: ";
	private final int maxCantMesesDefault = 4;
	
	public FechaFuturaSeleccionPanel() {
		inicializarComponent(mensajeLabelDefault, maxCantMesesDefault);
	}
	
	public FechaFuturaSeleccionPanel(String mensajeLabel) {
		if  (mensajeLabel.isBlank()) {
			mensajeLabel = mensajeLabelDefault;
		}
		
		inicializarComponent(mensajeLabel, maxCantMesesDefault);
	}
	
	public FechaFuturaSeleccionPanel(int maxCantMeses) {
		if (maxCantMeses < 0) {
			maxCantMeses = maxCantMesesDefault;
		}
		
		inicializarComponent(mensajeLabelDefault, maxCantMeses);
	}
	
	public FechaFuturaSeleccionPanel(String mensajeLabel, int maxCantMeses) {
		if  (mensajeLabel.isBlank()) {
			mensajeLabel = mensajeLabelDefault;
		}
		
		if (maxCantMeses < 0) {
			maxCantMeses = maxCantMesesDefault;
		}
		
		inicializarComponent(mensajeLabel, maxCantMeses);
	}

	public void inicializarComponent(String mensajeLabel, int maxCantMeses) {
		this.setLayout(new FlowLayout());
		JLabel fechaInicioLabel = new JLabel(mensajeLabel, SwingConstants.RIGHT);
		JPanel fechasPanel = new JPanel(new FlowLayout());
		anioComboBox = new JComboBox<String>();
		rellenarAniosComboBox(anioComboBox, maxCantMeses);
		int currentYearIndex = 0;
		int cantAnios = anioComboBox.getItemCount();
		String anioActual = String.valueOf(fechaInicio.get(Calendar.YEAR));
		while (currentYearIndex < cantAnios) {
			String anioCombo = anioComboBox.getItemAt(currentYearIndex);
			if (anioCombo.equals(anioActual)) {
				break;
			}
			currentYearIndex++;
		}
		anioComboBox.setSelectedIndex(currentYearIndex);
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

	private void rellenarAniosComboBox(JComboBox<String> aniosComboBox, int maxCantMeses) {
		int anioActual = fechaInicio.get(Calendar.YEAR);
//		int mesActual = fechaInicio.get(Calendar.MONTH);
		int cantAnios = (int)Math.ceil(maxCantMeses / 12.0);
		int anioFuturo = anioActual;
		if (cantAnios > 0) {
			anioFuturo = anioFuturo + cantAnios;
		}

		aniosComboBox.removeAllItems();

		for (int anioContador = anioActual; anioContador <= anioFuturo; anioContador++) {
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