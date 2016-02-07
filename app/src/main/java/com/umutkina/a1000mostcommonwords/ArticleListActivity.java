package com.umutkina.a1000mostcommonwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class ArticleListActivity extends BaseActivity {

    @Bind(R.id.lv_article_list)
    ListView lvArticleList;
    private ArrayList<String> allArticles;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.hikayeler));

        String[] stringArray = getResources().getStringArray(R.array.engwords_article);
        allArticles = new ArrayList<>(Arrays.asList(stringArray));
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.article_list_item, allArticles);
        lvArticleList.setAdapter(itemsAdapter);
        lvArticleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArticleListActivity.this, ArticlesActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}
