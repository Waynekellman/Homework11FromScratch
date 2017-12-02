package com.nyc.homework11.model;

import com.nyc.homework11.model.objects.Sprites;
import com.nyc.homework11.model.objects.Stats;
import com.nyc.homework11.model.objects.Types;

/**
 * Created by c4q on 12/1/17.
 */

public class Pokemon {
    private Stats[] stats;
    private Sprites sprites;
    private Types[] types;
    private int position;

    //TODO: Create getters

    public Stats[] getStats() {
        return stats;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public Types[] getTypes() {
        return types;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
