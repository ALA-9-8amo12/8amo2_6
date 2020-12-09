package com.example.amazighquiz;

public class SpeelModel {

    String image;
    String sound;

    public SpeelModel(){}

    public SpeelModel(String image, String sound) {
        this.image = image;
        this.sound = sound;
    }

    public String getImage() {
        return image;
    }

    public String getSound() {
        return sound;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
