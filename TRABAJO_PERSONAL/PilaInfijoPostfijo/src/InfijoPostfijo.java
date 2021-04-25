import java.util.ArrayList;

public class InfijoPostfijo {

    static int prioridades(Character caracter) {

        switch (caracter) {
            case '-':
            case '+':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;

            case '(':
            case ')':
                return 4;
        }
        return 0;
    }

    public static void main(String[] args) {
        String infijo = "a-((c*d)+((b*e)-f)*g)";
        StringBuilder postfijo = new StringBuilder();

        PilaEnlazada<Character> pila = new PilaEnlazada<>();

        for (int i = 0; i < infijo.length(); i++) {
            if (infijo.charAt(i) == '-' ||
                    infijo.charAt(i) == '*' ||
                    infijo.charAt(i) == '(' ||
                    infijo.charAt(i) == ')' ||
                    infijo.charAt(i) == '/' ||
                    infijo.charAt(i) == '+' ||
                    infijo.charAt(i) == '^'
            ) {
                if (pila.estaVacia()) {
                    pila.insertarElemento(infijo.charAt(i));
                } else {
                    if (pila.mostrarPrimero() == ')') {
                        while (pila.mostrarPrimero() != '(') {
                            if (pila.mostrarPrimero() != '(' && pila.mostrarPrimero() != ')') {
                                postfijo.append(pila.sacarElemento());
                            } else {
                                pila.sacarElemento();
                            }
                        }
                    }

                    if (prioridades(pila.mostrarPrimero()) >= prioridades(infijo.charAt(i)) && pila.mostrarPrimero() != '(') {
                        while (prioridades(pila.mostrarPrimero()) >= prioridades(infijo.charAt(i))) {
                            postfijo.append(pila.sacarElemento());
                        }
                    } else {
                        pila.insertarElemento(infijo.charAt(i));
                    }

                    if (prioridades(pila.mostrarPrimero()) < prioridades(infijo.charAt(i))) {
                        pila.insertarElemento(infijo.charAt(i));
                    }
                }


            } else {

                postfijo.append(infijo.charAt(i));
            }


            //pila.mostrarContenido();
            //System.out.println(postfijo);

            //System.out.println(prioridades('-'));


        }
        if (!pila.estaVacia()) {
            while (!pila.estaVacia()) {
                if (pila.mostrarPrimero() != '(' && pila.mostrarPrimero() != ')') {
                    postfijo.append(pila.sacarElemento());
                } else {
                    pila.sacarElemento();
                }
            }
        }
        System.out.println("INFIJO  : " + infijo);
        System.out.println("POSTFIJO: " + postfijo);
    }
}
