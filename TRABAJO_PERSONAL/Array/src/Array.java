public class Array {
    private int[] array;
    private int contador;

    public Array(int longitud){
        this.array=new int[longitud];
    }

    public void insertar(int elemento){
        if (contador< array.length){
            array[contador++]=elemento;
        }
        else {
            int array_aux[]= new int[array.length*2];
            for (int i=0; i<array.length; i++ ){
                array_aux[i]=array[i];
            }
            array=array_aux;
            array[contador++]=elemento;
        }
    }

    public void eliminarElemento(int posicion){
        for (int i=0; i< array.length; i++){
            if (i > posicion) {
                array[i-1] = array[i];
            }
        }
        array[array.length-1]=0;
        contador--;
    }

    public void imprimir(){
        for (int x: array) {
            System.out.println(x+" "+contador);
        }
    }

}
