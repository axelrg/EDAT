package es.ubu.inf.edat;

import es.ubu.inf.edat.pr03.ColeccionArray2DUtilidades;
import es.ubu.lsi.edat.datos.GeneradorEnteros;

import java.util.List;

/**
 * Clase que mide el tiempo en que se completa cada uno de los métodos, según 
 * varia el número de elementos que se encuentran almacenados en la colección.
 * 
 * @author Axel Rubio Gonzalez
 * @author Alvaro Hoyuelos Martin
 * @version 1.0
 * @since JDK 11
 *  
 */
public class MedicionTiempo {

	/**
	 * Muestra los distintos tiempos que tardan los distintos metodos en completarse
	 * con los distintos lados de la matriz.
	 * 
	 * @param args argumentos de entrada.
	 */
    public static void main(String[] args) {
    	//Elementos
        int[] pruebas = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};

        ColeccionArray2DUtilidades<Integer> col;
        Integer[][] matriz;

        System.out.printf("%14s %14s %14s %14s %14s %14s %14s\n","Elementos","MasFrecuente","SumaHash","DifHash","DifHashMatriz","Secuencial","Binaria");

        long tiempoInicio;
        long tiempoFin;
        double tiempoTot;

        for (int numero:pruebas) {
            matriz = matrizCuadradaAleatoria(numero);
            col = new ColeccionArray2DUtilidades<>(matriz);

            System.out.printf("%14d ", numero*numero);

            //Metodo masFrecuente
            tiempoInicio = System.nanoTime(); //tiempo medido en nanosegundos
            col.masFrecuente();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;//Calculamos el tiempo total en segundos
            System.out.printf("%14f ", tiempoTot);

            //Metodo sumaHash
            tiempoInicio = System.nanoTime();
            col.sumaHash();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            //Metodo diferenciaHash
            tiempoInicio = System.nanoTime();
            col.diferenciasHash(3);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);
            
            //Metodo diferenciaHash matriz de diferencias
            tiempoInicio = System.nanoTime();
            col.diferenciasHash();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            //Metodo busquedaSecuencial
            tiempoInicio = System.nanoTime();
            col.busquedaSecuencial(11);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            //Metodo busquedaBinaria
            tiempoInicio = System.nanoTime();
            col.busquedaBinaria(11);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            System.out.println();
        }

    }

	/**
	 * Metodo auxiliar que genera un array bidimensional del tamaño pedido con contenido aleatorio (entre 0 y 9).
	 * 
	 * @param lado		Parametro de entrada que indica el tamano del lado de la matriz a generar.
	 * 
	 * @return matriz	Matriz con elementos aleatorios.
	 */
    public static Integer[][] matrizCuadradaAleatoria(int lado) {
        int elementos = lado * lado;
        List<Integer> array = GeneradorEnteros.listaAleatoria(elementos, 0, 9);
        Integer[][] matriz = new Integer[lado][lado];
        int contador = 0;
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                matriz[i][j] = array.get(contador);
                contador++;
            }
        }
        return matriz;
    }

}
