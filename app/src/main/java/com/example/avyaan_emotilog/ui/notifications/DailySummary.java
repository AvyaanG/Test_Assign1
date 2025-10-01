package com.example.avyaan_emotilog.ui.notifications;

import java.util.HashMap;
import java.util.Map;

public class DailySummary {
    private String date;
    private Map<String, Integer> emotionCounts;
    private int totalCount;

    public DailySummary(String date) {
        this.date = date;
        this.emotionCounts = new HashMap<>();
        this.totalCount = 0;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Integer> getEmotionCounts() {
        return emotionCounts;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void addEmotionCount(String emotionKey, int count) {
        emotionCounts.put(emotionKey, count);
        totalCount += count;
    }
}