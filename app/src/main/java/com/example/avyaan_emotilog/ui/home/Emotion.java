package com.example.avyaan_emotilog.ui.home;

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
