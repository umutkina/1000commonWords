package com.umutkina.a1000mostcommonwords.modals;

import java.io.Serializable;

/**
 * Created by mac on 03/01/16.
 */
public class Word implements Serializable {
    private static final long serialVersionUID = 1013638015020171062L;
    private  long id;
    private String text;
    private  boolean selected;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
