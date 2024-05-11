package com.example.chapter3.homework;

import static android.app.PendingIntent.getActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class FriendsListFragment extends Fragment {
    private Handler handler = new Handler();
    private View rootView;
    private LottieAnimationView lottieAnimationView;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.friend_list, container, false);

        // Setup Lottie Animation View
        lottieAnimationView = rootView.findViewById(R.id.animation_view);
        lottieAnimationView.setVisibility(View.VISIBLE);
        fadeInAnimation(lottieAnimationView);
        lottieAnimationView.playAnimation();

        listView = rootView.findViewById(R.id.lvItems);
        listView.setVisibility(View.INVISIBLE);

        handler.postDelayed(() -> {
            fadeOutAnimation(lottieAnimationView);
            listView.setVisibility(View.VISIBLE);
            fadeInAnimation(listView);
            setupFriendList();
        }, 5000);

        return rootView;
    }



    private void setupFriendList() {
        String[] friends = {"Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, friends);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
    private void fadeInAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    private void fadeOutAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
}