package com.example.fedupmini;


public class USERS {



    String username,collage , email,location ,number;
    public USERS(String username, String collage, String email, String location, String number) {
        this.username = username;
        this.collage = collage;
        this.email = email;
        this.location = location;
        this.number = number;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }



    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
