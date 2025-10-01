package com.example.avyaan_emotilog.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.avyaan_emotilog.R;
import com.example.avyaan_emotilog.ui.dashboard.DataManager;
import com.example.avyaan_emotilog.ui.dashboard.EmotionLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private LinearLayout summaryContainer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        summaryContainer = root.findViewById(R.id.summaryContainer);

        loadSummary();

        return root;
    }

    private void loadSummary() {
        summaryContainer.removeAllViews(); // Clear old data before repopulating

        // Get logs grouped by date
        Map<String, List<EmotionLog>> logsByDate = DataManager.getInstance().getLogsByDate();
        List<String> sortedDates = DataManager.getInstance().getSortedDates();

        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (String date : sortedDates) {
            // Show the date
            TextView dateView = new TextView(getContext());
            dateView.setText("Date: " + date);
            dateView.setTextSize(18f);
            dateView.setPadding(0, 16, 0, 8);
            summaryContainer.addView(dateView);

            // Count emotions for this date
            Map<String, Integer> emotionCounts = new HashMap<>();
            int total = 0;

            for (EmotionLog log : logsByDate.get(date)) {
                String key = log.getEmotionIcon() + " " + log.getEmotionName();
                emotionCounts.put(key, emotionCounts.getOrDefault(key, 0) + 1);
                total++;
            }

            // Show each emotion with frequency
            for (Map.Entry<String, Integer> entry : emotionCounts.entrySet()) {
                TextView itemView = new TextView(getContext());
                itemView.setText(entry.getKey() + " → " + entry.getValue());
                itemView.setTextSize(16f);
                itemView.setPadding(16, 4, 0, 4);
                summaryContainer.addView(itemView);
            }

            // Show total for the date
            TextView totalView = new TextView(getContext());
            totalView.setText("Total Count: " + total);
            totalView.setTextSize(16f);
            totalView.setPadding(0, 8, 0, 16);
            summaryContainer.addView(totalView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSummary(); // Refresh when coming back
    }
}
