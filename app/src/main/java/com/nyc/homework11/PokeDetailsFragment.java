package com.nyc.homework11;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nyc.homework11.model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokeDetailsFragment extends Fragment {

    private MyListener listener;
    private TextView move1,move2,move3,move4,detail1,detail2,detail3,detail4;
    private View view;
    private Pokemon pokemon;

    public PokeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (RelativeLayout) inflater.inflate(R.layout.fragment_poke_details, container, false);
        setViews(view);
        this.view = view;
        move1.setText(pokemon.getName());
        return view;
    }

    private void setViews(View view) {
        move1 = view.findViewById(R.id.move1);
        move2 = view.findViewById(R.id.move2);
        move3 = view.findViewById(R.id.move3);
        move4 = view.findViewById(R.id.move4);
        detail1 = view.findViewById(R.id.details1);
        detail2 = view.findViewById(R.id.details2);
        detail3 = view.findViewById(R.id.details3);
        detail4 = view.findViewById(R.id.details4);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (listener != null) {
            listener.doStuff("passed data");
        }
    }

    public View getView(){
        return view;
    }

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
