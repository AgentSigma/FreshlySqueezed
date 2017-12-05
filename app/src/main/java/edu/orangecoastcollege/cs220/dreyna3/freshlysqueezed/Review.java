package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

/**
 * Created by Lelouch on 11/29/2017.
 */

public class Review {
    private int id;
    private String Author;
    private String title;
    private float rating;
    private String review;

    public Review(String author,String review, String title, float rating) {
        id=-1;
        this.Author = author;
        this.title = title;
        this.rating = rating;
        this.review = review;
    }

    public Review(int id, String author, String review, String title, float rating) {
        this.id = id;
        this.Author = author;
        this.title = title;
        this.rating = rating;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "Author='" + Author + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
