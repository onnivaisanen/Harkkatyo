package com.example.harkkatyo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemons;
    private ArrayList<Lutemon> homeLutemons;
    private ArrayList<Lutemon> trainingLutemons;
    private ArrayList<Lutemon> fightLutemons;
    private static Storage storage = null;

    private Storage() {
        lutemons = new HashMap<>();
        homeLutemons = new ArrayList<>();
        trainingLutemons = new ArrayList<>();
        fightLutemons = new ArrayList<>();

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

    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.writeInt(Lutemon.idCounter);
            lutemonWriter.close();
            Toast.makeText(context, "Lutemonit tallennettiin onnistuneesti tiedostoon lutemons.data", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Tallennus epäonnistui", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (HashMap<Integer, Lutemon>) lutemonReader.readObject();
            Lutemon.idCounter = lutemonReader.readInt();
            lutemonReader.close();
            Toast.makeText(context, "Lutemonit ladattiin onnistuneesti tiedostosta lutemons.data", Toast.LENGTH_SHORT).show();


            homeLutemons.clear();
            trainingLutemons.clear();
            fightLutemons.clear();

            for (Lutemon lutemon : lutemons.values()) {
                switch (lutemon.getLocation()) {
                    case "Kotiin":
                    case "Kotona":
                        homeLutemons.add(lutemon);
                        break;
                    case "Treenaamaan":
                        trainingLutemons.add(lutemon);
                        break;
                    case "Taisteluareenalle":
                        fightLutemons.add(lutemon);
                        break;
                    default:
                        homeLutemons.add(lutemon);
                        break;
                }
            }
            Log.d("Storage", "Ladattiin " + lutemons.size() + " lutemonia.");
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Tiedostoa ei löytynyt", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Lataus epäonnistui", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Lataus epäonnistui", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void moveLutemonToLocation(Lutemon lutemon, String location) {
        homeLutemons.remove(lutemon);
        trainingLutemons.remove(lutemon);
        fightLutemons.remove(lutemon);

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
            default:
                Log.d("Storage", "Tuntematon sijainti: " + location);
                break;
        }
        lutemon.setSelected(false);
        lutemon.setLocation(location);
    }
}

