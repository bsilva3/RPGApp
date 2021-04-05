package com.bsilva.rpgtext.story_handle;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChapterLoader {

    public ChapterStory load(Activity activity, String chapterTitle){
        Gson gson = new Gson();

        String storyStr = loadChapterFromFile(activity, chapterTitle);
        ChapterStory chapter = gson.fromJson(storyStr, ChapterStory.class);

        return chapter;

    }

    private String loadChapterFromFile(Activity activity, String chapter){
        String json = null;
        try {
            if (!chapter.contains(".json"))
                chapter+=".json";
            InputStream is = activity.getAssets().open(chapter);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Log.d("JSON", json+"");
        return json;
    }

    public List<ChapterDescription> getAvailableChapters(Activity activity){
        String[] chapterFiles = null;
        List<ChapterDescription> chapters= new ArrayList<>();
        Gson gson = new Gson();
        try {
            chapterFiles = activity.getAssets().list("");

            for (String file : chapterFiles){
                if (file.endsWith(".json")) {//maintain only assents ending with .json (chapters) //Todo: filter by name also? "ChapterXX.json"
                    String storyStr = loadChapterFromFile(activity, file);
                    ChapterDescription chapterDescription = gson.fromJson(storyStr, ChapterDescription.class);
                    chapterDescription.setFileName(file);
                    chapters.add(chapterDescription);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return chapters;
    }
}
