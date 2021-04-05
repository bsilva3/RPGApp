package com.bsilva.rpgtext.settings_screen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.bsilva.rpgtext.R;

//https://stackoverflow.com/a/51987784
public class CustomPreference extends Preference {
    private TextView text;

    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPreference(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder){
        super.onBindViewHolder(holder);
        text = (TextView) holder.findViewById(R.id.text_size_example);
    }

    public void changeFontSize(float size){
        text.setTextSize(size);
    }
}
