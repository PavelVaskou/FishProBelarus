package com.example.eugeney.fishprobelarus;

public class InformationRigging {

    String name;
    String image;
    String info;

    public InformationRigging(String name, String image, String info){
        this.name = name;
        this.image = image;
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
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
}
