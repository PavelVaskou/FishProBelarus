package com.example.eugeney.fishprobelarus;

public class InformationFish {
    String name;
    String text;
    String data;
    private int fishResource;

    public InformationFish(String name, String data, int fishResource){
        this.name = name;
        this.fishResource = fishResource;
        this.data = data;
    }

    public InformationFish(String name,String text, String data){
        this.name = name;
        this.text = text;
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int getFishResource() {
        return this.fishResource;
    }

    public void setFishResource(int flagResource) {
        this.fishResource = flagResource;
    }

}
