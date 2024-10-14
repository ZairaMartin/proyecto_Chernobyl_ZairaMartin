package personajes;
import generico.*;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Minero extends Operador {
	private ArrayList <Integer> lEscombros;
	
	
	//Constructores
	public Minero(){			
		super();
		this.lEscombros = new ArrayList<>();
	}
	
	public Minero(String nombre, int idCelda, int turno, char marca){		//Parametrizado
		super(nombre, idCelda, turno, marca);
		this.lEscombros=new ArrayList<>();
	}
	
	//getters y setters de la segunda entrega
	public ArrayList <Integer> getlEscombros(){
		return lEscombros;
	}
	
	public void setlEscombros(ArrayList <Integer> lEscombros) {
		this.lEscombros=lEscombros;
	}
	
	//Llamamos al metodo abstracto y lo sobreescribimos
	@Override
	public void realizarAcciones(PrintWriter pw){
		
		CentralNuclear central = CentralNuclear.getInstancia();
		
		if  (this.getTurno() == central.getTurno()) {
			if(comprobarPuertaSalida()!=true) {
				this.accionMover(pw);
				this.accionDesescombrar(pw);
			}
			//setTurno(getTurno()+1);
		}
	
	}
	
		
	
	
	//------ Métodos de la segunda entrega con los escombros
	
	public void accionDesescombrar (PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda=central.getCelda(f, c); 
		if(celda.getEscombros()>0) {
			recogerEscombros();
			//mostrarEscombrosRecogidos();
			//System.out.println(getNombre()+Constantes.LLEVO+ amontonarEscombros()+Constantes.KILOS);
			pw.println(getNombre()+Constantes.LLEVO+ amontonarEscombros()+Constantes.KILOS);
		}
		
				    
	}
	
	public void mostrarEscombrosRecogidos(PrintWriter pw) {
		//System.out.println(getNombre()+Constantes.ESCOMBROS_RECOGIDOS+ amontonarEscombros());
		pw.println(getNombre()+Constantes.ESCOMBROS_RECOGIDOS+ amontonarEscombros());
	}
	
	
	public void recogerEscombros () {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda=central.getCelda(f, c); //celda recoge las coordenadas de esa celda en la central
		
		if(celda.getEscombros()>0) {
			int escombros = celda.getEscombros();
			lEscombros.add(escombros);
			celda.setEscombros(0);  //ponemos los escombros de la celda a 0
		}
	}
	
	
	
	public int amontonarEscombros () {  //método para sumar todos los kilos de escombros
		int montonEscombros=0;
		int i;
		for(i = 0; i < lEscombros.size(); i++) {
			montonEscombros += lEscombros.get(i);
		}
		return montonEscombros;
	}
	
	

}
