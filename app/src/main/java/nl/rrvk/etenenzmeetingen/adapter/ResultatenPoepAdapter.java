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
import nl.rrvk.etenenzmeetingen.model.MeldingenPoep;

/**
 * Created by rvank on 9-10-2016.
 */

public class ResultatenPoepAdapter extends RecyclerView.Adapter<ResultatenPoepAdapter.ViewHolder> implements View.OnClickListener {
    private final List<MeldingenPoep> poepMeldingen;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultaat_poep, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemPoepComments.setText(poepMeldingen.get(position).comments);
        holder.itemPoepDate.setText(poepMeldingen.get(position).date.toString());
        holder.itemPoepTime.setText(poepMeldingen.get(position).time.toString());
        holder.iconImageView.setId(poepMeldingen.get(position).getId().intValue());
    }

    @Override
    public int getItemCount() {
        return poepMeldingen.size();
    }

    private MeldingenPoep getPoepById(long id) {
        for (Iterator<MeldingenPoep> i = poepMeldingen.iterator(); i.hasNext(); ) {
            MeldingenPoep item = i.next();
            if (item.getId() == id) {
                i.remove();
                return item;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        MeldingenPoep poep = getPoepById(v.getId());
        if (poep != null) {
            poep.delete();
            this.notifyDataSetChanged();
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_poep_delete), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_poep_fail_delete), Toast.LENGTH_SHORT).show();
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View poepResultaatView;
        ImageView iconImageView;
        TextView itemPoepTime;
        TextView itemPoepDate;
        TextView itemPoepComments;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            // get the vieuws
            poepResultaatView = v.findViewById(R.id.item_resultaat_poep);
            iconImageView = (ImageView) v.findViewById(R.id.poep_resultaten_del);
            itemPoepTime = (TextView) v.findViewById(R.id.poep_resultaten_time);
            itemPoepDate = (TextView) v.findViewById(R.id.poep_resultaten_date);
            itemPoepComments = (TextView) v.findViewById(R.id.poep_resultaten_comments);
            // register listener
            iconImageView.setOnClickListener(listener);
        }
    }

    public ResultatenPoepAdapter(List<MeldingenPoep> poepmeldingen) {
        this.poepMeldingen = poepmeldingen;
    }
}
