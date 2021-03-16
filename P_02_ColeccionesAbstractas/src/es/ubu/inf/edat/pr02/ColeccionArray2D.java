package es.ubu.inf.edat.pr02;

import java.util.AbstractCollection;
import java.util.Iterator;

public class ColeccionArray2D<E> extends AbstractCollection<E> {

    private E[][] matriz;

    public ColeccionArray2D(E[][] contenido) {
        matriz = contenido;
    }

    private int numeroFilas() {
        return matriz.length;
    }

    private int numeroColumnas() {
        return matriz[0].length;
    }


    // Devuelve un Iterador
    @Override
    public Iterator<E> iterator() {
        return new Iterator2D();
    }

    @Override
    public int size() {
        //TODO Implementar por el alumno
        return numeroColumnas() * numeroFilas();
    }


    private class Iterator2D implements Iterator<E> {

        int fila = 0;
        int columna = 0;

        //TODO Implementar por el alumno
        @Override
        public boolean hasNext() {
            if (fila >= numeroColumnas()) {
                return false;
            } else {
                return true;
            }
        }


        //El resto me da la columna y el cociente la fila j + (i * n)= pos j= pos - (i*n)
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
