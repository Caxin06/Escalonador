import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Main arquivo_de_processos.csv");
            return;
        }

        String arquivo = args[0];
        Schedule scheduler = new Schedule();

        try (BufferedReader leitor = Files.newBufferedReader(Paths.get(arquivo), StandardCharsets.UTF_8)) {
            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                linha = linha.trim();

                // Pular linhas vazias ou comentários
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                String[] partes = linha.split(",");

                // Verificar se a linha tem o número correto de campos
                if (partes.length < 4) {
                    System.err.println("Erro na linha " + numeroLinha + ": número insuficiente de campos");
                    continue;
                }

                try {
                    // Limpar caracteres especiais dos números
                    String idStr = limparNumero(partes[0].trim());
                    String prioridadeStr = limparNumero(partes[2].trim());
                    String ciclosStr = limparNumero(partes[3].trim());

                    int id = Integer.parseInt(idStr);
                    String nome = partes[1].trim();
                    int prioridade = Integer.parseInt(prioridadeStr);
                    int ciclos = Integer.parseInt(ciclosStr);
                    String recurso = partes.length > 4 ? partes[4].trim() : null;

                    if (recurso != null && recurso.isEmpty()) {
                        recurso = null;
                    }

                    Processo processo = new Processo(id, nome, prioridade, ciclos, recurso);
                    scheduler.adicionarProcesso(processo);

                } catch (NumberFormatException e) {
                    System.err.println("Erro na linha " + numeroLinha + ": formato de número inválido - " + e.getMessage());
                    System.err.println("Conteúdo da linha: " + linha);
                    continue;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return;
        }

        // Verificar se algum processo foi carregado
        if (scheduler.estaVazio()) {
            System.out.println("Nenhum processo válido foi carregado do arquivo.");
            return;
        }

        int ciclo = 1;
        while (true) {
            System.out.println("Ciclo " + ciclo);
            scheduler.imprimirEstado();
            scheduler.executarCicloDeCPU();

            if (scheduler.estaVazio()) {
                System.out.println("Todos os processos foram concluídos.");
                break;
            }
            ciclo++;
        }
    }

    /**
     * Remove caracteres especiais que podem aparecer em números
     * @param texto O texto a ser limpo
     * @return String contendo apenas dígitos
     */
    private static String limparNumero(String texto) {
        // Remove caracteres não numéricos, mantendo apenas dígitos
        return texto.replaceAll("[^0-9]", "");
    }
}
