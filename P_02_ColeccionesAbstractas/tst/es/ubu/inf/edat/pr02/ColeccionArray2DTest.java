package es.ubu.inf.edat.pr02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.*;

public class ColeccionArray2DTest {

	protected Integer[][] arrayInteger;
	protected ColeccionArray2D<Integer> coleccionInteger;

	String[][] arrayString;
	ColeccionArray2D<String> coleccionString;

	@Before
	public void setUp() throws Exception {

		// Creamos un array bidimensional de enteros
		arrayInteger = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

		System.out.println("arrayInteger es " + Arrays.deepToString(arrayInteger));

		// Creamos un array bidimensional de cadenas
		arrayString = new String[][] { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" }, { "10", "11", "12" } };

		System.out.println("arrayString es " + Arrays.deepToString(arrayString));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void pruebaColeccionArray2D_Integer() {

		// Obtenemos una colección a partir del array bidimensional
		coleccionInteger = new ColeccionArray2D<Integer>(arrayInteger);

		// Comprobamos la colección
		assertEquals(12, coleccionInteger.size());

		Integer[] control = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		/*
		 * La iteracion con for-each llama internamente al metodo Iterator de la
		 * coleccion
		 */
		int i = 0;
		for (Integer elem : coleccionInteger) {
			assertEquals(control[i], elem);
			i++;
		}
	}

	@Test
	public void pruebaColeccionArray2D_Integer_Modificado() {

		pruebaColeccionArray2D_Integer();

		// Cambiamos algun elemento del array
		arrayInteger[0][0] = -1;
		arrayInteger[1][1] = -5;
		arrayInteger[2][2] = -9;

		// Comprobamos que tambien ha cambiado la coleccion
		assertEquals(12, coleccionInteger.size());

		/*
		 * Como se puede ver, la colección es una VISTA sobre los datos que le hemos
		 * pasado en el constructor. Al cambiar los datos a los que hace referencia,
		 * también cambian en nuestra colección.
		 */
		Integer[] control = { -1, 2, 3, 4, -5, 6, 7, 8, -9, 10, 11, 12 };

		/*
		 * La iteracion con for-each llama internamente al metodo Iterator de la
		 * coleccion
		 */
		int i = 0;
		for (Integer elem : coleccionInteger) {
			assertEquals(control[i], elem);
			i++;
		}

	}

	@Test
	public void pruebaColeccionArray2D_String() {

		System.out.println("arrayInteger es " + Arrays.deepToString(arrayInteger));

		// Obtenemos una colección a partir del array bidimensional
		coleccionString = new ColeccionArray2D<String>(arrayString);

		// Comprobamos la colección
		assertEquals(12, coleccionString.size());

		String[] control = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

		int i = 0;
		for (String elem : coleccionString) {
			assertEquals(control[i], elem);
			i++;
		}
	}

	@Test
	public void testMetodosNoImplementados_remove() {

		coleccionInteger = new ColeccionArray2D<Integer>(arrayInteger);

		/*
		 * Notar que aunque no hemos implementado el metodo remove() ni el contains(),
		 * la clase AbstactCollection ya nos proporciona esos metodos, basandose en el
		 * iterado que si hemos tenido que implementar nosotros.
		 */
		coleccionInteger.remove(5);
		assertFalse(coleccionInteger.contains(5));

	}

	@Test
	public void iteratorRemove() {

		int i = 0;
		coleccionString = new ColeccionArray2D<String>(arrayString);

		// Se adelante el iterador hasta el elemento "4"
		Iterator<String> it = coleccionString.iterator();
		for (; i < 5 && it.hasNext(); i++) {
			System.out.println(it.next());
		}

		// Se elimina el 4
		// El elemento que se elimina tiene que ser el ultimo devuelto
		it.remove();

		// Notese que el alumno NO necesita implementar el metodo contains()
		// y sin embargo se puede ejecutar, ya que está incluido en la implementacion de
		// AbstractColletion

		assertFalse(coleccionString.contains("5"));
		assertTrue(it.hasNext());
		System.out.println(coleccionString);

		for (; i < 12 && it.hasNext(); i++) {
			System.out.println(it.next());
		}

		it.remove(); // Se elimina el ultimo devuelto
		assertFalse(coleccionString.contains("12"));
		assertFalse(it.hasNext());
		System.out.println(coleccionString);

	}
}
