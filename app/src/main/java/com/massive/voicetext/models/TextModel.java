package com.massive.voicetext.models;

import java.io.Serializable;


public class TextModel implements Serializable {
    private String ID ;
    private String text ;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
