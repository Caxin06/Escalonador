public class Node {
    public Processo processo;
    public Node proximo;
    public Node anterior;


    public Node(Processo processo) {
        this.anterior = null;
        this.processo = processo;
        this.proximo = null;

    }
}
