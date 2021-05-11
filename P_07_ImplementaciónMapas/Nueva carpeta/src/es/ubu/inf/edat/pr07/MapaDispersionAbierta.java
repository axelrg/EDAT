package es.ubu.inf.edat.pr07;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Esta clase extiende la clase AbstactMap.
 * Implementa un Hash Map de dispersion abierta.
 * El mapa se redimensiona cuando llega al tamano maximo de la cubeta.
 *
 * @param <K> Tipo de datos para la clave
 * @param <V> Tipo de datos para el valor
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 */
public class MapaDispersionAbierta<K, V> extends AbstractMap<K, V> {

    /**
     * Variables para establecer el tamano maximo dela cubeta y su numero.
     */
    private int tamanoCubeta = 3;
    private int numeroCubeta = 16;
    /**
     * HashMap
     */
    private List<EntradaMultiple<K, V>> indices;
    private int size = 0;
    /**
     * Lista para guardar las yaves ya introducidas.
     */
    private ArrayList<K> keys;
    /**
     * Lista para guardar los valores ya introducidos.
     */
    private ArrayList<V> values;

    /**
     * Constructor para inicializar el mapa.
     */
    public MapaDispersionAbierta() {
        indices = new ListaRedimensionable<>();
        values = new ArrayList<>();
        keys = new ArrayList<>();
    }

    /**
     * Constructor para especificar tamanos.
     * @param numeroCubeta numero inicial de cubetas
     * @param tamanoCubeta tamano maximo de la cubeta
     */
    public MapaDispersionAbierta(int tamanoCubeta, int numeroCubeta) {
        indices = new ListaRedimensionable<>();
        values = new ArrayList<>();
        keys = new ArrayList<>();
        this.tamanoCubeta = tamanoCubeta;
        this.numeroCubeta = numeroCubeta;
    }

    /**
     * Esta clase se encarga de operar con cada cubeta.
     *
     * @param <K> Tipo de datos para la clave
     * @param <V> Tipo de datos para el valor
     * @author Axel Rubio Gonzalez
     * @author Alvaro Hoyuelos Martin
     * @version 1.0
     * @since JDK 11
     */
    private class EntradaMultiple<K, V> {

        private List<K> claves;
        private List<V> valores;

        /**
         * Constructor para la cubeta.
         * @param key key
         * @param value valor
         */
        public EntradaMultiple(K key, V value) {
            claves = new ArrayList<>();
            claves.add(key);
            valores = new ArrayList<>();
            valores.add(value);
        }

        /**
         * Getter de la lista de claves.
         * @return la lista de claves
         */
        public List<K> getClaves() {
            return claves;
        }

        /**
         * Getter de la lista de valores.
         * @return la lista de valores
         */
        public List<V> getValores() {
            return valores;
        }

        /**
         * Getter del tamanio actual de la cubeta.
         * @return entero con el tamanio
         */
        public int tamanioActualCubeta() {
            return claves.size();
        }

        /**
         * Metodo para introducir un par clave valor.
         * @param key key
         * @param value valor
         */
        public void anadirElemento(K key, V value) {
            claves.add(key);
            valores.add(value);
        }

