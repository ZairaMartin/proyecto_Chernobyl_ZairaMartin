package generico;

import java.time.LocalDate;

public class Utilidad {

	public static int calcularFilas(int idCelda) {
		int fila;
		
		// Las filas es el cociente de la division de la posicion actual entre el numero de filas
		fila = idCelda / Constantes.FILAS;		
		return fila;
	}
	
	public static int calcularColumnas(int idCelda) {
		int col;
		
		// Las filas es el resto de la division de la posicion actual entre el numero de columnas
		col = idCelda % Constantes.COLUMNAS;		
		return col;
	}
	
	public static String generarNombreFichero(String nombre) {
		LocalDate d = LocalDate.now();
		int dia = d.getDayOfMonth();
		int mes = d.getMonthValue();
		if(nombre.equalsIgnoreCase(Constantes.NOMBRE_SIMULACION)) {
			nombre = Constantes.SIMULACION+dia+mes+Constantes.LOG;
		}else{
			nombre = Constantes.INFORMES+dia+mes+Constantes.LOG;
		}
		return nombre;
	}
	
}
