package personajes;

import generico.Celda;
import generico.CentralNuclear;
import generico.Constantes;
import generico.Utilidad;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Personaje {

    //ATRIBUTO
    private String nombre;
    private int turno;
    private int idCelda;
    private char marca;
    private ArrayList<Character> lRuta;

    //CONSTRUCTORES
    public Personaje() {
        this.nombre = "";
        this.turno = -1;
        this.idCelda = -1;
        this.marca = ' ';
        this.lRuta = new ArrayList<>();
    }

    public Personaje(String nombre, int turno, int idCelda, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCelda = idCelda;
        this.marca = marca;
        this.lRuta = new ArrayList<>();
    }

    //MÉTODOS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    //

    public int getIdCeldaActual() {
        return idCelda;
    }

    public void setIdCeldaActual(int idCelda) {
        this.idCelda = idCelda;
    }
    //

    public char getMarca() {
        return marca;
    }

    public void setMarca(char marca) {
        this.marca = marca;
    }

    public ArrayList<Character> getLRuta() {
        return lRuta;
    }

    public void setLRuta(ArrayList<Character> lRuta) {
        this.lRuta = lRuta;
    }

    //Metodo Abstracto realizarAcciones
    public abstract void realizarAcciones(PrintWriter pw);


    //introduce los valores del vector vRuta en el arrayList de lRuta de los movimientos del personaje
    public void cargarMovimientos(char[] vRuta) {
        int i;
        for (i = 0; i < vRuta.length; i++) {
            lRuta.add(i, vRuta[i]);
        }

    }

    //metodo para eliminar el primer movimiento
    public void borrarPrimerMovimiento() {
        int i = 0;
        if (arrayListVacio() == false) {
            this.lRuta.remove(i);
        }

    }

    //metodo para insertar un movimiento al final del arrayList
    public void insMovimiento(char mov) {
        lRuta.add(mov);  
    }

    //lRuta vacio
    public boolean arrayListVacio() {
        //esta sola linea retorna si esta vacio o no
        return this.lRuta.isEmpty();
    }

    //metodo calcular siguiente celda
    public int calcularSiguienteIdCelda() {
        int idCeldaDestino = Constantes.NUM_CERO;
        //hacer switch
        char opcionRuta;
        if (arrayListVacio() == false) {
            opcionRuta = lRuta.get(0);
            switch (opcionRuta) {
                case Constantes.N ->
                    idCeldaDestino = idCelda - Constantes.COLUMNAS;

                case Constantes.S ->
                    idCeldaDestino = idCelda + Constantes.COLUMNAS;  //sur suma 8 y baja a la casilla de abajo

                case Constantes.E ->
                    idCeldaDestino = idCelda + Constantes.UNO;   //Este y suma 1 para ir a la derecha

                case Constantes.O ->
                    idCeldaDestino = idCelda - Constantes.UNO;   //Oestre y resta 1 para ir a la izquierda

                //default-> idCeldaDestino = idCelda;
            }
        }
        return idCeldaDestino;

    }

    //-----------------Accion Mover -----------
    public void accionMover(PrintWriter pw) {
        //patron Singleton
        CentralNuclear central = CentralNuclear.getInstancia();
        int f = Utilidad.calcularFilas(idCelda);
        int c = Utilidad.calcularColumnas(idCelda);

        if (!lRuta.isEmpty()) {  //SI LrUTA TIENE CONTENIDO SEGUIMOS CALCULANDO
        
	    int destino = calcularSiguienteIdCelda();
        int fd = Utilidad.calcularFilas(destino);
        int cd = Utilidad.calcularColumnas(destino);

        // Por ello lo llamamos con el objeto central de la clase CN y no con "celda" OJO

            if ((fd >= 0 && fd < Constantes.FILAS) && (cd >= 0 && cd < Constantes.COLUMNAS)) {
              
        		Celda celdaActual = central.getCelda(f, c);  			// Creamos los objetos de la clase celda y le damos las posiciones con el getCelda de la clase CN
                Celda celdaDestino = central.getCelda(fd, cd);

                if (central.hayCamino(idCelda, destino) == true) {
                    this.idCelda = destino;
                    celdaDestino.insertarPersonaje(this);  //this para hacer referencia a esta clase personaje
                    celdaActual.borrarPersonaje(this.nombre);

                } else {
                	//System.out.println(this.getNombre() + Constantes.NO_PUEDO + "(" + lRuta.get(0) + ")");
                    pw.println(this.getNombre() + Constantes.NO_PUEDO + Constantes.PARENTESIS_ABIERTO + lRuta.get(0) + Constantes.PARENTESIS_CERRADO);

                }
            } else {
            	//System.out.println(this.getNombre() + Constantes.NO_PUEDO + "(" + lRuta.get(0) + ")");
                pw.println(this.getNombre() + Constantes.NO_PUEDO + Constantes.PARENTESIS_ABIERTO + lRuta.get(0) + Constantes.PARENTESIS_CERRADO);
            }
        	
			borrarPrimerMovimiento(); //fuera del condicional, debe borrar el movimiento haya o no camino

        }

    }

}
