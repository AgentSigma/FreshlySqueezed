package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lelouch on 11/20/2017.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {
    private Context mContext;
    private List<Movie> mMovieList = new ArrayList<>();
    private int mResourceId;
    private LinearLayout MovieListLinearLayout;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private RatingBar RatingBar;
    private ImageView MovieImageView;

    public MovieListAdapter(Context c, int rId, List<Movie> movie) {
        super(c, rId, movie);
        mContext = c;
        mResourceId = rId;
        mMovieList = movie;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        Movie movies = mMovieList.get(pos);
        MovieListLinearLayout =
                (LinearLayout) view.findViewById(R.id.MovieDescriptionLinearLyaout);
        descriptionTextView   =
                (TextView) view.findViewById(R.id.MovieDescriptionItem);
        nameTextView =
                (TextView) view.findViewById(R.id.MovieNameItem);
        RatingBar =
                (RatingBar) view.findViewById(R.id.MovieratingBarItem);
        MovieImageView=
                (ImageView) view.findViewById(R.id.MovieImageView);
        nameTextView.setText(movies.getMovieTitle());
        descriptionTextView.setText(movies.getDescription());
        RatingBar.setRating(movies.getRating());
        String movieName= movies.getImageName();

        MovieImageView.setImageURI(Uri.parse("android.resource://edu.orangecoastcollege.cs220.dreyna3.freshsqueezed/drawable/"+movieName));
        MovieListLinearLayout.setTag(movies);
        return view;
    }
}


