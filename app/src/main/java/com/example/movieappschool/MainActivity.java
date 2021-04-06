package com.example.movieappschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.movieappschool.data.CinemaDatabaseService;
import com.example.movieappschool.data.LocalAppStorage;
import com.example.movieappschool.data.MovieAPIService;
import com.example.movieappschool.domain.Movie;
import com.example.movieappschool.ui.home.MovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final CinemaDatabaseService cinemaDatabaseService;
    private final MovieAPIService movieAPIService;
    private final String API_KEY = "b3dc30d1b882188c9c0161b97d66f032";
    private final LocalAppStorage localAppStorage;
    private List<Movie> mMovies;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Integer> databaseIdsResult;

    public MainActivity() {
        cinemaDatabaseService = new CinemaDatabaseService("jdbc:jtds:sqlserver://aei-sql2.avans.nl:1443/CinemaApplicationDB", "MovieB2", "AnikaWante");
        movieAPIService = new MovieAPIService(API_KEY, "en-US");
        localAppStorage = (LocalAppStorage) this.getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        recyclerView = findViewById(R.id.movie_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Create three new threads.
        Thread cinemaDatabaseThread = new Thread(() -> databaseIdsResult = cinemaDatabaseService.getAllMovieIds());

        Thread movieAPIThread = new Thread(() -> {
            mMovies = movieAPIService.getMoviesByIds(databaseIdsResult);
            localAppStorage.setMovies(mMovies);
        });

        Thread adapterThread = new Thread(() -> {
            mAdapter = new MovieAdapter(mMovies, MainActivity.this);
            recyclerView.setAdapter(mAdapter);
        });

        // Start and join the threads.
        try {
            cinemaDatabaseThread.start();
            cinemaDatabaseThread.join();

            movieAPIThread.start();
            movieAPIThread.join();

            adapterThread.start();
            adapterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}