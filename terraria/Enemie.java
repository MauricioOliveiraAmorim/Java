// Enemie.java
package terraria;

import java.util.Random;

public class Enemie {
    private String nome;
    private int vida;
    private int recompensaMin;
    private int recompensaMax;
    private int dano;

    private static final Random random = new Random();

    public Enemie(String nome, int vida, int recompensaMin, int recompensaMax, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.recompensaMin = recompensaMin;
        this.recompensaMax = recompensaMax;
        this.dano = dano;
    }

    // Método para o inimigo receber ataque e retaliar
    public void receberAtaque(int danoDoJogador, Player jogador) {
        vida -= danoDoJogador;
        System.out.println(jogador.getNome() + " ataca " + nome + " causando " + danoDoJogador + " de dano!");

        if (vida <= 0) {
            int recompensa = random.nextInt(recompensaMax - recompensaMin + 1) + recompensaMin;
            System.out.println("Você derrotou o " + nome + "! Recompensa: " + recompensa + " géis.");
            jogador.coletarRecompensa(recompensa);
        } else {
            retaliar(jogador);
        }
    }

    private void retaliar(Player jogador) {
        jogador.reduzirVida(dano);
        System.out.println(nome + " ataca " + jogador.getNome() + " causando " + dano + " de dano!");
    }

    public String getNome() {
        return nome;
    }
    public int getRMin() {
        return recompensaMin;
    }
    public int getRMax() {
        return recompensaMax;
    }
    public int getDano() {
        return dano;
    }
    public int getVida() {
        return vida;
    }
    
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setRMin(int recompensaMin) {
		this.recompensaMin = recompensaMin;
	}
	public void setRMax(int recompensaMax) {
		this.recompensaMax = recompensaMax;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
}
