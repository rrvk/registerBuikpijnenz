package nl.rrvk.etenenzmeetingen.listeners;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by rvank on 9-10-2016.
 */

public class DateOnClickListeners implements OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText dateText;
    private Context context;
    public DateOnClickListeners(EditText dateText, Context context){
        this.dateText=dateText;
        this.context=context;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        dateText.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
