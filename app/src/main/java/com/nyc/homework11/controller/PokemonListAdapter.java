package com.nyc.homework11.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nyc.homework11.MyListener;
import com.nyc.homework11.PokeDetailsFragment;
import com.nyc.homework11.R;
import com.nyc.homework11.model.Pokemon;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> implements MyListener{

    private ArrayList<Pokemon> dataset;
    private Context context;

    private boolean isFragmentShown = false;
    private FragmentManager manager;
    private int position = 0;
    private ViewHolder holder;
    private Pokemon pokemon;

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public PokemonListAdapter(Context context) {
        this.context = context;
        this.dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        p.setPosition(position);
        setPokemon(p);
        MyThread myThread = new MyThread(holder,dataset,p);
        Thread thread = new Thread(myThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Picasso.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getId() + ".png")     // optional
                // optional
                .resize(350, 300)
                .into(holder.imageView);


    }

    public void setPokemon(Pokemon p){
        this.pokemon = p;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public void addPokemonList(ArrayList<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }

    @Override
    public void doStuff(String string) {
    }



    public class MyThread implements Runnable, View.OnClickListener{
        private ViewHolder holder;
        private ArrayList<Pokemon> dataset;
        private final Pokemon pokemon;

        public MyThread(ViewHolder holder,ArrayList<Pokemon> dataset, Pokemon pokemon) {
            this.holder = holder;
            this.dataset = dataset;
            this.pokemon = pokemon;
        }

        @Override
        public void run() {

            holder.textView.setText(dataset.get(pokemon.getPosition()).getName());
            holder.imageView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            // Delete old fragment

            PokeDetailsFragment fragment = new PokeDetailsFragment();
            View fragmentView = fragment.getView();
            fragment.setPokemon(getPokemon());
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.relativeLayout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageButton imageView;
        private TextView textView;
        private int mediaContainer;

        public void setMediaContainer(int mediaContainer) {
            this.mediaContainer = mediaContainer;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageButton) itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
            mediaContainer = itemView.getId();

        }

        public int getMediaContainer() {
            return mediaContainer;
        }

        public ImageButton getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
