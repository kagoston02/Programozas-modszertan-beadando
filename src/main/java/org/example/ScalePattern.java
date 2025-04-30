package org.example;

/**
 * Tárolja a dúr illetve mol skálák lépési mintázatát, amellyel generálhatóak a megadott hangnembe
 * tartozó hangjegyek.
 */
public enum ScalePattern {
    MAJOR(new int[] {2, 2, 1, 2, 2, 2, 1}),
    MINOR(new int[] {2, 1, 2, 2, 1, 2, 2});

    private final int[] pattern;

    ScalePattern(int[] pattern) {
        this.pattern = pattern;
    }

    public int[] getPattern() {
        return pattern;
    }
}
