package com.mhmdawad.moviescloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhmdawad.moviescloud.RetrofitFetchData.JSONData.popular.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Result> {
    public CustomArrayAdapter(Context context, ArrayList<Result> moviesData) {
        super(context, 0,moviesData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_data_layout, parent, false);
        }

        Result result = getItem(position);

        ImageView poster = convertView.findViewById(R.id.PosterImage);
        TextView MovieText = convertView.findViewById(R.id.MovieTextView);
        TextView VoteAverage = convertView.findViewById(R.id.voteAverage);
        ImageView voteStars = convertView.findViewById(R.id.voteStars);

        assert result != null;
        if (result.getVoteAverage() <= 10 && result.getVoteAverage() >= 8)
            voteStars.setImageResource(R.drawable.five);

        else if (result.getVoteAverage() < 8 && result.getVoteAverage() >= 6)
            voteStars.setImageResource(R.drawable.four);

        else if (result.getVoteAverage() < 6 && result.getVoteAverage() >= 4)
            voteStars.setImageResource(R.drawable.three);

        else if (result.getVoteAverage() < 4 && result.getVoteAverage() >= 2)
            voteStars.setImageResource(R.drawable.two);

        else if (result.getVoteAverage() < 2 && result.getVoteAverage() >= 0)
            voteStars.setImageResource(R.drawable.one);

        if (String.valueOf(result.getPosterPath()) == "null"){
            poster.setImageResource(R.drawable.no_image);
        }else{
        Picasso.get().load("http://image.tmdb.org/t/p/w185/"+result.getPosterPath()).into(poster);}
        MovieText.setText(result.getTitle());
        VoteAverage.setText(String.valueOf(result.getVoteAverage()));



        return convertView;
    }
}
