public class Processo {
    int id;
    String nome;
    int prioridade;
    int ciclosNecessarios;
    String recursoNecessario;



    public Processo(int id, String nome,int prioridade, int ciclosNecessarios, String recursoNecessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursoNecessario = recursoNecessario;
    }
}
