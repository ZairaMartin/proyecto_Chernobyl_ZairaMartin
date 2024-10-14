package ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import generico.CentralNuclear;
import generico.Constantes;
import generico.Utilidad;
import personajes.Minero;
import personajes.Personaje;
import personajes.Robot;

public class Escritura {
	
	public static void cerrarFicheroSimulacion(FileWriter fw,PrintWriter pw) {
		if(pw != null) {  //cerramos printWriter
			pw.close();
		}
		
		if(fw != null) {   //cerramos filewriter
			try {
				fw.close();
			}catch(IOException e) {
				System.out.println(Constantes.ERROR_IOE);
			}
		}
	}

	public static  FileWriter abrirFlujoFW(){
		FileWriter fws = null; 
		try{
			fws = new FileWriter(Utilidad.generarNombreFichero(Constantes.NOMBRE_SIMULACION));  
		}catch(IOException e) {
			System.out.println(Constantes.ERROR_IOE);
			System.out.println(e.getMessage());
		}
		return fws;
	}
	
	
	public static PrintWriter abrirFlujoPW(FileWriter fw) {
		PrintWriter pw = null;
		pw = new PrintWriter(fw);
		return pw;
	}
	
	public static  void generarInformeRobot(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		pw.println(Constantes.INFORMEROBOTS);
		for(Personaje p : central.getlPersonajes()) {   //	BUSCAMOS EN CADA PERSONAJE DE LA LISTA 
			if(p instanceof Robot) {					// COMPROBAMOS SI ES ROBOT
				((Robot)p).mostrarCeldasVisitadas(pw);	//"cASTEAMOS" EL PERSONAJE A ROBOT PARA LLAMAR A SU MÉTODO
			}
		}
	}
	
	public static void generarInformeBombero(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		pw.println(Constantes.INFORMEBOMBEROS);
		central.mostrarCeldasRefrigeradas(pw);
	}
	
	public static void generarInformeMinero(PrintWriter pw) {
		CentralNuclear central =CentralNuclear.getInstancia();
		pw.println(Constantes.INFORMEMINERO);
		for(Personaje p:central.getlPersonajes()) {
			if(p instanceof Minero) {
		        ((Minero) p).mostrarEscombrosRecogidos(pw);
			}
		}
		
	}
	
	
	
	
	
	public static void escribirInformesEnFicheroLog() {
		//FLUJOS PARA INFORMES
		FileWriter fw = null;
		PrintWriter pw = null;
	
		try {
			fw = new FileWriter(Utilidad.generarNombreFichero(Constantes.NOMBRE_INFORME));
			pw = new PrintWriter(fw);
			
			//METODOS INFORMES
			generarInformeBombero(pw);
			generarInformeMinero(pw);
			generarInformeRobot(pw);
		}catch(IOException e) {
			System.out.println(Constantes.ERROR_IOE);
		}finally {
			if(pw != null) {
				pw.close();
			}
			
			if(fw != null) {
				try {
					fw.close();
				}catch(IOException e) {
					System.out.println(Constantes.ERROR_IOE);
				}
			}
		}
			
	}
}
