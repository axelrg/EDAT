package es.ubu.inf.edat.pr10;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase extiende la clase AbstactSet.
 * Implementa un Arbol Generico.
 *
 * @param <E> Tipo de datos para la clave
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 */
public class ArbolGenerico<E> extends AbstractSet<E> {

    /**
     * Variable para guardar la raiz del arbol.
     */
    public Nodo<E> raiz;

    /**
     * Variable para guardar el tamanio del arbol.
     */
    public int tamanio;

    /**
     * Constructor para inicializar el arbol.
     */
    public ArbolGenerico() {
        tamanio = 0;
    }

    /**
     * Esta clase representa un nodo del arbol.
     *
     * @param <T> Tipo de datos del nodo
     * @author Axel Rubio Gonzalez
     * @author Alvaro Hoyuelos Martin
     * @version 1.0
     * @since JDK 11
     */
    private class Nodo<T> {

        /**
         * Nodo hermano derecho del nodo actual.
         */
        Nodo<T> hermano;

        /**
         * Nodo padre del nodo actual.
         */
        Nodo<T> padre;

        /**
         * Elemento del nodo actual.
         */
        T nodo;

        /**
         * Lista para guardar los nodos hijos.
         */
        ArrayList<Nodo<T>> hijos;

        /**
         * Constructor para cada nodo.
         *
         * @param nodo elemento del nodo
         */
        public Nodo(T nodo) {
            this.nodo = nodo;
            hijos = new ArrayList<>();
        }

        /**
         * Constructor para cada nodo.
         */
        public Nodo() {
            hijos = new ArrayList<>();
        }

        /**
         * Metodo que comprueba si el nodo tiene hijos.
         *
         * @return verdadero si tiene hijo, falso si no
         */
        public boolean tieneHijo() {
            return hijos.size() != 0;
        }

        /**
         * Metodo que devuelve el primer hijo.
         *
         * @return primer nodo hijo
         */
        public Nodo<T> getHijo() {
            return hijos.get(0);
        }

        /**
         * Metodo que aniade un hijo.
         *
         * @param hijo elemento del hijo
         */
        public void addHijo(T hijo) {
            hijos.add(new Nodo<>(hijo));
        }

        /**
         * Metodo que devuelve el hermano.
         *
         * @return nodo hermano derecho
         */
        public Nodo<T> getHermano() {
            return hermano;
        }

        /**
         * Metodo que devuelve el padre.
         *
         * @return nodo padre
         */
        public Nodo<T> getPadre() {
            return padre;
        }

        /**
         * Metodo que comprueba si tiene hermano.
         *
         * @return verdadero si hay hermano
         */
        public boolean tieneHemano() {
            return hermano != null;
        }

        /**
         * Metodo que comprueba si tiene padre.
         *
         * @return verdadero si hay padre
         */
        public boolean tienePadre() {
            return padre != null;
        }
    }

    /**
     * Esta clase implementa un iterador que recorre el arbol en preorden.
     *
     * @author Axel Rubio Gonzalez
     * @author Alvaro Hoyuelos Martin
     * @version 1.0
     * @since JDK 11
     */
    private class iterador implements Iterator<E> {

        /**
         * Nodo del que parte el iterador.
         */
        Nodo<E> actual;

        /**
         * Constructor del iterador, inicializa el nodo actual.
         */
        public iterador() {
            actual = raiz;
        }

        /**
         * Metodo que comprueba si hay siguiente en la iteracion.
         * Siempre es verdadero ya que cuando se termina de recorrer vuelve a empezar.
         * Por ello siempre hay siguiente elemento.
         *
         * @return booleano, siempre verdadero
         */
        @Override
        public boolean hasNext() {
            return true;
        }

        /**
         * Devuelve el siguiente elemento de la iteracion.
         *
         * @return el siguiente elemento
         */
        @Override
        public E next() {
            E nodoInicial = actual.nodo;

            if (actual.tieneHijo()) {
                actual = actual.getHijo();
            } else if (actual.tieneHemano()) {
                actual = actual.getHermano();
            } else {
                while (!actual.tieneHemano() && actual != raiz) {
                    if (actual.padre == null) {
                        actual = raiz;
                    }
                    actual = actual.getPadre();
                }
                if (actual.tieneHemano())
                    actual = actual.getHermano();
            }
            return nodoInicial;
        }

    }


    /**
     * Metodo que devuelve un iterador.
     *
     * @return instancia del iterador
     */
    @Override
    public Iterator<E> iterator() {
        return new iterador();
    }

    /**
     * Getter del tamanio.
     *
     * @return entero con el tamanio
     */
    @Override
    public int size() {
        return tamanio;
    }

