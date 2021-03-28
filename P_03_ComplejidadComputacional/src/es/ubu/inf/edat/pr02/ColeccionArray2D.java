package es.ubu.inf.edat.pr02;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * Clase que implementa una coleccion abstracta que permite el manejo 
 * de matrices de datos contiguas en memoria como si se tratara de una
 * coleccion lineal.
 *  
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 * 
 */
public class ColeccionArray2D<E> extends AbstractCollection<E> {

	/**
	 * Declara y crea la matriz bidimensional.
	 */
    public E[][] matriz;

	/**
	 * Constructor encargado de almacenar la informacion bi-dimensional en el
	 * interior de estructura de datos. 
	 * 
	 * @param contenido 	Parametro de entrada que indica el contenido almacenado en la matriz.
	 */
    public ColeccionArray2D(E[][] contenido) {
        matriz = contenido;
    }

    /**
     * Metodo que indica el numero de filas de la matriz.
     * 
     * @return devuelve la longitud de filas de la matriz.
     */
    public int numeroFilas() {
        return matriz.length;
    }

    /**
     * Metodo que indica el numero de columnas de la matriz.
     * 
     * @return devuelve la longitud de columnas de la matriz.
     */
    public int numeroColumnas() {
        return matriz[0].length;
    }


    // Devuelve un Iterador
    @Override
    public Iterator<E> iterator() {
        return new Iterator2D();
    }

    @Override
    public int size() {
        return numeroColumnas() * numeroFilas();
    }

	/**
	 * Clase interna Iterator que permite acceder a cada uno de los elementos
	 * de forma secuencial.
	 * 
	 * @author Axel Rubio Gonzalez
	 * @author Alvaro Hoyuelos Martin
	 * @version 1.0
	 * @since JDK 11
	 */
    private class Iterator2D implements Iterator<E> {

		/**
		 * Variable que indica la fila.
		 */
        int fila = 0;
        
		/**
		 * Variable que indica la columna.
		 */
        int columna = 0;

        @Override
        public boolean hasNext() {
            return fila < numeroColumnas();
        }

        @Override
        public E next() {
            int filaAux=fila;
            int colAux=columna;

            if (fila == numeroFilas() - 1 && columna == numeroColumnas() - 1) {
                fila = 0;
                columna = 0;
            } else if (columna == numeroColumnas() - 1) {
                columna=0;
                fila++;
            } else {
                columna++;
            }
            return matriz[filaAux][colAux];
        }

        @Override
        public void remove() {
            if (fila==0 && columna==0){
                matriz[numeroFilas()-1][numeroColumnas()-1] = null;
            }else if (fila !=0 && columna==0){
                matriz[fila-1][numeroColumnas()-1]=null;
            }else {
                matriz[fila][columna-1]=null;
            }
        }
    }
}
