package com.samples.devtools.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nishanthkumarg on 3/29/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private List<DemoItem> feedItemList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<DemoItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final DemoItem demoItem = feedItemList.get(position);
        holder.textView.setText(demoItem.getTitle());
        holder.button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).onItemClick(demoItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size():0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView textView;
        protected TextView button;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.button = (Button) view.findViewById(R.id.btn_action);
        }
    }
}
