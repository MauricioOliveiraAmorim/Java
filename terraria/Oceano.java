package terraria;
import java.util.List;

class Oceano extends Bioma {
    public Oceano() {
        super("Oceano", List.of(
                new NPC("Pirata"),
                new NPC("Cabeleireira"),
                new NPC("Pescador")
        ), new Pylon("Oceano", TipoPylon.OCEANO));
    }
}
