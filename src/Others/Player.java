package Others;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static String firstPlayerName;
    private static String secondPlayerName;
    private String username;
    private String password;
    private long score, wins, loses;
    private static List<Player> allPlayers = new ArrayList<>();

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
        this.wins = 0;
        this.loses = 0;
        allPlayers.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static boolean validateUsernameAndPassword(String username, String password) {
        Player player = Player.getPlayerByUsername(username);
        if (player.getPassword().equals(password))
            return true;
        else
            return false;
    }

    public static void removePlayer(String username) {
        allPlayers.remove(Player.getPlayerByUsername(username));
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers) {
            if (player.getUsername().equals(username))
                return player;
        }
        return null;
    }

    public static List<Player> getAllPlayers() {
        return allPlayers;
    }

    public static String getFirstPlayerName() {
        return firstPlayerName;
    }

    public static void setFirstPlayerName(String firstPlayerName) {
        Player.firstPlayerName = firstPlayerName;
    }

    public static String getSecondPlayerName() {
        return secondPlayerName;
    }

    public static void setSecondPlayerName(String secondPlayerName) {
        Player.secondPlayerName = secondPlayerName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public void setLoses(long loses) {
        this.loses = loses;
    }

    public long getScore() {
        return score;
    }

    public long getWins() {
        return wins;
    }

    public long getLoses() {
        return loses;
    }

}
