package com.nyc.homework11.model;

/**
 * Created by c4q on 11/29/17.
 */

public class Pokemon {

    private int id;
    private String name;
    private String url;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        String[] urlPartes = url.split("/");
        return Integer.valueOf(urlPartes[urlPartes.length - 1]);
    }

    public void setId(int id) {
        this.id = id;
    }
}
