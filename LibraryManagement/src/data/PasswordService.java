package data;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordService extends JDBCutil{

    public int changePassword(String oldPass, String password, String readerID){
        try{
            String sql = "UPDATE Reader SET ReaderPass = SHA2( ? ,256) " +
                         " WHERE ReaderID = ? AND ReaderPass =  SHA2( ? , 256); ";

            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, password);
            preState.setString(2, readerID);
            preState.setString(3,oldPass);
            return preState.executeUpdate();

        }catch (SQLException ex){
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
