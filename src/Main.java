
import dao.PlayerDao;
import dao.PlayerDaoImpl;
import entity.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerDao dao = new PlayerDaoImpl();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Player");
            System.out.println("2. Delete Player");
            System.out.println("3. Display All");
            System.out.println("4. Find by Name");
            System.out.println("5. Top 10 Players");
            System.out.println("6. Add National");
            System.out.println("7. Delete National");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter NationalId: ");
                    int nid = sc.nextInt();

                    System.out.print("Enter HighScore: ");
                    int score = sc.nextInt();

                    System.out.print("Enter Level: ");
                    int level = sc.nextInt();

                    Player p = new Player();
                    p.setPlayerName(name);
                    p.setNationalId(nid);
                    p.setHighScore(score);
                    p.setLevel(level);

                    if (dao.insertPlayer(p)) {
                        System.out.println("Add success!");
                    } else {
                        System.out.println("Add failed!");
                    }
                    break;

                case 2:
                    System.out.print("Enter PlayerId to delete: ");
                    int id = sc.nextInt();

                    if (dao.deletePlayer(id)) {
                        System.out.println("Delete success!");
                    } else {
                        System.out.println("Delete failed!");
                    }
                    break;

                case 3:
                    ArrayList<Player> list = dao.displayAll();
                    for (Player pl : list) {
                        System.out.println(pl.getPlayerId() + " | " +
                                pl.getPlayerName() + " | " +
                                pl.getHighScore() + " | " +
                                pl.getLevel() + " | " +
                                pl.getNationalName());
                    }
                    break;

                case 4:
                    System.out.print("Enter name to search: ");
                    String keyword = sc.nextLine();

                    ArrayList<Player> result = dao.displayAllByPlayerName(keyword);
                    for (Player pl : result) {
                        System.out.println(pl.getPlayerId() + " | " +
                                pl.getPlayerName() + " | " +
                                pl.getHighScore() + " | " +
                                pl.getLevel() + " | " +
                                pl.getNationalName());
                    }
                    break;

                case 5:
                    ArrayList<Player> top = dao.displayTop10();
                    for (Player pl : top) {
                        System.out.println(pl.getPlayerId() + " | " +
                                pl.getPlayerName() + " | " +
                                pl.getHighScore() + " | " +
                                pl.getLevel() + " | " +
                                pl.getNationalName());
                    }
                    break;

                case 0:
                    System.out.println("Exit...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}