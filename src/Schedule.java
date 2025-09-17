public class Schedule {
    int ContagemCiclosAltaPrioridade = 0;

    ListaDeProcessos ListaBaixaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaMediaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaAltaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaBloqueados = new ListaDeProcessos();

    public void adicionarProcesso(Processo processo) {
        switch (processo.prioridade) {
            case 1:
                ListaAltaPrioridade.adcionarFim(processo);
                break;
            case 2:
                ListaMediaPrioridade.adcionarFim(processo);
                break;

            case 3:
                ListaBaixaPrioridade.adcionarFim(processo);
                break;

            default:
                throw new IllegalArgumentException("Prioridade inválida");
        }
    }

    private void desbloquearProcesso() {
        Processo processo = ListaBloqueados.removerDoInicio();
        if (processo != null) {
            System.out.println("Desbloqueando processo " + processo.nome + " (ID " + processo.id + ")");
            adicionarProcesso(processo);
        }
    }

    private boolean executarProcessoDaLista(ListaDeProcessos lista) {
        Processo processo = lista.removerDoInicio();
        if (processo == null) {
            return false;
        }
        boolean discoOcupado = false;
        if ("DISCO".equals(processo.recursoNecessario)) {
            if (discoOcupado) {
                // Reinsere o processo no final da lista, pois recurso está ocupado
                lista.adcionarFim(processo);
                System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") aguardando recurso DISCO");
                return true;
            } else {
                // Recurso liberado, ocupa o recurso e executa o processo
                discoOcupado = true;
                System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") está usando o recurso DISCO");
            }
        }

        // Executa o processo
        processo.ciclosNecessarios--;
        System.out.println("Executando processo " + processo.nome + " (ID " + processo.id + "), ciclos restantes: " + processo.ciclosNecessarios);

        if (processo.ciclosNecessarios == 0) {
            System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") terminou a execução");
            if ("DISCO".equals(processo.recursoNecessario)) {
                discoOcupado = false; // libera o recurso
            }
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
                ListaBaixaPrioridade.isEmpyt() &&
                ListaBloqueados.isEmpyt();
    }
}

