package nl.rrvk.etenenzmeetingen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import nl.rrvk.etenenzmeetingen.R;
import nl.rrvk.etenenzmeetingen.model.MeldingenEten;

/**
 * Created by rvank on 9-10-2016.
 */

public class ResultatenEtenAdapter extends RecyclerView.Adapter<ResultatenEtenAdapter.ViewHolder> implements View.OnClickListener {
    private final List<MeldingenEten> etenMeldingen;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultaat_eten, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemEtenDate.setText(etenMeldingen.get(position).date.toString());
        holder.itemEtenName.setText(etenMeldingen.get(position).name.toString());
        holder.itemEtenTime.setText(etenMeldingen.get(position).time.toString());
        holder.itemEtenComments.setText(etenMeldingen.get(position).comments.toString());
        holder.iconImageView.setId(etenMeldingen.get(position).getId().intValue());
    }

    @Override
    public int getItemCount() {
        return etenMeldingen.size();
    }

    /**
     * With this methode the etenmelding will be find by id
     * @param id
     * @return
     */
    private MeldingenEten getEetmeldingById(long id) {
        for (Iterator<MeldingenEten> i = etenMeldingen.iterator(); i.hasNext(); ) {
            MeldingenEten item = i.next();
            if (item.getId() == id) {
                i.remove();
                return item;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        MeldingenEten etenMelding = getEetmeldingById(v.getId());
        if (etenMelding!=null) {
            etenMelding.delete();
            this.notifyDataSetChanged();
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_eten_delete), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_eten_fail_delete), Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView iconImageView;
        TextView itemEtenDate;
        TextView itemEtenTime;
        TextView itemEtenName;
        TextView itemEtenComments;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            iconImageView = (ImageView) v.findViewById(R.id.etenResultaatDel);
            iconImageView.setOnClickListener(listener);
            itemEtenDate = (TextView) v.findViewById(R.id.textResultaatEtenDate);
            itemEtenName = (TextView) v.findViewById(R.id.textResultaatEtenName);
            itemEtenTime = (TextView) v.findViewById(R.id.textResultaatEtenTime);
            itemEtenComments = (TextView) v.findViewById(R.id.textResultaatEtenComments);
        }
    }

    public ResultatenEtenAdapter(List<MeldingenEten> eetMeldingen) {
        this.etenMeldingen = eetMeldingen;
    }
}
