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
        String sql = "CREATE TABLE IF NOT EXISTS player_stats(uuid varchar(36),deaths int, kills int, blocks_broken long)";


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

            int deaths = results.getInt("deaths");
            int kills = results.getInt("kills");
            long blocksBroken = results.getLong("blocks_broken");



            PlayerStats playerStats = new PlayerStats(uuid,deaths,kills,blocksBroken);

            statement.close();

            return playerStats;
        }
        statement.close();
        return null;
    }

    public void createPlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO player_stats(uuid,deaths,kills,blocks_broken) VALUES (?,?,?,?)");
        statement.setString(1, stats.getUuid());
        statement.setInt(2, stats.getDeaths());
        statement.setInt(3,stats.getKills());
        statement.setLong(4,stats.getBlocksBroken());

        statement.executeUpdate();

        statement.close();


    }

    public void updatePlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_stats SET deaths = ?, kills = ?, blocks_broken = ? WHERE uuid = ?");


        statement.setInt(1, stats.getDeaths());
        statement.setInt(2,stats.getKills());
        statement.setLong(3,stats.getBlocksBroken());
        statement.setString(4, stats.getUuid());


        statement.executeUpdate();

        statement.close();


    }


}
