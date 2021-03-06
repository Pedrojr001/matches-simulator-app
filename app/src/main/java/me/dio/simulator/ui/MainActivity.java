package me.dio.simulator.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

import me.dio.simulator.data.MatchesAPI;
import me.dio.simulator.databinding.ActivityMainBinding;
import me.dio.simulator.domain.Macth;
import me.dio.simulator.domain.Team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesAPI matchesApi;
    private MatchesAdapter matchesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton():
    }
    private void setupHttpClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedrojr001.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

       matchesApi =  retrofit.create(MatchesAPI.class);
    }
    private void setupMatchesList(){
        binding.rvMatches.setHasFixedSize(true);
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(this));
        findMatchesFromAii();
    }



    private void setupMatchesRefresh(){
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromAii);
    }
    private void setupFloatingActionButton(){
        binding.fabSimulate.setOnClickListener(view -> {
            view.animate().rotation(360).setDuration(500).setListener(new AnimatorListenerAdapter() (animation) {
                    Random random = new Random();
                for (int i = 0; i < matchesAdapter.getItemCount(); i++){
                       Macth match = matchesAdapter.getMatches().get(i);
                       match.getHomeTeam().setScore(random.nextInt(match.getHomeTeam().getStars() + 1));
                    match.getAwayTeam().setScore(random.nextInt(match.getAwayTeam().getStars() + 1));
                    matchesAdapter.notifyItemChanged(i);
                   }
                }
            });
        });
    }
    private void findMatchesFromAii() {
        binding.srlMatches.setRefreshing(true);
        matchesApi.getMaches().enqueue(new Callback<List<Macth>>() {
            @Override
            public void onResponse(Call<List<Macth>> call, Response<List<Macth>> response) {
                if (response.isSuccessful()){
                    List<Macth> matches = response.body();
                    matchesAdapter = new MatchesAdapter(matches);
                    binding.rvMatches.setAdapter(matchesAdapter);
                }else{
                    showErrorMessage();
                }
                binding.srlMatches.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Macth>> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(binding.fabSimulate,R.string.error_api, Snackbar.LENGTH_LONG).show();
    }

}
