package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class IceBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {seel, shellder, zubat}; // 75% chance

    private final int[] uncommonPokemonIds = {dewgong, cloyster, golbat}; // 20% chance

    private final int[] rarePokemonIds = {jynx, lapras}; // 4% chance

    private final int[] legendaryPokemonIds = {articuno}; // 1% chance

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
            Log.d(IceBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(IceBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.99) {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(IceBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        } else {
            arrayLength = this.legendaryPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.legendaryPokemonIds[index];
            Log.d(IceBiome.class.getSimpleName(), "legendary pokemon!!!, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
