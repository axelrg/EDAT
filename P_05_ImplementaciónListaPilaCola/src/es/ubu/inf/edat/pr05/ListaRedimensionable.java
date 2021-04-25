package es.ubu.inf.edat.pr05;

import java.util.*;

public class ListaRedimensionable<E> extends AbstractList<E> {

    LinkedList<E> lista;

    public ListaRedimensionable() {
        lista = new LinkedList<>();
    }

    public ListaRedimensionable(Collection<E> contenido) {
        lista = new LinkedList<>();
        this.lista.addAll(contenido);
        //TODO Implementar por el alumno
    }

    @Override
    public void add(int index, E element) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            lista.set(0, element);
        } else if (index >= lista.size()) {
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            lista.set(index, element);
        } else {
            lista.add(index, element);
        }
    }


    @Override
    public E get(int index) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.get(0);
        } else if (index >= lista.size()) {
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.get(index);
        } else {
            return lista.get(index);
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.set(0, element);
        } else if (index >= lista.size()) {
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.set(index, element);
        } else {
            return lista.set(index, element);
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0) {
            int tamanoInicial = lista.size();
            while (lista.size() + 1 <= tamanoInicial + Math.abs(index)) {
                if (lista.size() == 0) {
                    lista.addFirst(null);
                }
                lista.addFirst(null);
            }
            return lista.remove(0);
        } else if (index >= lista.size()) {
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(tamanoInicial - index)) {
                lista.addLast(null);
            }
            return lista.remove(index);
        } else {
            return lista.remove(index);
        }
    }


    @Override
    public boolean equals(Object comparada) {
        ListaRedimensionable<?> lista1;
        ListaRedimensionable<?> lista2 = this;
        if (comparada instanceof Collection) {
            lista1 = new ListaRedimensionable<>((Collection<?>) comparada);
            lista1.trim();
            lista2.trim();
            return Arrays.deepEquals(lista1.lista.toArray(), lista2.lista.toArray());
        } else {
            return false;
        }
    }


    @Override
    public void clear() {
        lista.clear();
    }

    @Override
    public int size() {
        return lista.size();
    }

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