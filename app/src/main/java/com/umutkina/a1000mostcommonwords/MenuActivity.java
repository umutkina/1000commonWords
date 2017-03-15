package com.umutkina.a1000mostcommonwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.umutkina.a1000mostcommonwords.utils.Utils;

import butterknife.Bind;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity {

    @Bind(R.id.tv_words)
    TextView tvWords;
    @Bind(R.id.tv_questions)
    TextView tvQuestions;
    @Bind(R.id.tv_saved_words)
    TextView tvSavedWords;
    @Bind(R.id.adView)
    AdView adView;

    @Bind(R.id.tv_saved_words_questions)
    TextView tvSavedWordsQuestions;
    @Bind(R.id.tv_hikayeler)
    TextView tvHikayeler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedWords.size() > 0) {
            tvSavedWords.setText(getString(R.string.kay_tl_kelimeler) + " (" + savedWords.size() + ")");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (savedWords.size() > 0) {
            tvSavedWords.setText(getString(R.string.kay_tl_kelimeler) + " (" + savedWords.size() + ")");
        }
    }

    @OnClick(R.id.tv_words)
    public void words() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.tv_questions)
    public void questions() {
        startActivity(new Intent(this, QuestionsActivity.class));
    }

    @OnClick(R.id.tv_saved_words)
    public void savedWords() {
        startActivity(new Intent(this, SavedWordsActivity.class));
    }

    @OnClick(R.id.tv_saved_words_questions)
    public void savedWordsQuestions() {
        if (savedWords.size() < 20) {
            Utils.showInfoDialog(this, null, getString(R.string.warnings), getString(R.string.must_be_20));
        } else {
            startActivity(new Intent(this, SavedWordsQuestionActivity.class));
        }

    }

    @OnClick(R.id.tv_hikayeler)
    public void hikayeler() {
        startActivity(new Intent(this, ArticleListActivity.class));
    }
}
