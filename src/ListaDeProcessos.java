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

    public void adcionarFim(Processo processo ) {
        Node novoNo = new Node( processo );

        if (this.cabeça == null) {
            this.cabeça = novoNo;
            this.cauda = novoNo;
            tamanho++;
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
        StringBuilder sb = new StringBuilder();
        Node atual = cabeça;
        while (atual != null) {
            sb.append(atual.processo.toString()).append(" ");
            atual = atual.proximo;
        }
        return sb.toString().trim();
    }
}

