package aplicacion.validator;

import aplicacion.exception.NumeroEnRangoException;
import aplicacion.exception.NumeroFueraDeRangoException;
import aplicacion.exception.NumeroValidatorException;

public class NumeroValidator {
	public static void ValidarNumeroPositivo(int numero) throws NumeroValidatorException {
		if(numero < 0) {
			throw new NumeroValidatorException("El número " + numero + " no es positivo");
		}
	}
	
	public static void ValidarNumeroNegativo(int numero) throws NumeroValidatorException {
		if(numero > 0) {
			throw new NumeroValidatorException("El número " + numero + " no es negativo");
		}
	}
	
	public static void ValidarNumeroEsCero(int numero) throws NumeroValidatorException {
		if(numero != 0) {
			throw new NumeroValidatorException("El número " + numero + " no es cero");
		}
	}
	
	public static void ValidarNumeroEnRango(int numero, int minimo, int maximo) throws NumeroEnRangoException {
		if (minimo > maximo || maximo < minimo) {
			throw new NumeroEnRangoException("Los valores mínimo y máximo no son válidos");
		}
		
		if(numero < minimo || numero > maximo) {
			throw new NumeroEnRangoException("El número " + numero + " está fuera del rango de valores " + minimo + " y " + maximo);
		}
	}
	
	public static void ValidarNumeroFueraDeRango(int numero, int minimo, int maximo) throws NumeroFueraDeRangoException {
		if (minimo > maximo || maximo < minimo) {
			throw new NumeroFueraDeRangoException("Los valores mínimo y máximo no son válidos");
		}

		if(numero >= minimo || numero <= maximo) {
			throw new NumeroFueraDeRangoException("El número " + numero + " está dentro del rango de valores " + minimo + " y " + maximo);
		}
	}
	
	public static void ValidarNumeroEsPar(int numero) throws NumeroValidatorException {
		if(numero % 2 != 0) {
			throw new NumeroValidatorException("El número " + numero + " no es par");
		}
	}
	
	public static void ValidarNumeroEsImpar(int numero) throws NumeroValidatorException {
		if(numero % 2 == 0) {
			throw new NumeroValidatorException("El número " + numero + " no es impar");
		}
	}
}
