package nl.rrvk.etenenzmeetingen.listeners;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by rvank on 9-10-2016.
 */

public class TimeOnClickListener implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private EditText timeText;
    private Context context;
    public TimeOnClickListener(EditText timeText, Context context) {
        this.timeText=timeText;
        this.context=context;
    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(context, this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        timeText.setText(String.format("%02d:%02d", hour, minute));
    }
}
