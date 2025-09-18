package pl.umcs.oop.server;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Connection conn;
    public Database(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public boolean authenticate(String login, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if(rs.next())
        {
            return true;
        }
        return false;
    }

    public void updateLeaderboard(String winner, String loser) throws SQLException {
        String win = "UPDATE users SET points = points + 1 WHERE login = ?";
        String los = "UPDATE users SET points = points - 1 WHERE login = ?";
        PreparedStatement stmt1 = conn.prepareStatement(win);
        stmt1.executeUpdate();
        PreparedStatement stmt2 = conn.prepareStatement(los);
        stmt2.executeUpdate();
    }

    public Map<String, Integer> getLeaderboard() throws SQLException {
        String sql =  "SELECT login, points FROM users ORDER BY points DESC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        Map<String, Integer> leaderBoard = new HashMap<>();
        while(rs.next())
        {
            leaderBoard.put(rs.getString("login"), rs.getInt("points"));
        }
        return leaderBoard;
    }
}
