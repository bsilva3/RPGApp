package com.transports.rpgtext.settings_screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.transports.rpgtext.R;

public class SettingsActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);

            MainSettingsFragment settingsFragment = new MainSettingsFragment();
            //If you want to insert data in your settings
            //settingsFragment. ....;

            getSupportFragmentManager().beginTransaction().replace(R.id.settings_frame, settingsFragment).commit();
        }

}
