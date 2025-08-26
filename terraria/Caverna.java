package terraria;

import java.util.List;

public class Caverna extends Bioma {
     public Caverna() {
        super("Caverna", List.of(
                new NPC("Demolidor"),
                new NPC("Mecânica"),
                new NPC("Inventor")
        ), new Pylon("Caverna", TipoPylon.CAVERNA));
    }
}
