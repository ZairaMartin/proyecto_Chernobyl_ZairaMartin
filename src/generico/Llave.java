package generico;

public class Llave {
	//ATRIBUTOS
	private int idLlave;
	private int celdaActual;
		
	//CONSTRUCTORES
	public Llave() {
		this.idLlave = -1;
		this.celdaActual = -1;
	}
	
	public Llave(int idLlave, int celdaActual) {
		this.idLlave = idLlave;
		this.celdaActual = celdaActual;
	}

	//GETTERS Y SETTERS
	public int getIdLlave() {
		return idLlave;
	}
	
	public void setIdLlave(int idLlave) {
		this.idLlave = idLlave;
	}
	
	public int getCeldaActual() {
		return celdaActual;
	}
	
	public void setCeldaActual(int celdaActual) {
		this.celdaActual = celdaActual;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "Llave{idLlave=" + idLlave + ", celdaAct=" + celdaActual + "}";
	}
	
}
