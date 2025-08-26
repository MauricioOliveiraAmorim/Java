package terraria;

import java.util.List;

// Implementação concreta da interface
public class MatrizNPC implements InterfaceNPC {
    @Override
    public void exibirBiomas(List<Bioma> biomas) {
        System.out.printf("%-15s %-15s %-15s %-15s\n", "NPC", "Casa", "Bioma", "Pylon");
        System.out.println("-----------------------------------------------------------");
        for (Bioma bioma : biomas) {
            for (NPC npc : bioma.getNPCs()) {
                System.out.printf("%-15s %-15s %-15s %-15s\n",
                        npc.getNome(),
                        npc.temCasa() ? "Sim" : "Não",
                        bioma.getNome(),
                        bioma.getPylon().isAtivo() ? "Ativo" : "Inativo");
            }
        }
    }
}
