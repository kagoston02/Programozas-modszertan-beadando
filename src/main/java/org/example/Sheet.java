package org.example;

import java.util.ArrayList;


/**
 * A kottához tartozó alap paramétereket tárolja el.
 */
public class Sheet {
    private Note[] chromaticScale = Note.values();
    private String title;
    private String composer;
    private String key;
    private int bars;
    private int segment;

    private SheetOutputManager outputManager;
    private SheetCalculations calculations;
    private SheetFileOutput fileOutput;

    public Note[] getChromaticScale() {
        return chromaticScale;
    }

    public Sheet(String title, String composer, String key, int bars, int segment) {
        this.title = title;
        this.composer = composer;
        this.key = key;
        this.bars = bars;
        this.segment = segment;

        this.outputManager = null;
        this.calculations = null;
        this.fileOutput = null;

    }

    // Getter Setter metódusok
    public void setOutputManager(SheetOutputManager outputManager) {
        this.outputManager = outputManager;
    }

    public void setCalculations(SheetCalculations calculations) {
        this.calculations = calculations;
    }

    public void setFileOutput(SheetFileOutput fileOutput) {
        this.fileOutput = fileOutput;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String author) {
        this.composer = author;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getBars() {
        return bars;
    }

    public void setBars(int bars) {
        this.bars = bars;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }
}