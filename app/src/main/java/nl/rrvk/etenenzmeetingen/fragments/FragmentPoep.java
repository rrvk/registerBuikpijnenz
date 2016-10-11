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

import nl.rrvk.etenenzmeetingen.MainActivity;
import nl.rrvk.etenenzmeetingen.R;
import nl.rrvk.etenenzmeetingen.listeners.DateOnClickListeners;
import nl.rrvk.etenenzmeetingen.listeners.TimeOnClickListener;
import nl.rrvk.etenenzmeetingen.model.MeldingenPoep;
import nl.rrvk.etenenzmeetingen.utils.ActivityUtils;

/**
 * Created by rvank on 9-10-2016.
 */

public class FragmentPoep extends Fragment implements View.OnClickListener {
    EditText dateText;
    EditText timeText;
    EditText commentsText;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poep, container, false);
        ActivityUtils.setActionbarTitles((MainActivity) getActivity(), R.string.nav_poep, null);

        initFields(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        updateTimeAndDate();
    }

    private void updateTimeAndDate(){
        Calendar c = Calendar.getInstance();
        dateText.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR));
        timeText.setText(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
    }

    private void initFields(View view) {
        this.dateText = (EditText) view.findViewById(R.id.textBuikpijnStartDate);
        dateText.setOnClickListener(new DateOnClickListeners(dateText, getContext()));

        this.timeText = (EditText) view.findViewById(R.id.textBuikpijnStartTime);
        timeText.setOnClickListener(new TimeOnClickListener(timeText, getContext()));
        this.commentsText = (EditText) view.findViewById(R.id.textMultiComment);

        this.saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        updateTimeAndDate();
    }

    @Override
    public void onClick(View view) {
        if (!dateText.getText().toString().isEmpty() && !timeText.getText().toString().isEmpty()) {
            // insert this poep melding in the database
            MeldingenPoep m = new MeldingenPoep(timeText.getText().toString(), dateText.getText().toString(), commentsText.getText().toString());
            m.save();
            // succes message
            Toast.makeText(this.getContext(), getString(R.string.notification_poep_add), Toast.LENGTH_SHORT).show();
            // empty all the fields
            dateText.setText("");
            timeText.setText("");
            commentsText.setText("");
        } else {
            Toast.makeText(this.getContext(), getString(R.string.notification_poep_fail_fields), Toast.LENGTH_SHORT).show();
        }
    }
}
