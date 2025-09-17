public class Processo {
    int id;
    String nome;
    int prioridade;
    int ciclosNecessarios;
    int recursoNecessario;


    public Processo(int id, String nome,int prioridade, int ciclosNecessarios, int recursoNecessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursoNecessario = recursoNecessario;
    }
}
