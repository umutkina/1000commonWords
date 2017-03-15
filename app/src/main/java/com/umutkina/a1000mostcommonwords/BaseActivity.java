package com.umutkina.a1000mostcommonwords;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.umutkina.a1000mostcommonwords.modals.TranslateInterface;
import com.umutkina.a1000mostcommonwords.requests.JacksonConverter;
import com.umutkina.a1000mostcommonwords.requests.RetrofitHttpClient;
import com.umutkina.a1000mostcommonwords.requests.WordsApiRequest;
import com.umutkina.a1000mostcommonwords.yandex.language.Language;
import com.umutkina.a1000mostcommonwords.yandex.translate.Translate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

/**
 * Created by mac on 03/01/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    static final String SAVED_WORDS = "savedWords";
    ArrayList<String> allWords;
    ArrayList<String> mTempArray = new ArrayList<>();
    ArrayList<String> savedWords = new ArrayList<>();
    TranslateInterface translateInterface;
    public WordsApiRequest wordsApiRequest;

    public SharedPreferences preferences;
    RestAdapter restAdapter;


    @Bind(R.id.iv_temp)
    @Nullable
    View ivTemp;

    public abstract int getLayoutId();

    InterstitialAd mInterstitialAd;
    AdView mAdView;
    AdRequest adRequestInter;

    public void setTranslateInterface(TranslateInterface translateInterface) {
        this.translateInterface = translateInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        Translate.setKey("trnsl.1.1.20160101T181412Z.e941ae9b4dedd95a.f8c28dbd9a9d975cb3c343e43439f1ccfea3b711");
        preferences = getSharedPreferences("Mypref", 0);
        String[] stringArray = getResources().getStringArray(R.array.engwords_part_1);
        allWords = new ArrayList<>(Arrays.asList(stringArray));


        // request kütüphanesi entegrasyonu

        RetrofitHttpClient client = new RetrofitHttpClient();


        savedWordsRefresh();


        restAdapter = new RestAdapter.Builder()
                .

                        setClient(client)

                .

                        setErrorHandler(new MyErrorHandler()

                        )


                .

                        setConverter(new JacksonConverter()

                        ).

                        setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)

                .

                        setLog(new AndroidLog("YOUR_LOG_TAG")

                        )
                .

                        setEndpoint(getString(R.string.base_url)

                        )
                .

                        build();

        wordsApiRequest = restAdapter.create(WordsApiRequest.class);


        AdRequest adRequest = new AdRequest.Builder().build();


        mAdView = (AdView) findViewById(R.id.adView);

        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);
        mAdView.setAdListener(adListener2);


        adRequestInter = new AdRequest.Builder().
//                addTestDevice("2795DA65D50FE4C721767208480E9ABC").
        build();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4443948134096736/4830862004");
        mInterstitialAd.loadAd(adRequestInter);
        mInterstitialAd.setAdListener(adListener);

    }

    private void savedWordsRefresh() {
        String serialized = preferences.getString(SAVED_WORDS, null);
        if (serialized != null) {
            savedWords = new ArrayList<>(Arrays.asList(TextUtils.split(serialized, ",")));


        }

        if (savedWords == null) {
            savedWords = new ArrayList<>();
        }
    }

    public Set<?> findDuplicatesInList(List<?> beanList) {
        System.out.println("findDuplicatesInList::" + beanList);
        Set<Object> duplicateRowSet = null;
        duplicateRowSet = new LinkedHashSet<Object>();
        for (int i = 0; i < beanList.size(); i++) {
            Object superString = beanList.get(i);
            System.out.println("findDuplicatesInList::superString::" + superString);
            for (int j = 0; j < beanList.size(); j++) {
                if (i != j) {
                    Object subString = beanList.get(j);
                    System.out.println("findDuplicatesInList::subString::" + subString);
                    if (superString.equals(subString)) {
                        duplicateRowSet.add(beanList.get(j));
                    }
                }
            }
        }
        System.out.println("findDuplicatesInList::duplicationSet::" + duplicateRowSet);
        return duplicateRowSet;
    }

    class MyErrorHandler implements ErrorHandler {


        @Override
        public Throwable handleError(RetrofitError cause) {
            if (cause.getMessage() != null) {
                cause.printStackTrace();
                Log.e("retrofit error", cause.getMessage());
            } else {
                //cause.getResponse().getReason();
            }
            Response r = cause.getResponse();
            if (r != null && r.getStatus() == 403) {
                //TODO
//                Toast.makeText(getContext(),"403 alınıdı ",Toast.LENGTH_SHORT).show();
            } else if (r != null && r.getStatus() == 500) {
                //TODO
//                Utils.showInfoDialog(getContext(), null, getString(R.string.warning), getString(R.string.gneral_exception));
            }

            return cause;
        }


    }


    public void translate(final String text, final TextView textView) {

        class LongOperation extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String translatedText = null;
                try {
                    translatedText = Translate.execute(params[0], Language.ENGLISH, Language.TURKISH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return translatedText;
            }

            @Override
            protected void onPostExecute(String result) {
                if (textView != null) {
                    if (result == null) {
                        textView.setText(R.string.check_net_connection);
                    } else {
                        textView.setText(result);
                    }
                }
                if (translateInterface != null) {
                    translateInterface.translate(text, result);
                }

                // might want to change "executed" for the returned string passed
                // into onPostExecute() but that is upto you
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
        }
        new LongOperation().execute(text);
    }

    AdListener adListener2 = new AdListener() {
        @Override
        public void onAdClosed() {
            super.onAdClosed();
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            super.onAdFailedToLoad(errorCode);
        }

        @Override
        public void onAdLeftApplication() {
            super.onAdLeftApplication();
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mAdView.setVisibility(View.VISIBLE);
                    if (ivTemp != null) {
                        ivTemp.setVisibility(View.GONE);
                    }

                    ;
                }
            }, 5000);
        }
    };

    AdListener adListener = new AdListener() {
        @Override
        public void onAdClosed() {
            super.onAdClosed();
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            super.onAdFailedToLoad(errorCode);
        }

        @Override
        public void onAdLeftApplication() {
            super.onAdLeftApplication();
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            int i = randInt(3);
            if (i == 2) {
                mInterstitialAd.show();

            }

        }
    };

    public static int randInt(int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max + 1);

        return randomNum;
    }

    @Override
    protected void onStart() {
        super.onStart();
        savedWordsRefresh();
    }
}