        /**
         * Metodo para buscar si hay una clave igual.
         * @param key key
         * @return booleano, true si existe, falso si no
         */
        public boolean buscarClave(K key) {
            for (K clave : claves) {
                if (clave.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Metodo para buscar la posicion de la clave.
         * @param key key
         * @return entero con el indice, -1 si no existe
         */
        public int indiceClave(K key) {
            for (int i = 0; i < claves.size(); i++) {
                if (claves.get(i) == key) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * Esta clase se encarga de generar una entrada compatible.
     * con entryset.
     *
     * @param <K> Tipo de datos para la clave
     * @param <V> Tipo de datos para el valor
     * @author Axel Rubio Gonzalez
     * @author Alvaro Hoyuelos Martin
     * @version 1.0
     * @since JDK 11
     */
    @SuppressWarnings("hiding")
    private class EntradaPlana<K, V> extends SimpleEntry<K, V> {
        private static final long serialVersionUID = 1L;

        /**
         * Constructor para la cubeta.
         * @param clave key
         * @param valor valor
         */
        public EntradaPlana(K clave, V valor) {
            super(clave, valor);
        }
    }

    /**
     * Getter para conocer el numero actual de cubetas.
     * @return entero con el numero
     */
    public int getNumeroCubetas() {
        return this.numeroCubeta;
    }

    /**
     * Metodo para redimensionar.
     * Aumenta el numero de cubetas *1.5 y rellena de nuevo con los mismos elementos
     */
    private void redimensionar() {
        //Redimensionamos y reseteamos la tabla además del listado de claves y valores
        numeroCubeta = (int) (numeroCubeta * 1.5);
        indices = new ListaRedimensionable<>();
        size = 0;
        ArrayList<K> keysAux = new ArrayList<>(keys);
        ArrayList<V> valuesAux = new ArrayList<>(values);

        //Volvemos a introducir todos los valores previamente introducidos
        for (int i = 0; i < keys.size(); i++) {
            put(keys.get(i), values.get(i));
        }

        //Ambos listados quedan como antes
        keys = keysAux;
        values = valuesAux;
    }

    /**
     * Calcula el indice de la cubeta correspondiente a la llave.
     * @param key key
     * @return entero con el indice
     */
    private int posicionTabla(K key) {
        return key.hashCode() % numeroCubeta;
    }

    /**
     * Coloca elementos en la tabla hash.
     * @param key key
     * @param value valor
     * @return valor anterior si sustituye un valor, null en el resto de casos
     */
    @Override
    public V put(K key, V value) {
        //Comprobamos que la clave no sea nula
        if (key != null) {
            if (indices.get(posicionTabla(key)) == null) {
                //Si la cubeta estaba vacia
                indices.add(posicionTabla(key), new EntradaMultiple<>(key, value));
                keys.add(key);
                values.add(value);
                size++;
                return null;
            } else {
                //Si la cubeta contenia ya datos
                if (indices.get(posicionTabla(key)).buscarClave(key)) {
                    //Si contiene la misma clave sustituimos el valor y devolvemos el previo
                    V valor = indices.get(posicionTabla(key)).getValores().get(indices.get(posicionTabla(key)).indiceClave(key));
                    indices.get(posicionTabla(key)).valores.set(indices.get(posicionTabla(key)).indiceClave(key), value);
                    return valor;
                } else {
                    //Si no tiene la misma clave
                    if (indices.get(posicionTabla(key)).tamanioActualCubeta() < tamanoCubeta) {
                        //Si no se supera el tamaño de la cubeta se añade clave y valor
                        indices.get(posicionTabla(key)).anadirElemento(key, value);
                        size++;
                        keys.add(key);
                        values.add(value);
                        return null;
                    } else {
                        //Si se supera redimensionamos e introducimos el valor
                        redimensionar();
                        put(key, value);
                    }
                }
            }
        }
        return null;
    }


    /**
     * Devuelve el conjunto de llaves.
     * @return set con el conjunto de llaves
     */
    @Override
    public Set<K> keySet() {
        Set<K> llaves = new HashSet<>();
        for (EntradaMultiple<K, V> index : indices) {
            if (index != null) {
                llaves.addAll(index.getClaves());
            }
        }
        return llaves;
    }

    /**
     * Elimina elementos de la tabla hash.
     * @param key key
     * @return valor eliminado, null si no existe la clave
     */
    @SuppressWarnings("unchecked") //Quitamos los warnings por unchecked cast
    @Override
    public V remove(Object key) {
        //Comprobamos que exista la clave
        if (keys.contains(key)) {
            //Borramos las claves y valores de los arrays que las tienen guardadas
            int pos = keys.indexOf(key);
            values.remove(pos);
            keys.remove(pos);
            //Borramos las claves y valores del mapa
            int indice = indices.get(posicionTabla((K) key)).indiceClave((K) key);
            indices.get(posicionTabla((K) key)).claves.remove(indice);
            size--;
            return indices.get(posicionTabla((K) key)).valores.remove(indice);
        } else {
            return null;
        }
    }

    /**
     * Obtiene elementos de la tabla hash.
     * @param key key
     * @return valor correspondiente en la posicion
     */
    @SuppressWarnings("unchecked") //Quitamos los warnings por unchecked cast
    @Override
    public V get(Object key) {

        if (keys.contains(key)) {
            return indices.get(posicionTabla((K) key)).valores.get(indices.get(posicionTabla((K) key)).indiceClave((K) key));
        } else {
            return null;
        }
    }

    /**
     * Devuelve un conjunto de entradas.
     * @return conjunto con las entradas de la tabla
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> a = new HashSet<>();
        for (EntradaMultiple<K, V> index : indices) {
            if (index != null) {
                for (int i = 0; i < index.claves.size(); i++) {
                    a.add(new EntradaPlana<>(index.claves.get(i), index.valores.get(i)));
                }
            }
        }
        return a;
    }

    /**
     * Getter del tamanio.
     * @return valor anterior si sustituye un valor, null en el resto de casos
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resetea la tabla.
     */
    @Override
    public void clear() {
        indices = new ListaRedimensionable<>();
    }

    /**
     * Devuelve un conjunto de valores.
     * @return coleccion con los valores de la tabla
     */
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

}
