package terraria;

import java.util.List;

abstract class Bioma {
    protected String nome;
    protected List<NPC> npcs;
    protected Pylon pylon;

    public Bioma(String nome, List<NPC> npcs, Pylon pylon) {
        this.nome = nome;
        this.npcs = npcs;
        this.pylon = pylon;
    }

    public String getNome() {
        return nome;
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

    public Pylon getPylon() {
        return pylon;
    }

	public void setPylon(Pylon pylon) {
		this.pylon = pylon;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNPCs(List<NPC> npcs) {
		this.npcs = npcs;
	}
    public boolean verificarCasas() {
        for (NPC npc : npcs) { // Percorre todos os NPCs da lista
            if (!npc.temCasa()) { // Se algum NPC não tiver casa
                return false; // Retorna false imediatamente
            }
        }
        return true; // Se todos os NPCs têm casa, retorna true
    }


    public void atualizarPylon() {
        if (verificarCasas()) {
            pylon.ativar();
        } else {
            pylon.desativar();
        }
    }

    public void atribuirCasa(String nomeNPC) {
        for (NPC npc : npcs) { // Percorre todos os NPCs da lista
            if (npc.getNome().equals(nomeNPC)) { // Verifica se o nome bate
                npc.setCasa(true); // Atribui a casa ao NPC
                break; // Interrompe o loop, pois já encontrou o NPC
            }
        }
        atualizarPylon(); // Atualiza o estado após a mudança
    }
}
