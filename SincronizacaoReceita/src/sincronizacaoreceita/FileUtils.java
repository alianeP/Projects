
package sincronizacaoreceita;

import java.io.BufferedWriter;
import java.io.IOException;

class FileUtils {
    public static double convertDouble(String value) throws Exception{

        char[] avalue = value.toCharArray();

        for (int i = avalue.length - 1; i > -1; i--) {
            if (',' == avalue[i]) {
                return Double.parseDouble(value.replace(".", "").replace(",", "."));
            } else if ('.' == avalue[i]) {
                return Double.parseDouble(value.replace(",", ""));
            }
        }
        return Double.parseDouble(value);
    }

    public  static String formatAccount(String conta) throws Exception {
        String contaFormat = null;
        if (!conta.isEmpty()) {
            contaFormat = conta.replace("-", "");
        }
        return contaFormat;
    }

    public static void addCotentFile(String linha, BufferedWriter writer, boolean result) {
        try {
            StringBuilder bufSaida = new StringBuilder();

            writer.write(bufSaida.append(linha).append(";").append(result? "Success":"Failure").append("\n").toString());
            writer.flush();

        } catch (Exception e) {
            System.out.println("Erro ao adicionar o conteúdo da coluna result" + e);
        }
    }

    public static void addHeaderFile (String linha, BufferedWriter writer) {
        try {
            StringBuilder newLine = new StringBuilder();
            writer.write(newLine.append(linha).append(";").append("result").append("\n").toString());
            writer.flush();
            System.out.println("=============================Servico Receita=================================");
            System.out.println("Processando arquivo aguarde...");

        } catch (IOException e ) {
            System.out.println("Erro ao adicionar a coluna result" + e);
        }
    }
    
    
}
