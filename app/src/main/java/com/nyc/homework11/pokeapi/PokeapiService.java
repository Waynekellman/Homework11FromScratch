package com.nyc.homework11.pokeapi;

import com.nyc.homework11.model.PokemonRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by c4q on 11/29/17.
 */

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonRequest> obtainPokemonList(@Query("limit") int limit,@Query("offset") int offset);
}
