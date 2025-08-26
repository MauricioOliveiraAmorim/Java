package terraria;

import java.util.List;

class Floresta extends Bioma {
    public Floresta() {
        super("Floresta", List.of(
                new NPC("Guia"),
                new NPC("Golfista"),
                new NPC("Zoologista")
        ), new Pylon("Floresta", TipoPylon.FLORESTA));
    }
}