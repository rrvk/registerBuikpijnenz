package nl.rrvk.etenenzmeetingen.model;

/**
 * Created by Robert on 12-10-2016.
 */

public class MeldingenEten extends DatabaseAndTimeDate {
    public String comments;
    public String name;
    public String date;
    public String time;

    public MeldingenEten() {
    }

    public MeldingenEten(String comments, String name, String date, String time) {
        this.comments = comments;
        this.name = name;
        this.date = date;
        this.time = time;
    }

}
