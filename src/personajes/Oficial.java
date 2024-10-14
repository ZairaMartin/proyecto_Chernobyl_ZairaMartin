package personajes;

import java.io.PrintWriter;

import generico.*;

public class Oficial extends KGB {

	// Sin atributos

	// Constructores
	public Oficial() { // Por defecto
		super();
	}

	public Oficial(String nombre, int idCelda, int turno, char marca) { // Parametrizado
		super(nombre, idCelda, turno, marca);
	}

	// metodo abstracto del abuelo(personaje)
	@Override
	public void realizarAcciones(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		if (this.getTurno() == central.getTurno()) {
			
			this.accionMover(pw);
			this.destruirLlave(pw);
			
			this.accionCatalogar(pw);
		}
		//setTurno(getTurno() + 1);
	}

	public void accionCatalogar(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		celda = central.getCelda(f, c); // nos colocamos en la celda de la CN en la posicion en la qeu este nuestro personaje
		int i;
		for (i = 0; i < celda.getLPersonajes().size(); i++) { // de tope el tamaño del arrayList lPersonajes de la clase celda
																
			Operador operador; // creamos objeto de la clase operador
			if (celda.getLPersonajes().get(i) instanceof Operador) {
				
				operador = (Operador) celda.getLPersonajes().get(i);
				//casteamos para qeu funcione
				if (operador.isCatalogado() == false) { 
					
					operador.setCatalogado();  //catalogamos

					// clasificar los oficiales que va catalogando
					// operador instanceof personaje == true si ese operador es de ese tipo de personaje
					
					if (operador instanceof Cientifico) {
						//System.out.println(this.getNombre() + ": CIENTIFICO#" + operador.getNombre() + "#"+ operador.getIdCeldaActual() + "#" + operador.getMarca());
						pw.println(this.getNombre() + Constantes.CIENTIFICO_CATALOGADO + operador.getNombre() + Constantes.ALMOHADILLA + operador.getIdCeldaActual() + Constantes.ALMOHADILLA + operador.getMarca());
						
					}
					if (operador instanceof Bombero) {
						//System.out.println(this.getNombre() + ": BOMBERO#" + operador.getNombre() + "#"+ operador.getIdCeldaActual() + "#" + operador.getMarca());
						pw.println(this.getNombre() + Constantes.BOMBERO_CATALOGADO + operador.getNombre() + Constantes.ALMOHADILLA + operador.getIdCeldaActual() + Constantes.ALMOHADILLA + operador.getMarca());
					}
					if (operador instanceof Minero) {
						//System.out.println(this.getNombre() + ": MINERO#" + operador.getNombre() + "#"+ operador.getIdCeldaActual() + "#" + operador.getMarca());
						pw.println(this.getNombre() + Constantes.MINERO_CATALOGADO + operador.getNombre() + Constantes.ALMOHADILLA + operador.getIdCeldaActual() + Constantes.ALMOHADILLA + operador.getMarca());
					}

				}
			}

		}

	}
	
	
	//ACCION DESTRUIR LLAVE
	public void destruirLlave(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda = central.getCelda(f, c);
		
		if(celda.getLlave() != null) {
			celda.setLlave(null);		//DESTRUIMOS LA LLAVE
			
			//System.out.println(this.getNombre()+Constantes.LLAVE_DESTRIUDA+this.getIdCeldaActual()+Constantes.PUNTO);  //MOSTRAMOS MENSAJE DE CONFIRMACION
			pw.println(this.getNombre()+Constantes.LLAVE_DESTRIUDA+this.getIdCeldaActual()+Constantes.PUNTO);  //MOSTRAMOS MENSAJE DE CONFIRMACION
		}
	}

	
}
