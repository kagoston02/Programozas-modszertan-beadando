package org.example;

/**
 * Tárolja a hangjegyek abszolút helyét a kottában.
 */
public enum StaffPosition {
    // A C és D hangok az alapértelmezett 5 vonal alatt helyezkednek el
    C_BELOW_FIRST_LEDGER("C", 11),
    D_BELOW_FIRST_LINE("D", 10),

    // A többi hang a vonalakon illetve vonalközökben
    E_ON_FIRST_LINE("E", 9),
    F_IN_FIRST_SPACE("F", 8),
    G_ON_SECOND_LINE("G", 7),
    A_IN_SECOND_SPACE("A", 6),
    B_ON_THIRD_LINE("B", 5),
    C_IN_THIRD_SPACE("C", 4);

    private final String noteName;
    private final int index;

    StaffPosition(String noteName, int index) {
        this.noteName = noteName;
        this.index = index;
    }

    public String getNoteName() {
        return noteName;
    }

    public int getIndex() {
        return index;
    }


    public static StaffPosition findByNoteName(String noteName) {
        for (StaffPosition position : StaffPosition.values()) {
            if (position.getNoteName().equalsIgnoreCase(noteName)) {
                return position;
            }
        }
        throw new IllegalArgumentException("Invalid note name: " + noteName);
    }
}
