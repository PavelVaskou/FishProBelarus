package com.example.eugeney.fishprobelarus;

public class InformationReservoir {

    String name;
    String info;
    String image;
    String id;

    public InformationReservoir (String id, String name, String info, String image){
        this.name = name;
        this.info = info;
        this.image = image;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
