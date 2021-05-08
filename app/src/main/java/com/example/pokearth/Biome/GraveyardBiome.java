package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class GraveyardBiome implements Biome {

    private Random random;

    /**
     * zubat, gastly
     */
    private final int[] commonPokemonIds = {41, 92}; // 75% chance

    /**
     * golbat, grimer, haunter ,cubone, koffing
     */
    private final int[] uncommonPokemonIds = {42, 88, 93, 104, 109}; // 20% chance

    /**
     * muk, gengar, marowak, weezing
     */
    private final int[] rarePokemonIds = {89, 94, 105, 110}; // 5% chance

    @Override
    public int spawnWildPokemon() {
        this.random = new Random();
        double encounterRoll = random.nextDouble();
        int arrayLength;
        int wildPokemonId;
        int index;
        if (encounterRoll <= 0.75) {
            // spawn common
            arrayLength = this.commonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.commonPokemonIds[index];
            Log.d(GraveyardBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(GraveyardBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(GraveyardBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
