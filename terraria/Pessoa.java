package terraria;

public abstract class Pessoa {
    protected String Nome;

    // Construtor
    public Pessoa(String nome) {
        this.Nome = nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }
}
