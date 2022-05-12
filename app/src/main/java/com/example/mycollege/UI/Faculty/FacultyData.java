package com.example.mycollege.UI.Faculty;

public class FacultyData {
    private String name, eMail, post, image, key;

    public FacultyData() {

    }

    public FacultyData(String name, String eMail, String post, String image, String key) {
        this.name = name;
        this.eMail = eMail;
        this.post = post;
        this.image = image;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
