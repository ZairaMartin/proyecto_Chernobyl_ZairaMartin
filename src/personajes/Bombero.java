package personajes;

import java.io.PrintWriter;

import generico.*;

public class Bombero extends Operador {
	//Atributos
	private int bateriaRefrigerador;
	
	//Constructores
	public Bombero(){		//Por defecto inicializada a 5
		super();
		bateriaRefrigerador = 5;
	}
	
	public Bombero(String nombre, int idCelda, int turno, char marca){		//Parametrizada
		super(nombre, idCelda, turno, marca);
		this.bateriaRefrigerador = 5;
	}

	//metodos propios
	//getters y setters
	public int getBateriaRefrigerador(){
		return bateriaRefrigerador;
	}
	
	public void setBateriaRefrigerador(int bateriaRefrigerador){
		this.bateriaRefrigerador = bateriaRefrigerador;
	}
	
	
	
	
	//metodo abstracto del abuelo(personaje)
	@Override
	public void realizarAcciones(PrintWriter pw){
		
		CentralNuclear central = CentralNuclear.getInstancia();
		if (this.getTurno() == central.getTurno()) {
			if(comprobarPuertaSalida()!=true) {
				
				if(this.bateriaRefrigerador==0) {
					accionRecargarRefrigerador(pw);
					
				}else{
					this.accionMover(pw);
					this.accionRefrigerar();
				}
			}
			
			//setTurno(getTurno()+1);
		}
	}
	
	
	public void accionRefrigerar() {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda=central.getCelda(f, c); //celda recoge las coordenadas de esa celda en la central
		
		if(celda.isRefrigerada()==false) {  //Si la celda no está refrigerada...
			celda.setRefrigerada(true);  // la refrigeramos
		}
		bateriaRefrigerador--;  //decrementamos la bateria del bombero
	}
	
	
	private void accionRecargarRefrigerador(PrintWriter pw) {   //private porque no puede llamarse desde fuera de esta clase		
		this.setBateriaRefrigerador(Constantes.CINCO);
		//System.out.println(this.getNombre()+Constantes.RECARGA_BATERIA);
		pw.println(this.getNombre()+Constantes.RECARGA_BATERIA);
		
	}
	
	
	

	
	
}
