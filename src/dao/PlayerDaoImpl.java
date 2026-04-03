package dao;

import database.Database;
import entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDaoImpl implements PlayerDao{
    Database db = Database.getInstance();

    @Override
    public boolean insertPlayer(Player p) {
        try {
            String sql = "INSERT INTO Player (NationalId, PlayerName, HighScore, Level) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = db.getStatement().getConnection().prepareStatement(sql);
            ps.setInt(1, p.getNationalId());
            ps.setString(2, p.getPlayerName());
            ps.setInt(3, p.getHighScore());
            ps.setInt(4, p.getLevel());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deletePlayer(int playerId) {
        try {
            // Tạo câu lệnh SQL xóa theo PlayerId
            String sql = "DELETE FROM Player WHERE PlayerId = " + playerId;

            // Sử dụng Database instance và getStatement() từ file Database.java của bạn
            int rowsAffected = Database.getInstance().getStatement().executeUpdate(sql);

            // Nếu số dòng bị ảnh hưởng > 0 tức là đã xóa thành công
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện xóa người chơi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Player> displayAll() {
        ArrayList<Player> arr = new ArrayList<>();
        try {
            // Lấy Statement từ Database instance [cite: 1, 4]
            Statement st = Database.getInstance().getStatement();

            // Câu lệnh JOIN để lấy đầy đủ thông tin Player và tên Quốc gia [cite: 1, 13, 16]
            String sql = "SELECT p.*, n.NationalName FROM Player p " +
                    "JOIN National n ON p.NationalId = n.NationalId";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                // Khởi tạo Player theo đúng thứ tự Constructor bạn đã gen:
                // 1. highScore, 2. level, 3. nationalId, 4. nationalName, 5. playerId, 6. playerName
                arr.add(new Player(
                        rs.getInt("HighScore"),      // 1. highScore
                        rs.getInt("Level"),          // 2. level
                        rs.getInt("NationalId"),     // 3. nationalId
                        rs.getString("NationalName"),// 4. nationalName [cite: 16]
                        rs.getInt("PlayerId"),       // 5. playerId
                        rs.getString("PlayerName")   // 6. playerName
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi hiển thị danh sách: " + e.getMessage());
        }
        return arr;
    }

    @Override
    public ArrayList<Player> displayAllByPlayerName(String name) {
        ArrayList<Player> arr = new ArrayList<>();
        try {
            String sql = "SELECT p.*, n.NationalName FROM Player p " +
                    "JOIN National n ON p.NationalId = n.NationalId " +
                    "WHERE p.PlayerName LIKE ?";

            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(new Player(
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getInt("NationalId"),
                        rs.getString("NationalName"),
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    @Override
    public ArrayList<Player> displayTop10() {
        ArrayList<Player> arr = new ArrayList<>();
        try {
            // Lấy Statement từ Database
            Statement st = Database.getInstance().getStatement();

            // SQL lấy Top 10 người có điểm cao nhất [cite: 23]
            String sql = "SELECT p.*, n.NationalName FROM Player p " +
                    "JOIN National n ON p.NationalId = n.NationalId " +
                    "ORDER BY p.HighScore DESC LIMIT 10";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                // Khởi tạo đúng thứ tự tham số trong file Player.java của bạn
                arr.add(new Player(
                        rs.getInt("HighScore"),      // 1. highScore
                        rs.getInt("Level"),          // 2. level
                        rs.getInt("NationalId"),     // 3. nationalId
                        rs.getString("NationalName"),// 4. nationalName
                        rs.getInt("PlayerId"),       // 5. playerId
                        rs.getString("PlayerName")   // 6. playerName
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi Top 10: " + e.getMessage());
        }
        return arr;
    }
}
