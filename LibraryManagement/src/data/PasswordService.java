package data;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordService extends JDBCutil{

    public int changePassword(String oldPass, String password, String readerID){
        int find = findPassword(password);
        System.out.println("pass: " + find);
        if(find == -2 ){
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
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Prolem");
            alert.setContentText("This password is already in use by another account.\nPlease choose a different password.");
            alert.showAndWait();
        }

        return -2;
    }

    public int findPassword(String pass){
        try{
            String sql = "SELECT ReaderPass FROM Reader WHERE ReaderPass = SHA2(?,256); ";

            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, pass);
            ResultSet rel = preState.executeQuery();
            if(rel.next()){
                return 1;
            }

        }catch (SQLException ex){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Problem");
            alert.setContentText("Perhaps you are having a system related problem? Report by email: libraryctu@edu.vn");
            alert.showAndWait();
            ex.printStackTrace();

        }
        return -2;
    }
}
