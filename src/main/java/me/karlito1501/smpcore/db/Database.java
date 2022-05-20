package me.karlito1501.smpcore.db;

import me.karlito1501.smpcore.podels.PlayerStats;
import org.bukkit.entity.Player;

import java.sql.*;

public class Database {

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        String url = "jdbc:mysql://localhost";
        String user = "root";
        String password = "";


        this.connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the Database!");

        return this.connection;


    }

    public void initializeDatabase() throws SQLException{

        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS player_stats(uuid varchar(36),nickname text,deaths int, kills int, blocks_broken long, stone_broken long, diamonds_broken long)";


        statement.execute("CREATE DATABASE IF NOT EXISTS playerData;");
        statement.execute("USE playerData");
        statement.execute(sql);

        statement.close();

        System.out.println("Created stats table in database");




    }

    public PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1,uuid);

        ResultSet results = statement.executeQuery();

        if (results.next()){
            String nickname = results.getString("nickname");
            int deaths = results.getInt("deaths");
            int kills = results.getInt("kills");
            long blocksBroken = results.getLong("blocks_broken");
            long stoneBroken = results.getLong("stone_broken");
            long diamondsBroken = results.getLong("diamonds_broken");



            PlayerStats playerStats = new PlayerStats(uuid,nickname,deaths,kills,blocksBroken,stoneBroken,diamondsBroken);

            statement.close();

            return playerStats;
        }
        statement.close();
        return null;
    }

    public void createPlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO player_stats(uuid,nickname,deaths,kills,blocks_broken, stone_broken, diamonds_broken) VALUES (?,?,?,?,?,?,?)");
        statement.setString(1, stats.getUuid());
        statement.setString(2,stats.getNickname());
        statement.setInt(3, stats.getDeaths());
        statement.setInt(4,stats.getKills());
        statement.setLong(5,stats.getBlocksBroken());
        statement.setLong(6, stats.getStoneBroken());
        statement.setLong(7,stats.getDiamondsBroken());

        statement.executeUpdate();

        statement.close();


    }

    public void updatePlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_stats SET nickname = ? ,deaths = ?, kills = ?, blocks_broken = ?, stone_broken = ?, diamonds_broken = ? WHERE uuid = ?");

        statement.setString(1, stats.getNickname());
        statement.setInt(2, stats.getDeaths());
        statement.setInt(3,stats.getKills());
        statement.setLong(4,stats.getBlocksBroken());
        statement.setLong(5, stats.getStoneBroken());
        statement.setLong(6,stats.getDiamondsBroken());
        statement.setString(7, stats.getUuid());


        statement.executeUpdate();

        statement.close();


    }


}
