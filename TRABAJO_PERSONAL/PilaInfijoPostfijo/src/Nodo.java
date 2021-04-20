public class Nodo <E> {
    private E contenido;
    private Nodo siguienteNodo;

    public Nodo (E contenido){
        this.contenido=contenido;
    }

    public void setSiguienteNodo(Nodo siguienteNodo){
        this.siguienteNodo=siguienteNodo;
    }

    public Nodo getSiguienteNodo(){
        return siguienteNodo;
    }

    public E getContenido(){
        return contenido;
    }
}
