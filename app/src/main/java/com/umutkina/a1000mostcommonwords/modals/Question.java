package com.umutkina.a1000mostcommonwords.modals;

import java.io.Serializable;

/**
 * Created by mac on 03/01/16.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = -1353750357261585690L;

    public Question(String text, String answer1, String answer2, String aswer3) {
        this.text = text;

        this.answer1 = answer1;
        this.answer2 = answer2;
        this.aswer3 = aswer3;
    }

    private  String text;
    private  String answer1;
    private String answer2;
    private  String aswer3;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAswer3() {
        return aswer3;
    }

    public void setAswer3(String aswer3) {
        this.aswer3 = aswer3;
    }
}
