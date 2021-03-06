package nl.rrvk.etenenzmeetingen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.rrvk.etenenzmeetingen.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.Holder> {

    private static final int DRAWER_TYPE_HEADER = 0;
    private static final int DRAWER_TYPE_ITEM = 1;
    private String title;
    private final String[] mNavTitles;
    private View.OnClickListener mListener;

    public DrawerAdapter(String[] navTitles, View.OnClickListener listener) {
        mNavTitles = navTitles;
        mListener = listener;
    }

    /**
     * This is the holder. Each holder = 1 item in the drawer
     */
    public static class Holder extends RecyclerView.ViewHolder {
        int type;
        TextView titleTextView;

        View drawerItem;
        TextView itemTextView;

        public Holder(View itemView, int viewType, View.OnClickListener listener) {
            super(itemView);
            type = viewType;

            if (DRAWER_TYPE_HEADER == viewType) {
                titleTextView = (TextView) itemView.findViewById(R.id.drawer_header_text);
            } else if (DRAWER_TYPE_ITEM == viewType) {
                // get the views
                drawerItem = itemView.findViewById(R.id.drawer_item);
                itemTextView = (TextView) itemView.findViewById(R.id.drawer_item_text);
                // add the onclick listener
                drawerItem.setOnClickListener(listener);
            }
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int resourceId = viewType == DRAWER_TYPE_HEADER ? R.layout.header_drawer : R.layout.item_drawer;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resourceId, viewGroup, false);
        Holder holder = new Holder(view, viewType, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        if (isHeader(holder.type)) {
            if (title != null) holder.titleTextView.setText(title);
        } else {
            int position = i - 1;
            holder.itemTextView.setText(mNavTitles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1; // +1 because of header
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) return DRAWER_TYPE_HEADER;
        return DRAWER_TYPE_ITEM;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    public void setHeader(String title) {
        this.title = title;
        notifyDataSetChanged();
    }
}