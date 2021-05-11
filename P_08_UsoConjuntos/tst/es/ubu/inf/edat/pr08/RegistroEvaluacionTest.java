package es.ubu.inf.edat.pr08;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class RegistroEvaluacionTest {

	private static int puntuacion;

	private RegistroEvaluacion clase = null;
	private Set<String> auxiliar = null;

	private NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
	private DecimalFormat df = (DecimalFormat) nf;

	@Before
	public void antes() {
		clase = new RegistroEvaluacion();
	}

	@After
	public void despues() {
		clase.limpiaListado();
	}

	@AfterClass
	public static void puntuacion() {
		System.out.println("Puntuacion: " + puntuacion);
	}

	/**
	 * Metodo que permite la comprobación de la inserción de nuevas notas
	 */
	@Test
	public void insertaNotas() {

		Alumno ret = null;
		ret = clase.anadeNota("11111111A", (float) 5.7);
		assertTrue("Error al añadir", (float) 5.7 == ret.getMedia());
		ret = clase.anadeNota("22222222B", (float) 3.2);
		assertTrue("Error al añadir", (float) 3.2 == ret.getMedia());
		ret = clase.anadeNota("33333333C", (float) 8.5);
		assertTrue("Error al añadir", (float) 8.5 == ret.getMedia());
		ret = (clase.anadeNota("33333333C", (float) 7.7));
		assertTrue("Error al añadir", (float) 8.1 == ret.getMedia());

		assertTrue("Error al calcular numero de alumnos", 3 == clase.numeroAlumnos());

		puntuacion = puntuacion + 2;

	}

	/**
	 * Metodo que permite la comprobacion de las medias sobre los alumnos ya
	 * insertados
	 */
	@Test
	public void calculaMedias() {

		inicializaEvaluaciones();

		assertTrue("Error al calcular media",
				(float) 2.3 == Float.parseFloat(df.format(clase.devuelveNota("11111111A"))));
		assertTrue("Error al calcular media",
				(float) 3.55 == Float.parseFloat(df.format(clase.devuelveNota("22222222B"))));
		assertTrue("Error al calcular media",
				(float) 5.9 == Float.parseFloat(df.format(clase.devuelveNota("33333333C"))));
		assertTrue("Error al calcular media",
				(float) 7.35 == Float.parseFloat(df.format(clase.devuelveNota("44444444D"))));
		assertTrue("Error al calcular media",
				(float) 5.7 == Float.parseFloat(df.format(clase.devuelveNota("55555555E"))));

		puntuacion = puntuacion + 2;

	}

	/**
	 * Metodo que permite la comprobación de la eliminacion de partes de
	 * evaluaciones o de la evaluacion completa de un alumno
	 */
	@Test
	public void eliminaNotas() {

		inicializaEvaluaciones();

		// Elimino nota de alumnos ya insertados (con más de una evaluacion)
		assertTrue("Eliminando notas a alumnos con mas de una evaluacion",
				null != clase.eliminaNota("22222222B", (float) 3.9));
		assertTrue("Eliminando notas a alumnos con mas de una evaluacion",
				null != clase.eliminaNota("44444444D", (float) 6.8));

		System.out.println(df.format(clase.devuelveNota("22222222B")));
		System.out.println(df.format(clase.devuelveNota("44444444D")));

		// Compruebo la nueva media de cada uno (se deberá modificar al haber cambiado
		// el nº de evaluaciones)
		assertTrue("Error al calcular media",
				(float) 3.2 == Float.parseFloat(df.format(clase.devuelveNota("22222222B"))));
		assertTrue("Error al calcular media",
				(float) 7.9 == Float.parseFloat(df.format(clase.devuelveNota("44444444D"))));

		puntuacion = puntuacion + 2;

		// Elimino nota de un alumno no insertado (simplemente devolverá nulo)
		assertNull("Error al buscar alumno inexistente", clase.eliminaNota("123456789E", (float) 6.8));

		assertEquals("Error al calcular numero de alumnos", 5, clase.numeroAlumnos());

		System.out.println(clase);

		puntuacion = puntuacion + 1;

		// Elimino nota de un alumno con una sola nota. En ese instante queda a 0, con
		// lo que
		// debe desalojarse el registro completo del alumno
		assertTrue(null == clase.eliminaNota("11111111A", (float) 8.5));

		assertEquals(4, clase.numeroAlumnos());

		System.out.println(clase);

		puntuacion = puntuacion + 2;

	}

	/***
	 * Metodo que comprueba la obtencion de diferentes listados de alumnos (segun
	 * filtros). Se comprueba que aparezcan ordenados en función de su DNI.
	 */
	@Test
	public void obtenerListadoCompleto() {

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado completo (ordenado por DNI)
		lista = clase.devuelveListado();
		assertEquals(5, lista.size());

		for (int i = 1; i < lista.size(); i++) {
			String dni1 = lista.get(i - 1).getDNI();
			String dni2 = lista.get(i).getDNI();
			assert 0 > dni1.compareTo(dni2);
		}

		puntuacion = puntuacion + 2;

	}

	@Test
	public void obtenerListadoInicio() {

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado con limite inferior
		lista = clase.devuelveListadoHasta("33333333C");
		System.out.println(lista);

		assertEquals(2, lista.size());
		assertEquals("22222222B", lista.get(1).getDNI());

		for (int i = 1; i < lista.size(); i++) {
			String dni1 = lista.get(i - 1).getDNI();
			String dni2 = lista.get(i).getDNI();
			assertTrue(dni1.compareTo(dni2) < 0);
		}

		puntuacion = puntuacion + 2;

	}

	@Test
	public void obtenerListadoFin() {

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado con limite superior

		lista = clase.devuelveListadoDesde("33333333C");
		System.out.println(lista);

		assertEquals(3, lista.size());
		assertEquals("33333333C", lista.get(0).getDNI());

		for (int i = 1; i < lista.size(); i++) {
			String dni1 = lista.get(i - 1).getDNI();
			String dni2 = lista.get(i).getDNI();
			assertTrue(dni1.compareTo(dni2) < 0);
		}

		puntuacion = puntuacion + 2;

	}

	@Test
	public void obtenerListadoRango() {

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado con limite inferior y superior
		lista = clase.devuelveListadoEntre("22222222B", "44444444D");
		System.out.println(lista);

		assertEquals(2, lista.size());
		assertEquals("22222222B", lista.get(0).getDNI());
		assertEquals("33333333C", lista.get(1).getDNI());

		for (int i = 1; i < lista.size(); i++) {
			String dni1 = lista.get(i - 1).getDNI();
			String dni2 = lista.get(i).getDNI();
			assertTrue(dni1.compareTo(dni2) < 0);
		}

		puntuacion = puntuacion + 2;

	}

	@Test
	public void obtenerListadoOrdenado() {

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado ordenado por notas
		lista = clase.devuelveListadoNotas();
		System.out.println(lista);

		assertEquals(5, lista.size());
		assertEquals(2.3, lista.get(0).getMedia(), 0.0001);
		assertEquals(7.35, lista.get(lista.size() - 1).getMedia(), 0.0001);

		for (int i = 1; i < lista.size(); i++) {
			float media1 = lista.get(i - 1).getMedia();
			float media2 = lista.get(i).getMedia();
			assertTrue(media1 <= media2);
		}

		puntuacion = puntuacion + 2;

	}

	/**
	 * Metodo que permite el filtrado de los listados de alumnos para incluir
	 * determinados registros por DNI.
	 */
	@Test
	public void filtraAlumnosIncluyedo() {

		Set<Alumno> filtrado = null;
		inicializaEvaluaciones();
		inicializaAuxiliar();

		// Interseccion
		filtrado = clase.filtraIncluyendoAlumnos(auxiliar);
		assertEquals(2, filtrado.size());

		assertTrue(filtrado.contains(new Alumno("22222222B")));
		assertTrue(filtrado.contains(new Alumno("44444444D")));

		puntuacion = puntuacion + 3;

		System.out.println(filtrado);
	}

	/**
	 * Metodo que permite el filtrado de los listados de alumnos para
	 * excluir determinados registros por DNI.
	 */
	@Test
	public void filtraAlumnosExcluyedo() {

		Set<Alumno> filtrado = null;
		inicializaEvaluaciones();
		inicializaAuxiliar();

		// Diferencia
		filtrado = clase.filtraExcluyendoAlumnos(auxiliar);
		assertEquals(3, filtrado.size());

		System.out.println(filtrado);

		assertFalse(filtrado.contains(new Alumno("22222222B")));
		assertFalse(filtrado.contains(new Alumno("44444444D")));

		puntuacion = puntuacion + 3;

	}

	/**
	 * Metodo auxiliar para los tests de las clases.
	 */
	private void inicializaEvaluaciones() {

		clase.anadeNota("22222222B", (float) 3.2);
		clase.anadeNota("33333333C", (float) 9.1);
		clase.anadeNota("44444444D", (float) 7.9);
		clase.anadeNota("11111111A", (float) 2.3);
		clase.anadeNota("33333333C", (float) 2.7);
		clase.anadeNota("22222222B", (float) 3.9);
		clase.anadeNota("44444444D", (float) 6.8);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);

		assert 5 == clase.numeroAlumnos();

	}

	/**
	 * Metodo auxiliar para los tests de las clases.
	 */
	private void inicializaAuxiliar() {

		auxiliar = new TreeSet<String>();

		auxiliar.add("22222222B");
		auxiliar.add("44444444D");
		auxiliar.add("77777777G");
		auxiliar.add("66666666F");

	}

}
