package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Sheet sheet = input();

            // A kotta szerkeztő osztályok példányosítása
            SheetOutputManager outputManager = new SheetOutputManager(sheet.getTitle(), sheet.getComposer(), sheet.getKey(), sheet.getBars(), sheet.getSegment());
            SheetCalculations calculations = new SheetCalculations(sheet.getTitle(), sheet.getComposer(), sheet.getKey(), sheet.getBars(), sheet.getSegment());
            SheetFileOutput fileOutput = new SheetFileOutput(sheet.getTitle(), sheet.getComposer(), sheet.getKey(), sheet.getBars(), sheet.getSegment());


            sheet.setOutputManager(outputManager);
            sheet.setCalculations(calculations);
            sheet.setFileOutput(fileOutput);


            System.out.println(calculations.scale(sheet.getKey())); // Felhasználó által választott hangnem kiírása
            outputManager.generateSheet(sheet.getBars(), sheet.getSegment());

            // Hangjegyek hozzáadása
            int location = 0;

            outputManager.addNoteToSheet(location, scanner, outputManager.getSheet(), sheet.getKey(), sheet.getChromaticScale());
            location++;
            while (location < sheet.getBars() * sheet.getSegment()) {
                System.out.print("Szeretnél hozzáadni még hangjegyeket? (igen/nem): ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("igen")) {
                    System.out.println(calculations.scale(sheet.getKey()));
                    outputManager.addNoteToSheet(location, scanner, outputManager.getSheet(), sheet.getKey(), sheet.getChromaticScale());
                    location++;
                } else if (userInput.equalsIgnoreCase("nem")) {
                    System.out.println("Hangjegyek hozzáadása befejeződött.");
                    break;
                } else {
                    System.out.println("Érvénytelen parancs, az alábbiakat használhatod: 'igen' vagy 'nem'.");
                }
            }

            System.out.println("A végleges kotta:");
            outputManager.displaySheet();
            fileOutput.display(outputManager.getSheet()); // XML-be írás

        } catch (InvalidInputException e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }

    /**
     * A felhasználó megadhatja a kotta paramétereit.
     * @return
     */
    public static Sheet input() throws InvalidInputException {
        Sheet sheet = new Sheet("title", "Composer", "C major", 1, 8);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ez a program egy zenei kottát fog szimulálni, amelyhez tetszőleges hangokat adhatsz hozzá.");

        System.out.println("Nevezd el a kottádat: ");
        sheet.setTitle(scanner.nextLine());

        System.out.println("A szerző neve: ");
        sheet.setComposer(scanner.nextLine());

        System.out.println("Adj meg egy hangnemet (A-tól F-ig egy hang esetleg # vagy b és major/minor pl: C major, A# minor, Eb major): ");
        String key = scanner.nextLine();
        if (!key.matches("[A-G](#|b)? (major|minor)")) { // Hangnem formátum ellenőrzés
            throw new InvalidInputException("Helytelen hangnem formátum!");
        }
        sheet.setKey(key);

        System.out.println("Hány ütemből fog állni a kotta: ");
        int bars = scanner.nextInt();
        if (bars <= 0) { // Helyes bemeneti érték ellenőrzése
            throw new InvalidInputException("Az ütemek száma csak pozitív egész szám lehet.");
        }
        sheet.setBars(bars);

        // Összegzés
        System.out.println("Cím: " + sheet.getTitle() + "\n" +
                "Szerző neve: " + sheet.getComposer() + "\n" +
                "Hangnem: " + sheet.getKey() + "\n" +
                "A kottádhoz választott hangnembe az alábbi hangok tartoznak bele, ettől függetlenül " +
                "bármelyik hangjegyet használhatod, viszont akkor a harmónia megtörhet.");

        return sheet;
    }
}