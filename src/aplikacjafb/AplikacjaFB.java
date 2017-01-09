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

    public static void Likes(String accessToken) {
        Baza baza = new Baza();
        baza.polaczZbazaDanych();
        baza.createTable(domena);
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        Connection<Post> result = fbClient.fetchConnection(domena + "/feed", Post.class);
        int licznik = 0;
        for (List<Post> page : result) {
            for (Post aPost : page) {
                if (licznik <= 20) {
                    System.out.println(aPost.getName() + " " + aPost.getId());
                    //lajki

                    if (aPost.getLikes() != null) {

                        for (LikeItem lajk : aPost.getLikes().getData()) {
                            //System.out.println(lajk.getId());
                            System.out.println(lajk.getName() + " " + lajk.getId());//wyciąga kto zlajkował post
                            baza.insertDane(domena, lajk.getId(), 0);
                            licznik++;
                        }
                    }
                    //komentarze
                    if (aPost.getComments() != null) {
                        for (Comment kom : aPost.getComments().getData()) {
                            System.out.println(kom.getFrom().getName() + " " + kom.getFrom().getId()); //wyciaga kto skomentował post
                            baza.insertDane(domena, kom.getFrom().getId(), 0);
                            licznik++;
                        }

                    }
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
    public static String domena = "BilardClubCafeuKrolevica";
public static String wyszukiwanie = "ja";
    public static String accessToken;

    public static void main(String[] args) {
        launch(args);
        

        Likes(accessToken);

    }

}
