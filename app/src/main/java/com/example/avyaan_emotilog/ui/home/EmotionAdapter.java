package com.example.avyaan_emotilog.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avyaan_emotilog.R;

import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> {

    private List<Emotion> emotions;
    private Context context;
    private OnEmotionClickListener listener;

    public interface OnEmotionClickListener {
        void onEmotionClick(Emotion emotion);
    }

    public EmotionAdapter(Context context, List<Emotion> emotions, OnEmotionClickListener listener) {
        this.context = context;
        this.emotions = emotions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.emotions, parent, false);
        return new EmotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder holder, int position) {
        Emotion emotion = emotions.get(position);
        holder.emotionIcon.setText(emotion.getIcon());
        holder.emotionName.setText(emotion.getName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEmotionClick(emotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emotions.size();
    }

    static class EmotionViewHolder extends RecyclerView.ViewHolder {
        TextView emotionIcon;
        TextView emotionName;

        public EmotionViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionIcon = itemView.findViewById(R.id.emotionIcon);
            emotionName = itemView.findViewById(R.id.emotionName);
        }
    }
}
