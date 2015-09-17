package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2015-08-28.
 */

public class Item {
    private int id;
    private String title;    //곡 제목
    private String artist;  //아티스트
    private String genre;   //장르
    private String filename;    //파일이름
    private String gender; //성별

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImageUrl() {
        return String.format("http://220.149.119.118/BitProject/AlbumImage/%d.jpg", this.getId());
    }

    public String getMp3Url() {
        return String.format("http://220.149.119.118/BitProject/Mp3/%d.mp3", this.getId());
    }

}
