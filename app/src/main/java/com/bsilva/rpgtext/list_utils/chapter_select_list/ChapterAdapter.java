package com.bsilva.rpgtext.list_utils.chapter_select_list;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsilva.rpgtext.R;
import com.bsilva.rpgtext.story_handle.ChapterDescription;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    Context ctx;
    LayoutInflater lInflater;
    List<ChapterDescription> chapters;
    private RecyclerViewChapterClickListener itemListener;


    public ChapterAdapter(List<ChapterDescription> chapter, RecyclerViewChapterClickListener itemListener) {
        this.chapters = chapter;
        this.itemListener = itemListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.chapter_title_item);
        }



        public TextView getTextView() {
            return textView;
        }
    }


    @Override
    public ChapterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_select_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ViewHolder holder, final int position) {
        //ChapterChecked chapterChecked = (ChapterChecked) getItem(position);

        holder.textView.setText(chapters.get(position).getChapterTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //item clicked, return index of item clicked
                itemListener.recyclerViewListClicked(position);
            }
        });
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



}