    /**
     * Busca un nodo, devuelvve null si no lo encuentra.
     *
     * @param nodo nodo buscado
     * @return nodo buscado o null
     */
    private Nodo<E> buscadorNodo(E nodo) {
        Nodo<E> actual = raiz;
        int contador = 0;
        while (nodo != actual.nodo && contador <= size()) {
            if (actual.tieneHijo()) {
                actual = actual.getHijo();
            } else if (actual.tieneHemano()) {
                actual = actual.getHermano();
            } else {
                while (!actual.tieneHemano() && actual != raiz) {
                    actual = actual.padre;
                }
                if (actual.tieneHemano())
                    actual = actual.getHermano();
            }
            contador++;
        }
        if (contador > size()) {
            return null;
        } else {
            return actual;
        }
    }

    /**
     * Metodo que comprueba la existencia de un nodo.
     *
     * @param nodo nodo buscado
     * @return booleano, verdadero si existe falso si no
     */
    public boolean existeNodo(E nodo) {
        return buscadorNodo(nodo) != null;
    }


    /**
     * Metodo que aniade un elemento al arbol.
     *
     * @param padre nodo padre
     * @param hijo  nodo a aniadir
     * @return booleano, verdadero si se ha insertado, falso si no
     */
    public boolean add(E padre, E hijo) {
        if (raiz == null && padre == null) {
            raiz = new Nodo<>(hijo);
        } else {
            if (existeNodo(padre) && !existeNodo(hijo)) {
                Nodo<E> p = buscadorNodo(padre);
                assert p != null;
                p.addHijo(hijo);
                p.hijos.get(p.hijos.size() - 1).padre = p;
                if (p.hijos.size() > 1) {
                    p.hijos.get(p.hijos.size() - 2).hermano = p.hijos.get(p.hijos.size() - 1);
                }
            } else {
                return false;
            }

        }
        tamanio++;
        return true;


    }


