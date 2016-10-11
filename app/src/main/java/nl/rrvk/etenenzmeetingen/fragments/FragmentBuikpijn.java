package nl.rrvk.etenenzmeetingen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import nl.rrvk.etenenzmeetingen.MainActivity;
import nl.rrvk.etenenzmeetingen.R;
import nl.rrvk.etenenzmeetingen.listeners.DateOnClickListeners;
import nl.rrvk.etenenzmeetingen.listeners.TimeOnClickListener;
import nl.rrvk.etenenzmeetingen.model.MeldingenBuikpijn;
import nl.rrvk.etenenzmeetingen.utils.ActivityUtils;

/**
 * Created by rvank on 9-10-2016.
 */

public class FragmentBuikpijn extends Fragment implements View.OnClickListener {
    EditText startDateText;
    EditText startTimeText;
    EditText endDateText;
    EditText endTimeText;
    EditText commentsText;
    Button saveButton;

    MeldingenBuikpijn melding = null;

    public void setMeldingenBuikpijn() {
        //// TODO: 10-10-2016
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buikpijn, container, false);
        ActivityUtils.setActionbarTitles((MainActivity) getActivity(), R.string.nav_resultaten_buikpijn, null);

        initFields(view);
        return view;
    }

    private void checkOldRegistration() {
        List<MeldingenBuikpijn> meldingenBuikpijn = MeldingenBuikpijn.find(MeldingenBuikpijn.class, "end_Date = ?", "");
        if (meldingenBuikpijn.size()>0){
            melding = meldingenBuikpijn.get(meldingenBuikpijn.size()-1);
            startDateText.setText(meldingenBuikpijn.get(meldingenBuikpijn.size()-1).startDate);
            startTimeText.setText(meldingenBuikpijn.get(meldingenBuikpijn.size()-1).startTime);
            commentsText.setText(meldingenBuikpijn.get(meldingenBuikpijn.size()-1).comments);
        }
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        checkOldRegistration();
        updateTimeAndDate();
    }

    private void updateTimeAndDate() {
        Calendar c = Calendar.getInstance();
        if (melding == null) {
            startDateText.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR));
            startTimeText.setText(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
        } else {
            startDateText.setText(melding.startDate);
            startTimeText.setText(melding.startTime);
            endDateText.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR));
            endTimeText.setText(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
        }
    }

    private void initFields(View view) {
        // start time and date fields
        this.startDateText = (EditText) view.findViewById(R.id.textBuikpijnStartDate);
        this.startTimeText = (EditText) view.findViewById(R.id.textBuikpijnStartTime);
        startDateText.setOnClickListener(new DateOnClickListeners(startDateText, getContext()));
        startTimeText.setOnClickListener(new TimeOnClickListener(startTimeText, getContext()));
        // end time and date fields
        this.endDateText = (EditText) view.findViewById(R.id.textBuikpijnEindDate);
        this.endTimeText = (EditText) view.findViewById(R.id.textBuikpijnEindTime);
        endDateText.setOnClickListener(new DateOnClickListeners(endDateText, getContext()));
        endTimeText.setOnClickListener(new TimeOnClickListener(endTimeText, getContext()));
        // rest
        this.commentsText = (EditText) view.findViewById(R.id.textBuikpijnComments);
        this.saveButton = (Button) view.findViewById(R.id.buikpijnSave);

        saveButton.setOnClickListener(this);

        checkOldRegistration();
        updateTimeAndDate();
    }

    private boolean checkEndDateAndTime() {
        if (!endDateText.getText().toString().isEmpty() && !endTimeText.getText().toString().isEmpty())
            return true;
        else
            return false;
    }

    private boolean checkStartDateAndTime() {
        if (!startDateText.getText().toString().isEmpty() && !startTimeText.getText().toString().isEmpty())
            return true;
        else
            return false;
    }

    private void updateStartDateTime() {
        if (melding == null) {
            melding = new MeldingenBuikpijn();
            melding.startDate = startDateText.getText().toString();
            melding.startTime = startTimeText.getText().toString();
            melding.comments = commentsText.getText().toString();
        }
    }

    private void updateEndDateTime() {
        if (melding != null) {
            melding.endDate = endDateText.getText().toString();
            melding.endTime = endTimeText.getText().toString();
            melding.comments = commentsText.getText().toString();
        }
    }

    private void clearFields() {
        startDateText.setText("");
        startTimeText.setText("");
        endDateText.setText("");
        endTimeText.setText("");
        commentsText.setText("");
    }

    @Override
    public void onClick(View view) {
        if (checkStartDateAndTime()) {
            if (checkEndDateAndTime()) {
                // update time and date
                updateStartDateTime();
                updateEndDateTime();
                // save the melding
                melding.save();
                Toast.makeText(this.getContext(), getString(R.string.notification_buikpijn_add), Toast.LENGTH_SHORT).show();
                // clear all the data
                melding = null;
                clearFields();
            } else {
                if (melding == null) {
                    // update stat date and time
                    updateStartDateTime();
                    // save this part of the melding
                    melding.save();
                    Toast.makeText(this.getContext(), getString(R.string.notification_buikpijn_add_start), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this.getContext(), getString(R.string.notification_buikpijn_add_start_fail), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this.getContext(), getString(R.string.notification_poep_fail_fields), Toast.LENGTH_SHORT).show();
        }
    }
}
