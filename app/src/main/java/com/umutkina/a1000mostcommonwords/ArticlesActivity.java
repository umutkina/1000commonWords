package com.umutkina.a1000mostcommonwords;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.umutkina.a1000mostcommonwords.modals.TranslateInterface;
import com.umutkina.a1000mostcommonwords.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ArticlesActivity extends BaseActivity implements TranslateInterface {

    @Bind(R.id.tv_article)
    TextView tvArticle;
    @Bind(R.id.iv_prew)
    ImageView ivPrew;
    @Bind(R.id.iv_forward)
    ImageView ivForward;
    private ArrayList<String> allArticles;
    int position;

    @Override
    public int getLayoutId() {
        return R.layout.activity_articles;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.hikayeler));
        boolean showSavedDialog = preferences.getBoolean("showStoriesDialog",false);
        if (!showSavedDialog) {
            Utils.showInfoDialog(this, null, getString(R.string.warnings), getString(R.string.articles_dialog));


        }
        preferences.edit().putBoolean("showStoriesDialog", true).commit();

        ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipBoard.addPrimaryClipChangedListener(new ClipboardListener());

        setTranslateInterface(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        }
        String[] stringArray = getResources().getStringArray(R.array.engwords_article);
        allArticles = new ArrayList<>(Arrays.asList(stringArray));
        String s = allArticles.get(position);
        tvArticle.setText(s);
        if (position == 0) {

            ivPrew.setVisibility(View.GONE);
        }
    }

    @Override
    public void translate(String word, String tanslatedText) {
        if(!(this).isFinishing())
        {
            //show dialog
            new SweetAlertDialog(this)
                    .setTitleText("")
                    .setContentText(word + "\n\n" + tanslatedText)
                    .show();
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class ClipboardListener implements
            ClipboardManager.OnPrimaryClipChangedListener {
        public void onPrimaryClipChanged() {
            ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            CharSequence pasteData = "";
            ClipData.Item item = clipBoard.getPrimaryClip().getItemAt(0);
            pasteData = item.getText();
            translate(pasteData.toString(), (TextView) null);

        }
    }

    public void next(View v) {
        if (position == allArticles.size() - 2) {
            ivForward.setVisibility(View.GONE);
        } else {
            ivForward.setVisibility(View.VISIBLE);
        }
        ivPrew.setVisibility(View.VISIBLE);

        final String s = allArticles.get(++position);
        tvArticle.setText(s);
        YoYo.with(Techniques.SlideOutLeft)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        YoYo.with(Techniques.SlideInRight)
                                .duration(200)
                                .playOn(tvArticle);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(tvArticle);

    }

    public void back(View v) {

        ivForward.setVisibility(View.VISIBLE);
        final String s = allArticles.get(--position);
        if (position == 0) {
            ivPrew.setVisibility(View.GONE);
        } else {
            ivPrew.setVisibility(View.VISIBLE);
        }
        tvArticle.setText(s);
        YoYo.with(Techniques.SlideOutRight)
                .duration(200)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        YoYo.with(Techniques.SlideInLeft)
                                .duration(200)
                                .playOn(tvArticle);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(tvArticle);


    }
}
