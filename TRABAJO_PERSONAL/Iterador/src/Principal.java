import java.util.Iterator;

public class Principal {
    public static void main(String[] args) {
        ColeccionMatriz<Integer> col= new ColeccionMatriz<>();
        col.contenido = new Integer[3][3];
        int cont=0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                col.contenido[i][j]=cont;
                cont++;
            }
        }
        Iterator<Integer> e = col.iterator();

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                System.out.println(col.contenido[i][j]);
                cont++;
            }
        }


        for (int i=0; i<3; i++){
            System.out.println(e.next());
        }
    }
}
