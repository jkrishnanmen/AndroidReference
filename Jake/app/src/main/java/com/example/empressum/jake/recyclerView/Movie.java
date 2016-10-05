package com.example.empressum.jake.recyclerView;

/**
 * Created by empressum on 5/8/16.
 */
public class Movie {
    private String title,genre,year;

    public Movie(){
    }

    public Movie(String title,String genre,String year){
        this.title=title;
        this.genre=genre;
        this.year=year;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String name){
        this.title=name;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String Year){
        this.year=year;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }
}
