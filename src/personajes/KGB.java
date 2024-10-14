package personajes;

import java.io.PrintWriter;

import generico.Constantes;

public abstract class KGB extends Personaje {
	//Sin atributos
	//Constructores
	public KGB(){		//por defecto
		super();
	}

	public KGB(String nombre, int idCelda, int turno, char marca){		//Parametrizado
		super(nombre, idCelda, turno, marca);
	}
	
	//ni getters ni setters
	//metodo abstracto para pasarle al hijo/nieto
	public abstract void realizarAcciones(PrintWriter pw);
	
	//metodo catalogar
	public void catalogar(PrintWriter pw){
		//System.out.println(getNombre()+Constantes.CATALOGANDO_OPERADOR);
		pw.println(getNombre()+Constantes.CATALOGANDO_OPERADOR);
	}
	
	
	
	
	
}
