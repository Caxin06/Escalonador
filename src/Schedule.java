public class Schedule {
    int ContagemCiclosAltaPrioridade = 0;

    ListaDeProcessos ListaBaixaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaMediaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaAltaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaBloqueados = new ListaDeProcessos();

    public void AdicionarProcesso(Processo processo) {
        switch (processo.prioridade) {
            case 1:
                ListaBaixaPrioridade.adcionarFim(processo);
                break;
            case 2:
                ListaMediaPrioridade.adcionarFim(processo);
                break;

            case 3:
                ListaAltaPrioridade.adcionarFim(processo);
                break;

            default:
                throw new IllegalArgumentException("Prioridade inválida");
        }
    }

    private void desbloquearProcesso() {
        Processo processo = ListaBloqueados.removerDoInicio();
        if (processo != null) {
            System.out.println("Desbloqueando processo " + processo.nome + " (ID " + processo.id + ")");
            AdicionarProcesso(processo);
        }
    }

    private boolean executarProcessoDaLista(ListaDeProcessos lista) {
        Processo processo = lista.removerDoInicio();
        if (processo == null) {
            return false;
        }

        if ("DISCO".equals(processo.recursoNecessario)) {
            // Bloqueia o processo na primeira vez que solicita o recurso
            ListaBloqueados.adcionarFim(processo);
            System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") bloqueado por recurso DISCO");
            return true;
        }

        // Executa o processo
        processo.ciclosNecessarios--;
        System.out.println("Executando processo " + processo.nome + " (ID " + processo.id + "), ciclos restantes: " + processo.ciclosNecessarios);

        if (processo.ciclosNecessarios == 0) {
            System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") terminou a execução");
        } else {
            // Reinsere no final da lista original
            lista.adcionarFim(processo);
        }
        return true;
    }

    public void executarCicloDeCPU() {
        desbloquearProcesso();

        if (ContagemCiclosAltaPrioridade >= 5) {
            if (executarProcessoDaLista(ListaMediaPrioridade)) {
                ContagemCiclosAltaPrioridade = 0;
                return;
            }
            if (executarProcessoDaLista(ListaBaixaPrioridade)) {
                ContagemCiclosAltaPrioridade = 0;
                return;
            }
            ContagemCiclosAltaPrioridade = 0;
            System.out.println("Nenhum processo de prioridade média ou baixa para evitar inanição.");
            return;
        }

        if (executarProcessoDaLista(ListaAltaPrioridade)) {
            ContagemCiclosAltaPrioridade++;
            return;
        }
        if (executarProcessoDaLista(ListaMediaPrioridade)) {
            ContagemCiclosAltaPrioridade = 0;
            return;
        }
        if (executarProcessoDaLista(ListaBaixaPrioridade)) {
            ContagemCiclosAltaPrioridade = 0;
            return;
        }

        System.out.println("Nenhum processo para executar neste ciclo.");
    }

    public void imprimirEstado() {
        System.out.println("Estado atual do Scheduler:");
        System.out.println("Alta prioridade: " + ListaAltaPrioridade.listarProcessos());
        System.out.println("Média prioridade: " + ListaMediaPrioridade.listarProcessos());
        System.out.println("Baixa prioridade: " + ListaBaixaPrioridade.listarProcessos());
        System.out.println("Bloqueados: " + ListaBloqueados.listarProcessos());
        System.out.println("Contador ciclos alta prioridade: " + ContagemCiclosAltaPrioridade);
        System.out.println("--------------------------------------------------");
    }

    public boolean estaVazio() {
        return ListaAltaPrioridade.isEmpyt() &&
                ListaMediaPrioridade.isEmpyt() &&
                ListaMediaPrioridade.isEmpyt() &&
                ListaMediaPrioridade.isEmpyt();
    }
}

