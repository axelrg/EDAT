
public class PilaEnlazada <E> {
    private Nodo<E> cabeza;

    public PilaEnlazada(){
    }

    public void insertarElemento( E elemento){
        Nodo<E> nodo = new Nodo<>(elemento);

        if (!estaVacia()) {
            nodo.setSiguienteNodo(cabeza);
        }
        cabeza=nodo;
    }

    public E sacarElemento (){
        if (!estaVacia()){
            E elemento = cabeza.getContenido();
            cabeza=cabeza.getSiguienteNodo();
            return elemento;
        } else {
            return null;
        }

    }

    public boolean estaVacia(){
        return cabeza == null;
    }

    public E mostrarPrimero(){
        return cabeza.getContenido();
    }

    public void mostrarContenido(){
        Nodo<E> ultimoNodoMostrado=cabeza;
        while (!estaVacia()){
            System.out.println(ultimoNodoMostrado.getContenido());
            ultimoNodoMostrado=ultimoNodoMostrado.getSiguienteNodo();
        }
    }


}
