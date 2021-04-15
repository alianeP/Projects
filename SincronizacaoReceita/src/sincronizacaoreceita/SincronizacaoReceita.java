

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
                    executaReceitaService(writer, linha, receitaService, conta);
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
    
    public static void executaReceitaService(BufferedWriter writer, String linha, ReceitaService receitaService, String[] conta) throws Exception {    
        try {
            String contaFormat = FileUtils.formatAccount(conta[1]);
            double saldo = FileUtils.convertDouble(conta[2]);
            boolean result = receitaService.atualizarConta(conta[0], contaFormat, saldo, conta[3]);
            System.out.println("Agencia: " + conta[0] + " Conta: " + contaFormat +
                    " Saldo: "+ saldo + " Status: " + conta[3] + " Result: " + (result? "Succes":"Failure"));
            FileUtils.addContentFile(linha, writer, result);
        } catch (InterruptedException e) {
            System.out.println("Erro ao enviar os dados ao receita service " + e);
        }
    }
}
