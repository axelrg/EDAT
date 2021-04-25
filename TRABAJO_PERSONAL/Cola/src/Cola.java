import java.util.AbstractQueue;
import java.util.Iterator;

public class Cola<E> extends AbstractQueue<E> {

    private class Nodo<E>{
        private E dato;
        private Nodo<E> siguiente;
        private Nodo<E> anterior;

        public E getDato() {
            return dato;
        }

        public void setDato(E dato) {
            this.dato = dato;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<E> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }
    }

    Nodo<E> cabeza;
    Nodo<E> cola;
    int numeroElementos;

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    @Override
    public boolean offer(E e) {

        return false;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
