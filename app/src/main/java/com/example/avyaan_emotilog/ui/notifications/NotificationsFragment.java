package com.example.avyaan_emotilog.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.avyaan_emotilog.R;
import com.example.avyaan_emotilog.ui.dashboard.DataManager;
import com.example.avyaan_emotilog.ui.dashboard.EmotionLog;
import java.util.List;
import java.util.Map;


public class NotificationsFragment extends Fragment {

    private RecyclerView summaryRecyclerView;
    private SummaryAdapter summaryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        summaryRecyclerView = root.findViewById(R.id.summaryRecyclerView);

        loadSummary();

        return root;
    }

    private void loadSummary() {
        // Get logs grouped by date
        Map<String, List<EmotionLog>> logsByDate = DataManager.getInstance().getLogsByDate();
        List<String> sortedDates = DataManager.getInstance().getSortedDates();

        // Set up adapter
        summaryAdapter = new SummaryAdapter(getContext(), logsByDate, sortedDates);
        summaryRecyclerView.setAdapter(summaryAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh summary when returning to this fragment
        loadSummary();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        summaryRecyclerView = null;
        summaryAdapter = null;
    }
}