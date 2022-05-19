package me.karlito1501.smpcore.podels;

import java.sql.Date;

public class PlayerStats {

    private String uuid;
    private int deaths;
    private int kills;
    private long blocksBroken;
    private double balance;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public long getBlocksBroken() {
        return blocksBroken;
    }

    public void setBlocksBroken(long blocksBroken) {
        this.blocksBroken = blocksBroken;
    }




    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PlayerStats(String uuid, int deaths, int kills, long blocksBroken, double balance) {
        this.uuid = uuid;
        this.deaths = deaths;
        this.kills = kills;
        this.blocksBroken = blocksBroken;
        this.balance = balance;


    }
}
