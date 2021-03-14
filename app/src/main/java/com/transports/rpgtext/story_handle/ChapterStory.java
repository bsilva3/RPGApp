package com.transports.rpgtext.story_handle;

import java.util.List;
import java.util.Map;

/*
A full chapter, with a collection of scenes which depend on choices made
 */
public class ChapterStory {

    private String chapterTitle;
    private int chapterNumber;
    private String currentScene;
    private Map<String, Scene> scenes;

    public ChapterStory() {
        currentScene = "1";
    }//TODO: maybe the first scene could have a field that says that its the first one..

    public ChapterStory(String chapterTitle, int chapterNumber, Map<String, Scene> scenes) {
        this.chapterTitle = chapterTitle;
        this.chapterNumber = chapterNumber;
        this.scenes = scenes;
        currentScene = "1";
    }

    public ChapterStory(String chapterTitle, int chapterNumber, Map<String, Scene> scenes, String currentScene) {
        this.chapterTitle = chapterTitle;
        this.chapterNumber = chapterNumber;
        this.scenes = scenes;
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene(){
        return scenes.get(currentScene);
    }

    public Scene changeScene(String sceneID){
        this.currentScene = sceneID;
        return scenes.get(sceneID);
    }


    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Map<String, Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Map<String, Scene> scenes) {
        this.scenes = scenes;
    }

    @Override
    public String toString() {
        return "ChapterStory{" +
                "chapterTitle='" + chapterTitle + '\'' +
                ", chapterNumber=" + chapterNumber +
                ", scenes=" + scenes +
                ", currentScene=" + currentScene +
                '}';
    }
}
