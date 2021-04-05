package com.bsilva.rpgtext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bsilva.rpgtext.list_utils.main_menu.RecyclerViewMenuOptionClickListener;

public class MainActivity extends AppCompatActivity {

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerViewMenuOptionClickListener itemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view_main, MainMenuFragment.class, null)
                    .commit();
        }

        //initializing views
        //recyclerView = (RecyclerView) findViewById(R.id.main_menu_recycler_view);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        //itemListener = this; // hear item menu clicks and call function in this class to handle it
        //loading main menu options
        //loadRecyclerViewItem();

        /*ChapterLoader chapterLoader = new ChapterLoader();
        ChapterStory ch = chapterLoader.load(this, );
        Log.d("chapter", ch+"");*/
    }

    /*private void loadRecyclerViewItem() {
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


                //navigate to game activity, load chapter 1 for testing
                Intent i=new Intent(this, TextRPGActivity.class);
                i.putExtra(CHAPTER_TITLE_EXTRA, "Chapter 01");
                startActivity(i);
                break;
            case Constants.CHAPTERS_OPT:
                // select chapter option selected
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
            case Constants.NEW_GAME_OPT:
                // continue option selected
                break;
            case Constants.OPTIONS_OPT:
                // settings option selected
                Intent settingsInt = new Intent(this, SettingsActivity.class);
                startActivity(settingsInt);
                break;
        }
    }*/
}
