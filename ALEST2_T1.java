import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ALEST2_T1{

    //Cria HashMap como armazenamento das regras
    public static Map<Character , String> regras = new HashMap<>();

    public static void main(String args[]){
        
    final String numeroCaso = "x16.txt";
    final String caminhoArquivo = "CasosTeste-32/";
    String primeiraLetra = "";
        
    try {
        File casoUso = new File(caminhoArquivo + numeroCaso);
        Scanner scanner = new Scanner(casoUso);

        String[] primeiraLinha = scanner.nextLine().trim().split("\\s+"); // RegEx
        primeiraLetra = primeiraLinha[0];

        if(primeiraLetra.isEmpty()){
            System.out.println("Erro");
            return;
        }

    }catch(FileNotFoundException e) {
        System.out.println("Erro");
    }
    
    try {
            File casoUso = new File(caminhoArquivo + numeroCaso);
            Scanner scanner = new Scanner(casoUso);
            

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();

                if (linha.isEmpty()) {
                    break;
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
            System.out.println("Numero de caso não encontrado");
            return;
        }
        
    
        long startTime = System.currentTimeMillis();
        long resultado = expandir(primeiraLetra);
        long endingTime = System.currentTimeMillis();

        long totalTime = endingTime - startTime;

        System.out.println("Tamanho Final: " + resultado);
        System.out.println("\nTempo decorrido: " + totalTime);

    }



    public static long expandir(String palavra){
        long quant = 0;
        
        for(int i = 0; i < palavra.length(); i++){
            char letra = palavra.charAt(i);

            String resultado = regras.get(letra);

            if(resultado != null){
                quant += expandir(resultado);
            }else{
                quant += 1;
            }
        }

        return quant;
    }

}

