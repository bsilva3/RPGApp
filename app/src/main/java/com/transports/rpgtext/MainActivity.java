package com.transports.rpgtext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.transports.rpgtext.list_utils.main_menu.CustomAdapter;
import com.transports.rpgtext.list_utils.main_menu.MainMenuItem;
import com.transports.rpgtext.story_handle.ChapterLoader;
import com.transports.rpgtext.story_handle.ChapterStory;

import java.util.ArrayList;
import java.util.List;

import static com.transports.rpgtext.Constants.CHAPTER_TITLE_EXTRA;
import static com.transports.rpgtext.Constants.MAIN_MENU_OPTS_HEADINGS;
import static com.transports.rpgtext.Constants.MAIN_MENU_OPTS_ICONS;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerViewClickListener itemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        recyclerView = (RecyclerView) findViewById(R.id.main_menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        itemListener = this; // hear item menu clicks and call function in this class to handle it
        //loading main menu options
        loadRecyclerViewItem();

        /*ChapterLoader chapterLoader = new ChapterLoader();
        ChapterStory ch = chapterLoader.load(this, );
        Log.d("chapter", ch+"");*/
    }

    private void loadRecyclerViewItem() {
        //you can fetch the data from server or some apis
        //for this tutorial I am adding some dummy data directly
        List <MainMenuItem> list = new ArrayList<>();
        for (int i = 0; i < MAIN_MENU_OPTS_HEADINGS.length; i++) {
            MainMenuItem myList = new MainMenuItem(MAIN_MENU_OPTS_HEADINGS[i], MAIN_MENU_OPTS_ICONS[i]);
            list.add(myList);
        }
        adapter = new CustomAdapter(list, this, itemListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void recyclerViewListClicked(String heading) {
        switch (heading) {
            case Constants.CONTINUE_OPT:
                //Check saved data, if no save, show chapter selection
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ChapterSelectionFragment fragment = new ChapterSelectionFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment);
                fragmentTransaction.commit();*/

                //navigate to game activity, load chapter 1 for testing
                Intent i=new Intent(this, TextRPGActivity.class);
                i.putExtra(CHAPTER_TITLE_EXTRA, "Chapter 01");
                startActivity(i);

                break;
            case Constants.CHAPTERS_OPT:
                // select chapter option selected
                break;
            case Constants.NEW_GAME_OPT:
                // continue option selected
                break;
            case Constants.OPTIONS_OPT:
                // settings option selected
                break;
        }
    }
}
