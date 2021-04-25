import java.util.AbstractCollection;
import java.util.Iterator;

public class ColeccionMatriz<E> extends AbstractCollection<E> {

    public E[][] contenido;

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return new iterador();
    }

    @Override
    public int size() {
        return getNumeroFilas() * getNumeroColumnas();
    }

    private int getNumeroFilas() {
        return contenido.length;
    }

    private int getNumeroColumnas() {
        return contenido[0].length;
    }

    private class iterador implements Iterator<E> {
        private int fila;
        private int columna;
        private int paso = 3;

        @Override
        public boolean hasNext() {
            return size()>=((fila+1)*(columna+1))+paso;
        }

        @Override
        public E next() {
            E res=null;
             for (int i=0; i<paso; i++){
                 res=siguiente();
             }

             return res;
        }

        private E siguiente(){
            if (columna == getNumeroColumnas() - 1 && fila == getNumeroFilas() - 1) {
                fila = 0;
                columna = 0;
            } else if (columna == getNumeroColumnas()-1) {
                fila++;
                columna = 0;
            } else if (columna < getNumeroColumnas()) {
                columna++;
            }
            return contenido[fila][columna];
        }
    }
}


