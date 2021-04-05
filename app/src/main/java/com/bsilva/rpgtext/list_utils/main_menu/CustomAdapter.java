package com.bsilva.rpgtext.list_utils.main_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bsilva.rpgtext.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MainMenuItem> list;
    private Context mCtx;
    private RecyclerViewMenuOptionClickListener itemListener;

    public CustomAdapter(List<MainMenuItem> list, Context mCtx, RecyclerViewMenuOptionClickListener itemListener) {
        this.itemListener = itemListener;
        this.list = list;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_menu_list_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MainMenuItem myList = list.get(position);
        holder.textViewHead.setText(myList.getHeading());
        holder.icon.setImageResource(myList.getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mCtx, myList.getHeading()+"", Toast.LENGTH_SHORT).show();
                itemListener.recyclerViewListClicked(myList.getHeading());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.menu_item_heading);
            icon = (ImageView) itemView.findViewById(R.id.menu_item_icon);
        }
    }
}