package es.ubu.inf.edat.pr01;

import java.util.Iterator;

public class IteradorPrimos implements Iterator<Integer> {

    protected  Integer actual = 1;


    @Override
    public boolean hasNext() {
        return true;
    }

    public Integer next() {
        actual++;
        while (!esPrimo(actual)){
            actual++;
        }
        return actual;
    }

	private boolean esPrimo(int numero){
        for (int i=2;i<numero; i++){
            if (numero%i==0)
                return false;
        }
        return true;
    }
}
