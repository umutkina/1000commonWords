package com.umutkina.a1000mostcommonwords.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umutkina.a1000mostcommonwords.R;
import com.umutkina.a1000mostcommonwords.SavedWordsActivity;
import com.umutkina.a1000mostcommonwords.utils.Utils;

import java.util.ArrayList;

/**
 * Created by mac on 03/01/16.
 */
public class SavedWordsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> users=new ArrayList<>();
    public SavedWordsAdapter(Context context, ArrayList<String> users) {
        super();
        this.users=users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final String text = users.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.saved_words_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_word);
        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);
        // Populate the data into the template view using the data object
        tvName.setText(text);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SavedWordsActivity savedWordsActivity = (SavedWordsActivity) context;
                savedWordsActivity.itemDelete(text);
            }
        };

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showInfoDialog(context, runnable, context.getString(R.string.warnings), context.getString(R.string.delete_item_warning));

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}