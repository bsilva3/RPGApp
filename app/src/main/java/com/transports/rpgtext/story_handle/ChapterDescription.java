package com.transports.rpgtext.story_handle;

public class ChapterDescription {

    private String chapterTitle;
    private int chapterNumber;
    private String chapterDescription;
    private String fileName;
    public boolean box;//for the checkbox in chapter selection

    public ChapterDescription(String title, int chapterNumber, String description, String fileName) {
        this.chapterTitle = title;
        this.chapterNumber = chapterNumber;
        this.chapterDescription = description;
        this.fileName = fileName;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
}
