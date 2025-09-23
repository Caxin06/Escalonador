public class ListaDeProcessos {
    private Node cabeça;
    private Node cauda;
    private int tamanho;

    public ListaDeProcessos() {
        this.cabeça = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public boolean isEmpyt(){
        return cabeça == null;
    }

    public void adicionarFim(Processo processo ) {
        Node novoNo = new Node( processo );

        if (this.cabeça == null) {
            this.cabeça = novoNo;
            this.cauda = novoNo;
            tamanho++;
            return;
        }
        cauda.proximo = novoNo;
        novoNo.anterior = cauda;
        cauda = novoNo;
    }
    public Processo removerDoInicio() {
        if (this.cabeça == null) {
            return null;
        }
        Node removido = cabeça;
        if (cabeça == cauda) {
            cabeça = null;
            cauda = null;
        } else {
            cabeça = cabeça.proximo;
            cabeça.anterior = null;
            removido.proximo = null;
        }
        return removido.processo;
    }

    public String listarProcessos() {
        if (cabeça == null) {
            return "Nenhum processo";
        }

        StringBuilder sb = new StringBuilder();
        Node atual = cabeça;
        int contador = 0;

        while (atual != null) {
            if (contador > 0 && contador % 10 == 0) {
                sb.append("\n                     "); // Indentação para alinhamento
            }
            sb.append("P").append(atual.processo.id);
            if (atual.proximo != null) {
                sb.append(", ");
            }
            atual = atual.proximo;
            contador++;
        }

        return sb.toString();
    }

}

