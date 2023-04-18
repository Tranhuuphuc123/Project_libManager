package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.DiscussionRoom;
import model.RetristersBorrowed;

import java.sql.*;
import java.time.LocalDate;

public class DiscussionRoomService extends JDBCutil{

    public ObservableList<DiscussionRoom> showListOfDiscussionRoom(){
        ObservableList<DiscussionRoom> disRoomList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT DisName FROM DiscussionRoom WHERE DisStatus = 'Active'";
            Statement statement = connectJDBC().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                DiscussionRoom disRoom = new DiscussionRoom();
                disRoom.setDisName(resultSet.getString(1));
                disRoomList.add(disRoom);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  disRoomList;
    }

    public int handleRegisterBorrowedDisRoom(RetristersBorrowed retrister, LocalDate disDate){
        String disID = handleDisID(retrister.getDisID());
        System.out.println("Reader id: " + retrister.getReaderID());
        if(seachRoom(disID, retrister.getReaderID()) < -1){
            try{
                String sql = "INSERT INTO RetristersBorrowed (DisID, ReaderID, BorrowedByShift, DisDate," +
                        " Description, Activity) VALUES (?, ?, ?, ?, ?,'Processed');";
                PreparedStatement preState = connectJDBC().prepareStatement(sql);
                preState.setString(1, disID);
                preState.setString(2, retrister.getReaderID());
                preState.setString(3, retrister.getBorrowedByShift());
                preState.setDate(4, Date.valueOf(disDate));
                preState.setString(5, retrister.getDescription());

                return preState.executeUpdate();

            }catch (SQLException ex){

                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Warning");
            alert.setContentText("Room has been borrowed!");
            alert.showAndWait();
        }
        return -2;
    }

    protected String handleDisID(String name){
        String id = null;
        try{
            String sql = "SELECT DisID FROM DiscussionRoom WHERE DisName = ? ";
            PreparedStatement pre = connectJDBC().prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rel = pre.executeQuery();
            if(rel.next()){
                id = rel.getString(1);
                System.out.println("ID: " + id);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return id;
    }

    public int seachRoom(String disID, String readerID){
        try{
            System.out.println("Reader id: " + readerID);
            String sql = "SELECT DisID FROM RetristersBorrowed WHERE DisID = ? AND ReaderID = ?";
            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, disID);
            preState.setString(2, readerID);
            ResultSet rel = preState.executeQuery();
            if(rel.next()){
                return 1;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  -2;
    }

}
