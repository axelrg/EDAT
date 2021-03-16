package es.ubu.inf.edat.pr01;

public class IteradorPrimosReverso extends IteradorPrimos {
    protected  Integer actual = 1;

    public IteradorPrimosReverso(int i) {
        actual=i;
    }


    public boolean hasPrevious() {
        if (actual>2)
            return true;
        else
            return false;
    }

    public Integer previous() {
        actual--;
        while (!esPrimo(actual)){
            actual--;
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

    //TODO Implementar por parte del alumno

}
