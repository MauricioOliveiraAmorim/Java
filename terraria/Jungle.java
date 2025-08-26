package terraria;
import java.util.List;

class Jungle extends Bioma {
    public Jungle() {
        super("Jungle", List.of(
                new NPC("Médico bruxo"),
                new NPC("Dríade"),
                new NPC("Pintor")
        ), new Pylon("Jungle", TipoPylon.JUNGLE));
    }
}
