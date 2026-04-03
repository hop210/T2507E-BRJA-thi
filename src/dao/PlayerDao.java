package dao;

import entity.Player;

import java.util.ArrayList;

public interface PlayerDao {
    boolean insertPlayer(Player p);
    boolean deletePlayer(int playerId);
    ArrayList<Player> displayAll();
    ArrayList<Player> displayAllByPlayerName(String name);
    ArrayList<Player> displayTop10();
}
