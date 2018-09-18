package com.example.eugeney.fishprobelarus;

public class InformationReservoir {

    String image;
    String name;
    String info;
    String deep;
    String area;
    String[] pisces;

    public InformationReservoir (String image, String name, String info, String deep, String area, String[] pisces){
        this.name = name;
        this.info = info;
        this.deep = deep;
        this.area = area;
        this.pisces = pisces;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInfo (String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setDeep(String deep) {
        this.deep = deep;
    }

    public String getDeep() {
        return deep;
    }

    public void setPisces(String[] pisces) {
        this.pisces = pisces;
    }

    public String[] getPisces() {
        return pisces;
    }

}
