package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {
    private static final String connectionString = "jdbc:mysql://localhost:3306/HeroGame";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    private static Database instance;

    private Connection conn;
    private Database(){
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(connectionString,user,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    public Statement getStatement() throws Exception{
        return conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws Exception{
        return conn.prepareStatement(sql);
    }


}
