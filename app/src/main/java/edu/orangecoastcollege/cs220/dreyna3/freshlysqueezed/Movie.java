package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anamvari on 11/28/2017.
 */

public class Movie implements Parcelable {

    private String movieTitle;
    private String genre;
    private float rating;
    private String description;
    private String imageName;


    Movie(){}

    Movie(String movieTitle, String genre, float rating, String description, String imageName) {
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.imageName = imageName;
    }

    Movie(Parcel in) {
        movieTitle = in.readString();
        genre = in.readString();
        rating = in.readFloat();
        description = in.readString();
        imageName = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieTitle()
    {
        return movieTitle;
    }

    public String getGenre()
    {
        return genre;
    }

    public float getRating() {
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

    public void setRating(float newRating)
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieTitle);
        parcel.writeString(genre);
        parcel.writeFloat(rating);
        parcel.writeString(description);
        parcel.writeString(imageName);
    }
}
