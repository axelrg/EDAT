package es.ubu.inf.edat.pr05;

import java.util.*;

/**
 * Esta clase extiende la clase AbstactList.
 * Esta Lista basada en LINKED LIST nos permite no tener que tratar excepciones.
 * al  acceder, establecer o borrar en una posición no definida.
 *
 * @param <E> Tipo de datos contenidos en la lista
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 */
public class ListaRedimensionable<E> extends AbstractList<E> {

    LinkedList<E> lista;


    /**
     * Constructor encargado de inicializar la lista.
     */
    public ListaRedimensionable() {
        lista = new LinkedList<>();
    }

    /**
     * Constructor encargado de inicializar la lista con una Collection dada.
     *
     * @param contenido Parametro de entrada que indica el contenido a introducir en la lista.
     */
    public ListaRedimensionable(Collection<E> contenido) {
        lista = new LinkedList<>();
        this.lista.addAll(contenido);
    }

    /**
     * Este metodo añade un elemento a la lista independientemente de que el indice aportado exista o no.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index   indice en el que añadir el elemento
     * @param element elemento a añadir a la lista
     */
    @Override
    public void add(int index, E element) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            lista.set(0, element);
        } else if (index >= lista.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            lista.set(index, element);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            lista.add(index, element);
        }
    }

    /**
     * Este metodo devuelve un elemento de la lista independientemente de que el indice aportado exista o no.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index indice en el que devolver el elemento
     * @return el elemento en la posición
     */
    @Override
    public E get(int index) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.get(0);
        } else if (index >= lista.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.get(index);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return lista.get(index);
        }
    }

    /**
     * Este metodo cambia un elemento de la lista independientemente de que el indice aportado exista o no.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index   indice en el que cambiar el elemento
     * @param element elemento por el que cambiar
     * @return el elemento cambiado
     */
    @Override
    public E set(int index, E element) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.set(0, element);
        } else if (index >= lista.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.set(index, element);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return lista.set(index, element);
        }
    }

    /**
     * Este metodo elimina un elemento de la lista independientemente de que el indice aportado exista o no.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index indice en el que eliminar el elemento
     * @return el elemento eliminado
     */
    @Override
    public E remove(int index) {

        if (index < 0) {
            int tamanoInicial = lista.size();

            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.remove(0);
        } else if (index >= lista.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.remove(index);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return lista.remove(index);
        }
    }


    /**
     * Este metodo compara dos Objetos, uno siempre del tipo ListaRdimensionable.
     * otro del tipo Object siempre que sea instancia de Collection.
     *
     * @param comparada objeto a comparar
     * @return verdadero si son iguales falso si no
     */
    @Override
    public boolean equals(Object comparada) {
        //1º Creamos dos listas redimensionables, una será la actual
        ListaRedimensionable<?> lista1;
        ListaRedimensionable<?> lista2 = this;
        //2º Confirmamos que comparada es instancia de collection
        if (comparada instanceof Collection) {
            lista1 = new ListaRedimensionable<>((Collection<?>) comparada);
            //3º Eliminamos los nulos de ambas listas
            lista1.trim();
            lista2.trim();
            //4º Devolvemos verdadero si ambas listas sin nulos son iguales
            return Arrays.deepEquals(lista1.lista.toArray(), lista2.lista.toArray());
        } else {
            return false;
        }
    }


    /**
     * Este metodo vacia la lista.
     */
    @Override
    public void clear() {
        lista.clear();
    }

    /**
     * Este metodo devuelve el tamaño de la lista.
     *
     * @return entero con el tamaño de la lista
     */
    @Override
    public int size() {
        return lista.size();
    }

    /**
     * Este metodo elimina los null de la lista.
     */
    public void trim() {
        LinkedList<E> sinNull = new LinkedList<>();
        for (E e : lista) {
            if (e != null) {
                sinNull.add(e);
            }
        }
        lista = sinNull;
    }
}