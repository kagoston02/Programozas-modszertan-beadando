package org.example;

/**
 * Megfelelteti a félhangokat a megfelelő fajtájuknak, attól függően hogy # vagy b előjegyzésűek.
 */
public enum Note {
    C("C", "B"), // B# és C hangok harmonikusan megyeggyeznek
    C_SHARP("C#", "Db"),
    D("D", "D"),
    D_SHARP("D#", "Eb"),
    E("E", "Fb"), // Fb is megegyezik E-vel
    F("F", "E#"), // E# és F is
    F_SHARP("F#", "Gb"),
    G("G", "G"),
    G_SHARP("G#", "Ab"),
    A("A", "A"),
    A_SHARP("A#", "Bb"),
    B("B", "Cb");

    private final String sharpName;
    private final String flatName;

    Note(String sharpName, String flatName) {
        this.sharpName = sharpName;
        this.flatName = flatName;
    }

    public String getSharpName() {
        return sharpName;
    }

    public String getFlatName() {
        return flatName;
    }

    //megfelelő félhangtípus megkeresése
    public static Note fromString(String name) {
        for (Note note : Note.values()) {
            if (note.sharpName.equalsIgnoreCase(name)) {
                return note;
            }
            if (note.flatName != null && note.flatName.equalsIgnoreCase(name)) {
                return note;
            }
        }
        throw new IllegalArgumentException("Invalid note name: " + name);
    }
}
