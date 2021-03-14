package com.transports.rpgtext;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transports.rpgtext.list_utils.main_menu.CustomAdapter;
import com.transports.rpgtext.list_utils.main_menu.MainMenuItem;
import com.transports.rpgtext.settings_screen.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import static com.transports.rpgtext.Constants.CHAPTER_TITLE_EXTRA;
import static com.transports.rpgtext.Constants.MAIN_MENU_OPTS_HEADINGS;
import static com.transports.rpgtext.Constants.MAIN_MENU_OPTS_ICONS;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MainMenuFragment extends Fragment implements RecyclerViewClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerViewClickListener itemListener;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        //initializing views
        recyclerView = (RecyclerView) view.findViewById(R.id.main_menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2, GridLayoutManager.VERTICAL, false));
        itemListener = this; // hear item menu clicks and call function in this class to handle it
        loadRecyclerViewItem();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadRecyclerViewItem() {
        //you can fetch the data from server or some apis
        //for this tutorial I am adding some dummy data directly
        List<MainMenuItem> list = new ArrayList<>();
        for (int i = 0; i < MAIN_MENU_OPTS_HEADINGS.length; i++) {
            MainMenuItem myList = new MainMenuItem(MAIN_MENU_OPTS_HEADINGS[i], MAIN_MENU_OPTS_ICONS[i]);
            list.add(myList);
        }
        adapter = new CustomAdapter(list, this.getContext(), itemListener);
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
                Intent i=new Intent(this.getActivity(), TextRPGActivity.class);
                i.putExtra(CHAPTER_TITLE_EXTRA, "Chapter 01");
                startActivity(i);
                break;
            case Constants.CHAPTERS_OPT:
                // select chapter option selected
                FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view_main, ChapterSelectionFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();
            case Constants.NEW_GAME_OPT:
                // continue option selected
                break;
            case Constants.OPTIONS_OPT:
                // settings option selected
                Intent settingsInt = new Intent(this.getActivity(), SettingsActivity.class);
                startActivity(settingsInt);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
