/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjafb;

import static aplikacjafb.AplikacjaFB.Likes;
import static aplikacjafb.AplikacjaFB.accessToken;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.WebRequestor;
import com.restfb.types.Group;
import com.restfb.types.User;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Homik
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField token1;
    
    public static String token;

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {

        String appId = "1028610047267967";
        String secretKey = "692f663aa398e40dffac358f41636462";
//        String redirectUrl = "https://www.google.pl/";
//        String code = "user_about_me,"
//                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
//                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_relationship_details,"
//                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
//                + "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event,user_posts,pages_show_list,pages_messaging,business_management,pages_messaging_payments,"
//                + "pages_messaging_phone_number,user_events,pages_messaging_subscriptions,user_managed_groups,pages_manage_instant_articles,read_audience_network_insights,"
//                + "publish_pages,pages_manage_cta";
//----------------------------------------------
//        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get(authUrl);
//        while (true) {
//
//            if (!driver.getCurrentUrl().contains("facebook.com")) {
//                String url = driver.getCurrentUrl();
//                AplikacjaFB.accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
//                System.out.println(accessToken);
//                driver.quit();
//
//            }
//        }
//-------------------------------------------------
//        WebRequestor wr = new DefaultWebRequestor();
//    WebRequestor.Response accessToken = wr.executeGet(
//            "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
//            + "&client_secret=" + secretKey + "&code=" + code);
        AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(appId, secretKey);
        token = accessToken.getAccessToken();
        System.out.println(token);
        token1.setText(token);

    }
    @FXML
    private TextField ile_danych;
    @FXML
    public void closeButton(ActionEvent event)  throws Exception{
        
            //token2.setText(AplikacjaFB.wyszukiwanie);
            Likes(accessToken);
           
            Graph frame = new Graph(Integer.parseInt(ile_danych.getText()), AplikacjaFB.wyszukiwanie);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1366, 768);
            frame.setVisible(true);

        
    }
    @FXML
    private TextField posty;
    @FXML
    private TextField komentarze;
    @FXML
    private TextField likes;
    @FXML
    private TextField lokal;
    @FXML
    public void wprowadzButton(ActionEvent event) throws Exception{
        
        AplikacjaFB.limit_posts=Integer.parseInt(posty.getText());
        AplikacjaFB.limit_comments=Integer.parseInt(komentarze.getText());
        AplikacjaFB.limit_like=Integer.parseInt(likes.getText());
        AplikacjaFB.wyszukiwanie=lokal.getText();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
