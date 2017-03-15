package com.umutkina.a1000mostcommonwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by mac on 01/01/16.
 */
public class Translate{
    public static String translate(String sl, String tl, String text) throws IOException {
        // fetch
        URL url = new URL("http://translate.google.com.tw/translate_a/t?client=t&hl=en&sl=" +
                sl + "&tl=" + tl + "&ie=UTF-8&oe=UTF-8&multires=1&oc=1&otf=2&ssel=0&tsel=0&sc=1&q=" +
                URLEncoder.encode(text, "UTF-8"));
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Something Else");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String result = br.readLine();
        br.close();
        // parse
        // System.out.println(result);
        result = result.substring(2, result.indexOf("]]") + 1);
        StringBuilder sb = new StringBuilder();
        String[] splits = result.split("(?<!\\\\)\"");
        for(int i = 1; i < splits.length; i += 8)
            sb.append(splits[i]);
        return sb.toString().replace("\\n", "\n").replaceAll("\\\\(.)", "$1");
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Source language:");
        String sl = br.readLine();
        System.out.println("Translation language:");
        String tl = br.readLine();
        System.out.println("Source:");
        String text = br.readLine() + "\n" + br.readLine(); // read two lines
        System.out.println("Translation:");
        System.out.println(translate(sl, tl, text));
    }
}
