/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjafb;

import static aplikacjafb.AplikacjaFB.accessToken;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
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
    private TextField textfield1;
    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {

        String domain = "http://google.com/";
        String appId = "1028610047267967";
        // user_activities, user_groups, user_interests, manage_notifications, read_friendlists, read_mailbox, read_stream. 
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event";

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        while (true) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                AplikacjaFB.accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                driver.quit();

            }
        }

    }

    public void CloseButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.show();
                    int ile_danych=5;

         Graph frame = new Graph(ile_danych,AplikacjaFB.wyszukiwanie);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800, 620);
	frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
//private void closeButtonAction(){
//    // get a handle to the stage
//    Stage stage = (Stage) closeButton.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//}
    public void wyczyscPole(ActionEvent event){
        textfield1.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
