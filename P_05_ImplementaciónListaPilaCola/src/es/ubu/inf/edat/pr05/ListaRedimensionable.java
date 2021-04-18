package es.ubu.inf.edat.pr05;

import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;

public class ListaRedimensionable<E> extends AbstractList<E> {

    LinkedList<E> lista;

    public ListaRedimensionable() {
        lista= new LinkedList<>();
    }

    public ListaRedimensionable(Collection<E> contenido) {
            lista = new LinkedList<>();
            this.lista.addAll(contenido);
        //TODO Implementar por el alumno
    }

    @Override
    public void add(int index, E element) {
        int size = lista.size();
/*
        lista.addLast(null);
        lista.addLast(element);
        lista.addLast(null);
        lista.addLast(element);
*/
        System.out.println(lista.toString());

        if (index < 0) {
            while (index <= lista.size() + Math.abs(index)) {
                lista.addFirst(null);
            }
            lista.set(-index, element);
        } else if (index > lista.size()) {
            int tamanoInicial = lista.size();
            while (lista.size() <= tamanoInicial + Math.abs(index)) {
                System.out.println(lista.toString());
                System.out.println(lista.size());
                lista.addLast(null);
            }
            lista.set(index, element);
        } else {
            lista.add(index, element);
        }
    }


    @Override
    public E get(int index) {
        return lista.get(index);
    }

    @Override
    public E set(int index, E element) {
        if (index < 0) {
            while (index <= lista.size() + Math.abs(index)) {
                lista.addFirst(null);
            }
            return lista.set(index, element);
        } else if (index >= lista.size()) {
            while (index <= lista.size() + (Math.abs(index) - lista.size())) {
                lista.addLast(null);
            }
            return lista.set(index, element);
        } else {
            return lista.set(index, element);
        }
    }

    @Override
    public E remove(int index) {
        return lista.remove(index);
    }

    @Override
    public boolean equals(Object comparada) {
        return lista.equals(comparada);
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

    }


}