package com.transports.rpgtext.list_utils.chapter_select_list;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;
        import android.widget.TextView;

import com.transports.rpgtext.R;
import com.transports.rpgtext.story_handle.ChapterDescription;

public class ChapterAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<ChapterDescription> objects;

    public ChapterAdapter(Context context, List<ChapterDescription> chapter) {
        ctx = context;
        objects = chapter;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.chapter_select_item, parent, false);
        }

        ChapterChecked chapterChecked = (ChapterChecked) getItem(position);

        ((TextView) view.findViewById(R.id.chapter_descr)).setText(chapterChecked.title);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.chapter_select_box);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(false);
        return view;
    }

    String getChapterTitle(int position) {
        return ((String) getItem(position));
    }

    ArrayList<ChapterDescription> getBox() {
        ArrayList<ChapterDescription> box = new ArrayList<>();
        for (ChapterDescription c : objects) {
            if (c.box)
                box.add(c);
        }
        return box;
    }
    ChapterDescription getChapterChecked(int position) {
        return ((ChapterDescription) getItem(position));
    }

    OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getChapterChecked((Integer) buttonView.getTag()).box = isChecked;
        }
    };
}