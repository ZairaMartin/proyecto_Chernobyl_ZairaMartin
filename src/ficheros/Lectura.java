package ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import generico.CentralNuclear;
import generico.Constantes;
import generico.Llave;
import generico.Puerta;
import personajes.Bombero;
import personajes.Cientifico;
import personajes.Minero;
import personajes.Oficial;
import personajes.Robot;
import personajes.Voluntario;

public class Lectura {
	
	public static int queElemento(String token) {
		String [] personajes = {Constantes.CENTRAL,Constantes.PUERTA,Constantes.LLAVE,Constantes.MINERO, Constantes.CIENTIFICO, Constantes.BOMBERO, Constantes.VOLUNTARIO, Constantes.OFICIAL, Constantes.ROBOT};
		int pos = -1;
		int i = 0;
		boolean enc = false;
		while(!enc && i<personajes.length){
			if(personajes[i].equalsIgnoreCase(token)) {
				enc = true;
				pos = i;
			}
			i++;
		}
		return pos;
	}
	
	public static void crearObjeto(int posicion, String [] cadena) {
		CentralNuclear central = CentralNuclear.getInstancia();

		Puerta puerta;
		Llave llave;
		Minero minero;
		Cientifico cientifico;
		Bombero bombero;
		Voluntario voluntario;
		Oficial oficial;
		Robot robot;
		
		
		switch(posicion) {
			case 0:		//central
				//INICIALIZO LA CENTRAL EN EL INICIO DEL METODO PARA VALIDAR TODAS LAS OPCIONES
				central.setTurnosNecesarios(Integer.parseInt(cadena[1]));
				break;
			case 1:		//puerta
				puerta = new Puerta(Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2]));
		        central.insPuertaSalida(puerta);
				break;
			case 2:
				llave = new Llave(Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2]));
				central.insLlaveEnCelda(llave);
				break;
			case 3:			//NOMBRE - TURNO - IDCELDA - MARCA 
				minero = new Minero(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				minero.cargarMovimientos(cadena[5].toCharArray());
				central.insPersonaje(minero);
		        central.insPersonajeEnListaPersonajes(minero);
				break;
			case 4:
				cientifico = new Cientifico(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				cientifico.cargarMovimientos(cadena[5].toCharArray());
				central.insPersonaje(cientifico);
		        central.insPersonajeEnListaPersonajes(cientifico);
				break;
			case 5:
				bombero = new Bombero(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				bombero.cargarMovimientos(cadena[5].toCharArray());
				central.insPersonaje(bombero);
		        central.insPersonajeEnListaPersonajes(bombero);
				break;
			case 6:
				voluntario = new Voluntario(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				voluntario.cargarMovimientos(cadena[5].toCharArray());
				central.insPersonaje(voluntario);
		        central.insPersonajeEnListaPersonajes(voluntario);
				break;
			case 7:
				oficial = new Oficial(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				oficial.cargarMovimientos(cadena[5].toCharArray());
				central.insPersonaje(oficial);
		        central.insPersonajeEnListaPersonajes(oficial);
				break;
			case 8:
				robot = new Robot(cadena[1], Integer.parseInt(cadena[2]), Integer.parseInt(cadena[3]), cadena[4].charAt(0));
				robot.descifrarRuta(cadena[5]);
				central.insPersonaje(robot);
		        central.insPersonajeEnListaPersonajes(robot);
				break;
				
		}
	}
	
	public static boolean cargarFichero() {
		String linea;
		int posicion;
		String [] cadena;
		String token;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(Constantes.INICIO_TXT);
			br = new BufferedReader(fr);
			linea = br.readLine();
			while(linea != null) {
				System.out.println(linea);
				cadena = linea.split(Constantes.ALMOHADILLA);  //PARTIMOS LA LINEA EN PALABRAS Y LAS GUARDAMOS EN EL ARRAY
				token = cadena[0];
				if(!linea.startsWith(Constantes.GUIONES)) {  //SI NO EMPIEZA POR --
					posicion = queElemento(token);
					crearObjeto(posicion, cadena);
				}
				
				linea= br.readLine();				
			}

		}catch(FileNotFoundException e) {
			System.out.println(Constantes.ERROR_FNFE);
			return false;
		}catch(IOException e) {
			System.out.println(Constantes.ERROR_IOE);
			return false;
		}finally {
			if(br != null) {
				try {
					br.close();
				}catch(IOException e) {
					System.out.println(Constantes.ERROR_IOE);
				}
			}
			if(fr != null) {
				try {
					fr.close();
				}catch(IOException e) {
					System.out.println(Constantes.ERROR_IOE);
				}
			}
		}	
		return true;
	}
	
}
