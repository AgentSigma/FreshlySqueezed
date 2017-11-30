package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sryan10 on 11/30/2017.
 */

public class MovieJSONLoader { /**

 * Loads JSON data from a file in the assets directory.
 * @param context The activity from which the data is loaded.
 * @throws IOException If there is an error reading from the JSON file.
 */
public static List<Movie> loadJSONFromAsset(Context context) throws IOException {
    ArrayList<Movie> allMoviesList = new ArrayList<>();
    String json = null;
    InputStream is = context.getAssets().open("MovieList.json");
    int size = is.available();
    byte[] buffer = new byte[size];
    is.read(buffer);
    is.close();
    json = new String(buffer, "UTF-8");

    //TODO: Now that the JSON string has been retrieved, parse it for each individual
    //TODO: MusicEvent object and add each object to the ArrayList (allEventsList)

    try {
        JSONObject rootObject = new JSONObject(json);
        JSONArray allMoviesArray = rootObject.getJSONArray("Movies");
        int length = allMoviesArray.length();

        // Loop through the array one by one, create a new MusicEvent object,
        // add the object to an array list
        for(int i = 0; i < length; ++i)
        {
            JSONObject event = allMoviesArray.getJSONObject(i);
            Movie movie = new Movie();

            movie.setMovieTitle(event.getString("Title"));
            movie.setGenre(event.getString("Genre"));
            movie.setRating(Float.parseFloat(event.getString("Rating")));
            movie.setDescription(event.getString("Description"));
            movie.setImageName(event.getString("ImageName"));

            allMoviesList.add(movie);
        }
    }

    catch (JSONException e) {
        e.printStackTrace();
    }


    return allMoviesList;
}
}
