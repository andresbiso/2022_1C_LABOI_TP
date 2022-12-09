package aplicacion.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aplicacion.exception.TextoSoloLetrasException;
import aplicacion.exception.TextoVacioException;
import aplicacion.exception.TextoValidatorException;

public class TextoValidator {
	public static void ValidarLongitudMaxima(String texto, int maximo) throws TextoValidatorException {
		if (maximo < 0) {
			throw new TextoValidatorException("El maximo ingresado no es un valor válido");
		}

		if(texto.length() > maximo) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "supera los " + maximo + " caracteres");
		}
	}
	
	public static void ValidarLongitudMinima(String texto, int minimo) throws TextoValidatorException {
		if (minimo < 0) {
			throw new TextoValidatorException("El mínimo ingresado no es un valor válido");
		}
		
		if(texto.length() < minimo) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "no supera los " + minimo + " caracteres");
		}
	}
	
	public static void ValidarLongitudDistinta(String texto, int maxmin) throws TextoValidatorException {
		if (maxmin < 0) {
			throw new TextoValidatorException("El máximo y mínimo ingresado no es un valor válido");
		}

		if(texto.length() != maxmin) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "es distinta de " + maxmin + " caracteres");
		}
	}
	
	public static void ValidarSoloEspacios(String texto) throws TextoValidatorException {
		if(texto.trim().length() != 0) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "no tiene solamente espacios");
		}
	}
	
	public static void ValidarNoSoloEspacios(String texto) throws TextoValidatorException {
		if(texto.trim().length() == 0) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "tiene solamente espacios");
		}
	}
	
	public static void ValidarTextoVacio(String texto) throws TextoVacioException {
		if(texto.length() != 0) {
			String textoError = getTextoError(texto);
			throw new TextoVacioException("La entrada" + textoError + "no está vacía");
		}
	}
	
	public static void ValidarTextoNoVacio(String texto) throws TextoVacioException {
		if(texto.length() == 0) {
			String textoError = getTextoError(texto);
			throw new TextoVacioException("La entrada" + textoError + "está vacía");
		}
	}
	
	
	public static void ValidarSoloLetras(String texto) throws TextoSoloLetrasException {
		if (!Pattern.matches("[a-zA-Z]+", texto)) {
			String textoError = getTextoError(texto);
			throw new TextoSoloLetrasException("La entrada" + textoError + "no contiene solo letras");
		}
	}
	
	public static void ValidarNumerico(String texto) throws TextoValidatorException {
		try {
			Integer.valueOf(texto);
		} catch (NumberFormatException e) {
			String textoError = getTextoError(texto);
			throw new TextoValidatorException("La entrada" + textoError + "no es un número");
		}
	}
	
	public static void ValidarEmail(String texto) throws TextoValidatorException {
		// https://www.rfc-editor.org/rfc/rfc5322
		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(texto);
	    if (!matcher.matches()) {
			String textoError = getTextoError(texto);
	    	throw new TextoValidatorException("La entrada" + textoError + "no es un email válido");	
	    }
	}
	
	private static String getTextoError(String texto) {
		return (texto == null || texto.trim().length() == 0) ? " " : " \"" + texto + "\" ";
	}
}
