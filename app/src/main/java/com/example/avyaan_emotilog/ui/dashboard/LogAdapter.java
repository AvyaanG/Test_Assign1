package com.example.avyaan_emotilog.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.avyaan_emotilog.R;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private List<EmotionLog> logs;
    private Context context;

    public LogAdapter(Context context, List<EmotionLog> logs) {
        this.context = context;
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        EmotionLog log = logs.get(position);
        holder.logEmotionIcon.setText(log.getEmotionIcon());
        holder.logEmotionName.setText("(" + log.getEmotionName() + ")");
        holder.logTimestamp.setText(log.getFormattedDateTime());
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView logEmotionIcon;
        TextView logEmotionName;
        TextView logTimestamp;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            logEmotionIcon = itemView.findViewById(R.id.logEmotionIcon);
            logEmotionName = itemView.findViewById(R.id.logEmotionName);
            logTimestamp = itemView.findViewById(R.id.logTimestamp);
        }
    }
}