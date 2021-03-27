package es.ubu.inf.edat.pr03;

import es.ubu.inf.edat.pr02.ColeccionArray2D;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Esta clase extiende la clase ColeccionArray2D, implementada en prácticas anteriores.
 * Por lo tanto, deberá seguir implementando el funcionamiento de la clase ya implementada (herencia),
 * incluyendo varios otros comportamientos nuevos, pensados como utilidades de consulta sobre la
 * estructura de datos en 2D.
 *
 * @param <E> Tipo de datos contenidos en el array 2D que
 */
public class ColeccionArray2DUtilidades<E> extends ColeccionArray2D<E> {

    public ColeccionArray2DUtilidades(E[][] contenido) {
        super(contenido);
    }

    /**
     * Este método permite localizar el elemento más frecuente en la colección.
     * Es decir el elemento que aparece más veces repetido en el contenido de la misma
     *
     * @return Elemento con mayor frecuencia en el contenido de la coleccion
     */
    public E masFrecuente() {
        //TODO Implementar por el alumno
        Iterator<E> iterador = iterator();
        Iterator<E> iteradorDos = iterator();
        int repeticiones = 0;
        E elemento = null;


        for (int i = 0; i < size(); i++) {

            E contenido = iterador.next();
            int contador = 0;

            for (int j = 0; j < size(); j++) {
                if (iteradorDos.next() == contenido) {
                    contador++;
                }

            }

            if (contador > repeticiones) {
                repeticiones = contador;
                elemento = contenido;
            }
        }
        return elemento;
    }

    /**
     * Todos los objetos en Java tienen asignado un código individual y único
     * a lo largo de la ejecución de un programa. Esto permite identificar unívocamente
     * cada objeto y realizar organizaciones y busquedas más eficientes de los datos. Veremos estas
     * más adelante en la asignatura.
     * <p>
     * El código mencionado se llama Hash Code y se puede obtener de cualquier objeto con la llamada:
     * nombreObjeto.hashCode();
     * Este método devuelve un numero entero (único) para cada objeto.
     * <p>
     * Se pide que este método devuelva el sumatorio de los hashCode de todos los objetos que almacena
     * en su interior.
     *
     * @return sumatorio de los códigos hash de todos los elementos contenidos en la colección
     */
    public int sumaHash() {
        //TODO Implementar por el alumno
        Iterator<E> iterador = iterator();
        int suma = 0;

        for (int i = 0; i < size(); i++) {
            suma = suma + iterador.next().hashCode();
        }
        return suma;
    }

    /**
     * En este metodo se solicita comprobar la diferencia del valor del codigo hash de uno de los
     * elementos contenidos en la colección con el resto de elementos que aparecen en la misma.
     * <p>
     * Si consideramos que el codigo hash está relacionado con el contenido de cada objeto, esto
     * nos permitiría conocer "como de similar" es el objeto seleccionado con el resto de los contenidos.
     *
     * @return array de enteros que incluye la diferencia del código hash del elemento que ocupa esa posic posicion que ocupa el elemento que se quiere comprobar, en una iteracion secuencial en la coleción
     * en una iteración secuencial, con el código hash del elemento elegido como referencia (ver parametro de entrada).
     * @paramcion
     */
    public int[] diferenciasHash(int posicion) {
        //TODO Implementar por el alumno
        int[] diferencia = new int[size()];
        Iterator<E> iterador = iterator();

        //Primero averiguo el hash code de la posicion
        int hashPos = -1;
        for (int i = 0; i < size(); i++) {
            if (i == posicion) {
                hashPos = iterador.next().hashCode();
            } else {
                iterador.next();
            }

        }
        //Reseteo el iterador
        iterador = iterator();

        //Finalmente calculo las deiferencias
        for (int i = 0; i < size(); i++) {
            diferencia[i] = Math.abs(iterador.next().hashCode() - hashPos);
        }

        return diferencia;
    }

    /**
     * Matriz de diferencias -> O(n^2)
     *
     * @return
     */
    public int[][] diferenciasHash() {
        //TODO Implementar por el alumno
        int[][] diferencia = new int[size()][size()];
        for (int i = 0; i < size(); i++) {
            diferencia[i] = diferenciasHash(i);
        }
        return diferencia;
    }

    /**
     * Método que devuelve la posición, en una iteracion secuencial, en la que aparecería el elemento
     * que se pasa como parámetro. Si el elemento se encuentra en la primera posición de la iteración, el valor
     * a devolver es 0, de forma análoga a las posiciones de un array.
     * En caso de que el elemento no se encuentre en la colección, se devolverá un número menor que 0 para indicarlo.
     * <p>
     * En este caso se solicita al alumno que realice esta búsqueda de forma secuencial. Es decir, recorriendo el contenido
     * hasta localizar el elemento.
     *
     * @param buscado Elemento que se solicita buscar en la coleccion
     * @return posicion en la que aparecerá el elemento en una iteracion o número negativo si no aparecerá
     */
    public int busquedaSecuencial(E buscado) {
        //TODO Implementar por el alumno
        Iterator<E> iterador = iterator();
        int posicion = -1; //Si no encontramos coincidencia devolvemos -1
        for (int i = 0; i < size(); i++) {
            if (buscado == iterador.next()) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Método que devuelve la posición, en una iteracion secuencial, en la que aparecería el elemento
     * que se pasa como parámetro. Si el elemento se encuentra en la primera posición de la iteración, el valor
     * a devolver es 0, de forma análoga a las posiciones de un array.
     * En caso de que el elemento no se encuentre en la colección, se devolverá un número menor que 0 para indicarlo.
     * <p>
     * En este caso se solicita al alumno que realice esta búsqueda empleando el método de
     * <a href=https://es.wikipedia.org/wiki/B%C3%BAsqueda_binaria> búsqueda binaria</a>.
     * Para ello, el alumno deberá:
     * 1. Realizar la ordenación del contenido. Se puede alamacenar en un array o lista nueva y ordenar ésta.
     * Ver método: Arrays.sort()
     * 2. Realizar la busqueda binaria: comprobar los elementos de los extremos y el cento, seleccionar el subconjunto
     * en el que se debe continuar la búsqueda y repetir.
     *
     * @param buscado Elemento que se solicita buscar en la coleccion
     * @return Posicion en la que aparecerá el elemento en una iteracion o número negativo si no aparecerá
     */

    public int busquedaBinaria(E buscado)  {
        //TODO Implementar por el alumno

        //1º Guardo la coleccion en un array para que pueda ser ordenada con Arrays.sort
        int[] array = new int[size()];
        Iterator<E> iterador = iterator();

        for (int i = 0; i < size(); i++) {
            array[i] = (int) iterador.next();
        }
        Arrays.sort(array);

        //2º Implemento la busqueda
        int posicionInicial = 0;
        int posicionFinal = array.length - 1;
        int mitad;

        while (posicionInicial <= posicionFinal) {
            mitad = (posicionFinal + posicionInicial) / 2;
            if ( array[mitad]== (Integer) buscado) {
                return mitad;
            }else if ((Integer)buscado<(Integer)array[mitad]){
                posicionFinal=mitad-1;
            } else  {
                posicionInicial=mitad+1;
            }
        }

        return -1;

    }
}
