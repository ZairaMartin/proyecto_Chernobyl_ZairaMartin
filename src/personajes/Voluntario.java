package personajes;

import java.io.PrintWriter;

import generico.*;

public class Voluntario extends KGB {
	// Sin atributos

	// constructores
	public Voluntario() { // Por defecto
		super();
	}

	public Voluntario(String nombre, int idCelda, int turno, char marca) { // Parametrizado
		super(nombre, idCelda, turno, marca);
	}

	// metodo abstracto del abuelo(personaje)
	@Override
	public void realizarAcciones(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		if (this.getTurno() == central.getTurno()) {
			this.accionMover(pw);
			this.accionCatalogar(pw);
			//setTurno(getTurno() + 1);
		}
	}

	public void accionCatalogar(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda = central.getCelda(f, c); //nos colocamos en la celda de la central en la que esta el personaje
		int i;
		for(i = 0; i<celda.getLPersonajes().size();i++) {  //hasta el tamaño del arrayList de personajes
			Operador operador;  
			if (celda.getLPersonajes().get(i) instanceof Operador) {
				operador = (Operador) celda.getLPersonajes().get(i);
				
				if(operador.isCatalogado()==false) {  //si no esta catalogado
					operador.setCatalogado(); //catalogamos
					//System.out.println(this.getNombre()+": "+operador.getNombre()+"#"+operador.getIdCeldaActual()+"#"+operador.getMarca());  // NombreVoluntario: nombre#celda#MarcaOperador
					pw.println(this.getNombre()+Constantes.DOS_PUNTOS+operador.getNombre()+Constantes.ALMOHADILLA+operador.getIdCeldaActual()+Constantes.ALMOHADILLA+operador.getMarca());  // NombreVoluntario: nombre#celda#MarcaOperador
				}
		
			}
		
		}
		
		
	}

	
}
