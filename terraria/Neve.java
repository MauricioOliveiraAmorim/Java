package terraria;
import java.util.List;

class Neve extends Bioma {
    public Neve() {
        super("Neve", List.of(
                new NPC("Ciborgue"),
                new NPC("Steampunker"),
                new NPC("Alfaiate")
        ), new Pylon("Neve", TipoPylon.NEVE));
    }
}
