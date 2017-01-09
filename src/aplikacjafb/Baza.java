/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjafb;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Comment;
import com.restfb.types.Likes;
import com.restfb.types.Post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Grzesiek
 */
public class Baza {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Homik\\Downloads\\Lokale";
    private static Connection connection = null;
    private static Statement statement = null;

    public static void polaczZbazaDanych() {
        try {
            Class.forName(DRIVER);
            System.out.println("1. Zarejestrowano sterownik do bazy danych SQLITE!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Niewłaściwy sterownik JDBC  lub jego brak");
        }
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            System.out.println("polaczono");
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem połączenie");
        }

    }

    public static void rozlaczZbazaDanych() {

        try {
            connection.close();
            statement.close();
            System.out.println("rozlaczono");
        } catch (SQLException e) {
            System.err.println("Problem z rozłaczeniem połączenie");
        }

    }

    public void insertDane(String tab, String name, int s) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + tab + " values (NULL, ?, ?);");
            //prepStmt.setString(1, id);
            prepStmt.setString(1, name);
            prepStmt.setInt(2, s);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu tabeli");
            e.printStackTrace();
        }

    }

    public void createTable(String nazwa) {
        String createCzytelnicy = "CREATE TABLE IF NOT EXISTS " + nazwa + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), suma int)";
        try {
            statement.execute(createCzytelnicy);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();

        }

    }
    public void przenies(String tablica) {
        try {
            statement.execute("create table " + tablica + "2" + " as SELECT null,id,name,count(id) FROM " + tablica + " group by id order by count(id) desc");
            ResultSet result2 = statement.executeQuery("SELECT * FROM " + tablica +"2");
            //for(int i=1;i<=sum;i++)
            while (result2.next()) {
                System.out.println(result2.getString(1) + " " + result2.getString(2) + " " + result2.getString(3) + " " + result2.getInt(4));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}

//    public class Lajki {
//    private int id;
//    private String tabela;
//    private String nazwisko;
//    private int suma;
// 
//    public int getId() {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//    public String getTab() {
//        return tabela;
//    }
//    public void setTab(String imie) {
//        this.tabela = imie;
//    }
//    public String getNazwisko() {
//        return nazwisko;
//    }
//    public void setNazwisko(String nazwisko) {
//        this.nazwisko = nazwisko;
//    }
//    public int getSuma() {
//        return suma;
//    }
//    
// 
//    public Lajki() { }
//    public Lajki(int id, String tab, String nazwisko, int s) {
//        this.id = id;
//        this.tabela = tab;
//        this.nazwisko = nazwisko;
//        this.suma = s;
//    }
// 
//    @Override
//    public String toString() {
//        return "["+id+"] - "+tabela+" "+nazwisko+" - "+suma;
//    }
//    }
