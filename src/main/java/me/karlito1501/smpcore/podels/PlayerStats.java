package me.karlito1501.smpcore.podels;

import java.sql.Date;

public class PlayerStats {

    private String uuid;
    private String nickname;
    private int deaths;
    private int kills;
    private long blocksBroken;
    private long stoneBroken;
    private long diamondsBroken;



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

    public long getStoneBroken(){
        return stoneBroken;
    }

    public void setStoneBroken(long stoneBroken){
        this.stoneBroken = stoneBroken;
    }

    public long getDiamondsBroken() {
        return diamondsBroken;
    }

    public void setDiamondsBroken(long diamondsBroken) {
        this.diamondsBroken = diamondsBroken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PlayerStats(String uuid,String nickname ,int deaths, int kills, long blocksBroken, long stoneBroken, long diamondsBroken) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.deaths = deaths;
        this.kills = kills;
        this.blocksBroken = blocksBroken;
        this.stoneBroken = stoneBroken;
        this.diamondsBroken = diamondsBroken;



    }
}
