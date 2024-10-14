package personajes;


import generico.Celda;
import generico.CentralNuclear;
import generico.Constantes;
import generico.Llave;
import generico.Puerta;
import generico.Utilidad;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

public class Cientifico extends Operador {
	//ATRIBUTOS
	private HashSet <Llave> conjuntoLlaves;
	
	//Constructores con el super
	public Cientifico(){		//Por defecto
		super();
		this.conjuntoLlaves = new HashSet <>();
	}
	
	public Cientifico(String nombre, int idCelda, int turno, char marca){		//Parametrizado
		super(nombre, idCelda, turno, marca);
		this.conjuntoLlaves = new HashSet <>();  //LO CREO VACIO PARA QUE SE RELLENE CON SUS MÉTODOS
	}

	//GETTERS Y SETTERS
	
	public HashSet<Llave> getConjuntoLlaves() {
		return conjuntoLlaves;
	}

	public void setConjuntoLlaves(HashSet<Llave> conjuntoLlaves) {
		this.conjuntoLlaves = conjuntoLlaves;
	}

	//Metodo abstracto del abuelo
	@Override
	public void realizarAcciones(PrintWriter pw){
		CentralNuclear central = CentralNuclear.getInstancia();
		if(this.getTurno() == central.getTurno()) {
			if(!comprobarPuertaSalida()) {
				this.accionMover(pw);
				this.cogerLlave(pw);
				this.abrirPuerta(pw);
			}
		}
		
	}
	
	
	
	
	
	//METODOS PARA EL HASHSET
	public  void insertarLlaveConjunto(Llave llave) {
		if(llave != null) {			
			this.conjuntoLlaves.add(llave); //INSERTAMOS LA LLAVE EN EL CONJUNTO
		}
	}
	
	public  void eliminarLlaveConjunto(Llave llave) {
		if(llave != null) {
			this.conjuntoLlaves.remove(llave);    // ELIMINAMOS LA LLAVE DEL CONJUNTO			
		}
	}
	
	//ACCION COGER LLAVES
	public void cogerLlave(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		Llave llave;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda = central.getCelda(f, c);// NOS SITUAMOS EN LA CELDA ACTUAL EN LA QUE ESTA EL PERSONAJE
		
		if(celda.getLlave() != null) {
			llave = celda.getLlave();  //GUARDAMOS LA INFORMACION DE LA LLAVE DE LA CELDA
			this.insertarLlaveConjunto(llave);//INSERTAMOS LA CELDA PARA QUE LA GUARDE EL CIENTIFICO
			central.eliminarLlave(llave);  //BORRAMOS LA LLAVE
			
			//System.out.println(getNombre()+Constantes.LLAVE_RECOGIDA+getIdCeldaActual()+Constantes.PUNTO); //MOSTRAMOS MENSAJE DE CONFIRMACION
			pw.println(getNombre()+Constantes.LLAVE_RECOGIDA+getIdCeldaActual()+Constantes.PUNTO); //MOSTRAMOS MENSAJE DE CONFIRMACION
		}
		
	}
	
	//ACCION ABRIR PUERTA
	public boolean abrirPuerta(PrintWriter pw) {
		CentralNuclear central = CentralNuclear.getInstancia();
		Celda celda;
		Puerta puerta;
		
		Iterator <Llave> it = conjuntoLlaves.iterator();
		Llave clave;
		boolean enc = false;
		
		int f = Utilidad.calcularFilas(getIdCeldaActual());
		int c = Utilidad.calcularColumnas(getIdCeldaActual());
		
		celda = central.getCelda(f, c); //NOS COLOCAMOS EN LA CELDA EN LA QUE ESTA EL CIENTIFICO
		
		if(celda.getPuerta() != null) {   //COMPROBAMOS SI HAY PUERTA
			
			puerta = celda.getPuerta();			//GUARDAMOS LOS DATOS DE LA PUERTA
			
			if(puerta.isAbierta() != true) {  //SI LA PUERTA NO ESTA ABIERTA...
				
				if(this.conjuntoLlaves != null) {     //SI EL CONJUNTO DE LLAVES TIENE CONTENIDO HACEMOS LA BUSQUEDA
					
					while(!enc && it.hasNext()) {
						
						clave = it.next();
						
						if(puerta.getIdLlave() == clave.getIdLlave()) {  //SI COINCIDE EL IDlLAVE DE LA PUERTA CON LA LLAVE ABRIMOS
							enc = true;
							puerta.setPuertaAbierta(enc);  //AQUI ABRIMOS LA PUERTA!!!!
							//System.out.println(this.getNombre()+Constantes.PUERTA_ABIERTA);
							pw.println(this.getNombre()+Constantes.PUERTA_ABIERTA);
							
						}
					}				
				}
			}
			
		}// SI ESTÁ ABIERTA NO HACE NADA
		
		return enc;
		
	}
			
	
	
}





