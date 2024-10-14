package personajes;

import java.io.PrintWriter;
import java.util.ArrayList;

import generico.Celda;
import generico.CentralNuclear;
import generico.Constantes;
import generico.Utilidad;

public class Robot extends Personaje{

	//ATRIBUTOS
	private ArrayList <Integer> lCeldas;
	
	
	public Robot() {
		super();
		this.lCeldas = new ArrayList<>();
	}
	
	public Robot(String nombre, int idCelda, int turno, char marca) {
		super(nombre, idCelda, turno, marca);
		this.lCeldas = new ArrayList <>();
	}
	
	
	public ArrayList <Integer> getLCeldas(){
		return this.lCeldas;
	}
	
	public void setLCeldas(ArrayList <Integer> lCeldas){
		this.lCeldas = lCeldas;
	}
	
	//METODO PARA DESCIFRAR LA RUTA 
	public void descifrarRuta(String rutaCifrada){
		ArrayList <Character> lRutaDescifrada = new ArrayList<>();
		
		for(char r:rutaCifrada.toCharArray()) {
			if(r==Constantes.N || r==Constantes.S || r==Constantes.O || r==Constantes.E) {
				lRutaDescifrada.add(r);
			}
		}
		setLRuta(lRutaDescifrada);
	}
	
	//METODO PARA REGISTRAR LAS CELDAS VISITADAS POR EL ROBOT
	public void mostrarCeldasVisitadas(PrintWriter pw) {
		//System.out.println(); //SALTO DE LINEA
		//System.out.println(Constantes.INFORME+this.getNombre());
		pw.println(Constantes.INFORME+this.getNombre());
		for(Integer c:lCeldas) {
			//System.out.print(Constantes.AR + c + Constantes.RAY);
			pw.print(Constantes.AR + c + Constantes.RAY);
		}
		pw.println(Constantes.NADA);
	}
	
	
	//ACCION REGISTRAR MOVIMIENTO
	public ArrayList<Integer> registrarMovimiento(){
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda = central.getCelda(f, c);

		if(this.lCeldas.isEmpty()){  // SI ESTA VACIA SE GUARDA LA POSICION
			this.lCeldas.add(celda.getIdCelda());
		}else{
			if(this.lCeldas.get(lCeldas.size()-1) != celda.getIdCelda()){  // SI LA ULTUMA CELDA GUARDADA ES DISTINTA A LA IDcELDAaCTUAL, SE GUARDA
				
				this.lCeldas.add(celda.getIdCelda());
			}else{ 
				this.lCeldas.add(-1);		//SI NO SE MUEVE GUARDAMOS -1
			}
		}

		return this.lCeldas;  
	}

	
	
	
	//ACCION ESCANEAR
	public void escanear(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
	
		//CALCULAMOS FILA Y COLUMNA DE LA POSICION ACTUAL DEL ROBOT
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		//celda = central.getCelda(f, c);
		
		//ARRAY aUXILIAR
		ArrayList <Celda> celdasAdyacentes = central.getAdyacentes(f, c);
		
		//CONTADOR DE PERSONAJES
		int numPersonajes = 0;

		for(Celda cAdy : celdasAdyacentes) {
			numPersonajes += cAdy.longitudLPersonajes();
		}
		
		if(numPersonajes > 0) {  //CONTROLAMOS QUE MUESTRE CUANDO HAYA PERSONAJES DETECTADOS
			//System.out.println(this.getNombre() + Constantes.DETECTADAS + numPersonajes + Constantes.INFLUENCIA);			
			pw.println(this.getNombre() + Constantes.DETECTADAS + numPersonajes + Constantes.INFLUENCIA);			
		}
		
	}
	
	
	@Override
	public void realizarAcciones(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
			this.accionMover(pw);
			this.registrarMovimiento();
			this.escanear(pw);
		
		
	}

}
