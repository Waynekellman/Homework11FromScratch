package com.nyc.homework11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nyc.homework11.controller.PokemonListAdapter;
import com.nyc.homework11.model.PokemonRequest;
import com.nyc.homework11.model.Pokemon;
import com.nyc.homework11.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;

    private int offset;
    private boolean suitableForLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonListAdapter = new PokemonListAdapter(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemcount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(suitableForLoad){
                        if((visibleItemCount + pastVisibleItems) >= totalItemcount){
                            Log.i(TAG, "endpoint");
                            suitableForLoad = false;
                            offset += 20;
                            obtainData(offset);
                        }
                    }
                }
            }
        });

        pokemonListAdapter.setManager(getFragmentManager());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        suitableForLoad = true;
        offset = 0;
        obtainData(offset);
    }

    private void obtainData(int offset) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRequest> pokemonRequestCall = service.obtainPokemonList(20,offset);

        pokemonRequestCall.enqueue(new Callback<PokemonRequest>() {
            @Override
            public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {
                suitableForLoad = true;
                if (response.isSuccessful()) {
                    PokemonRequest pokemonRequest = response.body();
                    ArrayList<Pokemon> pokemonList = pokemonRequest.getResults();

                    pokemonListAdapter.addPokemonList(pokemonList);
                } else {
                    Log.e(TAG, "onResponce: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRequest> call, Throwable t) {
                suitableForLoad = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
