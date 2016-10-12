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
import nl.rrvk.etenenzmeetingen.model.MeldingenBuikpijn;

/**
 * Created by rvank on 9-10-2016.
 */

public class ResultatenBuikpijnAdapter extends RecyclerView.Adapter<ResultatenBuikpijnAdapter.ViewHolder> implements View.OnClickListener {
    private final List<MeldingenBuikpijn> buikpijnMeldingen;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultaat_buikpijn, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemBuikpijnComments.setText(buikpijnMeldingen.get(position).comments);
        holder.itemBuikpijnStartTime.setText(buikpijnMeldingen.get(position).startTime.toString());
        holder.itemBuikpijnStartDate.setText(buikpijnMeldingen.get(position).startDate.toString());
        holder.itemBuikpijnEndTime.setText(buikpijnMeldingen.get(position).endTime.toString());
        holder.itemBuikpijnEndDate.setText(buikpijnMeldingen.get(position).endDate.toString());
        holder.iconImageView.setId(buikpijnMeldingen.get(position).getId().intValue());
    }

    @Override
    public int getItemCount() {
        return buikpijnMeldingen.size();
    }

    /**
     * With this methode the buikpijnmelding will be find by id
     * @param id
     * @return
     */
    private MeldingenBuikpijn getBuikpijnById(long id) {
        for (Iterator<MeldingenBuikpijn> i = buikpijnMeldingen.iterator(); i.hasNext(); ) {
            MeldingenBuikpijn item = i.next();
            if (item.getId() == id) {
                i.remove();
                return item;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        MeldingenBuikpijn buikpijn = getBuikpijnById(v.getId());
        if (buikpijn!=null) {
            buikpijn.delete();
            this.notifyDataSetChanged();
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_buikpijn_delete), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), v.getContext().getString(R.string.notification_buikpijn_fail_delete), Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View buikpijnResultaatView;
        ImageView iconImageView;
        TextView itemBuikpijnStartTime;
        TextView itemBuikpijnEndTime;
        TextView itemBuikpijnStartDate;
        TextView itemBuikpijnEndDate;
        TextView itemBuikpijnComments;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            buikpijnResultaatView = v.findViewById(R.id.item_resultaat_buikpijn);
            iconImageView = (ImageView) v.findViewById(R.id.buikpijn_resultaten_del);
            iconImageView.setOnClickListener(listener);
            itemBuikpijnStartTime = (TextView) v.findViewById(R.id.buikpijn_resultaten_start_time);
            itemBuikpijnStartDate = (TextView) v.findViewById(R.id.buikpijn_resultaten_start_date);
            itemBuikpijnEndTime = (TextView) v.findViewById(R.id.buikpijn_resultaten_end_time);
            itemBuikpijnEndDate = (TextView) v.findViewById(R.id.buikpijn_resultaten_end_date);
            itemBuikpijnComments = (TextView) v.findViewById(R.id.buikpijn_resultaten_comments);
        }
    }

    public ResultatenBuikpijnAdapter(List<MeldingenBuikpijn> buikpijnMeldingen) {
        this.buikpijnMeldingen = buikpijnMeldingen;
    }
}
