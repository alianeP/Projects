

package sincronizacaoreceita;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SincronizacaoReceita {

    public static void main(String[] args) {
        readFile();
    }
    public static void readFile() {
        try {
            String folderPath = "C:\\Temp";
            
            BufferedReader reader = Files.newBufferedReader(Paths.get(folderPath, "servico.csv"));
            String linha;
            String csvDivisor = ";";

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Temp\\servicoResult.csv"));

            ReceitaService receitaService = new ReceitaService();

            boolean first = true;
            while ((linha = reader.readLine()) != null) {
                String[] conta = linha.split(csvDivisor);

                if (first) {
                    first = false;
                    FileUtils.addHeaderFile(linha, writer);
                } else {
                    boolean result = receitaService.atualizarConta(conta[0], FileUtils.formatAccount(conta[1]), FileUtils.convertDouble(conta[2]), conta[3]);
                    System.out.println("Agencia: " + conta[0] + " Conta: " + FileUtils.formatAccount(conta[1]) +
                            " Saldo: "+ FileUtils.convertDouble(conta[2]) + " Status: " + conta[3] + " Result: " + (result? "Succes":"Failure"));
                    FileUtils.addCotentFile(linha, writer, result);
                }
            }

            writer.close();
            reader.close();
            System.out.println("Arquivo processado com sucesso");
            System.out.println("Verifique o retorno do processamento no arquivo: C:\\Temp\\servicoResult.csv");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
