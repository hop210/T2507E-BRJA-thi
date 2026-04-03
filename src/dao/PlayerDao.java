package dao;

import entity.Player;

import java.util.ArrayList;

public interface PlayerDao {
    // Yêu cầu 1: Thêm và xóa
    boolean insertPlayer(Player p);
    boolean deletePlayer(int playerId);

    // Yêu cầu 2: Hiển thị tất cả
    ArrayList<Player> displayAll();

    // Yêu cầu 3: Tìm kiếm theo tên
    ArrayList<Player> displayAllByPlayerName(String name);

    // Yêu cầu 4: Thống kê Top 10
    ArrayList<Player> displayTop10();
}
