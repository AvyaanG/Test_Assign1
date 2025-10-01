package com.example.avyaan_emotilog.ui.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//represents a single logged emotion entry and provided getter and setter methods for getEmotionName(), getEmotionIcon(), getTimestamp() getFormattedDateTime()
public class EmotionLog {
    private String emotionName;
    private String emotionIcon;
    private long timestamp;

    public EmotionLog(String emotionName, String emotionIcon, long timestamp) {
        this.emotionName = emotionName;
        this.emotionIcon = emotionIcon;
        this.timestamp = timestamp;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public String getEmotionIcon() {
        return emotionIcon;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}