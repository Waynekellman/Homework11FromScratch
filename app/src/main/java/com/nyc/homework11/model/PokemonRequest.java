package com.nyc.homework11.model;

import java.util.ArrayList;

/**
 * Created by c4q on 11/29/17.
 */

public class PokemonRequest {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
