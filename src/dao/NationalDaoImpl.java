package dao;

import database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class NationalDaoImpl implements NationalDao{
    Database db = Database.getInstance();

    @Override
    public boolean insertNational(String name) {
        try {
            String sql = "INSERT INTO National (NationalName) VALUES (?)";
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNational(int id) {
        try {
            String sql = "DELETE FROM National WHERE NationalId = ?";
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
