package es.ubu.inf.edat.pr10;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArbolGenerico<E> extends AbstractSet<E> {
    public Nodo<E> raiz;
    public int tamanio;


    public ArbolGenerico() {
    }

    private class Nodo<E> {
        Nodo<E> hermano;
        Nodo<E> padre;
        E nodo;
        List<Nodo<E>> hijos;

        public Nodo(E nodo) {
            this.nodo = nodo;
            hijos = new ArrayList<>();
        }

        public Nodo() {
            hijos = new ArrayList<>();
        }


        public E getHijo(E hijoBuscado) {
            for (Nodo<E> hijo : hijos) {
                if (hijo.nodo == hijoBuscado) {
                    return hijoBuscado;
                }
            }
            return null;
        }

        public boolean tieneHijo() {
            return hijos.size() != 0;
        }

        public Nodo<E> getHijo() {
            return hijos.get(0);
        }

        public void addHijo(E hijo) {
            hijos.add(new Nodo<>(hijo));
        }

        public int numeroHijos() {
            return hijos.size();
        }

        public Nodo<E> getHermano() {
            return hermano;
        }

        public Nodo<E> getPadre() {
            return padre;
        }

        public boolean tieneHemano() {
            return hermano != null;
        }
        public boolean tienePadre() {
            return padre != null;
        }
    }

    private class iterador implements Iterator<E> {

        Nodo<E> actual;

        public iterador() {
            actual = raiz;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return true;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            E nodoInicial = actual.nodo;

            if (actual.tieneHijo()) {
                actual = actual.getHijo();
            } else if (actual.tieneHemano()) {
                actual = actual.getHermano();
            } else {
                while (!actual.tieneHemano()&& actual != raiz) {
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


    @Override
    public Iterator<E> iterator() {
        return new iterador();
    }

    @Override
    public int size() {
        return tamanio;
    }

    private Nodo<E> buscadorNodo(E nodo) {
        Nodo<E> actual = raiz;
        while (nodo != actual.nodo) {
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
        }
        return actual;
    }


    public boolean add(E padre, E hijo) {
        if (raiz == null && padre == null) {
            raiz = new Nodo<>(hijo);
            tamanio++;
        } else {
            Nodo<E> p = buscadorNodo(padre);
            p.addHijo(hijo);
            p.hijos.get(p.hijos.size() - 1).padre = p;
            if (p.hijos.size() > 1) {
                p.hijos.get(p.hijos.size() - 2).hermano = p.hijos.get(p.hijos.size() - 1);
            }
            tamanio++;
        }
        return true;
    }




    public boolean tieneHermanoIzquierdo(Nodo<E> nodo ){
        if (nodo.tienePadre()){
            if (nodo.padre.hijos.size()>1){
                if (nodo.padre.hijos.indexOf(nodo)==0){
                    return false;
                }else {
                    return true;
                }
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public Nodo<E> getHermanoIzquierdo(Nodo<E> nodo ){
        if (tieneHermanoIzquierdo(nodo)){
            return  nodo.getPadre().hijos.get(nodo.getPadre().hijos.indexOf(nodo));
        }else {
            return null;
        }
    }

    public void clear(){
    }

    @Override
    public boolean remove(Object objeto) {
        Nodo<E> nodo = buscadorNodo((E) objeto);
        Nodo<E> padre = nodo.getPadre();

        if (!nodo.tieneHijo()){
            if (nodo.getPadre()!=null){
                if (!nodo.tieneHemano()){
                    if (tieneHermanoIzquierdo(nodo)) {
                        getHermanoIzquierdo(nodo).hermano = null;
                    }
                    nodo.getPadre().hijos.remove(nodo);
                }else {
                    if (tieneHermanoIzquierdo(nodo)){
                        getHermanoIzquierdo(nodo).hermano = nodo.getHermano();
                    }
                }
            }
        }

        return true;
    }




    public List<E> descendants(E padre) {
        ArrayList<E> lista = new ArrayList<>();
        Nodo<E> nodo = buscadorNodo(padre);
        if (!nodo.tieneHijo()) {
            return lista;
        } else {
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
                    if (actual.tieneHemano() && actual!=nodo){
                        actual = actual.getHermano();
                        lista.add(actual.nodo);
                    }


                }
            }
            while (nodo != actual);

            return lista;
        }

    }

    private int obtenerNivel(E nodo){
        int nivel = 1;
        Nodo<E> actual = buscadorNodo(nodo);
        while (actual!=raiz){
            actual= actual.getPadre();
            nivel++;
        }
        return nivel;
    }

    public List<E> breathFirstTraverse() {
        ArrayList<E> lista = new ArrayList<>();
        int contador=1;
        E nodo;
        while (lista.size()!=this.size()){
            Iterator<E> it = new iterador();
            for (int i=0; i<size(); i++){
                nodo= it.next();
                if (obtenerNivel(nodo)==contador){
                    lista.add(nodo);
                }
            }
            contador++;

        }
        return lista;
    }

    public List<E> preOrderTraverse() {
        ArrayList<E> lista = new ArrayList<>();
        Iterator<E> it = new iterador();
        int contador=0;
        do {
            contador++;
            lista.add(it.next());
        }while (contador<size());
        return lista;
    }

    public int height(E elemento) {
        int altura = 0;
        int alturamax = 0;
        Nodo<E> nodo = buscadorNodo(elemento);
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
                    if (actual.tieneHemano() && actual!=nodo)
                        actual = actual.getHermano();
                }
            }
            while (nodo != actual);

            return alturamax;
        }

    }

}



