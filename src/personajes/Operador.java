package personajes;



import java.io.PrintWriter;

import generico.*;
import generico.CentralNuclear;

public abstract class Operador extends Personaje {

	//Atributo
	private boolean catalogado;
	private boolean enPuertaDeSalida;
	
	//Constructores
	public Operador(){  //Por defecto inicializado en false
		super();
		catalogado = false;
		enPuertaDeSalida = false;
	}
	
	public Operador(String nombre, int idCelda, int turno, char marca){		//Parametrizado
		super(nombre, idCelda, turno, marca);
		this.catalogado=false; //por defecto qie se creen false
		this.enPuertaDeSalida = false; //NO PODEMOS CREAR UN OPERADOR EN PUERTA DE SALIDA
	}
	
	
	//Metodos setters y gettesr
	public boolean isCatalogado(){
		return catalogado;
	}
	
	public void setCatalogado(){
		this.catalogado = true;
	}
	
	public boolean getEnPuertaDeSalida() {
		return this.enPuertaDeSalida;
	}
	
	public void setEnPuertaDeSalida(boolean enPuertaDeSalida) {
		this.enPuertaDeSalida = enPuertaDeSalida;
	}
	
	//Metodo abstracto del padre 
	public abstract void realizarAcciones(PrintWriter pw);
	
	
	public boolean comprobarPuertaSalida() {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda=central.getCelda(f, c); //celda recoge las coordenadas de esa celda en la central
	
		if(this.enPuertaDeSalida==false) {   //SI NO ESTA EN LA PUERTA DE SALIDA...
			
			if(celda.getPuerta() != null) {  //Hay puerta
				setEnPuertaDeSalida(true);      //PONEMOS A TRUE EL ATRIBUTO PORQUE HAY PUERTA
				return true;
			}else {
				return false;    //No hay puerta
			}
		}else {
			return true;  //DEVOLVEMOS FALSO PORQUE YA ESTA EN LA SALIDA
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
