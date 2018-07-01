package com.edgencio.android.mjournal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edgencio.android.mjournal.R;
import com.edgencio.android.mjournal.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgencio on 6/30/18.
 */

public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.EntryViewHolder> {

    private final Context mContext;
    private final EntryItemListener mListener;

    private  List<Entry> mList = new ArrayList<>();


    public EntryListAdapter(Context context, EntryItemListener listener) {
        mContext = context;
        mListener = listener;
    }


    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_entry, parent, false);
        return new EntryViewHolder(view);
        }



    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        Entry entry = mList.get(position);

        holder.ttvTopic.setText(entry.getTopic());
        holder.ttvDescription.setText(entry.getDescription());
        holder.ttvScheduledTo.setText(entry.getScheduledTo().toString());
    /*    holder.ttvCreatedAt.setText(entry.getCreatedAt().toString());*/

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public void replaceData(List<Entry> entries) {
        mList = entries;
        notifyDataSetChanged();
    }





    protected  class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView ttvTopic;
        private final TextView ttvDescription;
        private final TextView ttvScheduledTo;
       /* private final TextView ttvCreatedAt;*/

        public EntryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ttvTopic = (TextView) itemView.findViewById(R.id.ttv_topic);
            ttvDescription = (TextView) itemView.findViewById(R.id.ttv_description);
            ttvScheduledTo = (TextView) itemView.findViewById(R.id.ttv_date_value);
           /* ttvCreatedAt = (TextView) itemView.findViewById(R.id.tv_wind_speed);*/
        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition() ;
            Entry entry = mList.get(position);
        }
    }


        public interface EntryItemListener {
            void onEntryItemClick(Entry item);
        }


    }