    /**
     * Metodo que comprueba si un determinado nodo tiene un hermano izquierdo.
     *
     * @param nodo nodo a comprobar
     * @return booleano, verdadero si tiene hermano izquierdo, falso si no
     */
    public boolean tieneHermanoIzquierdo(Nodo<E> nodo) {
        if (nodo.tienePadre()) {
            if (nodo.padre.hijos.size() > 1) {
                return nodo.padre.hijos.indexOf(nodo) != 0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que devuelve el hermano izquierdo.
     *
     * @param nodo nodo del que devolver el hermano
     * @return nodo hermano izquierdo, nulo si no existe
     */
    public Nodo<E> getHermanoIzquierdo(Nodo<E> nodo) {
        if (tieneHermanoIzquierdo(nodo)) {
            return nodo.getPadre().hijos.get(nodo.getPadre().hijos.indexOf(nodo));
        } else {
            return null;
        }
    }

    /**
     * Metodo borra el arbol al igualar la raiz a nulo.
     */
    public void clear() {
        raiz = null;
    }

    /**
     * Metodo que elimina nodos del arbol.
     *
     * @param objeto nodo que eliminar
     * @return verdadero si se ha eliminado, falso si no lo ha hecho por que no existe
     */
    @SuppressWarnings("unchecked") //Quitamos los warnings por unchecked cast
    @Override
    public boolean remove(Object objeto) {
        if (existeNodo((E) objeto)){
            Nodo<E> nodo = buscadorNodo((E) objeto);

            if (nodo == raiz) {
                if (raiz.tieneHijo()) {
                    Nodo<E> provisional = new Nodo<>();
                    provisional.nodo = raiz.hijos.get(0).nodo;

                    if (raiz.hijos.get(0).tieneHijo()) {
                        provisional.hijos.addAll(raiz.hijos.get(0).hijos);
                    }

                    if (raiz.hijos.get(0).tieneHemano()) {
                        raiz.hijos.remove(raiz.hijos.get(0));
                        provisional.hijos.addAll(raiz.hijos);
                    }

                    raiz = provisional;

                } else {
                    raiz = null;
                }
            } else {
                assert nodo != null;
                if (nodo.tieneHijo()) {
                    if (!nodo.tieneHemano() && !tieneHermanoIzquierdo(nodo)) {
                        int indice = nodo.getPadre().hijos.indexOf(nodo);
                        ArrayList<Nodo<E>> hijos = nodo.hijos;
                        nodo.getPadre().hijos.remove(nodo);
                        nodo.getPadre().hijos.addAll(indice, hijos);
                    } else {
                        int indice = nodo.getPadre().hijos.indexOf(nodo);
                        ArrayList<Nodo<E>> hijos = nodo.hijos;
                        if (tieneHermanoIzquierdo(nodo) && nodo.tieneHemano()) {
                            hijos.get(hijos.size() - 1).hermano = nodo.getHermano();
                            getHermanoIzquierdo(nodo).hermano = hijos.get(0);
                            nodo.getPadre().hijos.remove(nodo);
                            nodo.getPadre().hijos.addAll(indice, hijos);
                        } else if (!tieneHermanoIzquierdo(nodo) && nodo.tieneHemano()) {
                            hijos.get(hijos.size() - 1).hermano = nodo.getHermano();
                            nodo.getPadre().hijos.remove(nodo);
                            nodo.getPadre().hijos.addAll(indice, hijos);
                        } else if (tieneHermanoIzquierdo(nodo) && !nodo.tieneHemano()) {
                            getHermanoIzquierdo(nodo).hermano = hijos.get(0);
                            nodo.getPadre().hijos.remove(nodo);
                            nodo.getPadre().hijos.addAll(indice, hijos);
                        }
                    }
                }
            }

            if (!nodo.tieneHijo()) {
                if (nodo.getPadre() != null) {
                    if (!nodo.tieneHemano()) {
                        if (tieneHermanoIzquierdo(nodo)) {
                            getHermanoIzquierdo(nodo).hermano = null;
                        }
                        nodo.getPadre().hijos.remove(nodo);
                    } else {
                        if (tieneHermanoIzquierdo(nodo)) {
                            getHermanoIzquierdo(nodo).hermano = nodo.getHermano();
                        }
                    }
                }
            }
            tamanio--;
            return true;
        }
        return false;
    }


    /**
     * Metodo que devuelve los descendientes de un determinado nodo.
     *
     * @param padre nodo del que se devuelven los descendientes
     * @return lista con los descendientes, lista vacia si no tiene o no existe el padre
     */
    public List<E> descendants(E padre) {
        ArrayList<E> lista = new ArrayList<>();
        if (existeNodo(padre)){

            Nodo<E> nodo = buscadorNodo(padre);
            assert nodo != null;
            if (nodo.tieneHijo()) {
                Nodo<E> actual = nodo;
                do {
                    if (actual.tieneHijo()) {
                        actual = actual.getHijo();
                        lista.add(actual.nodo);
                    } else if (actual.tieneHemano()) {
                        actual = actual.getHermano();
                        lista.add(actual.nodo);
                    } else {
                        while (!actual.tieneHemano() && actual != nodo) {
                            actual = actual.padre;

                        }
                        if (actual.tieneHemano() && actual != nodo) {
                            actual = actual.getHermano();
                            lista.add(actual.nodo);
                        }


                    }
                }
                while (nodo != actual);

            }

        }
        return lista;

    }

    /**
     * Metodo que devuelve un entero con el nivel del nodo.
     *
     * @param nodo nodo del que obtener el nivel
     * @return entero con el nivel de un nodo, -1 si no existe el nodo
     */
    private int obtenerNivel(E nodo) {
        if (existeNodo(nodo)){
            int nivel = 1;
            Nodo<E> actual = buscadorNodo(nodo);
            while (actual != raiz) {
                assert actual != null;
                actual = actual.getPadre();
                nivel++;
            }
            return nivel;
        }
        return -1;

    }

    /**
     * Metodo que devuelve los nodos por niveles.
     *
     * @return lista de nodos por nivel
     */
    public List<E> breathFirstTraverse() {
        ArrayList<E> lista = new ArrayList<>();
        int contador = 1;
        E nodo;
        while (lista.size() != this.size()) {
            Iterator<E> it = new iterador();
            for (int i = 0; i < size(); i++) {
                nodo = it.next();
                if (obtenerNivel(nodo) == contador) {
                    lista.add(nodo);
                }
            }
            contador++;

        }
        return lista;
    }

    /**
     * Metodo que recorre el arbol en preorden.
     *
     * @return lista de nodos
     */
    public List<E> preOrderTraverse() {
        ArrayList<E> lista = new ArrayList<>();
        Iterator<E> it = new iterador();
        int contador = 0;
        do {
            contador++;
            lista.add(it.next());
        } while (contador < size());
        return lista;
    }


    /**
     * Metodo que devuelve un entero con la altura del nodo.
     *
     * @param elemento nodo del que calcular la altura
     * @return entero con el nivel de un nodo, -1 si no existe el nodo
     */
    public int height(E elemento) {
        if (existeNodo(elemento)){
            int altura = 0;
            int alturamax = 0;
            Nodo<E> nodo = buscadorNodo(elemento);
            assert nodo != null;
            if (!nodo.tieneHijo()) {
                return 0;
            } else {
                Nodo<E> actual = nodo;
                do {
                    if (altura > alturamax) {
                        alturamax = altura;
                    }
                    if (actual.tieneHijo()) {
                        actual = actual.getHijo();
                        altura++;
                    } else if (actual.tieneHemano()) {
                        actual = actual.getHermano();
                    } else {
                        while (!actual.tieneHemano() && actual != nodo) {
                            actual = actual.padre;
                            altura--;
                        }
                        if (actual.tieneHemano() && actual != nodo)
                            actual = actual.getHermano();
                    }
                }
                while (nodo != actual);

                return alturamax;
            }
        }
        return -1;

    }

}



