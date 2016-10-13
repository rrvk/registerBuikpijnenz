package nl.rrvk.etenenzmeetingen.model;

import com.orm.SugarRecord;

/**
 * Created by Robert on 12-10-2016.
 */

public class MeldingenDrinken extends DatabaseAndTimeDate {
    public int volume;
    public String name;
    public String date;
    public String time;

    public MeldingenDrinken() {
    }

    public MeldingenDrinken(int volume, String name, String date, String time) {
        this.volume = volume;
        this.name = name;
        this.date = date;
        this.time = time;
    }

}
