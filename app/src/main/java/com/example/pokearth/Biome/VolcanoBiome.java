package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class VolcanoBiome implements Biome {

    private Random random;

    /**
     * charmander, vuplix, growlithe, geodude, koffing
     */
    private final int[] commonPokemonIds = {4, 37, 58, 74, 109}; // 75% chance

    /**
     * charmeleon, magmar, flareon, weezing, graveller
     */
    private final int[] uncommonPokemonIds = {5, 126, 136, 110, 75}; // 20% chance

    /**
     * charizard, ninetales, arcanine, golem
     */
    private final int[] rarePokemonIds = {6, 38, 59, 76}; // 4% chance

    /**
     * Moltres
     */
    private final int[] legendaryPokemonIds = {146}; // 1% chance

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
            Log.d(VolcanoBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(VolcanoBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.99) {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(VolcanoBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        } else {
            arrayLength = this.legendaryPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.legendaryPokemonIds[index];
            Log.d(VolcanoBiome.class.getSimpleName(), "legendary pokemon!!!, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
