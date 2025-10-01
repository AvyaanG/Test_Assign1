package com.example.avyaan_emotilog.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.avyaan_emotilog.R;
import com.example.avyaan_emotilog.ui.dashboard.DataManager;
import com.example.avyaan_emotilog.ui.dashboard.EmotionLog;
import java.util.ArrayList;
import java.util.List;
//Main screen where users log emotions by tapping buttons.
//Creates 9 emotion buttons in a 3x3 grid manually
public class HomeFragment extends Fragment {

    private LinearLayout emotionGrid;
    private List<Emotion> emotionList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        emotionGrid = root.findViewById(R.id.emotionGrid);
        setupEmotionButtons();
        return root;
    }

    private void setupEmotionButtons() {
        emotionList = new ArrayList<>();
        emotionList.add(new Emotion("Happy", "😊"));
        emotionList.add(new Emotion("Sad", "😢"));
        emotionList.add(new Emotion("Angry", "😠"));
        emotionList.add(new Emotion("Excited", "🤩"));
        emotionList.add(new Emotion("Tired", "😴"));
        emotionList.add(new Emotion("Grateful", "🙏"));
        emotionList.add(new Emotion("Anxious", "😰"));
        emotionList.add(new Emotion("Loved", "🥰"));
        emotionList.add(new Emotion("Calm", "😌"));

        int columns = 3;
        LinearLayout currentRow = null;

        for (int i = 0; i < emotionList.size(); i++) {
            if (i % columns == 0) {
                currentRow = new LinearLayout(getContext());
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                emotionGrid.addView(currentRow);
            }

            Emotion emotion = emotionList.get(i);
            LinearLayout emotionButton = createEmotionButton(emotion);
            currentRow.addView(emotionButton);
        }
    }
    //LLM(Used for the code below, mainly for UI and java code , none for the logic applied)

    private LinearLayout createEmotionButton(Emotion emotion) {
        LinearLayout button = new LinearLayout(getContext());
        button.setOrientation(LinearLayout.VERTICAL);
        button.setGravity(Gravity.CENTER);
        button.setPadding(16, 16, 16, 16);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );
        button.setLayoutParams(params);

        TextView icon = new TextView(getContext());
        icon.setText(emotion.getIcon());
        icon.setTextSize(48f);
        icon.setGravity(Gravity.CENTER);
        button.addView(icon);

        TextView name = new TextView(getContext());
        name.setText(emotion.getName());
        name.setTextSize(14f);
        name.setGravity(Gravity.CENTER);
        button.addView(name);

        button.setOnClickListener(v -> {
            EmotionLog log = new EmotionLog(
                    emotion.getName(),
                    emotion.getIcon(),
                    System.currentTimeMillis()
            );
            DataManager.getInstance().addEmotionLog(log);
            Toast.makeText(getContext(), "Logged: " + emotion.getIcon() + " " + emotion.getName(), Toast.LENGTH_SHORT).show();
        });

        return button;
    }
}