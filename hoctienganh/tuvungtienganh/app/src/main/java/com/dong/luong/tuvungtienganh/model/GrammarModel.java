package com.dong.luong.tuvungtienganh.model;

public class GrammarModel {
    private int indexGrammar, level, id;
    private String titleGrammar;
    private String contentGrammar;

    public GrammarModel(String titleGrammar) {
        this.titleGrammar = titleGrammar;
    }

    public GrammarModel(int id, int indexGrammar, int level, String titleGrammar, String contentGrammar) {
        this.indexGrammar = indexGrammar;
        this.level = level;
        this.id = id;
        this.titleGrammar = titleGrammar;
        this.contentGrammar = contentGrammar;
    }

    public String getContentGrammar() {
        return contentGrammar;
    }

    public void setContentGrammar(String contentGrammar) {
        this.contentGrammar = contentGrammar;
    }

    public int getIndexGrammar() {
        return indexGrammar;
    }

    public void setIndexGrammar(int indexGrammar) {
        this.indexGrammar = indexGrammar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleGrammar() {
        return titleGrammar;
    }

    public void setTitleGrammar(String titleGrammar) {
        this.titleGrammar = titleGrammar;
    }
}
