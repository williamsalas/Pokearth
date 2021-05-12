package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class PowerPlantBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {magnemite, voltorb}; // 75% chance

    private final int[] uncommonPokemonIds = {pikachu, magneton, electrode}; // 20% chance

    private final int[] rarePokemonIds = {jolteon, raichu, electabuzz, ditto}; // 4% chance

    private final int[] legendaryPokemonIds = {zapdos}; // 1% chance

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
            Log.d(PowerPlantBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(PowerPlantBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.99) {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(PowerPlantBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        } else {
            arrayLength = this.legendaryPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.legendaryPokemonIds[index];
            Log.d(PowerPlantBiome.class.getSimpleName(), "legendary pokemon!!!, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
