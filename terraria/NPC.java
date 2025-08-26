package terraria;

public class NPC extends Pessoa {
    private boolean casa;

    public NPC(String nome) {
        super(nome); 
        this.casa = false;
    }

    public boolean temCasa() {
        return casa;
    }

    public void setCasa(boolean casa) {
        this.casa = casa;
    }

    @Override
    public String toString() {
        return Nome + ": " + (casa ? "Tem casa" : "Sem casa");
    }
}
