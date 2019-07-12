package com.mhmdawad.moviescloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MoviesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_details_layout);

        TextView titleText = findViewById(R.id.title);
        TextView videoText = findViewById(R.id.videoText);
        TextView adultText = findViewById(R.id.adultText);
        TextView voteText = findViewById(R.id.voteText);
        TextView languageText = findViewById(R.id.languageText);
        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView dateText = findViewById(R.id.dateText);
        TextView starText = findViewById(R.id.starTextView);
        ImageView backgroundImage = findViewById(R.id.imageBackground);
        final ImageView whiteLikeImage = findViewById(R.id.whitelove);
        final ImageView redLikeImage = findViewById(R.id.redlove);

        assert getIntent().getExtras() != null;
        String title = getIntent().getExtras().getString("title");
        String language = getIntent().getExtras().getString("language");
        String description = getIntent().getExtras().getString("description");
        String date = getIntent().getExtras().getString("date");
        String background = getIntent().getExtras().getString("background");
        String vote = String.valueOf(getIntent().getExtras().getLong("vote"));
        String voteAvg = String.valueOf(getIntent().getExtras().getDouble("voteAvg"));

        String booleanVideo;
        if (getIntent().getExtras().getBoolean("video")) {
            booleanVideo = String.valueOf(getIntent().getExtras().getDouble("video"));
        } else {
            booleanVideo = "No Video URL!";
        }

        String booleanAdult;
        if (getIntent().getExtras().getBoolean("adult")) {
            booleanAdult = "Adult!";
        } else {
            booleanAdult = "Not Adult";
        }





        titleText.setText(title);
        videoText.setText(booleanVideo);
        adultText.setText(booleanAdult);
        voteText.setText(vote);
        languageText.setText(language);
        descriptionText.setText(description);
        dateText.setText(date);
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + background).into(backgroundImage);
        starText.setText(voteAvg);


        whiteLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteLikeImage.setVisibility(View.INVISIBLE);
                redLikeImage.setVisibility(View.VISIBLE);
            }
        });

        redLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redLikeImage.setVisibility(View.INVISIBLE);
                whiteLikeImage.setVisibility(View.VISIBLE);
            }
        });



    }


}
