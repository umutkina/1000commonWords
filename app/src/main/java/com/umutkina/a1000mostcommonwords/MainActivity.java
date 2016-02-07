package com.umutkina.a1000mostcommonwords;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.CheckBox;
import com.nineoldandroids.animation.Animator;
import com.umutkina.a1000mostcommonwords.utils.FlipAnimation;
import com.umutkina.a1000mostcommonwords.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tv_eng)
    TextView tvEng;

    @Bind(R.id.tv_tr)
    TextView tvTr;

    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;

    @Bind(R.id.rl_front)
    CardView rlFront;
    @Bind(R.id.checkBox)
    CheckBox checkBox;
    @Bind(R.id.rl_back)
    CardView rlBack;

    int position;
    @Bind(R.id.iv_prew)
    ImageView ivPrew;
    @Bind(R.id.iv_forward)
    ImageView ivForward;

    String currentWord;
    String currentTr;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.kelime_ezber));

        ivPrew.setVisibility(View.GONE);


        boolean showSavedDialog = preferences.getBoolean("showWordsDialog", false);
        if (!showSavedDialog) {
            Utils.showInfoDialog(this, null, getString(R.string.warnings), getString(R.string.words_list_dialog));


        }
        preferences.edit().putBoolean("showWordsDialog", true).commit();

        String serialized = preferences.getString(SAVED_WORDS, null);
        if (serialized != null) {
            savedWords = new ArrayList<>(Arrays.asList(TextUtils.split(serialized, ",")));


        }

        if (savedWords == null) {
            savedWords = new ArrayList<>();
        }
        mTempArray.addAll(allWords);
        updateList();
        long seed = System.nanoTime();
        Collections.shuffle(mTempArray, new Random(seed));
        currentWord = mTempArray.get(0);
        tvEng.setText(currentWord);

        translate(currentWord, (TextView) findViewById(R.id.tv_tr));

        tvEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        tvTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        checkBox.setOncheckListener(new CheckBox.OnCheckListener() {
            @Override
            public void onCheck(CheckBox view, boolean check) {
                if (check) {

                    savedWords.add(mTempArray.get(position));
                } else {
                    for (int i = 0; i < savedWords.size(); i++) {
                        if (savedWords.get(i).equals(mTempArray.get(position))) {
                            savedWords.remove(i);
                        }

                    }

                }

                preferences.edit().putString(SAVED_WORDS, TextUtils.join(",", savedWords)).commit();

                updateList();
            }
        });

    }

    private void updateList() {
        for (int i = 0; i < mTempArray.size(); i++) {
            for (int i1 = 0; i1 < savedWords.size(); i1++) {
                if (mTempArray.get(i).equals(savedWords.get(i1))) {
                    mTempArray.remove(i);
                }
            }
        }
    }

    public void next(View v) {
        if (position == mTempArray.size() - 2) {
            ivForward.setVisibility(View.GONE);
        } else {
            ivForward.setVisibility(View.VISIBLE);
        }

        if (position % 20 == 0) {
            mAdView.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mAdView.setVisibility(View.VISIBLE);
                    ;
                }
            }, 5000);
        }
        ivPrew.setVisibility(View.VISIBLE);
        tvTr.setText("");
        checkBox.setChecked(false);
        currentWord = mTempArray.get(++position);
        YoYo.with(Techniques.SlideOutLeft)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        tvEng.setText(currentWord);
                        YoYo.with(Techniques.SlideInRight)
                                .duration(200)
                                .playOn(rlFront);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(rlFront);
        translate(currentWord, (TextView) findViewById(R.id.tv_tr));
    }

    public void back(View v) {

        ivForward.setVisibility(View.VISIBLE);
        tvTr.setText("");
        checkBox.setChecked(false);
        currentWord = mTempArray.get(--position);
        if (position == 0) {
            ivPrew.setVisibility(View.GONE);
        } else {
            ivPrew.setVisibility(View.VISIBLE);
        }
        YoYo.with(Techniques.SlideOutRight)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        tvEng.setText(currentWord);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(200)
                                .playOn(rlFront);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(rlFront);


        translate(currentWord, (TextView) findViewById(R.id.tv_tr));
    }

    private void flipCard() {
        View rootLayout = (View) findViewById(R.id.rl_root);
        View cardFace = (View) findViewById(R.id.rl_front);
        View cardBack = (View) findViewById(R.id.rl_back);

        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
    }


    @OnClick(R.id.iv_share)
    public void share() {
        String shareBody = getString(R.string.ing_words) + currentWord +
                getString(R.string.mean) + tvTr.getText().toString() + "\nhttps://play.google.com/store/apps/details?id=com.umutkina.a1000mostcommonwords";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
    }

}
