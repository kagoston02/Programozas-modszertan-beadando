package org.example;
/*
import java.util.ArrayList;
import java.util.Scanner;

public class ManageSheet extends Sheet implements SheetOperations {
    Scanner scanner = new Scanner(System.in);
    ArrayList<ArrayList<Character>> sheet = new ArrayList<>();

    public ManageSheet(String title, String author, String key, Integer bars, Integer segment) {
        super(title, author, key, bars, segment);
    }

    public ManageSheet(){
        super(null, null, null, 0, 0);
    }


    @Override
    public void generate(int bars, int segments) {

        for (int i = 0; i < 5; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < bars; j++) {
                for (int k = 0; k < segments; k++) {
                    row.add('_');
                }
                row.add('|');
            }
            sheet.add(row);
        }
        System.out.println(defineScale(getKey()));
        display();
    }


    @Override
    public void display() {
        for (ArrayList<Character> row : sheet) {
            for (Character ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }


    public ArrayList<AssignNotes> establishBase() {
        ArrayList<AssignNotes> base = new ArrayList<>();

        base.add(new AssignNotes(0, "C"));
        base.add(new AssignNotes(1, "C#")); // Sharp
        base.add(new AssignNotes(1, "Db")); // Flat equivalent
        base.add(new AssignNotes(2, "D"));
        base.add(new AssignNotes(3, "D#")); // Sharp
        base.add(new AssignNotes(3, "Eb")); // Flat equivalent
        base.add(new AssignNotes(4, "E"));
        base.add(new AssignNotes(5, "F"));
        base.add(new AssignNotes(6, "F#")); // Sharp
        base.add(new AssignNotes(6, "Gb")); // Flat equivalent
        base.add(new AssignNotes(7, "G"));
        base.add(new AssignNotes(8, "G#")); // Sharp
        base.add(new AssignNotes(8, "Ab")); // Flat equivalent
        base.add(new AssignNotes(9, "A"));
        base.add(new AssignNotes(10, "A#")); // Sharp
        base.add(new AssignNotes(10, "Bb")); // Flat equivalent
        base.add(new AssignNotes(11, "B"));

        return base;
    }


    @Override
    public ArrayList<String> defineScale(String key) {
        int[] majorPattern = {2, 2, 1, 2, 2, 2, 1};
        int[] minorPattern = {2, 1, 2, 2, 1, 2, 2};// Major scale step pattern
        ArrayList<AssignNotes> base = establishBase();
        ArrayList<String> notes = new ArrayList<>();

        String baseNote = key.split(" ")[0];
        String scaleType = key.split(" ")[1];// Extract base note (e.g., "C", "C#", "Bb")
        boolean isSharp = key.contains("#");
        boolean isFlat = key.contains("b");

        int startIndex = -1;

        // Locate the starting index in the chromatic scale
        for (int i = 0; i < base.size(); i++) {
            if (base.get(i).getNote().equals(baseNote)) {
                startIndex = i;
                break;
            }
        }

        if (startIndex == -1) {
            System.out.println("Invalid key provided.");
            return notes;
        }

        // Build the scale using the step pattern
        int currentIndex = startIndex;
        notes.add(base.get(currentIndex).getNote()); // Add the first note

        //major or minor
        if(scaleType.equals("major")){
            for (int step : majorPattern) {
                currentIndex = (currentIndex + step) % base.size(); // Wrap around using modulo
                notes.add(base.get(currentIndex).getNote()); // Add the next note
            }
        } else if (scaleType.equals("minor")){
            for (int step : minorPattern) {
                currentIndex = (currentIndex + step) % base.size();
                notes.add(base.get(currentIndex).getNote());
            }
        }

        return notes;
    }


    public int notePlace(Character note) {
        //find the proper height
        ArrayList<String> scale = defineScale(getKey());
        int height = 0;
        for(int i=0; i<8; i++){
            if(scale.get(i).equals(note.toString())){
                height = i;
            }
        }
        return height;
    }


    @Override
    public void addNote( int location) {
        System.out.print("Enter a note (single character): ");
        Character note = scanner.next().charAt(0);
        Integer height = notePlace(note);

        int rowIndex = height;
        int colIndex = location;

        // Insert note into the selected row and column
        sheet.get(rowIndex).set(colIndex, note);
        display();
    }
}
*/

