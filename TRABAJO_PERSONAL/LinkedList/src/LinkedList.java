public class LinkedList {
    private Nodo cabeza;
    private Nodo cola;

    public void addPrimero(int dato) {
        Nodo nodo= new Nodo(dato);
        if (cabeza==null){
            cabeza=cola=nodo;
        }else{
            nodo.siguienteNodo = cabeza.siguienteNodo;
            cabeza=nodo;
        }
    }

    public void addUltimo(int dato) {
        Nodo nodo = new Nodo(dato);
        if (cabeza==null){
            cabeza=cola=nodo;
        }else{
            cola.siguienteNodo=nodo;
            cola=nodo;
        }
    }

    public void imprimir(){
        Nodo nodo=cabeza;

        while (nodo.siguienteNodo!=null){

            System.out.print(nodo.valor);
            nodo=nodo.siguienteNodo;


        }
        System.out.println("");
    }
}
