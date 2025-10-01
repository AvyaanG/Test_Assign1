package com.example.avyaan_emotilog.ui.home;
//Emotion class represents emotion types and initiliazes getter and setter methods
public class Emotion {
    private String name;
    private String icon;

    public Emotion(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
