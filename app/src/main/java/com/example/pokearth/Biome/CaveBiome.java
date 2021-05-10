package com.example.pokearth.Biome;

import android.util.Log;

import java.util.Random;

public class CaveBiome implements Biome {

    private Random random;

    private final int[] commonPokemonIds = {sandshrew, machop, geodude, zubat, paras, diglett}; // 75% chance

    private final int[] uncommonPokemonIds = {sandslash, machoke, graveler, golbat, jigglypuff, parasect, dugtrio}; // 20% chance

    private final int[] rarePokemonIds = {machamp, golem, wigglytuff, onix}; // 4% chance

    private final int[] legendaryPokemonIds = {aerodactyl}; // 1% chance

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
            Log.d(CaveBiome.class.getSimpleName(), "common pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.95) {
            // spawn uncommon
            arrayLength = this.uncommonPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.uncommonPokemonIds[index];
            Log.d(CaveBiome.class.getSimpleName(), "uncommon pokemon, id: " + wildPokemonId);
        } else if (encounterRoll <= 0.99) {
            // spawn rare
            arrayLength = this.rarePokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.rarePokemonIds[index];
            Log.d(CaveBiome.class.getSimpleName(), "rare pokemon, id: " + wildPokemonId);
        } else {
            arrayLength = this.legendaryPokemonIds.length;
            index = this.random.nextInt(arrayLength);
            wildPokemonId = this.legendaryPokemonIds[index];
            Log.d(CaveBiome.class.getSimpleName(), "legendary pokemon!!!, id: " + wildPokemonId);
        }

        return wildPokemonId;
    }
}
