package trabalhomotoqueiro;

import java.io.IOException;
import static java.lang.System.currentTimeMillis;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

public class TrabalhoMotoqueiro {

    private static LinkedList<String> aux = new LinkedList<>();
    private static LinkedList<Integer> texto = new LinkedList<>();
    private static LinkedList<Integer> moto1 = new LinkedList<>();
    private static LinkedList<Integer> moto2 = new LinkedList<>();
    private static LinkedList<Integer> melhorMoto1 = new LinkedList<>();
    private static LinkedList<Integer> melhorMoto2 = new LinkedList<>();
    private static LinkedList<Integer> aleatorio = new LinkedList<>();
    private static int melhorAux, melhorTempo = 9999999;
    private static long tempoInicial;
    private static long tempoFinal;

    public static void main(String[] args) throws IOException {
        LinkedList<Integer> ordenado = new LinkedList<>();

        int maior1, maior2, aux2, auxSoma1 = 0, auxSoma2 = 0;

        aux = LeitorDeArquivo.leitorString("teste5.txt");

        tempoInicial = currentTimeMillis();

        texto = getIntegerArray(aux);

        ordenado = new LinkedList<>(texto);
        Collections.sort(ordenado);
        maior1 = ordenado.getLast();
        ordenado.removeLast();
        texto.removeFirstOccurrence(maior1);
        maior2 = ordenado.getLast();
        ordenado.removeLast();
        texto.removeFirstOccurrence(maior2);

        GeraAleatorio geraAleatorio = new GeraAleatorio(texto, 0);

        while (geraAleatorio.hasNext()) {
            aleatorio = geraAleatorio.next();
            moto1.clear();
            moto2.clear();
            auxSoma1 = 0;
            auxSoma2 = 0;
            moto1 = new LinkedList<>(aleatorio);
            moto2 = new LinkedList<>(pegarValoresRestantes(moto1));

            moto1.add(maior2);
            moto2.add(maior1);

            for (int i = 0; i < moto1.size(); i++) {
                if (i != moto1.size() - 1) {
                    auxSoma1 = auxSoma1 + 2 * moto1.get(i);
                } else {
                    auxSoma1 = auxSoma1 + moto1.get(i);
                }
            }

            for (int i = 0; i < moto2.size(); i++) {
                if (i != moto2.size() - 1) {
                    auxSoma2 = auxSoma2 + 2 * moto2.get(i);
                } else {
                    auxSoma2 = auxSoma2 + moto2.get(i);
                }
            }

//                System.out.println("Aux1 : " + auxSoma1 + " aux2:  " + auxSoma2);
            if (auxSoma1 > auxSoma2) {
                melhorAux = auxSoma1;
            } else {
                melhorAux = auxSoma2;
            }

            if (melhorAux < melhorTempo) {
                melhorMoto1 = new LinkedList<>(moto1);
                melhorMoto2 = new LinkedList<>(moto2);
                melhorTempo = melhorAux;
            }

//            System.out.println("Moto 1: " + moto1);
//            System.out.println("Moto 2: " + moto2);
        }

        tempoFinal = currentTimeMillis();
        System.out.println(melhorMoto1.size()+melhorMoto2.size() + " entradas.");
        System.out.println("Melhor tempo: " + melhorTempo);
        Collections.sort(melhorMoto1);
        Collections.sort(melhorMoto2);
        System.out.println("Melhor combinação para moto 1: " + melhorMoto1);
        System.out.println("Melhor combinação para moto 2: " + melhorMoto2);
        System.out.println("Tempo de processamento: " + (tempoFinal - tempoInicial) + " milisegundos.");
    }

    private static LinkedList<Integer> getIntegerArray(LinkedList<String> stringArray) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);

            }
        }
        return result;
    }

    private static LinkedList<Integer> pegarValoresRestantes(LinkedList<Integer> valoresUsados) {
        LinkedList<Integer> valoresRestantes = new LinkedList<>();
        LinkedList<Integer> aux = new LinkedList<>(texto);

        for (int i = 0; i < valoresUsados.size(); i++) {

            for (int j = 0; j < aux.size(); j++) {
                if (Objects.equals(valoresUsados.get(i), aux.get(j))) {
                    aux.remove(valoresUsados.get(i));
                    j = aux.size();
                }

            }
        }

        valoresRestantes = new LinkedList<>(aux);
        return valoresRestantes;
    }
}
