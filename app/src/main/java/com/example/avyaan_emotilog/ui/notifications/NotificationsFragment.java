package com.example.avyaan_emotilog.ui.notifications;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.avyaan_emotilog.R;
import com.example.avyaan_emotilog.ui.dashboard.DataManager;
import com.example.avyaan_emotilog.ui.dashboard.EmotionLog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
//Shows daily summary with emotion counts and frequencies. Supports date filtering so the user can select date which they want to see the daily summary for
public class NotificationsFragment extends Fragment { //LLM USED FOR CLARIFYING LOGIC AND GETTING THE UI TO WORK

    private LinearLayout summaryContainer;
    private Button btnSelectDate;
    private String selectedDate = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        summaryContainer = root.findViewById(R.id.summaryContainer);
        btnSelectDate = root.findViewById(R.id.btnSelectDate);

        btnSelectDate.setOnClickListener(v -> showDatePicker());

        loadSummary(selectedDate);
        return root;
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                    selectedDate = dateFormat.format(selectedCalendar.getTime());

                    loadSummary(selectedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
//LLM Used for code below, slight help in logic and java coding
    private void loadSummary(String filterDate) {
        summaryContainer.removeAllViews();

        DataManager dataManager = DataManager.getInstance();
        Map<String, List<EmotionLog>> logsByDate = dataManager.getLogsByDate();
        List<String> dates = dataManager.getSortedDates();

        if (filterDate != null) {
            dates = dates.stream()
                    .filter(date -> date.equals(filterDate))
                    .collect(java.util.stream.Collectors.toList());
        }

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