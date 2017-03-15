package com.umutkina.a1000mostcommonwords;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.umutkina.a1000mostcommonwords.modals.Question;
import com.umutkina.a1000mostcommonwords.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class SavedWordsQuestionActivity extends BaseActivity {

    @Bind(R.id.tv_text)
    TextView tvText;
    @Bind(R.id.tv_answer1)
    TextView tvAnswer1;
    @Bind(R.id.tv_answer2)
    TextView tvAnswer2;
    @Bind(R.id.tv_answer3)
    TextView tvAnswer3;
    @Bind(R.id.tv_answer4)
    TextView tvAnswer4;
    int position = 0;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.view_flipper)
    ViewFlipper viewFlipper;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<String> questionsText = new ArrayList<>();

    private int correctCount = 0;
    int questionCount = 20;
    String currentQuestionText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_questions;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.saved_words_question));

        boolean showSavedDialog = preferences.getBoolean("showSavedDialog",false);
        if (!showSavedDialog) {
            Utils.showInfoDialog(this, null, getString(R.string.warnings), getString(R.string.saved_words_dialog));


        }
        preferences.edit().putBoolean("showSavedDialog", true).commit();
        for (int i = questionCount; i > 0; i--) {
            ArrayList<String> strings = new ArrayList<>();
            Random random = new Random();
            int i1;
            String word;
            do {
                i1 = random.nextInt(savedWords.size());

                word = savedWords.get(i1);
            }
            while (questionsText.contains(word));

            strings.add(word);
            questionsText.add(word);
            for (int i2 = 0; i2 < 3; i2++) {
                int ans;
                String st;
                do {
                    ans = random.nextInt(allWords.size());

                    st = allWords.get(ans);
                }
                while (strings.contains(st));


                strings.add(st);
            }
            String text = strings.get(0);
            Question question = new Question(text, strings.get(1), strings.get(2), strings.get(3));
            questions.add(question);
        }
        ivRight.setVisibility(View.INVISIBLE);
        Question question = questions.get(0);

        fillQuestion(question);
    }

    private void fillQuestion(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getText());
        answers.add(question.getAnswer1());
        answers.add(question.getAnswer2());
        answers.add(question.getAswer3());
        long seed = System.nanoTime();
        Collections.shuffle(answers, new Random(seed));

        String text = answers.get(0);
        String text1 = answers.get(1);
        String text2 = answers.get(2);
        String text3 = answers.get(3);
        tvText.setText(question.getText());
        currentQuestionText = question.getText();
        tvAnswer1.setTag(text.equals(question.getText()));
        tvAnswer2.setTag(text1.equals(question.getText()));
        tvAnswer3.setTag(text2.equals(question.getText()));
        tvAnswer4.setTag(text3.equals(question.getText()));


        setAnswer(text, tvAnswer1);
        setAnswer(text1, tvAnswer2);
        setAnswer(text2, tvAnswer3);
        setAnswer(text3, tvAnswer4);
    }

    @OnClick(R.id.iv_right)
    public void right() {
        ivRight.setVisibility(View.INVISIBLE);
        int index = ++position;

        if (index == questionCount) {
            viewFlipper.setDisplayedChild(1);
            tvResult.setText(getString(R.string.correct_count) + correctCount + getString(R.string.wrong_count) + (questionCount - correctCount));
        } else {
            Question question = questions.get(index);
            fillQuestion(question);
            setQuestionView(tvAnswer1);
            setQuestionView(tvAnswer2);
            setQuestionView(tvAnswer3);
            setQuestionView(tvAnswer4);
        }

    }

    public void setQuestionView(TextView textView) {
        textView.setText("");


        textView.setClickable(true);


        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));

    }

    public void setAnswer(final String text, final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correctAnswer = (boolean) textView.getTag();
                answerColor(tvAnswer1);
                answerColor(tvAnswer2);
                answerColor(tvAnswer3);
                answerColor(tvAnswer4);
                if (correctAnswer) {
                    correctCount++;
                    textView.setBackgroundColor(ContextCompat.getColor(SavedWordsQuestionActivity.this, R.color.green));
                } else {

                    itemDelete(currentQuestionText);
                    textView.setBackgroundColor(ContextCompat.getColor(SavedWordsQuestionActivity.this, R.color.red));

                }
                ivRight.setVisibility(View.VISIBLE);
            }
        });
        translate(text, textView);
//        class LongOperation extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//                String translatedText = null;
//                try {
//                    translatedText = Translate.execute(params[0], Language.ENGLISH,  Language.TURKISH);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return translatedText;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                if (result == null) {
//                    textView.setText(getString(R.string.check_net_connection));
//                } else {
//                    textView.setText(result);
//                }
//
//                ; // txt.setText(result);
//                // might want to change "executed" for the returned string passed
//                // into onPostExecute() but that is upto you
//            }
//
//            @Override
//            protected void onPreExecute() {
//            }
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//            }
//        }
//
//        new LongOperation().execute(text);
    }

    public void itemDelete(String text) {
        for (int i = 0; i < savedWords.size(); i++) {
            if (savedWords.get(i).equals(text)) {
                savedWords.remove(i);
            }

        }


        preferences.edit().putString(SAVED_WORDS, TextUtils.join(",", savedWords)).commit();


    }

    public void answerColor(TextView textView) {
        boolean b = (boolean) textView.getTag();
        if (b) {
            textView.setBackgroundColor(ContextCompat.getColor(SavedWordsQuestionActivity.this, R.color.green));

        } else {
            textView.setBackgroundColor(ContextCompat.getColor(SavedWordsQuestionActivity.this, R.color.gray));

        }
        textView.setClickable(false);
    }
}
