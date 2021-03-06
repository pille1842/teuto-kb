package de.erixpage.teuto_kb;

import java.io.Serializable;

/**
 * Created by Eric on 05.09.2016.
 */
public class Song implements Serializable {
    private long id;
    private int number;
    private int page;
    private String title;
    private String beginning;
    private String text;
    private String tonality;
    private String author_melody;
    private String author_text;
    private int year_melody;
    private int year_text;
    private String note;

    public Song(long id, int number, int page, String title, String beginning,
                String text, String tonality, String author_melody, String author_text,
                int year_melody, int year_text, String note) {
        this.id = id;
        this.number = number;
        this.page = page;
        this.title = title;
        this.beginning = beginning;
        this.text = text;
        this.tonality = tonality;
        this.author_melody = author_melody;
        this.author_text = author_text;
        this.year_melody = year_melody;
        this.year_text = year_text;
        this.note = note;
    }

    public String toString() {
        return title.equals("") ? beginning : title;
    }

    public String getDisplayText() {
        return beginning;
    }

    public String getDisplayInfo() {
        return "Nr. " + number + ", p. " + page + "  " + title;
    }

    public String getDisplayDetails() {
        String str = getDisplayInfo();
        if ((! author_melody.equals("")) || year_melody > 0 || (! author_text.equals("")) || year_text > 0) {
            str += "\r\n";
        }
        if ((! author_melody.equals("")) || year_melody > 0) {
            str += "Weise: ";
        }
        if (! author_melody.equals("")) {
            str += author_melody;
        }
        if (year_melody > 0) {
            if (! author_melody.equals("")) {
                str += ", ";
            }
            str += Integer.toString(year_melody);
        }
        if (! (str.equals("") && (author_text.equals("") || year_text > 0))) {
            str += "\r\n";
        }
        if ((! author_text.equals("")) || year_text > 0) {
            str += "Worte: ";
        }
        if (! author_text.equals("")) {
            str += author_text;
        }
        if (year_text > 0) {
            if (! author_text.equals("")) {
                str += ", ";
            }
            str += Integer.toString(year_text);
        }
        if (! note.equals("")) {
            if ((!author_melody.equals("")) || year_melody > 0 || (!author_text.equals("")) || year_text > 0) {
                str += "\r\n";
            }
            str += note;
        }
        return str;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTonality() {
        return tonality;
    }

    public void setTonality(String tonality) {
        this.tonality = tonality;
    }

    public String getAuthor_melody() {
        return author_melody;
    }

    public void setAuthor_melody(String author_melody) {
        this.author_melody = author_melody;
    }

    public String getAuthor_text() {
        return author_text;
    }

    public void setAuthor_text(String author_text) {
        this.author_text = author_text;
    }

    public int getYear_melody() {
        return year_melody;
    }

    public void setYear_melody(int year_melody) {
        this.year_melody = year_melody;
    }

    public int getYear_text() {
        return year_text;
    }

    public void setYear_text(int year_text) {
        this.year_text = year_text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
