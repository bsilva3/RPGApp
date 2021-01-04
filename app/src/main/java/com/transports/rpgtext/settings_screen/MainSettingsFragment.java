package com.transports.rpgtext.settings_screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import com.transports.rpgtext.R;

public class MainSettingsFragment extends PreferenceFragmentCompat {

    private SeekBarPreference fontSizeBar;
    private CustomPreference preferenceLayout;
    private TextView textSizeExample;
    private View view;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.settings, rootKey);

        fontSizeBar = findPreference("font_size");
        preferenceLayout = findPreference("text_layout");
        view = (View) LayoutInflater.from(this.getActivity()).inflate(R.layout.font_text_example_layout, null);
        textSizeExample = view.findViewById(R.id.text_size_example);

        fontSizeBar.setMin(12);
        fontSizeBar.setMax(30);
        fontSizeBar.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                final float progress = Float.valueOf(String.valueOf(newValue));
                preferenceLayout.changeFontSize(progress);
                return true;
            }
        });
    }

}