package com.example.assignment_try;

public class Question {
    private Integer score;
    String title;
    String link;
    Integer creation_date;


    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public Integer getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Integer getCreation_date() {
        return creation_date;
    }
}

    class Owner
{
    String display_name;

    public String getDisplay_name() {
        return display_name;
    }

    String profile_image;

    public String getProfile_image() {
        return profile_image;
    }
}
