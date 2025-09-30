package com.example.avyaan_emotilog.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avyaan_emotilog.R;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements EmotionAdapter.OnEmotionClickListener {

    private RecyclerView emotionRecyclerView;
    private EmotionAdapter emotionAdapter;
    private List<Emotion> emotionList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setupEmotionButtons(root);

        return root;
    }

    private void setupEmotionButtons(View root) {
        emotionRecyclerView = root.findViewById(R.id.emotionRecyclerView);

        // Create 6-9 emotions
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

        // Set up adapter with 3 columns grid
        emotionAdapter = new EmotionAdapter(getContext(), emotionList, this);
        emotionRecyclerView.setAdapter(emotionAdapter);
        emotionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    public void onEmotionClick(Emotion emotion) {
        // This is where you'll log the emotion
        Toast.makeText(getContext(), "Clicked: " + emotion.getName() + " " + emotion.getIcon(),
                Toast.LENGTH_SHORT).show();

        // TODO: Add emotion to DataManager with timestamp
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}