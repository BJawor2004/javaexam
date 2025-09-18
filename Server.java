package pl.umcs.oop.server;
import pl.umcs.oop.game.Duel;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.sql.*;
public class Server {
    private int port;
    private Database db;
    private List<ClienHandler> clients = new CopyOnWriteArrayList<>();
    public void listen() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Serwer nasłuchuje na porcie: " + this.port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket);
                ClienHandler ch = new ClienHandler(clientSocket, this);
                clients.add(ch);
                ch.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server(int port)
    {
        this.port = port;
    }

    public void removeClient(ClienHandler ch)
    {
        clients.remove(ch);
    }

    public void connectToDB() throws SQLException {
        String url = "jdbc:sqlite:users.db";
        db = new Database(url);

    }
    public Database getDb()
    {
        return db;
    }
    public static void main(String[] args) throws SQLException {
        Server server = new Server(1234);
        server.connectToDB();
        server.listen();
    }

    public void challengeToDuel(ClienHandler challenger, String challengeeLogin) throws IOException {
        for(int i = 0; i < clients.size(); i++)
        {
            System.out.println(i+": "+clients.get(i).getLogin());
            if(clients.get(i).getLogin().trim().equalsIgnoreCase(challengeeLogin.trim()))
            {
                startDuel(challenger, clients.get(i));
                return;
            }
        }
        challenger.sendMessage("Nie znaleziono osoby o takim loginie");
    }
    private void startDuel(ClienHandler challenger, ClienHandler challengee) throws IOException {
        if(challengee.isDuel())
        {
            challenger.sendMessage("Jest w trakcie pojedynku");
            return;
        }
        if(challenger == challengee)
        {
            challenger.sendMessage("Nie mozna wyzwac siebie");
            return;
        }

        Duel duel = new Duel(challenger, challengee);
        challenger.sendMessage("Rozpoczęto pojedynek");
        challengee.sendMessage("Rozpoczęto pojedynek");

        duel.setOnEnd(() -> {
            Duel.Result dr = duel.evaluate();
            if(dr == null)
            {
                try {
                    challengee.sendMessage("Remis");
                    challenger.sendMessage("Remis");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                try {
                    ((ClienHandler)dr.loser()).sendMessage("Loser");
                    ((ClienHandler)dr.winner()).sendMessage("Winner");
                    db.updateLeaderboard(((ClienHandler) dr.winner()).getLogin(), ((ClienHandler) dr.loser()).getLogin());
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
