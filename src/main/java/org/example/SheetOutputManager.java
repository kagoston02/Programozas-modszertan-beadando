package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A kotta megjelenítísét teszi lehetővé.
 */
public class SheetOutputManager extends Sheet implements SheetOperations {
    private ArrayList<ArrayList<Character>> sheet = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public SheetOutputManager(String title, String author, String key, Integer bars, Integer segment) {
        super(title, author, key, bars, segment);
    }


    public void generateSheet(int bars, int segment) {
        generate(bars, segment, sheet);
    }


    public void displaySheet() {
        display(sheet);
    }

    public void addNoteToSheet(int location, Scanner scanner, ArrayList<ArrayList<Character>> sheet, String key, Note[] chromaticScale) {
        addNote(location, scanner, sheet, key, chromaticScale); // Pass the chromaticScale directly
    }

    public ArrayList<ArrayList<Character>> getSheet() {
        return sheet;
    }
}