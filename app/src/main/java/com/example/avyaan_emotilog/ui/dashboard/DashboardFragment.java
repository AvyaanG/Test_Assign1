package com.example.avyaan_emotilog.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.avyaan_emotilog.R;
import java.util.List;
//Displays list of all logged emotions.
//Shows all EmotionLog entries in scrollable list
public class DashboardFragment extends Fragment {

    private LinearLayout logsContainer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        logsContainer = root.findViewById(R.id.logsContainer);
        loadLogs();
        return root;
    }

    private void loadLogs() {
        logsContainer.removeAllViews();

        List<EmotionLog> logs = DataManager.getInstance().getEmotionLogs();

        for (EmotionLog log : logs) {
            View logCard = LayoutInflater.from(getContext()).inflate(R.layout.item_log, logsContainer, false);

            TextView iconView = logCard.findViewById(R.id.logEmotionIcon);
            TextView nameView = logCard.findViewById(R.id.logEmotionName);
            TextView timeView = logCard.findViewById(R.id.logTimestamp);

            iconView.setText(log.getEmotionIcon());
            nameView.setText("(" + log.getEmotionName() + ")");
            timeView.setText(log.getFormattedDateTime());

            logsContainer.addView(logCard);
        }
    }
}