package data;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedBackService extends JDBCutil{

    public int addFeedBack(String typeFeed, String contentFeed, String detailFeed){
        try{
            String sql = "INSERT INTO FeedBack (TypeOfFeed, ContentOfFeed, ContentDetaildOfFeed) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, typeFeed);
            preState.setString(2, contentFeed);
            preState.setString(3, detailFeed);
            return preState.executeUpdate();

        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Prolem");
            alert.setContentText("Perhaps you are having a system related problem? Report by email: libraryctu@edu.vn");
            alert.showAndWait();
            ex.printStackTrace();
        }
        return -2;
    }


}
