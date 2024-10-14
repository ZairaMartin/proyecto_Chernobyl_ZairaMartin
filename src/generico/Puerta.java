package generico;

public class Puerta {

	//ATRIBUTOS
	private int idCelda;
	private int idLlave;
	private boolean puertaAbierta;
	
	
	//constructor por defecto
	public Puerta() {
		this.idCelda = -1;
		this.idLlave = -1;
		this.puertaAbierta = false;
	}
	
	//constructor parametrizado
	public Puerta(int idCelda, int idLlave) {  
		this.idCelda = idCelda;
		this.idLlave = idLlave;  
		this.puertaAbierta = false;
	}

	public Puerta(int idCelda) {  
		this.idCelda = idCelda;
		this.idLlave = -1;  
		this.puertaAbierta = false;
	}

	
	//getter y setters
	public int getIdCelda() {
		return idCelda;
	}


	public void setIdCelda(int idCelda) {
		this.idCelda = idCelda;
	}

	public boolean isAbierta() {
		return puertaAbierta;
	}

	public void setPuertaAbierta(boolean puertaAbierta) {
		this.puertaAbierta = puertaAbierta;
	}
	
	public int getIdLlave() {
		return idLlave;
	}

	public void setIdLlave(int idLlave) {
		this.idLlave = idLlave;
	}
	

	
	
}
