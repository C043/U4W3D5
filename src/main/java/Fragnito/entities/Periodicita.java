package Fragnito.entities;

import java.util.List;
import java.util.Random;

public enum Periodicita {
    SETTIMANALE, MENSILE, SEMESTRALE;

    private static final List<Periodicita> values =
            List.of(values());
    private static final int size = values.size();
    private static final Random rand = new Random();

    public static Periodicita randomPeriodicita() {
        return values.get(rand.nextInt(size));
    }
}
