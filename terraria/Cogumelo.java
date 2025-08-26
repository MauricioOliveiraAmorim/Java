package terraria;

import java.util.List;

public class Cogumelo extends Bioma {
    public Cogumelo() {
        super("Cogumelo", List.of(
                new NPC("Trufa"),
                new NPC("Comerciante"),
                new NPC("Cobrador")
        ), new Pylon("Cogumelo", TipoPylon.COGUMELO));
    }
}
