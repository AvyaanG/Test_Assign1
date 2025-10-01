package com.example.avyaan_emotilog.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.avyaan_emotilog.R;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView logRecyclerView;
    private LogAdapter logAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        logRecyclerView = root.findViewById(R.id.logRecyclerView);

        // Get logs from DataManager
        List<EmotionLog> logs = DataManager.getInstance().getEmotionLogs();

        // Set up adapter
        logAdapter = new LogAdapter(getContext(), logs);
        logRecyclerView.setAdapter(logAdapter);
        logRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh logs when returning to this fragment
        if (logAdapter != null) {
            logAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logRecyclerView = null;
        logAdapter = null;
    }
}