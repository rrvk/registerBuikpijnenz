package nl.rrvk.etenenzmeetingen.model;


/**
 * Created by rvank on 9-10-2016.
 */
public class MeldingenPoep extends DatabaseAndTimeDate {
    public String time;
    public String date;
    public String comments;

    public MeldingenPoep(){}

    public MeldingenPoep(String time, String date, String comments){
        this.time=time;
        this.date=date;
        this.comments=comments;
    }
}
