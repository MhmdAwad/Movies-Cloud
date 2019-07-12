package com.mhmdawad.moviescloud.MoviesFragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mhmdawad.moviescloud.CustomArrayAdapter;
import com.mhmdawad.moviescloud.MoviesDetails;
import com.mhmdawad.moviescloud.R;
import com.mhmdawad.moviescloud.RetrofitFetchData.ApiEndPoint;
import com.mhmdawad.moviescloud.RetrofitFetchData.JSONData.popular.MoviesData;
import com.mhmdawad.moviescloud.RetrofitFetchData.JSONData.popular.Result;
import com.mhmdawad.moviescloud.RetrofitFetchData.MoviesURL;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.grid_view_layout , container,false);
        getData(rootView);
        final SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(rootView);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    private void getData(final View rootView) {
        Random r = new Random();
        int low = 1;
        int high = 18;
        int result = r.nextInt(high-low) + low;
        ApiEndPoint api = MoviesURL.getMoviesUrl().create(ApiEndPoint.class);

        if (isInternetAvailable()) {
            api.getUpcoming("2e583849f358f9c5f190de1e2b175244", result).enqueue(new Callback<MoviesData>() {
                @Override
                public void onResponse(@NotNull Call<MoviesData> call, @NotNull final Response<MoviesData> response) {
                    ProgressBar progressBar = rootView.findViewById(R.id.progress);
                    progressBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    ArrayList<Result> resultsList = (ArrayList<Result>) response.body().getResults();
                    GridView gridView = rootView.findViewById(R.id.gridView);
                    CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getActivity(), resultsList);
                    gridView.setAdapter(customArrayAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Result result = response.body().getResults().get(position);
                            Intent intent = new Intent(getActivity(), MoviesDetails.class);
                            intent.putExtra("title", result.getTitle());
                            intent.putExtra("video", result.getVideo());
                            intent.putExtra("adult", result.getAdult());
                            intent.putExtra("vote", result.getVoteCount());
                            intent.putExtra("language", result.getOriginalLanguage());
                            intent.putExtra("description", result.getOverview());
                            intent.putExtra("date", result.getReleaseDate());
                            intent.putExtra("background", result.getPosterPath());
                            intent.putExtra("voteAvg", result.getVoteAverage());
                            startActivity(intent);

                        }
                    });

                }

                @Override
                public void onFailure(@NotNull Call<MoviesData> call, @NotNull Throwable t) {
                    ProgressBar progressBar = rootView.findViewById(R.id.progress);
                    progressBar.setVisibility(View.GONE);
                    ImageView noDataImage = rootView.findViewById(R.id.noWifiImage);
                    TextView noDataText = rootView.findViewById(R.id.noWifiText);
                    noDataImage.setVisibility(View.VISIBLE);
                    noDataText.setVisibility(View.VISIBLE);
                    noDataImage.setImageResource(R.drawable.no_data);
                    noDataText.setText(R.string.noData);

                }
            });
        }else {
            ProgressBar progressBar = rootView.findViewById(R.id.progress);
            progressBar.setVisibility(View.GONE);
            ImageView noWifiImage = rootView.findViewById(R.id.noWifiImage);
            TextView noWifiText = rootView.findViewById(R.id.noWifiText);
            noWifiImage.setVisibility(View.VISIBLE);
            noWifiText.setVisibility(View.VISIBLE);
            noWifiImage.setImageResource(R.drawable.no_wifi);
            noWifiText.setText(R.string.NoInternet);
        }
    }

    public boolean isInternetAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
