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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        summaryContainer = root.findViewById(R.id.summaryContainer);
        loadSummary();
        return root;
    }

    private void loadSummary() {
        summaryContainer.removeAllViews();

        DataManager dataManager = DataManager.getInstance();
        Map<String, List<EmotionLog>> logsByDate = dataManager.getLogsByDate();
        List<String> dates = dataManager.getSortedDates();

        for (String date : dates) {
            View card = LayoutInflater.from(getContext()).inflate(R.layout.item_summary, summaryContainer, false);

            TextView dateText = card.findViewById(R.id.summaryDate);
            LinearLayout emotionsBox = card.findViewById(R.id.emotionsContainer);
            TextView totalText = card.findViewById(R.id.summaryTotalCount);

            dateText.setText("Date: " + date);
            dateText.setVisibility(View.VISIBLE);

            Map<String, Integer> counts = new HashMap<>();
            List<EmotionLog> logs = logsByDate.get(date);

            for (EmotionLog log : logs) {
                String key = log.getEmotionIcon() + " " + log.getEmotionName();
                counts.put(key, counts.getOrDefault(key, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                TextView row = new TextView(getContext());
                row.setText(entry.getKey() + " → " + entry.getValue());
                row.setTextSize(16f);
                row.setPadding(0, 8, 0, 8);
                emotionsBox.addView(row);
            }

            totalText.setText("Total Count: " + logs.size());
            totalText.setVisibility(View.VISIBLE);
            summaryContainer.addView(card);
        }
    }
}