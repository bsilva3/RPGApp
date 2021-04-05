package com.bsilva.rpgtext.story_handle;

import java.util.Arrays;
import java.util.Map;

public class Scene {

    private String sceneID;
    private String[] previousChoicesToThisScene; //the choices made in previous scene that led to this scene (empty if none happens anyway)
    private String[] text;
    private Map<String, String> choices;
    private int currentText;

    public Scene(String sceneID, String[] text, String[] previousChoicesToThisScene, Map<String, String> choices) {
        this.sceneID = sceneID;
        this.text = text;
        this.choices = choices;
        this.previousChoicesToThisScene = previousChoicesToThisScene;
        currentText = 0;
    }

    public Scene(String sceneID, String[] text) {
        this.sceneID = sceneID;
        this.text = text;
        currentText = 0;
    }

    public boolean isLast(){
        return (text.length - 1) == currentText ;
    }

    public String getCurrentText() {
        return text[currentText];
    }

    public String getNextText() {
        currentText++;
        if (currentText >= text.length)
            return null;
        return text[currentText];
    }

    public void nextScene() {
        currentText++;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public Map<String, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, String> choices) {
        this.choices = choices;
    }

    public String getSceneID() {
        return sceneID;
    }

    public void setSceneID(String sceneID) {
        this.sceneID = sceneID;
    }

    public String[] getSceneFlowChoices() {
        return previousChoicesToThisScene;
    }

    public void setSceneFlowChoices(String[] sceneFlowChoices) {
        this.previousChoicesToThisScene = sceneFlowChoices;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "sceneID='" + sceneID + '\'' +
                ", sceneFlowChoices=" + Arrays.toString(previousChoicesToThisScene) +
                ", text='" + text + '\'' +
                ", choices=" + choices +
                '}';
    }
}
