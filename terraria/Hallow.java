package terraria;
import java.util.List;

class Hallow extends Bioma {
    public Hallow() {
        super("Hallow", List.of(
                new NPC("Taberneiro"),
                new NPC("Garota da festa"),
                new NPC("Feiticeiro")
        ), new Pylon("Hallow", TipoPylon.HALLOW));
    }
}