package terraria;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player extends Pessoa{
    private int vida;
    private Map<String, Integer> inventario;
    private int casasConstruidas;
    private int slimesDerrotados;

    // Constantes
    private static final int VIDA_MAXIMA = 100;
    private static final int MADEIRA_POR_CASA = 50;
    private static final int GEL_POR_CASA = 2;
    private static final Random random = new Random();

    public Player(String nome) {
        super(nome);
        this.vida = VIDA_MAXIMA;
        this.slimesDerrotados = 0;
        this.inventario = new HashMap<>();
        inventario.put("madeira", 0);
        inventario.put("gel", 0);
        this.casasConstruidas = 0;
    }

    // Método para verificar se o jogador tem materiais suficientes
    public boolean temMateriais() {
        int madeiraDisponivel = inventario.getOrDefault("madeira", 0);
        int gelDisponivel = inventario.getOrDefault("gel", 0);

        return madeiraDisponivel >= MADEIRA_POR_CASA && gelDisponivel >= GEL_POR_CASA;
    }

    // Método para usar materiais para construir uma casa
    public void usarMateriais() {
        if (temMateriais()) {
            inventario.put("madeira", inventario.get("madeira") - MADEIRA_POR_CASA);
            inventario.put("gel", inventario.get("gel") - GEL_POR_CASA);
        } else {
            System.out.println("Materiais insuficientes! Não foi possível construir a casa.");
        }
    }

    // Método para pegar gel ao derrotar slimes
    public void pegarGel() {
        Enemie slime = new Enemie("Slime", 10, 5, 7, 2);  // Slime com 10 de vida e recompensa entre 5-7 géis
        while (slime.getVida() > 0) {
            int danoDoJogador = random.nextInt(5) + 8;  // Dano aleatório entre 8 e 12
            slime.receberAtaque(danoDoJogador, this); // Ataca o slime

            if (vida <= 0) {
                System.out.println("Você foi derrotado! A batalha terminou.");
                break;
            }
        }
    }

    // Método para pegar madeira
    public void pegarMadeira() {
        int madeiraColetada = random.nextInt(10) + 27;  // Entre 27 e 36 unidades de madeira
        adicionarItem("madeira", madeiraColetada);
        System.out.println("Você coletou " + madeiraColetada + " unidades de madeira!");
    }

    // Adiciona itens ao inventário
    private void adicionarItem(String item, int quantidade) {
        inventario.put(item, inventario.getOrDefault(item, 0) + quantidade);
    }

    // Mostrar inventário atual
    public void mostrarInventario() {
        System.out.println("\nStatus de " + Nome + ":");
        System.out.println("- Vida: " + vida);
        System.out.println("- Gel: " + inventario.getOrDefault("gel", 0) + " unidades");
        System.out.println("- Madeira: " + inventario.getOrDefault("madeira", 0) + " unidades");
    }

    // Coletar recompensa de géis
    public void coletarRecompensa(int recompensa) {
        adicionarItem("gel", recompensa);
        slimesDerrotados++;
        if (slimesDerrotados % 15 == 0) {
            recuperarVida();
        }
        System.out.println(Nome + " coletou " + recompensa + " géis de recompensa!");
    }

    // Restaurar vida após derrotar 15 slimes
    private void recuperarVida() {
        vida = VIDA_MAXIMA;
        System.out.println("Parabéns! Vida restaurada após derrotar 15 slimes!");
    }

    public void reduzirVida(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println(Nome + " sofreu " + dano + " de dano. Vida atual: " + vida);
    }
    public int getCasasConstruidas() {
        return casasConstruidas;
    }

    public int getVida() {
        return casasConstruidas;
    }
    public int getSlimesDerrotados() {
        return slimesDerrotados;
    }
    public Map<String, Integer> getInventario() {
        return inventario;
    }
	public void setVida(int vida) {
		this.vida = vida;
	}
	public void setInventario(Map<String, Integer> inventario) {
		this.inventario = inventario;
	}
	public void setCasasConstruidas(int CasasConstruidas) {
		this.casasConstruidas = CasasConstruidas;
	}
	public void setSlimeDerrotados(int SlimeDerrotados) {
		this.slimesDerrotados = SlimeDerrotados;
	}
}
