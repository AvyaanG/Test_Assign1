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
        summaryContainer.removeAllViews();

        Map<String, List<EmotionLog>> logsByDate = DataManager.getInstance().getLogsByDate();
        List<String> sortedDates = DataManager.getInstance().getSortedDates();

        for (String date : sortedDates) {
            View dateCard = LayoutInflater.from(getContext()).inflate(R.layout.item_summary, summaryContainer, false);

            TextView dateText = dateCard.findViewById(R.id.summaryDate);
            LinearLayout emotionsContainer = dateCard.findViewById(R.id.emotionsContainer);
            TextView totalText = dateCard.findViewById(R.id.summaryTotalCount);

            dateText.setText("Date: " + date);
            dateText.setVisibility(View.VISIBLE);

            Map<String, Integer> emotionCounts = new HashMap<>();
            int total = 0;

            for (EmotionLog log : logsByDate.get(date)) {
                String key = log.getEmotionIcon() + " " + log.getEmotionName();
                emotionCounts.put(key, emotionCounts.getOrDefault(key, 0) + 1);
                total++;
            }

            for (Map.Entry<String, Integer> entry : emotionCounts.entrySet()) {
                TextView emotionRow = new TextView(getContext());
                emotionRow.setText(entry.getKey() + " → " + entry.getValue());
                emotionRow.setTextSize(16f);
                emotionRow.setPadding(0, 8, 0, 8);
                emotionsContainer.addView(emotionRow);
            }

            totalText.setText("Total Count: " + total);
            totalText.setVisibility(View.VISIBLE);
            summaryContainer.addView(dateCard);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSummary();
    }
}