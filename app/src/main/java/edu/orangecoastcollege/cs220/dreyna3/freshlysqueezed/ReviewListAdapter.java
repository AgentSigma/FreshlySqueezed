package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.app.Activity;
import android.content.Context;
import android.media.Rating;
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

public class ReviewListAdapter extends ArrayAdapter<Review> {
    private Context mContext;
    private List<Review> mLogsList = new ArrayList<>();
    private int mResourceId;
    private LinearLayout ReviewListLinearLayout;
    private TextView titleTextView;
    private TextView AuthorTextView;
    private RatingBar ReviewRatingBar;
    private TextView descriptionTextView;

    public ReviewListAdapter(Context c, int rId, List<Review> logs) {
        super(c, rId, logs);
        mContext = c;
        mResourceId = rId;
        mLogsList = logs;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        Review review = mLogsList.get(pos);
        ReviewListLinearLayout =
                (LinearLayout) view.findViewById(R.id.ReviewItemLinearLayout);
        descriptionTextView   =
                (TextView) view.findViewById(R.id.ReviewItemText);
        titleTextView =
                (TextView) view.findViewById(R.id.ReviewmovieTitle);
        AuthorTextView=
                (TextView) view.findViewById(R.id.authorTextViewItem);
        ReviewRatingBar =
                (RatingBar) view.findViewById(R.id.ReviewratingBar);

        titleTextView.setText(review.getTitle());
        descriptionTextView.setText(review.getReview());
       ReviewRatingBar.setRating(review.getRating());;
       AuthorTextView.setText(review.getAuthor());
        ReviewListLinearLayout.setTag(review);
        return view;
    }
}


