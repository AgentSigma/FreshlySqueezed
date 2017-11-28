package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

/**
 * Created by anamvari on 11/28/2017.
 */

public class Movie {

    private String movieTitle;
    private String genre;
    private int rating;
    private String description;
    private String imageName;


    Movie(){}

    public String getMovieTitle()
    {
        return movieTitle;
    }

    public String getGenre()
    {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setMovieTitle(String newMovieTitle)
    {
        movieTitle = newMovieTitle;
    }

    public void setGenre(String newGenre)
    {
        genre = newGenre;
    }

    public void setRating(int newRating)
    {
        rating = newRating;
    }

    public void setDescription(String newDescription)
    {
        description = newDescription;
    }

    public void setImageName(String newImageName)
    {
        imageName = newImageName;
    }



}
