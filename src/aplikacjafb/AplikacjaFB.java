/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjafb;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Comment;
import com.restfb.types.Likes.LikeItem;
import com.restfb.types.Post;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Homik
 */
public class AplikacjaFB extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public static int limit_like;
    public static int limit_comments;
    public static int limit_posts;

    public static void Likes(String accessToken) {
        Baza baza = new Baza();
        baza.polaczZbazaDanych();
        baza.createTable(wyszukiwanie);
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        Connection<Post> result = fbClient.fetchConnection(wyszukiwanie + "/feed", Post.class);
        int licznik = 0;
        int licznik1 = 0;
        int licznik2 = 0;
        for (List<Post> page : result) {
            for (Post aPost : page) {
                if (licznik2 <= limit_posts) {
                    //System.out.println(aPost.getName()+" "+aPost.getId());
                    //lajki
                    if (aPost.getLikes() != null) {

                        for (LikeItem lajk : aPost.getLikes().getData()) {
                            if (licznik <= limit_like) {
                                //System.out.println(lajk.getId());
                                //System.out.println(lajk.getName() + " " + lajk.getId());//wyciąga kto zlajkował post
                                baza.insertDane(wyszukiwanie, lajk.getId(), lajk.getName(), 0);
                                licznik++;
                            }
                        }
                        //komentarze
                        if (aPost.getComments() != null) {
                            for (Comment kom : aPost.getComments().getData()) {
                                if (licznik1 <= limit_comments) {
                                    //System.out.println(kom.getFrom().getName() + " " + kom.getFrom().getId()); //wyciaga kto skomentował post
                                    baza.insertDane(wyszukiwanie, kom.getFrom().getId(), kom.getFrom().getName(), 0);
                                    licznik1++;
                                }
                            }
                        }
                    }
                    licznik2++;
                }
            }
        }
        System.out.println("suma danych " + licznik);

        baza.przenies(wyszukiwanie);
        baza.rozlaczZbazaDanych();

    }

    /**
     * @param args the command line arguments
     */
    public static String wyszukiwanie;
    public static String accessToken = "EAACEdEose0cBAOAT4I9090rnfZCHhQMQDZCIIduD9EmuZBL201ZBj7kaGteRZAHaZAg5Vr3s3yeEObSlokHnoZAV6bqWXeKjRpW57nphCGvZBPXctr2lKwErvqjbd8WeOle3UA0lWP9rtYR35Nn4TSAsY5GdJUxJMA9qQ42Lx9NQbgZDZD";

    public static void main(String[] args) {
        launch(args);

    }

}
