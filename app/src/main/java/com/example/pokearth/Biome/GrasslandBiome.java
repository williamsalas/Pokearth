package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class GrasslandBiome implements Biome {

    private Random random;

    /**
     * pidgey, rattata, nidoran fem/male, mankey, bellsprout, doduo
     */
    private final int[] commonPokemonIds = {16, 19, 29, 32, 56, 69, 84}; // 75% chance

    /**
     * pidgeotto, raticate, primeape, ponyta, dodrio, rhyhorn, eevee
     */
    private final int[] uncommonPokemonIds = {17, 20, 57, 77, 85, 111, 133}; // 20% chance

    /**
     * pidgeot, rapidash, hitmonlee, hitmonchan, rhydon, kangaskhan, tauros
     */
    private final int[] rarePokemonIds = {18, 78, 106, 107, 112, 115, 128}; // 5% chance

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
            Log.d(GrasslandBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(GrasslandBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(GrasslandBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
