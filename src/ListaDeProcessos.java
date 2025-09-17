public class ListaDeProcessos {
    private Node cabeça;
    private Node cauda;
    private int tamanho;

    public ListaDeProcessos() {
        this.cabeça = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void AdcionarFim(Processo processo ) {
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

}

