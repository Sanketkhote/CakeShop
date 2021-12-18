package com.example.cakeshop.adapter;

public class Listitem_cake {

    String image,heading,subHeading;
    int price;

    public Listitem_cake(String image, String heading, String subHeading,int price) {
        this.image=image;
        this.heading=heading;
        this.subHeading=subHeading;
        this.price =price;
    }

    public String getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }
    public int getPrice() {
        return price;
    }

    public String getSubHeading() {
        return subHeading;
    }

}