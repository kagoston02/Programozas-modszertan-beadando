package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Az Interface tárolja a kotta szerkeztéséhez szűkséges parancsok soblonjait.
 */
public interface SheetOperations {
    /**
     * Megjeleníti a kottát, mindig az aktuális formájában.
     *
     * @param sheet A kotta objektumja amelyet egy karaktermátrixként tárol.
     */
    default void display(ArrayList<ArrayList<Character>> sheet) {
        for (ArrayList<Character> row : sheet) {
            for (Character ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /**
     * Létrehozza az alapértelmezett kottát a felhasználó paraméterei alapján
     *
     * @param bars Az ütemek számát jelöli.
     * @param segment Az egy ütemben lévő egységek számát, alapértelmezetten 8.
     * @param sheet A kotta objektum amely egy karaktermátrixként van tárolva.
     */
    default void generate(int bars, int segment, ArrayList<ArrayList<Character>> sheet) {
        for (int i = 13; i >= -3; i--) { // Megfelelő indexeléshez hátrafelé generálódik le a kotta.
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < bars; j++) {
                for (int k = 0; k < segment; k++) {
                    // Minden páratlan indexű sor egy vonalat reprezentál a vonalközt pedig a páros indexűek.
                    if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9) {
                        row.add('_');
                    } else {
                        row.add(' ');
                    }
                }
                row.add('|'); // Ütem elválasztó beszűrása
            }
            sheet.add(row);
        }
        display(sheet);
    }

    /**
     * Hangjegyek hozzáadását teszi lehetővé.
     *
     * @param location
     * @param scanner bemenet
     * @param sheet kotta objektuma (egy karaktermátrix)
     * @param key A felhasználó által megadott hangnem.
     * @param chromaticScale A felhasználó által megadott hangnembe tartozó hangjegyeket tároló lista.
     */
    default void addNote(int location, Scanner scanner, ArrayList<ArrayList<Character>> sheet, String key, Note[] chromaticScale) {
        System.out.print("Adj meg egy hangjegyet (pl C / F# / Gb): ");
        String input = scanner.nextLine();

        // Az első karakter mindenképpen nagybet kell hogy legyen
        String noteName = input.substring(0, 1).toUpperCase() + input.substring(1);

        // Hangjegyformátum ellenőrzése
        if (noteName.matches("[A-G](#|b)?")) {
            try {
                Note note = Note.fromString(noteName); // Match the note correctly
                int rowIndex = notePlace(note.getSharpName().charAt(0), key, chromaticScale); // Use sharpName for placement
                int colIndex = location % sheet.get(0).size();
                sheet.get(rowIndex).set(colIndex, '\u266A'); // Use ♪ symbol for notes
                display(sheet);
            } catch (IllegalArgumentException e) {
                System.out.println("Hiba: " + e.getMessage());
            }
        } else {
            System.out.println("Érvénytelen hangjegy");
        }
    }

    /**
     * Megadja félhangokat tartalmazó hangnem esetén hogy kereszt vagy b-ként jelenjenek meg.
     *
     * @param key A felhasználó által megadott hangnem.
     * @return
     */
    default boolean shouldUseFlatNames(String key) {
        return key.contains("b");
    }

    /**
     * Legenerálja a felhasználó által megadott hangnembe tartozó hangjegyeket.
     *
     * @param key A felhasználó által megadott hangnem
     * @param chromaticScale A felhasználó által megadott hangnembe tartozó hangjegyeket tároló lista.
     * @return
     */
    default ArrayList<String> defineScale(String key, Note[] chromaticScale) {
        boolean useFlatNames = shouldUseFlatNames(key);
        ArrayList<String> notes = new ArrayList<>();
        Note baseNote = Note.fromString(key.split(" ")[0]);
        ScalePattern scalePattern = ScalePattern.valueOf(key.split(" ")[1].toUpperCase());

        int startIndex = baseNote.ordinal();

        // Kezdőhangjegy hozzáadása
        if (useFlatNames && baseNote.getFlatName() != null) {
            notes.add(baseNote.getFlatName());
        } else {
            notes.add(baseNote.getSharpName());
        }

        // Hangnem legenerálása
        for (int step : scalePattern.getPattern()) {
            startIndex = (startIndex + step) % chromaticScale.length;

            Note currentNote = chromaticScale[startIndex];
            if (useFlatNames && currentNote.getFlatName() != null) {
                notes.add(currentNote.getFlatName());
            } else {
                notes.add(currentNote.getSharpName());
            }
        }

        return notes;
    }

    /**
     * Megadja az épp aktuálisan hozzáadni kívánt hangjegy helyét.
     *
     * @param note Az éppen hozzáadni kívánt hangjegy.
     * @param key A felhasználó által megadott hangnem.
     * @param chromaticScale A felhasználó által megadott hangnembe tartozó hangjegyeket tároló lista.
     * @return
     */
    default int notePlace(Character note, String key, Note[] chromaticScale) {
        String noteName = note.toString();
        StaffPosition position = StaffPosition.findByNoteName(noteName);

        return position.getIndex() + 3; // A megfelelő besorláshoz elkell tolni a hangjegyeket 3al
    }
}