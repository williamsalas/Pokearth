package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class GrasslandBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {pidgey, rattata, nidoranfemale, nidoranmale, mankey, bellsprout, doduo}; // 75% chance

    private final int[] uncommonPokemonIds = {pidgeotto, raticate, primeape, ponyta, dodrio, rhyhorn, eevee}; // 20% chance

    private final int[] rarePokemonIds = {pidgeot, rapidash, hitmonlee, hitmonchan, rhydon, kangaskhan, tauros}; // 5% chance

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
