package com.transports.rpgtext.list_utils.main_menu;

import android.media.Image;

public class MainMenuItem {

    private String heading;
    private int icon;

    public MainMenuItem(String heading, int icon) {
        this.heading = heading;
        this.icon = icon;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
