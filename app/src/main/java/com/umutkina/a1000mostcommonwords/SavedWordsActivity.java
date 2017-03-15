package com.umutkina.a1000mostcommonwords;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.umutkina.a1000mostcommonwords.adapters.SavedWordsAdapter;

import butterknife.Bind;

public class SavedWordsActivity extends BaseActivity {
    SavedWordsAdapter savedWordsAdapter;
    @Bind(R.id.lv_saved_words)
    ListView lvSavedWords;

    @Override
    public int getLayoutId() {
        return R.layout.activity_saved_words;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle(getString(R.string.kay_tl_kelimeler));


        savedWordsAdapter = new SavedWordsAdapter(this, savedWords);

        lvSavedWords.setAdapter(savedWordsAdapter);

    }

    public void itemDelete(String text) {
        for (int i = 0; i < savedWords.size(); i++) {
            if (savedWords.get(i).equals(text)) {
                savedWords.remove(i);
            }

        }


        preferences.edit().putString(SAVED_WORDS, TextUtils.join(",", savedWords)).commit();
        savedWordsAdapter.setUsers(savedWords);
        savedWordsAdapter.notifyDataSetChanged();

    }
}
