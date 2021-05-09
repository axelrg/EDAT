package es.ubu.inf.edat.pr07;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Clase que programa listas que extiende de AbstractList utilizando
 * ArrayList.
 *
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 *
 * @param <E> Tipo de datos contenidos en la lista
 *
 */
public class ListaRedimensionable<E> extends AbstractList<E>{

    /**
     * Lista que almacena el contenido
     */
    private List<E> contenido;

    /**
     * Primer constructor encargado de inicializar la lista
     */

    public ListaRedimensionable() {
        contenido = new ArrayList<E>();
    }

    /**
     * Segundo constructor que define la lista interna de contenido como un ArrayList.
     *
     * @param contenido Parametro de entrada que añade el contenido a la lista contenido
     */
    public ListaRedimensionable(Collection<E> contenido) {
        this.contenido = new ArrayList<>();
        this.contenido.addAll(contenido);
    }

    /**
     * Metodo Override que añade el elemento a la lista independientemente de que el
     * indice aportado exista o no.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index   posicion en el que añadir el elemento
     * @param element elemento a añadir
     */
    @Override
    public void add(int index, E element) {
        if (index < 0) {
            int tamanoInicial = contenido.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (contenido.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (contenido.size() == 0) {
                    contenido.add(0,null);
                }
                contenido.add(0,null);
            }
            contenido.set(0, element);
        } else if (index >= contenido.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = contenido.size();
            while (contenido.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                contenido.add(null);
            }
            contenido.set(index, element);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            contenido.add(index, element);
        }
    }

    /**
     * Metodo Override que devuelve el elemento de la lista que indiques.
     * Añade nulls cuando la posicion es mas grande que la lista.
     *
     * @param index indice en el que devolver el elemento
     * @return elemento en la posición
     */
    @Override
    public E get(int index) {
        if (index < 0) {
            int tamanoInicial = contenido.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (contenido.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (contenido.size() == 0) {
                    contenido.add(0,null);
                }
                contenido.add(0,null);
            }
            return contenido.get(0);
        } else if (index >= contenido.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = contenido.size();
            while (contenido.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                contenido.add(null);
            }
            return contenido.get(index);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return contenido.get(index);
        }
    }

    /**
     * Metodo Override cambia un elemento de la lista sobreescribiendola.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index   indice en el que cambiar el elemento
     * @param element elemento por el que cambiar
     * @return el elemento cambiado con los nulls añadidos
     */
    @Override
    public E set(int index, E element) {
        if (index < 0) {
            int tamanoInicial = contenido.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (contenido.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (contenido.size() == 0) {
                    contenido.add(0,null);
                }
                contenido.add(0,null);
            }
            return contenido.set(0, element);
        } else if (index >= contenido.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = contenido.size();
            while (contenido.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                contenido.add(null);
            }
            return contenido.set(index, element);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return contenido.set(index, element);
        }
    }

    /**
     * Metodo Override que elimina el elemento de la lista.
     * Añade nulls para rellenar la lista hasta el indice solicitado.
     *
     * @param index indice en el que eliminar el elemento
     * @return lista con el elemento eliminado
     */
    @Override
    public E remove(int index) {
        if (index < 0) {
            int tamanoInicial = contenido.size();
            //Si el indice es menor que 0 añadimos al principio nulls hasta llegar al indice
            while (contenido.size() + 1 <= tamanoInicial + Math.abs(index)) {
                // Si la lista no tiene nada hay que añadir el correspondiente a la posicion 0
                if (contenido.size() == 0) {
                    contenido.add(0,null);
                }
                contenido.add(0,null);
            }
            return contenido.remove(0);
        } else if (index >= contenido.size()) {
            //Si el indice es mayor que el tamaño añadimos al final nulls hasta llegar al indice
            int tamanoInicial = contenido.size();
            while (contenido.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                contenido.add(null);
            }
            return contenido.remove(index);
        } else {
            //Si el indice está dentro del tamaño no hace falta hacer nada
            return contenido.remove(index);
        }
    }

    /**
     * Metodo Override compara dos listas sin los nulls.
     *
     * @param comparada objeto a comparar
     * @return boolean verdadero si se cumple o falso si no
     */
    @Override
    public boolean equals(Object comparada) {
        // Creacion de dos listas redimensionables
        ListaRedimensionable<?> listaActual ;
        ListaRedimensionable<?> listaComparar = this;

        // comprueba la comparacion
        if (comparada instanceof Collection) {
            listaActual = new ListaRedimensionable<>((Collection<?>) comparada);

            // eliminanion los nulos de ambas listas
            listaActual.trim();
            listaComparar.trim();

            // devolvemos verdadero si ambas listas sin nulos cumplen la igualdad
            return Arrays.deepEquals(listaActual.contenido.toArray(), listaComparar.contenido.toArray());
        } else {
            return false;
        }
    }

    /**
     * Metodo Override que limpia la lista.
     */
    @Override
    public void clear() {
        contenido.clear();
    }

    /**
     * Metodo Override que devuelve el tamaño de la lista.
     */
    @Override
    public int size() {
        return contenido.size();
    }

    /**
     * Metodo que elimina todos los nulls que contiene.
     */
    public void trim() {

        for(int i=0; i<contenido.size(); i++) {
            if(contenido.get(i)==null) {
                contenido.remove(i);
                i--;
            }
        }

    }
}