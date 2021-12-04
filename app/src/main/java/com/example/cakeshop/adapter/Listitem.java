package com.example.cakeshop.adapter;


public class Listitem {

    String image,heading,subHeading;

    public Listitem(String image, String heading, String subHeading) {
        this.image=image;
        this.heading=heading;
        this.subHeading=subHeading;
    }

    public String getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

}