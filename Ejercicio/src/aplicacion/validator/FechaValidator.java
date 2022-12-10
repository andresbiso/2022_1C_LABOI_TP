package aplicacion.validator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import aplicacion.exception.FechaValidatorException;

public class FechaValidator {
	public static void ValidarEdadMinima(String texto, int minimo) throws FechaValidatorException {
		if (texto == null || texto.trim().length() == 0) {
			throw new FechaValidatorException("El texto ingresado no representa una fecha");
		}
		
		if (minimo < 0) {
			throw new FechaValidatorException("El mínimo ingresado no es un valor válido");
		}
		
	    Date date = Date.valueOf(texto);
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - minimo);
	    if(!calendar.getTime().after(date)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String fechaConFormato = simpleDateFormat.format(date);
			throw new FechaValidatorException("La edad definida por la fecha " + fechaConFormato + " no supera los " + minimo + " años");
		}
	}
	
	public static void ValidarEdadMaxima(String texto, int maximo) throws FechaValidatorException {
		if (texto == null || texto.trim().length() == 0) {
			throw new FechaValidatorException("El texto ingresado no representa una fecha");
		}
		
		if (maximo < 0) {
			throw new FechaValidatorException("El máximo ingresado no es un valor válido");
		}
		
	    Date date = Date.valueOf(texto);
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - maximo);
	    if(calendar.getTime().after(date)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String fechaConFormato = simpleDateFormat.format(date);
			throw new FechaValidatorException("La edad definida por la fecha " + fechaConFormato + " supera los " + maximo + " años");
		}
	}
	
//	public static void ValidarFechaFutura(String texto, int maximo) throws FechaValidatorException {
//		if (texto == null || texto.trim().length() == 0) {
//			throw new FechaValidatorException("El texto ingresado no representa una fecha");
//		}
//		
//		if (maximo < 0) {
//			throw new FechaValidatorException("El máximo ingresado no es un valor válido");
//		}
//		
//	    Date date = Date.valueOf(texto);
//	    Calendar calendar = GregorianCalendar.getInstance();
//	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - maximo);
//	    if(calendar.getTime().after(date)) {
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//	        String fechaConFormato = simpleDateFormat.format(date);
//			throw new FechaValidatorException("La edad definida por la fecha " + fechaConFormato + " supera los " + maximo + " años");
//		}
//	}
}
