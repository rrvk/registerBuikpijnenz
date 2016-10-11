package nl.rrvk.etenenzmeetingen.model;


import com.orm.SugarRecord;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by rvank on 9-10-2016.
 */
public class MeldingenBuikpijn extends SugarRecord {
    public String startTime = "";
    public String startDate = "";
    public String endTime = "";
    public String endDate = "";
    public String comments = "";

    public MeldingenBuikpijn() {
    }

    public MeldingenBuikpijn(String startDate, String startTime, String endDate, String endTime, String comments) {
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
        this.comments = comments;
    }


    /**
     * This methode will transfer the string date to java.sql.Date
     * And will return null when failed
     *
     * @return
     */
    public Date transferDate(String date) {
        long ms = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ms = sdf.parse(date).getTime();
        } catch (ParseException e) {
            return null;
        }
        return new Date(ms);
    }

    /**
     * This methode will transfer the string time to java.sql.Time
     * And will return null when failed
     *
     * @return
     */
    public Time transerTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long ms = 0;
        try {
            ms = sdf.parse(time).getTime();
        } catch (ParseException e) {
            return null;
        }
        return new Time(ms);
    }


}
