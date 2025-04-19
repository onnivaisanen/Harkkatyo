package com.example.harkkatyo;

public class Battle {
    public static String fight(Lutemon a, Lutemon b) {
        StringBuilder battleLog = new StringBuilder();
        while (true) {
            battleLog.append("1: ").append(statsString(a)).append("\n");
            battleLog.append("2: ").append(statsString(b)).append("\n");
            battleLog.append(a.getName()).append(" hyökkää ").append(b.getName()).append(" kimppuun!\n");
            boolean alive = b.defend(a.getAttack());
            if (!alive) {
                battleLog.append(b.getName()).append(" hävisi.\n");
                battleLog.append("Taistelu päättyi.\n");
                a.train();
                a.setWins(a.getWins() + 1);
                a.setFights(a.getFights() + 1);
                a.resetLife();
                b.resetLife();
                b.setFights(b.getFights() + 1);
                b.setLosses(b.getLosses() + 1);
                Storage.getInstance().moveLutemonToLocation(a, "Kotiin");
                Storage.getInstance().moveLutemonToLocation(b, "Kotiin");
                break;
            } else {
                battleLog.append(b.getName()).append(" onnistui välttämään tappion.\n");
            }

            Lutemon temp = a;
            a = b;
            b = temp;
        }
        return battleLog.toString();
    }

    public static String statsString(Lutemon l) {
        return l.getColor() + "("  + l.getName() + ") hyökkäys:" + l.getAttack() + "; puolustus: " + l.getDefense() + "; kokemus: " + l.getExperience() + "; hp: " + l.getLife() + "/" + l.getMaxLife();
    }
}
