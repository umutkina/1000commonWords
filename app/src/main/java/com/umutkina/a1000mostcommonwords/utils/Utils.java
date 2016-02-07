package com.umutkina.a1000mostcommonwords.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by mac on 03/01/16.
 */
public class Utils {
    public static void showInfoDialog(Context context, final Runnable onOk,
                                     String title, String message) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle(title);
        alert.setMessage(message);

        if (onOk != null) {
            alert.setNegativeButton("Vazgeç",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
        }
        alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                if (onOk != null) {
                    onOk.run();
                }
            }
        });

        try {
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                    alert.show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
