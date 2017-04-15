package com.tan_ds.homework_1;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    // Первый и второй вью, на которых мы будем анимировать фон
    private View mFirstAnimatedView, mSecondAnimatedView;
    // Аниматор фона, общий на двоих
    private ValueAnimator mAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mFirstAnimatedView = findViewById(R.id.anima_first);
       // mSecondAnimatedView = findViewById(R.id.second_animated_view);


        mAnimator = ValueAnimator.ofInt(0, 10000);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new DecelerateInterpolator(2));
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Integer value = (Integer) valueAnimator.getAnimatedValue();
                mFirstAnimatedView.getBackground().setLevel(value);
                //mSecondAnimatedView.getBackground().setLevel(value);
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Здесь, перед удалением Activity, зачистим аниматор во избежание утечек памяти
        mAnimator.cancel();
        mAnimator.removeAllUpdateListeners();
        mAnimator = null;
    }
}