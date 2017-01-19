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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools .|. Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Grzesiek
 */
public class Baza {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\2marc\\Desktop\\Bivlioteki\\Lokale";
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

    public void insertDane(String tab, String id, String name, int s) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + tab + " values (NULL, ?, ?, ?);");
            //prepStmt.setString(1, id);
            prepStmt.setString(1, id);
            prepStmt.setString(2, name);
            prepStmt.setInt(3, s);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu tabeli");
            e.printStackTrace();
        }

    }

    public void createTable(String nazwa) {
        String createTablica = "CREATE TABLE if not exists " + nazwa + " ( lp INTEGER PRIMARY KEY AUTOINCREMENT, id varchar(255), name varchar(255), suma int)";
        try {
            statement.execute(createTablica);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();

        }

    }

    public void przenies(String tablica) {
        try {
            statement.execute("create table if not exists " + tablica + "2" + " as SELECT null,id,name,count(id) FROM " + tablica + " group by id order by count(id) desc");
            ResultSet result2 = statement.executeQuery("SELECT * FROM " + tablica + "2");
            statement.executeUpdate("drop table " + tablica);

            while (result2.next()) {
                System.out.println(result2.getString(1) + " " + result2.getString(2) + " " + result2.getString(3) + " " + result2.getInt(4));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public List<String> selectDane(String tablica, int ile) {
        List<String> dane = new ArrayList<>();
        //List<String> dane2 = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM " + tablica + "2");
            int suma;
            String nazwisko, adres;
            int licznik = 0;

            while (result.next() && licznik < ile) {
                //lp=result.getString(1);
                adres = result.getString(2);
                nazwisko = result.getString(3);
                suma = result.getInt(4);
                dane.add(nazwisko + " " + Integer.toString(suma) + "\nhttps://fb.com/" + adres);
                //dane2.add("fb.com/"+adres);
                // nazwisko+" "+Integer.toString(suma)+" "+

            }
            statement.executeUpdate("drop table " + tablica + "2");
            //System.out.println("suma= "+ suma );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return dane;
    }
}
