package com.example.harkkatyo;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemons;
    private ArrayList<Lutemon> homeLutemons;
    private ArrayList<Lutemon> trainingLutemons;
    private ArrayList<Lutemon> fightLutemons;
    private ArrayList<Lutemon> deadLutemons;
    private static Storage storage = null;

    private Storage() {
        lutemons = new HashMap<>();
        homeLutemons = new ArrayList<>();
        trainingLutemons = new ArrayList<>();
        fightLutemons = new ArrayList<>();
        deadLutemons = new ArrayList<>();

    }
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
        homeLutemons.add(lutemon);
    }

    public void removeLutemon(Lutemon lutemon) {
        if (lutemon != null) {
            homeLutemons.remove(lutemon);
            trainingLutemons.remove(lutemon);
            fightLutemons.remove(lutemon);
            lutemons.remove(lutemon);
        }
    }

    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    public ArrayList<Lutemon> getAllLutemons() {
        return new ArrayList<>(lutemons.values());
    }

    public ArrayList<Lutemon> getHomeLutemons() {
        return homeLutemons;
    }

    public ArrayList<Lutemon> getTrainingLutemons() {
        return trainingLutemons;
    }

    public ArrayList<Lutemon> getFightLutemons() {
        return fightLutemons;
    }

    public ArrayList<Lutemon> getDeadLutemons() {
        return deadLutemons;
    }

    public void moveLutemonToLocation(Lutemon lutemon, String location) {
        Log.d("Storage", "Siirretään Lutemon " + lutemon.getName() + " sijaintiin: " + location);  // Lisätty logi
        homeLutemons.remove(lutemon);
        trainingLutemons.remove(lutemon);
        fightLutemons.remove(lutemon);
        deadLutemons.remove(lutemon);

        switch (location) {
            case "Kotiin":
                homeLutemons.add(lutemon);
                break;
            case "Treenaamaan":
                trainingLutemons.add(lutemon);
                break;
            case "Taisteluareenalle":
                fightLutemons.add(lutemon);
                break;
            case "Kuollut":
                deadLutemons.add(lutemon);
                break;
            default:
                Log.d("Storage", "Tuntematon sijainti: " + location);
                break;
        }
        lutemon.setSelected(false);
        lutemon.setLocation(location);
    }
}

