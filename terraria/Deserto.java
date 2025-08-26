package terraria;

import java.util.List;

class Deserto extends Bioma {
    public Deserto() {
        super("Deserto", List.of(
                new NPC("Enfermeira"),
                new NPC("Comerciante T"),
                new NPC("Comerciante A")
        ), new Pylon("Deserto", TipoPylon.DESERTO));
    }
}