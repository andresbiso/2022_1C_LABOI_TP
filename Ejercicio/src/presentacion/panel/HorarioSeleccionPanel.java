package presentacion.panel;

import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public final class HorarioSeleccionPanel extends JPanel {
    private JSpinner horaSpinner;
    private JSpinner meridiemSpinner;
    private JSpinner minutoSpinner;
	private Calendar fechaActual = Calendar.getInstance();
	
	private final String mensajeLabelDefault = "Seleccionar Horario: ";
	private final int horaDefault = 8;
	private final int minutoDefault = 0;
	private final String AM = "AM";
	private final String PM = "PM";
	
    public HorarioSeleccionPanel() {
    	inicializarComponent(mensajeLabelDefault, horaDefault, minutoDefault);
    }

    public HorarioSeleccionPanel(String mensajeLabel, int hora, int minuto) {
    	if (mensajeLabel.isEmpty()) {
    		mensajeLabel = mensajeLabelDefault;
    	}
    	if (hora < 1) {
    		hora = horaDefault;
    	}
    	if (minuto < 0) {
    		minuto = minutoDefault;
    	}
    	inicializarComponent(mensajeLabel, hora, minuto);
    }

    private void inicializarComponent(String mensajeLabel, int hora, int minuto) {
    	this.setLayout(new FlowLayout());
		JLabel horariosLabel = new JLabel(mensajeLabel, SwingConstants.RIGHT);
		JPanel horariosPanel = new JPanel(new FlowLayout());

	    horaSpinner = new JSpinner();
	    horaSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
	    minutoSpinner = new JSpinner();
	    minutoSpinner.setModel(new SpinnerListModel(generarOpcionesMinutos()));
	    meridiemSpinner = new JSpinner();
	    meridiemSpinner.setModel(new SpinnerListModel(new String[] {AM, PM}));
	    actualizarHorario(hora, minuto);
	    horariosPanel.add(horariosLabel);
	    horariosPanel.add(horaSpinner);
	    horariosPanel.add(minutoSpinner);
	    horariosPanel.add(meridiemSpinner);
		this.add(horariosPanel);
    }
    
    private String[] generarOpcionesMinutos() {
    	ArrayList<String> opciones = new ArrayList<String>();
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j < 60; j++) {
    			opciones.add(String.valueOf(i) + String.valueOf(j));
    		}
    	}
    	return opciones.toArray(new String[opciones.size()]);
    }

    public void actualizarHorario(String horario) {
        if(horario.matches("\\d{1,2}\\s*\\:\\s*\\d{2}\\s+[AP]M")){
            String[] splitted = horario.split("\\:");
            int hora = Integer.parseInt(splitted[0]);
            if(PM.equals(splitted[2])){
                hora += 12;
            }
            int minuto = Integer.parseInt(splitted[1]);
            actualizarHorario(hora, minuto);
        }
    }

    public void actualizarHorario(int hora, int minuto) {
        fechaActual.set(Calendar.HOUR_OF_DAY, hora);
        fechaActual.set(Calendar.MINUTE, minuto);
        
        int horaActual = fechaActual.get(Calendar.HOUR_OF_DAY);
        int minutoActual = fechaActual.get(Calendar.MINUTE);
        if (horaActual >= 0 && horaActual < 12) {
            if (horaActual == 0) {
                horaSpinner.setValue(12);
            } else {
            	horaSpinner.setValue(horaActual);
            }
            meridiemSpinner.setValue(AM);
        } else if (horaActual >= 12 && horaActual <= 23) {
            if (horaActual == 12) {
            	horaSpinner.setValue(12);
            } else {
            	horaSpinner.setValue(horaActual - 12);
            }
            meridiemSpinner.setValue(PM);
        }

        minutoSpinner.setValue(numberFormat(minutoActual, "##"));
    }
    
    public void actualizarHorarioAlt(String horario) {
        int indexSeparadorMinutos = horario.indexOf(':');
        int indexSeparadorMeridiem = horario.indexOf(' ');
        int horaActual = Integer.valueOf(horario.substring(0, indexSeparadorMinutos));
        String minutoActual = horario.substring(indexSeparadorMinutos, indexSeparadorMeridiem);
        String meridiemActual = horario.substring(indexSeparadorMeridiem, horario.length() + 1);
        
        horaSpinner.setValue(horaActual);
        minutoSpinner.setValue(minutoActual);
        meridiemSpinner.setValue(meridiemActual);    
    }

    private static String numberFormat(long source, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format.replaceAll("#", "0"));
        return decimalFormat.format(source);
    }

    public String getHora() {
        return numberFormat(Integer.parseInt(horaSpinner.getValue().toString().trim()), "##");
    }

    public String getMinuto() {
        return numberFormat(Integer.parseInt(minutoSpinner.getValue().toString().trim()), "##");
    }

    public String getMeridiem() {
        return meridiemSpinner.getValue().toString();
    }

    public String getHorario() {
        return getHora() + ":" + getMinuto() + " " + getMeridiem();
    }
}