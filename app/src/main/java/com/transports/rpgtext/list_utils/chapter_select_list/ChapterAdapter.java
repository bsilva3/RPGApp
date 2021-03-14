package com.transports.rpgtext.list_utils.chapter_select_list;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;
        import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.transports.rpgtext.R;
import com.transports.rpgtext.story_handle.ChapterDescription;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    Context ctx;
    LayoutInflater lInflater;
    List<ChapterDescription> chapters;
    ItemClickListener mClickListener;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.chapter_title_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public ChapterAdapter(List<ChapterDescription> chapter) {
        this.chapters = chapter;
        Log.d("test", "in constructor");
    }


    @Override
    public ChapterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_select_item, parent, false);
        Log.d("test", "in view create");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ViewHolder holder, int position) {
        Log.d("test", "in view holder bind");
        //ChapterChecked chapterChecked = (ChapterChecked) getItem(position);

        holder.textView.setText(chapters.get(position).getChapterTitle());

        /*CheckBox cbBuy = (CheckBox) holder.findViewById(R.id.chapter_select_box);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(false);*/
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    // convenience method for getting data at click position
    public ChapterDescription getItem(int id) {
        return chapters.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}