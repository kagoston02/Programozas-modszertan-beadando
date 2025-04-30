package org.example;

import java.util.ArrayList;

/**
 * A kottához választott hangnemhez tartozó függvényeket tárolja.
 */
public class SheetCalculations extends Sheet implements SheetOperations {

    public SheetCalculations(String title, String author, String key, Integer bars, Integer segment) {
        super(title, author, key, bars, segment);
    }

    public ArrayList<String> scale(String key){
        return defineScale(key, getChromaticScale());
    }


    public int place(Character note, String key, Character halfType) {
        return notePlace(note, key, getChromaticScale());
    }



}