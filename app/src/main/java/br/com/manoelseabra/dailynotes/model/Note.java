package br.com.manoelseabra.dailynotes.model;

import android.graphics.Color;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String content;
    private int color;

    public Note(int id) {
        this.id = id;
        this.color = Color.WHITE;
    }

    public Note(int id, String title, String content, int color) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
