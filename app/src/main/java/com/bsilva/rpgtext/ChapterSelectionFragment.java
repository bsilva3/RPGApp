package com.bsilva.rpgtext;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bsilva.rpgtext.list_utils.chapter_select_list.ChapterAdapter;
import com.bsilva.rpgtext.list_utils.chapter_select_list.RecyclerViewChapterClickListener;
import com.bsilva.rpgtext.story_handle.ChapterDescription;
import com.bsilva.rpgtext.story_handle.ChapterLoader;

import java.util.ArrayList;
import java.util.List;


public class ChapterSelectionFragment extends Fragment implements RecyclerViewChapterClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView chapterDescr;
    private TextView chapterNumber;
    private TextView chapterTitle;
    private RecyclerView chapterListRecyclerView;
    private ChapterAdapter chapterAdapter;
    private List<ChapterDescription> chapters;
    private ChapterLoader chapterLoader;

    private RecyclerViewChapterClickListener chapterClickListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChapterSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterSelectionFragment newInstance(String param1, String param2) {
        ChapterSelectionFragment fragment = new ChapterSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_chapter_selection, container, false);
        chapterDescr = view.findViewById(R.id.chapter_descr);
        chapterNumber = view.findViewById(R.id.chapter_number);
        chapterTitle = view.findViewById(R.id.chapter_title);
        chapterListRecyclerView = view.findViewById(R.id.list_chapters);

        //chapterAdapter.setClickListener(this);
        chapterListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //get list of chapters
        //chapterLoader = new ChapterLoader();
        chapters = new ArrayList<>();
        //chapters = chapterLoader.getAvailableChapters(this.getActivity());
        chapters.add(new ChapterDescription("Test", 1, "eshgf", "ufnaiufu.json"));
        chapters.add(new ChapterDescription("MyTest", 2, "afsdasf", "safgsdhs.json"));
        chapterClickListener = this;
        chapterAdapter = new ChapterAdapter(chapters, chapterClickListener);
        chapterListRecyclerView.setAdapter(chapterAdapter);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chapter_selection, container, false);
        return view;//!!!! <- return view created, do not inflate again!
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void recyclerViewListClicked(int index) {
        Toast.makeText(getContext(), chapters.get(index).getChapterTitle()+"", Toast.LENGTH_SHORT).show();
        setTitleInfo(index);

    }

    public void setTitleInfo(int index){
        ChapterDescription c = chapters.get(index);
        chapterNumber.setText(c.getChapterNumber());
        chapterTitle.setText(c.getChapterTitle());
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
