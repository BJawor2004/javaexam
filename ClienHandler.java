package pl.umcs.oop.server;
import pl.umcs.oop.game.Gesture;
import pl.umcs.oop.game.Player;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class ClienHandler extends Player {
    private Socket socket;
    private  PrintWriter out;
    private Server server;
    private String login;
    public ClienHandler(Socket socket, Server server)
    {
        this.socket = socket;
        this.server = server;
    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
    }

    @Override
    public void run() {
        System.out.println("Stworzono wątek obsługujący: " + socket);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out  = new PrintWriter(socket.getOutputStream(),true);
            out.println("Podaj login");
            String login;
            login = in.readLine().trim();
            out.println("Podaj hasło: ");
            String password = in.readLine().trim();
            if(server.getDb().authenticate(login, password)) {
                this.login = login;
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Odebrano: " + line);
                    out.println(line);
                    if(isDuel())
                    {
                        line = line.trim();
                        if(line.equals("p") || line.equals("r") || line.equals("s"))
                        {
                            makeGesture(Gesture.fromString(line));
                            sendMessage("Wybrano: " + line);
                        }
                    }
                    else
                    {
                        server.challengeToDuel(this, line);
                    }
                }
            } else {
                System.out.println("Logowanie się nie powiodło");
            }
            in.close();
            out.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try
            {
                socket.close();
                server.removeClient(this);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public String getLogin()
    {
        return login;
    }
}
