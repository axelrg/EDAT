import java.util.LinkedList;

public class Suma {


    public static void main(String[] args) {
        LinkedList<Integer> operador1= new LinkedList<>();
        LinkedList<Integer> operador2= new LinkedList<>();
        LinkedList<Integer> solucion = new LinkedList<>();
        operador1.addFirst(1);
        operador1.addFirst(9);
        operador1.addFirst(3);
        operador1.addFirst(9);
        operador1.addFirst(7);
        operador1.addFirst(6);

        operador2.addFirst(1);
        operador2.addFirst(9);
        operador2.addFirst(3);
        operador2.addFirst(4);
        operador2.addFirst(7);
        operador2.addFirst(9);

        System.out.println();
        solucion=operacionEnterosLargos(operador1,operador2,'+');
        System.out.println(operador1.toString());
        System.out.println(operador2.toString());
        System.out.println(solucion.toString());


    }



    private static LinkedList<Integer> operacionEnterosLargos(LinkedList<Integer> operador1, LinkedList<Integer> operador2, char operacion){
        int meLlevoPrevio=0;
        int meLlevo=0;
        LinkedList<Integer> solucion = new LinkedList<>();
        if (operacion == '+'){
            for (int i=0; i<operador1.size(); i++){
                meLlevo= (meLlevoPrevio+(operador2.get(operador2.size()-1-i))+(operador1.get(operador1.size()-1-i)))/10;

                if (meLlevo>0){
                    solucion.addFirst((operador2.get(operador2.size()-1-i))+(operador1.get(operador1.size()-1-i)+meLlevoPrevio)-meLlevo*10);
                }else {
                    solucion.addFirst((operador2.get(operador2.size()-1-i))+(operador1.get(operador1.size()-1-i))+meLlevoPrevio);
                }
                meLlevoPrevio=meLlevo;
            }
            if (meLlevo>0){
                solucion.addFirst(meLlevo);
            }
        }
        return solucion;
    }
}
