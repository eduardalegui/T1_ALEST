import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ALEST2_T1 {

    public static Map<Character, String> regras = new HashMap<>();
    public static Map<Character, Long> memo = new HashMap<>();

    public static void main(String args[]) {

        final String numeroCaso = "t30_01.txt";
        final String caminhoArquivo = "casos_30/";

        String primeiraLetra = "v";

        try {
            File casoUso = new File(caminhoArquivo + numeroCaso);
            Scanner scanner = new Scanner(casoUso);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();

                if (linha.isEmpty()) {
                    continue;
                }

                String[] strings = linha.split("\\s+");

                if (strings.length >= 2) {
                    char letra = strings[0].charAt(0);
                    String replaceString = strings[1];
                    regras.put(letra, replaceString);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            return;
        }

        if (!regras.containsKey('c')) {
            System.out.println("Aviso: 'c' não possui regra, será tratada como letra final.");
        }

        long startTime = System.currentTimeMillis();
        long resultado = expandir(primeiraLetra);
        long endingTime = System.currentTimeMillis();

        long totalTime = endingTime - startTime;

        System.out.println("Tamanho Final: " + resultado);
        System.out.println("\nTempo decorrido: " + totalTime + " ms");
    }

    public static long expandir(String palavra) {
        long quant = 0;

        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);

            if (memo.containsKey(letra)) {
                quant += memo.get(letra);
                continue;
            }

            String resultado = regras.get(letra);

            if (resultado != null) {
                long tamanhoSubarvore = expandir(resultado);
                memo.put(letra, tamanhoSubarvore);
                quant += tamanhoSubarvore;
            } else {
                // Letra sem regra conta como 1
                quant += 1;
            }
        }

        return quant;
    }
}