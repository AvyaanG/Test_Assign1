package com.example.avyaan_emotilog.ui.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private List<EmotionLog> emotionLogs;

    private DataManager() {
        emotionLogs = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void addEmotionLog(EmotionLog log) {
        emotionLogs.add(log);
    }

    public List<EmotionLog> getEmotionLogs() {
        return emotionLogs;
    }

    public Map<String, Integer> getEmotionCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (EmotionLog log : emotionLogs) {
            String key = log.getEmotionIcon() + "-" + log.getEmotionName();
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }
        return counts;
    }

    // NEW METHOD: Get logs grouped by date
    public Map<String, List<EmotionLog>> getLogsByDate() {
        Map<String, List<EmotionLog>> logsByDate = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

        for (EmotionLog log : emotionLogs) {
            String dateKey = dateFormat.format(new Date(log.getTimestamp()));

            if (!logsByDate.containsKey(dateKey)) {
                logsByDate.put(dateKey, new ArrayList<>());
            }
            logsByDate.get(dateKey).add(log);
        }

        return logsByDate;
    }

    // NEW METHOD: Get daily summaries sorted by date (most recent first)
    public List<String> getSortedDates() {
        Map<String, List<EmotionLog>> logsByDate = getLogsByDate();
        List<String> dates = new ArrayList<>(logsByDate.keySet());

        // Sort dates in reverse chronological order (most recent first)
        Collections.sort(dates, Collections.reverseOrder());

        return dates;
    }

    public void clearLogs() {
        emotionLogs.clear();
    }
}