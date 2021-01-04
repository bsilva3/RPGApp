package com.transports.rpgtext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transports.rpgtext.story_handle.ChapterLoader;
import com.transports.rpgtext.story_handle.ChapterStory;
import com.transports.rpgtext.story_handle.Scene;

import java.util.Map;

import in.codeshuffle.typewriterview.TypeWriterListener;
import in.codeshuffle.typewriterview.TypeWriterView;

import static com.transports.rpgtext.Constants.CHAPTER_TITLE_EXTRA;

//Typewritter effect text from: https://github.com/skymansandy/typewriterview

public class TextRPGActivity extends AppCompatActivity implements TypeWriterListener {

    private ChapterStory chapterStory;
    private String chapterTitle;
    private TextView chapterTitleText;
    private TypeWriterView sceneText;
    private LinearLayout mainLayout;
    private Button optBtn1;
    private Button optBtn2;
    private Button optBtn3;
    private Button optBtn4;
    private Button next;
    private String[] choiceIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide top bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        choiceIDs = new String[0];
        setContentView(R.layout.activity_text_rpg);
        Intent intent = getIntent();
        mainLayout = (LinearLayout) findViewById(R.id.mainTextGameLayout);
        chapterTitle = intent.getStringExtra(CHAPTER_TITLE_EXTRA);
        chapterTitleText = (TextView) findViewById(R.id.chapter_title_text);
        sceneText = (TypeWriterView) findViewById(R.id.scene_text);
        //Setting each character animation delay (in miliseconds)
        sceneText.setDelay(20);
        //Setting music effect On/Off
        sceneText.setWithMusic(false);
        sceneText.setTypeWriterListener(this);//listen to typewriter animation callbacks, such as animation end
        optBtn1 = (Button) findViewById(R.id.opt1_btn);
        optBtn2 = (Button) findViewById(R.id.opt2_btn);
        optBtn3 = (Button) findViewById(R.id.opt3_btn);
        optBtn4 = (Button) findViewById(R.id.opt4_btn);
        next = (Button) findViewById(R.id.nextBtn);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                continueText();
            }
        });

        //choice buttons click listeners
        optBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScene(0);
            }
        });

        optBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScene(1);
            }
        });

        optBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScene(2);
            }
        });

        optBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScene(3);
            }
        });

        chapterStory = loadChapter();
        chapterTitleText.setText(chapterStory.getChapterTitle());
        startChapter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Remove Animation. This is required to be called when you want to minimize the app while animation is going on. Call this in onPause() or onStop()
        sceneText.removeAnimation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            sceneText.removeAnimation();
        }
        return super.onTouchEvent(event);
    }


    private void startChapter(){
        this.chapterTitleText.setText(chapterTitle);
        this.chapterStory.getCurrentScene();
        this.chapterTitleText.setText(chapterTitle);
        showCurrentSceneText();
    }

    private ChapterStory loadChapter(){
        ChapterLoader chapterLoader = new ChapterLoader();
        return chapterLoader.load(this, chapterTitle);
    }

    private void continueText(){
        next.setVisibility(View.GONE);
        String sceneTextStr = this.chapterStory.getCurrentScene().getNextText();
        if (sceneTextStr !=null){
            //this.sceneText.setText(sceneTextStr);
            showCurrentSceneText();
        }
    }

    private void showCurrentSceneText(){
        Scene scene = this.chapterStory.getCurrentScene();
        //Animating Text
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        }
         */
        this.sceneText.animateText(HtmlCompat.fromHtml(scene.getCurrentText(), 0).toString());
    }

    private void showButtons(){
        hideAllChoiceButtons();
        Scene scene = this.chapterStory.getCurrentScene();
        boolean last = scene.isLast();
        if (last){
            //set choices
            next.setVisibility(View.GONE);
            Map<String, String> choices = scene.getChoices();
            //key: letter; value: choice text
            //set choices in buttons
            int n = 1;
            int i = 0;
            choiceIDs = new String[choices.size()];
            for (Map.Entry<String, String> entry : choices.entrySet()) {
                String choiceID = entry.getKey();
                String choiceText = entry.getValue();
                choiceIDs[i] = choiceID;
                setButtonChoiceText(n, choiceText);
                ++n;
                ++i;
            }
        }
        else{
            //only set continue button
            next.setVisibility(View.VISIBLE);
            Log.d("visible", next.getVisibility()+"");
            hideAllChoiceButtons();
        }
    }

    private void setButtonChoiceText(int number, String text){
        switch (number){
            case 1:
                optBtn1.setText(text);
                optBtn1.setVisibility(View.VISIBLE);
                break;
            case 2:
                optBtn2.setText(text);
                optBtn2.setVisibility(View.VISIBLE);
                break;
            case 3:
                optBtn3.setText(text);
                optBtn3.setVisibility(View.VISIBLE);
                break;
            case 4:
                optBtn4.setText(text);
                optBtn4.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * Change to a new scene, given a choice from the user.
     * Receives an integer which is the index in the array of scene choices with the selected choice.
     * @param nChoiceIndex
     */
    private void changeScene(int nChoiceIndex){
        hideAllChoiceButtons();
        String choiceID = choiceIDs[nChoiceIndex];
        this.chapterStory.changeScene(choiceID);
        showCurrentSceneText();
    }

    private void hideAllChoiceButtons(){
        optBtn1.setVisibility(View.GONE);
        optBtn2.setVisibility(View.GONE);
        optBtn3.setVisibility(View.GONE);
        optBtn4.setVisibility(View.GONE);
    }

    @Override
    public void onTypingStart(String text) {

    }

    @Override
    public void onTypingEnd(String text) {
        showButtons();
    }

    @Override
    public void onCharacterTyped(String text, int position) {

    }

    @Override
    public void onTypingRemoved(String text) {
        showButtons();
    }
}
