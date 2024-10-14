package generico;

import java.util.ArrayList;

import personajes.Operador;
import personajes.Personaje;

public class Celda {

	//atributos
	private int idCelda;
	private ArrayList <Personaje> lPersonajes;
	private boolean refrigerada;
	private int escombros;
	private Puerta puerta;  //Objeto de tipo puerta 
	private Llave llave;
	
	
	public Celda() {
		this.idCelda = -1;
		this.lPersonajes = new ArrayList<>();
		this.refrigerada = false;
		this.escombros = -1;
		this.puerta = null;
		this.llave = null;
	}


	public Celda(int idCelda, ArrayList<Personaje> lPersonajes, boolean refrigerada, int escombros) {
		this.idCelda = idCelda;
		this.lPersonajes = lPersonajes;
		this.refrigerada = refrigerada;
		this.escombros = escombros;
		this.puerta = null; // con atributo o con null??
		this.llave = null;
	}
	
	public Celda(int idCelda) {  //Constructor especifico para la clase Central Nuclear en insertarPersonaje
		this.idCelda = idCelda;
		this.lPersonajes = new ArrayList<>();
		this.refrigerada = false;
		this.escombros = -1;
		this.puerta = null; 
		this.llave = null;
	}

	//geters y setters
	
	public int getIdCelda() {
		return idCelda;
	}


	public void setIdCelda(int idCelda) {
		this.idCelda = idCelda;
	}


	public ArrayList<Personaje> getLPersonajes() {
		return lPersonajes;
	}


	public void setLPersonajes(ArrayList<Personaje> lPersonajes) {
		this.lPersonajes = lPersonajes;
	}


	public boolean isRefrigerada() {
		return refrigerada;
	}


	public void setRefrigerada(boolean refrigerada) {
		this.refrigerada = refrigerada;
	}


	public int getEscombros() {
		return escombros;
	}


	public void setEscombros(int escombros) {
		this.escombros = escombros;
	}


	public Puerta getPuerta() {
		return puerta;
	}


	public void setPuerta(Puerta puerta) {
		this.puerta = puerta;
	}


	public Llave getLlave() {
		return llave;
	}


	public void setLlave(Llave llave) {
		this.llave = llave;
	}
		
	//METODOS EXTRA
	
	 @Override
    public String toString() {
        if  (this.lPersonajes.size() > 0){
            if  (this.lPersonajes.size() == 1){
                //valueOf convierte a String el char recibido por parámetro
                return String.valueOf(this.lPersonajes.get(0).getMarca());
            }
            else{
                //valueOf convierte a String el int recibido por parámetro
                return String.valueOf(this.lPersonajes.size());
                
            }        
        }
        
        if(this.llave != null) {
        	return Constantes.INTERROGACION;
        }
        else{
            return Constantes.CERO;
        }
    }
	
	
	
	
	//Metodo para insertar nuevos personajes al final de la lista
	public void insertarPersonaje( Personaje nuevoPersonaje) {
		if(lPersonajes == null) {   					//si la lista de personajes está null, la crea
			lPersonajes = new ArrayList<>();
		}
		lPersonajes.add(nuevoPersonaje);   //Añade el nuevo personaje al final de la lista
	}
	
	
	
	//metodo para buscar un personaje
	public Personaje buscarPersonaje(String nombre) {
		Personaje personaje;
		int i=0;
		boolean encontrado=false;  //para la busqueda lineal
		if(lPersonajes != null) {
			while(encontrado==false && i<lPersonajes.size()) {
				personaje = lPersonajes.get(i);  //recoge el personaje de cada posicion de la lista
				if(personaje.getNombre().equals(nombre)) {  //compara el nombre de cada personaje con el que buscamos
					encontrado=true;
					return personaje;
				}
				i++;
			}
		}
		return null;
	}
	//  si la lista no esta vacia va posicion a posicion comparando los nombres
	//  con el que buscan, si lo encuentra devuelve los datos de personaje
	//  si no lo encuentran o la lista esta vacia, devuelve null.
	
	
	// metodo para eliminar el nombre que se busca
	public void borrarPersonaje(String nombre) {  
		Personaje personajeBorrar;   //variable de tipo personaje vacia para recoger el valor del personaje que haya que borrar
		Personaje personaje;
		int i;
		boolean encontrado=false;    //para la busqueda lineal
		if(lPersonajes != null) {
			 personajeBorrar = null;
			 i=0;
			//busca al personaje con ese nombre y si lo hay será el personaje Borrar
			while(encontrado == false && i<lPersonajes.size()) {
				personaje = lPersonajes.get(i);  //recoge el personaje de cada posicion de la lista
				if(personaje.getNombre().equals(nombre)) {   //compara el nombre de cada personaje con el que buscamos
					encontrado=true;
					personajeBorrar = personaje;   //El que coincida pasa a ser el personaje a borrar
				}
				i++;
			}
			
			// Si hay personaje con ese nombre lo elimina de la lista
			if (personajeBorrar != null) {
				lPersonajes.remove(personajeBorrar);
			}
		}
	}
	
	//Buscar Operador
	//metodo para ver si hay algun operador entre los personajes creados, en tal caso devuelve true.
	public boolean buscarOperador(String nombre) {  
		int i = 0;
		boolean encontrado=false;    //para la busqueda lineal
		if(lPersonajes != null) {   //si la llista no esta vacia...
			while(encontrado==false && i<lPersonajes.size()) {
				if(lPersonajes.get(i) instanceof Operador) {   //busca si hay algun objeto de la clase Operador creado en esa celda
					encontrado=false;
					return true;
				}
				i++;
			}
			
		}
		return false;
	}
	
	public Personaje retornarPersonaje(int posicion) {  //METODO QUE NOS RETORNA EL PESONAJE DE UNA POSICION
		return this.lPersonajes.get(posicion);
	}
	
	public int longitudLPersonajes() {   //	RETORNA LOGITUD DEL ARRAYLIST DE PERSONAJES
		return this.lPersonajes.size();
	}
	
	
	
}
