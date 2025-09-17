public class Schedule {
    int ContagemCiclosAltaPrioridade = 0;

    ListaDeProcessos ListaBaixaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaMediaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaAltaPrioridade = new ListaDeProcessos();
    ListaDeProcessos ListaBloqueados = new ListaDeProcessos();

    public void AdicionarProcesso(Processo processo) {
        switch (processo.prioridade) {
            case 1:
                ListaBaixaPrioridade.AdcionarFim(processo);
                break;
            case 2:
                ListaMediaPrioridade.AdcionarFim(processo);
                break;

            case 3:
                ListaAltaPrioridade.AdcionarFim(processo);
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
            ListaBloqueados.AdcionarFim(processo);
            System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") bloqueado por recurso DISCO");
            return true;
        }
        processo.ciclosNecessarios--;
        System.out.println("Executando processo " + processo.nome + " (ID " + processo.id + "), ciclos restantes: " + processo.ciclosNecessarios);
        if (processo.ciclosNecessarios == 0) {
            System.out.println("Processo " + processo.nome + " (ID " + processo.id + ") terminou a execução");
        } else {
            lista.AdcionarFim(processo);
        }
        return true;
    }
}
