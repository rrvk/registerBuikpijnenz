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
import nl.rrvk.etenenzmeetingen.model.MeldingenDrinken;

/**
 * Created by rvank on 9-10-2016.
 */

public class ResultatenDrinkenAdapter extends RecyclerView.Adapter<ResultatenDrinkenAdapter.ViewHolder> implements View.OnClickListener {
    private final List<MeldingenDrinken> drinkenMeldingen;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultaat_drinken, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemDrinkenDate.setText(drinkenMeldingen.get(position).date.toString());
        holder.itemDrinkenName.setText(drinkenMeldingen.get(position).name.toString());
        holder.itemDrinkenTime.setText(drinkenMeldingen.get(position).time.toString());
        holder.itemDrinkenVolume.setText(""+drinkenMeldingen.get(position).volume);
        holder.iconImageView.setId(drinkenMeldingen.get(position).getId().intValue());
    }

    @Override
    public int getItemCount() {
        return drinkenMeldingen.size();
    }

    /**
     * With this methode the drinkenmelding will be find by id
     * @param id
     * @return
     */
    private MeldingenDrinken getDrinkMeldingById(long id) {
        for (Iterator<MeldingenDrinken> i = drinkenMeldingen.iterator(); i.hasNext(); ) {
            MeldingenDrinken item = i.next();
            if (item.getId() == id) {
                i.remove();
                return item;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        MeldingenDrinken drinkMelding = getDrinkMeldingById(v.getId());
        if (drinkMelding!=null) {
            drinkMelding.delete();
            this.notifyDataSetChanged();
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_drinken_delete), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_drinken_fail_delete), Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View etemResultaatView;
        ImageView iconImageView;
        TextView itemDrinkenDate;
        TextView itemDrinkenTime;
        TextView itemDrinkenName;
        TextView itemDrinkenVolume;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            etemResultaatView = v.findViewById(R.id.item_resultaat_drinken);
            iconImageView = (ImageView) v.findViewById(R.id.drinkenResultaatDel);
            iconImageView.setOnClickListener(listener);
            itemDrinkenDate = (TextView) v.findViewById(R.id.textResultaatDrinkenDate);
            itemDrinkenName = (TextView) v.findViewById(R.id.textResultaatDrinkenName);
            itemDrinkenTime = (TextView) v.findViewById(R.id.textResultaatDrinkenTime);
            itemDrinkenVolume = (TextView) v.findViewById(R.id.textResultaatDrinkenVolume);
        }
    }

    public ResultatenDrinkenAdapter(List<MeldingenDrinken> drinkMeldingen) {
        this.drinkenMeldingen = drinkMeldingen;
    }
}
