package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class GraveyardBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {zubat, gastly}; // 75% chance

    private final int[] uncommonPokemonIds = {golbat, grimer, haunter, cubone, koffing}; // 20% chance

    private final int[] rarePokemonIds = {muk, gengar, marowak, weezing}; // 5% chance

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
