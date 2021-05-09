package es.ubu.inf.edat.pr07;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MapaDispersionAbierta<K, V> extends AbstractMap<K, V> {

    private int tamanoCubeta = 3;
    private int numeroCubeta = 16;
    private List<EntradaMultiple<K, V>> indices;
    private int size=0;

    public MapaDispersionAbierta() {
        indices = new ListaRedimensionable<>();
    }

    public MapaDispersionAbierta(int tamanoCubeta, int numeroCubeta) {
        indices = new ListaRedimensionable<>();
        this.tamanoCubeta = tamanoCubeta;
        this.numeroCubeta = numeroCubeta;
    }

    private class EntradaMultiple<K, V> {

        private List<K> claves;
        private List<V> valores;
        int tam = 0;

        public EntradaMultiple(K key, V value) {
            claves = new ArrayList<>();
            claves.add(key);
            valores = new ArrayList<>();
            valores.add(value);
        }

        public List<K> getClaves() {
            return claves;
        }

        public List<V> getValores() {
            return valores;
        }

        public int tamanioActualCubeta(){
            return claves.size();
        }

        public void anadirElemento(K key, V value){
            claves.add(key);
            valores.add(value);
        }

        public boolean buscarClave(K key){
            for (int i=0; i<claves.size(); i++){
                if (claves.get(i).equals(key)){
                    return true;
                }
            }
            return false;
        }

        public int indiceClave(K key){
            for (int i=0; i<claves.size(); i++){
                if (claves.get(i)== key){
                    return i;
                }
            }
            return -1;
        }

        //TODO Implementar por el alumno

    }

    @SuppressWarnings("hiding")
    private class EntradaPlana<K, V> extends SimpleEntry<K, V> {
        private static final long serialVersionUID = 1L;

        public EntradaPlana(K clave, V valor) {
            super(clave, valor);
        }
    }


    public int getNumeroCubetas() {
        return this.numeroCubeta;
    }

    private  void redimensionar(){
        numeroCubeta= (int) (numeroCubeta*1.5);
    }

    private int posicionTabla(K key){
        return key.hashCode()%numeroCubeta;
    }

    @Override
    public V put(K key, V value) {
        //System.out.println(numeroCubeta);

            if (indices.get(posicionTabla(key))==null){
                //Si la cubeta estaba vacia
                indices.add(posicionTabla(key), new EntradaMultiple<>(key,value));
                size++;
                System.out.println("EYYY!!!!");
                return null;
            }else{
                //Si la cubeta contenia ya datos
                if (indices.get(posicionTabla(key)).buscarClave(key)){
                    //Si contiene la misma clave sustituimos el valor
                    V valor = indices.get(posicionTabla(key)).getValores().get(indices.get(posicionTabla(key)).indiceClave(key));
                    indices.get(posicionTabla(key)).valores.set(indices.get(posicionTabla(key)).indiceClave(key),value);
                    System.out.println("HOLA");
                    System.out.println("POS: "+ posicionTabla(key));
                    System.out.println("KEY: "+indices.get(posicionTabla(key)).claves.toString());
                    System.out.println("VAL: "+indices.get(posicionTabla(key)).valores.toString());
                    System.out.println("CUB: "+numeroCubeta);
                    return valor;
                }else {
                    if (indices.get(posicionTabla(key)).tamanioActualCubeta()<tamanoCubeta){
                        indices.get(posicionTabla(key)).anadirElemento(key, value);
                        size++;
                        System.out.println("POS: "+ posicionTabla(key));
                        System.out.println("KEY: "+indices.get(posicionTabla(key)).claves.toString());
                        System.out.println("VAL: "+indices.get(posicionTabla(key)).valores.toString());
                        System.out.println("CUB: "+numeroCubeta);
                        return null;
                    }else{

                        System.out.println("POS: "+ posicionTabla(key));
                        System.out.println("Estas superando el tam maximo de la cubeta");
                        numeroCubeta= (int) (numeroCubeta*1.5);
                        System.out.println("CC"+ numeroCubeta);
                        System.out.println(posicionTabla(key));

                        put(key, value);

                        System.out.println("POS: "+ posicionTabla(key));
                        System.out.println("KEY: "+indices.get(posicionTabla(key)).claves.toString());
                        System.out.println("VAL: "+indices.get(posicionTabla(key)).valores.toString());
                        System.out.println("CUB: "+numeroCubeta);


                    }
                }
            }

        return null;
    }

    @Override
    public Set<K> keySet(){
        Set<K> llaves = new HashSet<>();
        for (EntradaMultiple<K, V> index : indices) {
            if (index != null) {
                llaves.addAll(index.getClaves());
            }
        }
        return llaves;
    }

    @Override
    public V remove(Object key) {
        int indice = indices.get(posicionTabla((K) key)).indiceClave((K) key);
        indices.get(posicionTabla((K) key)).claves.remove(indice);
        size--;
        return indices.get(posicionTabla((K) key)).valores.remove(indice);
    }

    @Override
    public V get(Object key) {
        return indices.get(posicionTabla((K) key)).valores.get(indices.get(posicionTabla((K) key)).indiceClave((K) key));
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> a = new HashSet<>();
        for (EntradaMultiple<K, V> index : indices) {
            if (index != null) {
                for (int i=0; i<index.claves.size();i++){
                    a.add(new EntradaPlana<>(index.claves.get(i),index.valores.get(i)));
                }
            }
        }
        return a;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void clear(){
            indices=null;
    }

    @Override
    public Collection<V> values() {
        Set<V> valores = new HashSet<>();
        for (EntradaMultiple<K, V> index : indices) {
            if (index != null) {
                valores.addAll(index.getValores());
            }
        }
        return valores;
    }

    //TODO Implementar por el alumno
}
