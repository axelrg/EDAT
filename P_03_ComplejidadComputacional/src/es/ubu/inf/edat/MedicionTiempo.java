package es.ubu.inf.edat;

import es.ubu.inf.edat.pr03.ColeccionArray2DUtilidades;
import es.ubu.lsi.edat.datos.GeneradorEnteros;

import java.util.List;

public class MedicionTiempo {

    public static void main(String[] args) {
        int[] pruebas = {20, 40, 60, 80, 100, 120, 140, 160, 180};

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

            tiempoInicio = System.nanoTime();
            col.masFrecuente();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            tiempoInicio = System.nanoTime();
            col.sumaHash();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            tiempoInicio = System.nanoTime();
            col.diferenciasHash(3);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            tiempoInicio = System.nanoTime();
            col.diferenciasHash();
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            tiempoInicio = System.nanoTime();
            col.busquedaSecuencial(11);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            tiempoInicio = System.nanoTime();
            col.busquedaBinaria(11);
            tiempoFin = System.nanoTime();
            tiempoTot=((double) tiempoFin-(double) tiempoInicio)/1_000_000_000;
            System.out.printf("%14f ", tiempoTot);

            System.out.println();

            //System.out.println();
        }

    }

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
