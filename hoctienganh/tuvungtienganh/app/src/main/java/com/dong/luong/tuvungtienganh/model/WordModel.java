package com.dong.luong.tuvungtienganh.model;

public class WordModel {
    private int id;
    private String name, spelling, contain, topicName, audio;
    private int topicId;
    private int isSelect;
    private String key,value;
    private int idImage;

    public WordModel(String key, String value, int idImage,int topicId) {
        this.topicId=topicId;
        this.key = key;
        this.value = value;
        this.idImage = idImage;
    }

    public WordModel(int id, String name, String spelling, String contain, String audio, int isSelect, String topicName) {
        this.id = id;
        this.topicName = topicName;
        this.name = name;
        this.spelling = spelling;
        this.contain = contain;
        this.audio = audio;
        this.isSelect = isSelect;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }
}
