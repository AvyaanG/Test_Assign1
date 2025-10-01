package com.example.avyaan_emotilog.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.avyaan_emotilog.R;
import com.example.avyaan_emotilog.ui.dashboard.EmotionLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private Context context;
    private List<SummaryItem> summaryItems;

    // Helper class to represent each row
    static class SummaryItem {
        String date;
        String emotionIcon;
        String emotionName;
        int frequency;
        boolean isFirstOfDate;
        boolean showTotalCount;
        int totalCount;

        SummaryItem(String date, String emotionIcon, String emotionName, int frequency,
                    boolean isFirstOfDate, boolean showTotalCount, int totalCount) {
            this.date = date;
            this.emotionIcon = emotionIcon;
            this.emotionName = emotionName;
            this.frequency = frequency;
            this.isFirstOfDate = isFirstOfDate;
            this.showTotalCount = showTotalCount;
            this.totalCount = totalCount;
        }
    }

    public SummaryAdapter(Context context, Map<String, List<EmotionLog>> logsByDate, List<String> sortedDates) {
        this.context = context;
        this.summaryItems = new ArrayList<>();

        // Build summary items for each date
        for (String date : sortedDates) {
            List<EmotionLog> logsForDate = logsByDate.get(date);

            // Count emotions for this date
            Map<String, Integer> emotionCounts = new HashMap<>();
            int totalForDate = 0;

            for (EmotionLog log : logsForDate) {
                String key = log.getEmotionIcon() + "-" + log.getEmotionName();
                emotionCounts.put(key, emotionCounts.getOrDefault(key, 0) + 1);
                totalForDate++;
            }

            // Add items for this date
            boolean isFirst = true;
            for (Map.Entry<String, Integer> entry : emotionCounts.entrySet()) {
                String[] parts = entry.getKey().split("-");
                String emoji = parts[0];
                String name = parts.length > 1 ? parts[1] : "";

                summaryItems.add(new SummaryItem(
                        date,
                        emoji,
                        name,
                        entry.getValue(),
                        isFirst,
                        isFirst,  // Show total count on first item
                        totalForDate
                ));

                isFirst = false;
            }
        }
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        SummaryItem item = summaryItems.get(position);

        // Show date only on first item of each date group
        if (item.isFirstOfDate) {
            holder.summaryDate.setVisibility(View.VISIBLE);
            holder.summaryDate.setText(item.date);
        } else {
            holder.summaryDate.setVisibility(View.GONE);
        }

        // Show total count only on first item
        if (item.showTotalCount) {
            holder.summaryTotalCount.setVisibility(View.VISIBLE);
            holder.summaryTotalCount.setText("Total Count: " + item.totalCount);
        } else {
            holder.summaryTotalCount.setVisibility(View.GONE);
        }

        holder.summaryEmotionIcon.setText(item.emotionIcon);
        holder.summaryEmotionName.setText("(" + item.emotionName + ")");
        holder.summaryFrequency.setText(String.valueOf(item.frequency));
    }

    @Override
    public int getItemCount() {
        return summaryItems.size();
    }

    static class SummaryViewHolder extends RecyclerView.ViewHolder {
        TextView summaryDate;
        TextView summaryTotalCount;
        TextView summaryEmotionIcon;
        TextView summaryEmotionName;
        TextView summaryFrequency;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            summaryDate = itemView.findViewById(R.id.summaryDate);
            summaryTotalCount = itemView.findViewById(R.id.summaryTotalCount);
            summaryEmotionIcon = itemView.findViewById(R.id.summaryEmotionIcon);
            summaryEmotionName = itemView.findViewById(R.id.summaryEmotionName);
            summaryFrequency = itemView.findViewById(R.id.summaryFrequency);
        }
    }
}