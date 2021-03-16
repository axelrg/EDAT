package es.ubu.inf.edat.pr03;

import es.ubu.inf.edat.pr02.ColeccionArray2D;

/**
 * Esta clase extiende la clase ColeccionArray2D, implementada en prácticas anteriores.
 * Por lo tanto, deberá seguir implementando el funcionamiento de la clase ya implementada (herencia), 
 * incluyendo varios otros comportamientos nuevos, pensados como utilidades de consulta sobre la
 * estructura de datos en 2D.  
 *
 * @param <E> Tipo de datos contenidos en el array 2D que 
 */
public class ColeccionArray2DUtilidades <E> extends ColeccionArray2D<E> {

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
		E elemento;
		int repeticiones=0;

		for (int i=0; i<numeroFilas(); i++){

		}

		return null;
	}

	/**
	 * Todos los objetos en Java tienen asignado un código individual y único
	 * a lo largo de la ejecución de un programa. Esto permite identificar unívocamente
	 * cada objeto y realizar organizaciones y busquedas más eficientes de los datos. Veremos estas
	 * más adelante en la asignatura.
	 * 
	 * El código mencionado se llama Hash Code y se puede obtener de cualquier objeto con la llamada:
	 * nombreObjeto.hashCode();
	 * Este método devuelve un numero entero (único) para cada objeto.
	 * 
	 * Se pide que este método devuelva el sumatorio de los hashCode de todos los objetos que almacena
	 * en su interior.
	 * 
	 * @return sumatorio de los códigos hash de todos los elementos contenidos en la colección
	 */
	public int sumaHash() {
		//TODO Implementar por el alumno
		return 0;
	}

	/**
	 * En este metodo se solicita comprobar la diferencia del valor del codigo hash de uno de los
	 * elementos contenidos en la colección con el resto de elementos que aparecen en la misma.
	 * 
	 * Si consideramos que el codigo hash está relacionado con el contenido de cada objeto, esto 
	 * nos permitiría conocer "como de similar" es el objeto seleccionado con el resto de los contenidos.  
	 * 
	 * @param posicion que ocupa el elemento que se quiere comprobar, en una iteracion secuencial en la coleccion
	 * @return array de enteros que incluye la diferencia del código hash del elemento que ocupa esa posición 
	 * en una iteración secuencial, con el código hash del elemento elegido como referencia (ver parametro de entrada).
	 */
	public int[] diferenciasHash(int posicion) {
		//TODO Implementar por el alumno
		return null;
	}

	/**
	 * Matriz de diferencias -> O(n^2)
	 *  
	 * @return
	 */
	public int[][] diferenciasHash() {
		//TODO Implementar por el alumno
		return null;
	}
	
	/**
	 * Método que devuelve la posición, en una iteracion secuencial, en la que aparecería el elemento
	 * que se pasa como parámetro. Si el elemento se encuentra en la primera posición de la iteración, el valor
	 * a devolver es 0, de forma análoga a las posiciones de un array. 
	 * En caso de que el elemento no se encuentre en la colección, se devolverá un número menor que 0 para indicarlo.
	 * 
	 * En este caso se solicita al alumno que realice esta búsqueda de forma secuencial. Es decir, recorriendo el contenido
	 * hasta localizar el elemento. 
	 * 
	 * @param buscado Elemento que se solicita buscar en la coleccion
	 * @return posicion en la que aparecerá el elemento en una iteracion o número negativo si no aparecerá
	 */
	public int busquedaSecuencial(E buscado) {
		//TODO Implementar por el alumno
		return 0;
	}
		
	/**
	 * Método que devuelve la posición, en una iteracion secuencial, en la que aparecería el elemento
	 * que se pasa como parámetro. Si el elemento se encuentra en la primera posición de la iteración, el valor
	 * a devolver es 0, de forma análoga a las posiciones de un array. 
	 * En caso de que el elemento no se encuentre en la colección, se devolverá un número menor que 0 para indicarlo.
	 * 
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
	
	public int busquedaBinaria(E buscado) {
		//TODO Implementar por el alumno
		return  0;
	}	
}
