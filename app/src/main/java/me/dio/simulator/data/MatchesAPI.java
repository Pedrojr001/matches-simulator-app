package me.dio.simulator.data;


import java.util.List;

import me.dio.simulator.domain.Macth;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesAPI {

    @GET("matches.json")
    Call<List<Macth>> getMaches();
}
