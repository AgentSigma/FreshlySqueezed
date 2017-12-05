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
    private List<Review> mReviewList = new ArrayList<>();
    private int mResourceId;
    private LinearLayout mReviewItemLinearLayout;
    private TextView titleTextView;
    private TextView mAuthorTextView;
    private RatingBar ReviewRatingBar;
    private TextView reviewTextView;

    public ReviewListAdapter(Context c, int rId, List<Review> reviews) {
        super(c, rId, reviews);
        mContext = c;
        mResourceId = rId;
        mReviewList = reviews;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        Review review = mReviewList.get(pos);

        mReviewItemLinearLayout =
                (LinearLayout) view.findViewById(R.id.reviewItemLinearLayout);
        mAuthorTextView =
                (TextView) view.findViewById(R.id.reviewItemAuthorTextView);
        titleTextView =
                (TextView) view.findViewById(R.id.reviewItemMovieTitle);
        ReviewRatingBar =
                (RatingBar) view.findViewById(R.id.reviewItemRatingBar);
        reviewTextView =
                (TextView) view.findViewById(R.id.reviewItemReviewText);

        mAuthorTextView.setText("Author: " + review.getAuthor());
        titleTextView.setText("Title: " + review.getTitle());
        ReviewRatingBar.setRating(review.getRating());
        reviewTextView.setText(review.getReview());

        mReviewItemLinearLayout.setTag(review);
        return view;
    }
}


