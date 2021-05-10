package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class JungleBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {bulbasaur, ekans, nidoranfemale, nidoranmale, oddish, paras, bellsprout}; // 75% chance

    private final int[] uncommonPokemonIds = {ivysaur, arbok, nidorina, nidorino, gloom, parasect, venonat, weepinbell, exeggcute}; // 20% chance

    private final int[] rarePokemonIds = {venusaur, nidoqueen, nidoking, vileplume, venomoth, victreebel, exeggutor, scyther, pinsir}; // 5% chance

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
            Log.d(JungleBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(JungleBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(JungleBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
