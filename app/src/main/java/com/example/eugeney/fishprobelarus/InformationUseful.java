package com.example.eugeney.fishprobelarus;

public class InformationUseful {

    String name;
    String info;
    String image;

    public InformationUseful (String name, String info, String image){
        this.name = name;
        this.info = info;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
